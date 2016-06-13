package com.tonygalati.sprites;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate Sprite CSS file
 */
public class SpriteCSSGenerator {

    File cssFile = null;

    List<SpriteCSS> spriteCSSes = new ArrayList<SpriteCSS>();

    /**
     * Each icons height
     */
    private static final int ICON_HEIGHT = 60;

    /**
     * Each icons width
     */
    private static final int ICON_WIDTH = 60;


    /**
     * Create and Add SpriteCSS to the collection
     *
     * @param className
     * @param xCord
     * @param yCord
     */
    public void addSpriteCSS(String className, int xCord, int yCord){
        spriteCSSes.add(new SpriteCSS(className,xCord,yCord));
    }

    /**
     * Get the css sprite file
     * @return a file containing all css defintions needed for the sprites
     */
    public File getFile(String spriteFile){

        cssFile = new File(String.format("icon-%s.css", RandomStringUtils.randomAlphanumeric(10)));

        try {
            if(cssFile.createNewFile()) {
                FileWriter writer = new FileWriter(cssFile, true);
                String spriteCSSDef = ".app-icon{" + System.lineSeparator() +
                        "\tbackground: url(\"" + spriteFile + "\") no-repeat top left;" + System.lineSeparator() +
                        "\twidth: " + ICON_WIDTH + "px;" + System.lineSeparator() +
                        "\theight: " + ICON_HEIGHT + "px;"+ System.lineSeparator() +
                        "\tmargin: auto;" + System.lineSeparator() +
                        "}" + System.lineSeparator();

                writer.append(spriteCSSDef);

                for (SpriteCSS spriteCSS : spriteCSSes) {
                    writer.append(spriteCSS.getCss()).append(System.lineSeparator());
                }
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cssFile;
    }
}
