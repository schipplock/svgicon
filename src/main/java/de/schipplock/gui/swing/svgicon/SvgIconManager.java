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

import java.awt.*;
import java.net.URL;
import java.util.List;
import static java.lang.String.format;

public class SvgIconManager {

    private SvgIconManager() {}

    public static SvgIcon getBuiltinIcon(String iconName, Dimension dimension, String htmlColor) {
        URL iconUrl = SvgIconManager.class.getClassLoader().getResource(format("icons/%s.svg", iconName));
        return new SvgIcon(iconUrl, dimension.width, dimension.height, htmlColor);
    }

    public static SvgIcon getIcon(String iconName, Dimension dimension) {
        URL iconUrl = SvgIconManager.class.getClassLoader().getResource(iconName);
        return new SvgIcon(iconUrl, dimension.width, dimension.height);
    }

    public static List<Image> getBuiltinWindowIconImages(String iconName, String htmlColor) {
        return List.of(
            SvgIconManager.getBuiltinIcon(iconName, new Dimension(16, 16), htmlColor).getImage(),
            SvgIconManager.getBuiltinIcon(iconName, new Dimension(32, 32), htmlColor).getImage(),
            SvgIconManager.getBuiltinIcon(iconName, new Dimension(48, 48), htmlColor).getImage(),
            SvgIconManager.getBuiltinIcon(iconName, new Dimension(256, 256), htmlColor).getImage()
        );
    }

    public static List<Image> getWindowIconImages(String iconName) {
        return List.of(
                SvgIconManager.getIcon(iconName, new Dimension(16, 16)).getImage(),
                SvgIconManager.getIcon(iconName, new Dimension(32, 32)).getImage(),
                SvgIconManager.getIcon(iconName, new Dimension(48, 48)).getImage(),
                SvgIconManager.getIcon(iconName, new Dimension(256, 256)).getImage()
        );
    }
}
