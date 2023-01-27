package com.example.softwaredesignproject4;

import javafx.scene.canvas.GraphicsContext;

import java.util.Optional;

class MyPoint {
    int x, y;
    myColor color;

    MyPoint() { setPoint(0,0); this.color = myColor.AQUAMARINE; }
    MyPoint(int x, int y, myColor color) { setPoint(x,y); this.color = Optional.ofNullable(color).orElse(myColor.AQUAMARINE); }
    MyPoint(MyPoint p, myColor color) { setPoint(p); this.color = Optional.ofNullable(color).orElse(myColor.AQUAMARINE); }

    public void setPoint(int x, int y) { this.x = x; this.y = y; }
    public void setPoint(MyPoint p) { this.x = p.getX(); this.y = p.getY(); }

    public void setColor(myColor c) { this.color = c;}

    public int getX() { return x; }
    public int getY() { return y; }
    public myColor getColor() { return color;}

    public void translate(int dx, int dy) { setPoint( this.x + dx, this.y +dy); }

    public double distanceFromOrigin() { return Math.sqrt(x * x + y * y); }

    public double angleX(MyPoint p) {
        double dx = (double) (p.getX() - x);
        double dy = (double) (p.getY() - y);

        return Math.toDegrees(Math.atan(dy / dx));
    }

    public double distance(MyPoint p) {
        int dx = x - p.getX();
        int dy = this.y - p.getY();

        return Math.sqrt( dx * dx + dy * dy);
    }

    public void draw(GraphicsContext GC) {
        GC.setFill(color.getJavaFxColor());
        GC.fillRect(x, y, 1, 1);

    }

    @Override
    public String toString() { return "Point(" + x + ", " + y +")"; }


}


