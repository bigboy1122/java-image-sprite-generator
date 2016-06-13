package com.tonygalati.sprites;// com.tonygalati.sprites.SpriteGenerator - a simple java program for creating vertical sprites from a folder of PNG images.
//
// The software is released free of any copyright of license restrictions.
// Modified by Mark Young (http://www.zarzax.com) April 2011
// originally by Peter Moberg (http://sourcecodebean.com/archives/a-simple-image-sprite-generator-in-java/1065) April 2011

import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SpriteGenerator {

    public static final String NAIC_SMALL_ICON = "naic_small_icon";

    public static void main(String[] args) throws IOException {

//        if (args.length != 3)
//        {
//           System.out.print("Usage: com.tonygalati.sprites.SpriteGenerator {path to images} {margin between images in px} {output file}\n");
//           System.out.print("Note: The max height should only be around 32,767px due to Microsoft GDI using a 16bit signed integer to store dimensions\n");
//           System.out.print("going beyond this dimension is possible with this tool but the generated sprite image will not work correctly with\n");
//           System.out.print("most browsers.\n\n");
//           return;
//        }

//        Integer margin = Integer.parseInt(args[1]);
        Integer margin = Integer.parseInt("1");
        String spriteFile = "icons-" + RandomStringUtils.randomAlphanumeric(10) + ".png";
        SpriteCSSGenerator cssGenerator = new SpriteCSSGenerator();

        ClassLoader classLoader = SpriteGenerator.class.getClassLoader();
        URL folderPath = classLoader.getResource(NAIC_SMALL_ICON);
        if(folderPath != null){
            File imageFolder = new File(folderPath.getPath());

            // Read images
            ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
            Integer yCoordinate = null;

            for (File f : imageFolder.listFiles())
            {
                if (f.isFile())
                {
                    String fileName = f.getName();
                    String ext = fileName.substring(fileName.lastIndexOf(".")+1,
                            fileName.length());

                    if (ext.equals("png"))
                    {
                        System.out.println("adding file " + fileName);
                        BufferedImage image = ImageIO.read(f);
                        imageList.add(image);

                        if (yCoordinate == null) {
                            yCoordinate = 0;
                        }else{
                            yCoordinate += (image.getHeight()+margin);
                        }

                        // add to cssGenerator
                        cssGenerator.addSpriteCSS(fileName.substring(0,fileName.indexOf(".")),0,yCoordinate);
                    }
                }
            }

            // Find max width and total height
            int maxWidth = 0;
            int totalHeight = 0;

            for (BufferedImage image : imageList)
            {
                totalHeight += image.getHeight() + margin;

                if (image.getWidth() > maxWidth)
                    maxWidth = image.getWidth();
            }

            System.out.format("Number of images: %s, total height: %spx, width: %spx%n",
                    imageList.size(), totalHeight, maxWidth);


            // Create the actual sprite
            BufferedImage sprite = new BufferedImage(maxWidth, totalHeight,
                    BufferedImage.TYPE_INT_ARGB);

            int currentY = 0;
            Graphics g = sprite.getGraphics();
            for (BufferedImage image : imageList)
            {
                g.drawImage(image, 0, currentY, null);
                currentY += image.getHeight() + margin;
            }

            System.out.format("Writing sprite: %s%n", spriteFile);
            ImageIO.write(sprite, "png", new File(spriteFile));
            File cssFile = cssGenerator.getFile(spriteFile);
            System.out.println(cssFile.getAbsolutePath() + " created");
        }else{
            System.err.println("Could not find folder: " + NAIC_SMALL_ICON);

        }

    }
}
