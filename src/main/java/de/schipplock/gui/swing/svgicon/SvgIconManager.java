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
import java.util.List;
import static java.lang.String.format;

public class SvgIconManager {

    private SvgIconManager() {}

    public static SvgIcon getIcon(String iconName, Dimension dimension, String htmlColor) {
        return new SvgIcon(format("icons/%s.svg", iconName), dimension.width, dimension.height, htmlColor);
    }

    public static List<Image> getWindowIcons(String iconName, String htmlColor) {
        return List.of(
            SvgIconManager.getIcon(iconName, new Dimension(16, 16), htmlColor).getImage(),
            SvgIconManager.getIcon(iconName, new Dimension(32, 32), htmlColor).getImage(),
            SvgIconManager.getIcon(iconName, new Dimension(48, 48), htmlColor).getImage(),
            SvgIconManager.getIcon(iconName, new Dimension(256, 256), htmlColor).getImage()
        );
    }
}
