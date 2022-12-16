#!/bin/bash
icons=($(ls -1 src/main/resources/icons))
filename="src/main/java/de/schipplock/gui/swing/svgicons/SvgIcons.java"

cat LICENSE_TEMPLATE > ${filename}
echo "" >> ${filename}
echo "package de.schipplock.gui.swing.svgicons;" >> ${filename}
echo "" >> ${filename}
echo "public class SvgIcons {" >> ${filename}
echo "" >> ${filename}

for icon in "${icons[@]}"; do
  const_name=${icon^^}
  const_name=${const_name//.SVG/}
  const_name=${const_name//-/_}
  echo "    public static final String SVGICON_${const_name} = \"${icon//.svg/}\";" >> ${filename}
  echo "" >> ${filename}
done

echo "}" >> ${filename}