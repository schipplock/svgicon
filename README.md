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
<dependencies>
    <dependency>
        <groupId>de.schipplock.gui.swing</groupId>
        <artifactId>svgicon</artifactId>
        <version>0.0.3</version>
    </dependency>
</dependencies>
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
var icon = SvgIconManager.getIcon(SvgIcons.SVGICON_DATABASE, new Dimension(16, 16), "#419ee0");

// wenn man ein Image braucht
var image = SvgIconManager.getIcon(SvgIcons.SVGICON_DATABASE, new Dimension(16, 16), "#419ee0").getImage();
```

**Fenster-Icons laden:**

Angenommen wird, dass die Klasse `JFrame` erweitert.

```java
setIconImages(SvgIconManager.getWindowIcons(SvgIcons.SVGICON_DATABASE, "#d15000"));
```

Die Methode `getWindowIcons` stellt das gewünschte SVG-Icon in typischen Größen zur Verfügung
(16x16, 32x32, 48x48, 256x256).

## License
[Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)