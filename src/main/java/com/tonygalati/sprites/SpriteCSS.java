package com.tonygalati.sprites;

/**
 * Created by bigboy1122 on 6/10/16.
 */
public class SpriteCSS {
    String className;
    Integer xCoordinate;
    Integer yCoordinate;

    /**
     *
     * @param className
     * @param xCoordinate
     * @param yCoordinate
     */
    public SpriteCSS(String className, Integer xCoordinate, Integer yCoordinate) {
        this.className = className;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getCss(){
        return String.format(".%s{" + System.lineSeparator() +
                "\tbackground-position: %dpx -%dpx;" + System.lineSeparator() +
                "}",getClassName(),getxCoordinate(),getyCoordinate());
    }
}
