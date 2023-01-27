package com.example.softwaredesignproject4;

import javafx.scene.canvas.GraphicsContext;

import java.util.Optional;

abstract class MyShape {
    MyPoint p;
    myColor color;

    MyShape(MyPoint p, myColor color) { setPoint(p); this.color = Optional.ofNullable(color).orElse(myColor.WHITE); }
    MyShape(int x, int y, myColor color) { setPoint(x, y); this.color = Optional.ofNullable(color).orElse(myColor.WHITE); }

    public void setPoint(MyPoint p) { this.p = p; }
    public void setPoint(int x, int y) { p.setPoint(x, y); }

    public MyPoint getPoint() { return p;}
    public int getX() { return p.getX(); }
    public int getY() { return p.getY(); }
    public myColor getColor() { return color;}

    //abstract methods
    public abstract int area();
    public abstract int parameter();

    public abstract void stroke(GraphicsContext GC);
    public abstract void draw(GraphicsContext GC);

    @Override
    public String toString() { return "this is MyShape Object. "; }
}

