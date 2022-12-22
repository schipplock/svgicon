/*
 * Copyright 2022 Andreas Schipplock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.schipplock.gui.swing.svgicon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import de.schipplock.gui.swing.svgicon.exceptions.TranscodingException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

public class SvgIcon implements Icon {

    private final BufferedImage image;

    private final BufferedImage disabledImage;

    public SvgIcon(URL url, int width, int height, String fillColor) {
        var transcoder = new ImageTranscoder() {
            private BufferedImage img;

            @Override
            public BufferedImage createImage(int width, int height) {
                return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            }

            @Override
            public void writeImage(BufferedImage img, TranscoderOutput output) {
                this.img = img;
            }

            public BufferedImage getImage() {
                return img;
            }

            public void setDimension(Dimension dimension) {
                hints.put(KEY_WIDTH, (float) dimension.getWidth());
                hints.put(KEY_HEIGHT, (float) dimension.getHeight());
            }
        };

        transcoder.setDimension(new Dimension(width, height));

        try (InputStream is = Objects.requireNonNull(url).openStream())  {
            String svgXmlContent = new String(is.readAllBytes());
            // like a pro :P
            svgXmlContent = svgXmlContent.replace("fill=\"currentColor\"", "fill=\"" + fillColor +"\"");
            InputStream modifiedSvgIs = new ByteArrayInputStream(svgXmlContent.getBytes());
            transcoder.transcode(new TranscoderInput(modifiedSvgIs), null);
            image = transcoder.getImage();
            disabledImage = toBufferedImage(GrayFilter.createDisabledImage(image));
        } catch (TranscoderException | IOException e) {
            throw new TranscodingException(e);
        }
    }

    public SvgIcon(URL url, int width, int height) {
        this(url, width, height, "#000000");
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (c != null && !c.isEnabled()) {
            g.drawImage(disabledImage, x, y, null);
            return;
        }
        g.drawImage(image, x, y, null);
    }

    @Override
    public int getIconWidth() {
        return image.getWidth();
    }

    @Override
    public int getIconHeight() {
        return image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    private BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        return bimage;
    }
}
