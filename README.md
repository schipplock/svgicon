# SvgIcon

SvgIcon ist eine Java Bibliothek, mit dessen Hilfe man u.a. im Swing-Kontext SVG-Icons nutzen kann.
Ich nahm ja eigentlich an, dass Swing das ootb kann, aber das war leider nicht der Fall.
Ist ja auch schon etwas älter :). Und deswegen gibt es diese kleine Bibliothek.
Diese Bibliothek wurde mit Java 17 getestet.

## Screenshots

![](screenshots/banner.png)

## Installation

**Das muss in die pom.xml:**

```xml
<dependency>
    <groupId>de.schipplock.gui.swing</groupId>
    <artifactId>svgicon</artifactId>
    <version>0.0.4</version>
</dependency>
```

## Wie verwende ich diese Bibliothek?

**Es werden folgende Imports benötigt:**

```java
import de.schipplock.gui.swing.svgicon.SvgIconManager;
import de.schipplock.gui.swing.svgicon.SvgIcons;
```

**Ein SVG-Icon laden:**

```java
// wenn man ein Icon braucht
var icon = SvgIconManager.getBuiltinIcon(SvgIcons.SVGICON_DATABASE, new Dimension(16, 16), "#419ee0");

// wenn man ein Image braucht
var image = SvgIconManager.getBuiltinIcon(SvgIcons.SVGICON_DATABASE, new Dimension(16, 16), "#419ee0").getImage();

// wenn man kein mitgeliefertes Icon nutzen möchte
var icon = SvgIconManager.getIcon("images/icon.svg");
var image = SvgIconManager.getIcon("images/icon.svg").getImage();
```

**Fenster-Icons laden:**

Angenommen wird, dass die Klasse `JFrame` erweitert.

```java
setIconImages(SvgIconManager.getBuiltinWindowIconImages(SvgIcons.SVGICON_DATABASE, "#d15000"));
setIconImages(SvgIconManager.getWindowIconImages("images/logo.svg"));
```

Die Methode `getWindowIconImages` oder `getBuiltinWindowIconImages` stellt das gewünschte SVG-Icon in typischen Größen zur Verfügung
(16x16, 32x32, 48x48, 256x256).

## License
[Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)