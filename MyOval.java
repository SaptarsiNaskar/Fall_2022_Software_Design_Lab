package com.example.softwaredesignproject3;

import javafx.scene.canvas.GraphicsContext;

import java.util.Optional;

class MyOval extends MyShape {

    MyPoint center;
    int width, height;
    myColor color;

    MyOval(MyPoint p, int w, int h, myColor color) {
        super(new MyPoint(), null);

        this.center = p;
        this.width = w;
        this.height = h;
        this.color = Optional.ofNullable(color).orElse(myColor.AQUAMARINE);
    }

    public MyPoint getCenter() {
        return center;
    }

    public myColor getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int area() {
        return (int) (Math.PI * height * width);
    }

    @Override
    public int parameter() {
        return (int) (Math.PI * (height + width));
    }

    public void stroke(GraphicsContext GC) {
        GC.setStroke(color.getJavaFxColor());
        GC.strokeOval(center.getX() - width, center.getY() - height, 2 * width, 2 * height);
    }

    public void draw(GraphicsContext GC) {
        GC.setFill(color.getJavaFxColor());
        GC.fillOval(center.getX() - width, center.getY() - height, 2 * width, 2 * height);
    }

    @Override
    public String toString() {
        return "Oval Center: (" + center.getX() + "," + center.getY() + ") " + "\n" +
                "Width: " + width + "\n" +
                "Height: " + height + "\n" +
                "Area: " + area() + "\n" +
                "Parameter; " + parameter() + "\n";
    }

}

