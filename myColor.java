package com.example.softwaredesignproject4;


import javafx.scene.paint.Color;
import java.util.Random;



public enum myColor {
    STEELBLUE(70, 130, 180, 255),
    BLACK(0, 0, 0, 255),
    AQUAMARINE(127, 255, 212, 255),
    RED(255, 0, 0, 255),
    LIME(0, 255, 0, 255),
    MAGENTA(255, 0, 255, 255),
    PURPLE(128, 0, 128, 255),
    NAVY(0, 0, 128, 255),
    LINEN(250, 240, 230, 255),
    WHITE(255, 255, 255,255);


    private int r;
    private int g;
    private int b;
    private int a;
    private int argb;

    myColor(int r, int g, int b, int a) {
        setR(r);
        setG(g);
        setB(b);
        setA(a);
        setARGB(r, g, b, a);
    }

    public void setR(int r) { if (r >= 0 && r <= 255) this.r = r;}
    public void setG(int g) { if (r >= 0 && r <= 255) this.g = g;}
    public void setB(int b) { if (r >= 0 && r <= 255) this.b = b;}
    public void setA(int a) { if (r >= 0 && r <= 255) this.a = a;}
    public void setARGB(int r, int g, int b, int a) {
        this.argb = (a << 24) & 0xFF000000 |
                (r << 16) & 0x00FF0000 |
                (g << 8) & 0x0000FF00 |
                b;
    }

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public int getA() { return a; }
    public int getARGB() { return argb; }
    public String getHexColor() {
        return "#" + Integer.toHexString(argb).toUpperCase();

    }

    public Color getJavaFxColor() {
        return Color.rgb(r, g, b, (double) a / 255.0);
    }
    public Color getUniqueJavaFxColor() { return Color.rgb(255,127,80);}
    public Color getJavaFXOpaqueColor() {
        return Color.rgb(r, g, b);
    }

    public static myColor [] getMyColors() {
        return myColor.values();
    }

    public Color randomColor() {
        Random rand = new Random();
        return Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), (double) a / 255.0);
    }

    public Color invertColor() {
        return Color.rgb(255 - r, 255 - g, 255 - b, (double) a / 255.0);
    }

    public Color blendColors(myColor otherColor, double scale) {
        int rBlended, gBlended, bBlended;
        double aBlended;
        if(scale == 0.0) { return otherColor.getJavaFxColor();}
        if(scale == 1.0) { return this.getJavaFxColor();}
        if (scale < 0 || scale > 1) { scale = 0.5;}

        rBlended = (int) (this.r * scale + otherColor.getR() * (1.0 - scale));
        gBlended = (int) (this.g * scale + otherColor.getG() * (1.0 - scale));
        bBlended = (int) (this.b * scale + otherColor.getB() * (1.0 - scale));
        aBlended = (this.a * scale + otherColor.getA() * (1.0 - scale)) / 255.0;

        return Color.rgb(r, g, b, a);
    }

    public void print() {
        System.out.println(this + "(" + this.r + ", " + this.g + ", " + this.b + ", " + this.a + ") " +
                "HexadecimalCode: " + this.getHexColor());
    }


}

