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

import de.schipplock.gui.swing.lafmanager.LAFManager;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class SvgIconDemo extends JFrame {

    public SvgIconDemo() {
        setupFrame();
    }

    private void centerFrame() {
        GraphicsDevice screen = MouseInfo.getPointerInfo().getDevice();
        Rectangle r = screen.getDefaultConfiguration().getBounds();
        int x = (r.width - this.getWidth()) / 2 + r.x;
        int y = (r.height - this.getHeight()) / 2 + r.y;
        setLocation(x, y);
    }

    private void setupFrame() {
        setIconImages(SvgIconManager.getWindowIcons(SvgIcons.SVGICON_DATABASE, "#d15000"));
        setPreferredSize(new Dimension(330, 420));
        setMinimumSize(new Dimension(330, 420));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centerFrame();

        LAFManager.create().setLookAndFeelByName("FlatLaf IntelliJ");
        setTitle("Icon Demo");

        JPanel mainPanel = new JPanel(new MigLayout(""));

        JPanel actionPanel = new JPanel(new MigLayout(""));
        actionPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        var okButton = new JButton("ok");
        var cancelButton = new JButton("cancel");
        var disabledButton = new JButton("disabled");
        disabledButton.setEnabled(false);

        JPanel anotherPanel = new JPanel(new MigLayout("")) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(SvgIconManager.getIcon(SvgIcons.SVGICON_DATABASE, new Dimension(300, 300), "#419ee0").getImage(), 0, 0, this);
            }
        };

        cancelButton.addActionListener(e -> {
            disabledButton.setEnabled(true);
            cancelButton.setEnabled(false);
        });

        disabledButton.addActionListener(e -> {
            disabledButton.setEnabled(false);
            cancelButton.setEnabled(true);
        });

        okButton.setIcon(new SvgIcon("icons/check2-circle.svg", 16, 16, "#2a7d2a"));
        cancelButton.setIcon(new SvgIcon("icons/door-open.svg", 16, 16, "#d10000"));
        disabledButton.setIcon(new SvgIcon("icons/door-open.svg", 16, 16, "#d10000"));

        actionPanel.add(okButton, "right, pushx");
        actionPanel.add(cancelButton, "right, pushx");
        actionPanel.add(disabledButton, "right, pushx");
        mainPanel.add(actionPanel, "pushx, growx, wrap");
        mainPanel.add(anotherPanel, "pushx, growx, pushy, growy");
        getContentPane().add(mainPanel);
        pack();
    }

    public static void createAndShowGui() {
        JFrame frame = new SvgIconDemo();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SvgIconDemo::createAndShowGui);
    }
}
