package com.example.softwaredesignproject4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import java.util.Optional;

public class Slice {
    MyPoint center;
    int radius;
    double startAngle;
    double angle;
    double rStartAngle, rAngle, rEndAngle;
    myColor color;

    Slice(MyPoint p, int r, double startAngle, double angle, myColor color) {
        this.center = p;
        this.radius = r;
        this.startAngle = startAngle;
        this.angle = angle;
        this.rStartAngle = Math.toRadians(startAngle);
        this.rAngle = Math.toRadians(angle);
        this.rEndAngle = Math.toRadians(startAngle + angle);
        this.color = Optional.ofNullable(color).orElse(myColor.AQUAMARINE);
    }

    public MyPoint getCenter() { return center; }
    public int getRadius() { return radius; }
    public double getStartAngle() { return startAngle; }
    public double getAngle() { return angle; }
    public double getArcLength() { return (double) radius * rAngle; }
    public myColor getColor() { return color; }

    Slice(Slice s) {
        this.center = s.getCenter();
        this.radius = s.getRadius();
        this.startAngle = s.getStartAngle();
        this.angle = s.getAngle();

        this.rStartAngle = Math.toRadians(this.startAngle);
        this.rAngle = Math.toRadians(this.angle);
        this.rEndAngle = Math.toRadians(this.startAngle + this.angle);
    }

    public double area() { return 0.5 * rAngle * Math.pow(radius, 2); }
    public double parameter() { return getArcLength(); }

    public String toString() {
        return "Slice: Center(" + center.getX() + ", " + center.getY() + ") Radius " + radius +
                " (StartingAngle, Angle): (" + startAngle + ", " + angle + "), " + color.toString();
    }

    public void draw(GraphicsContext GC) {
        GC.setFill(color.getJavaFxColor());
        GC.fillArc(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius,
                startAngle, angle, ArcType.ROUND);
    }
    public void stroke(GraphicsContext GC) {
        GC.setStroke(myColor.BLACK.getJavaFxColor());
        GC.setLineWidth(0.5);
        GC.strokeArc(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius, startAngle, angle, ArcType.ROUND);
    }
    public void uniqueDraw(GraphicsContext GC) {
        GC.setFill(color.getUniqueJavaFxColor());
        GC.fillArc(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius,
                startAngle, angle, ArcType.ROUND);
    }
    public void uniqueStroke(GraphicsContext GC) {
        GC.setStroke(color.getUniqueJavaFxColor());
        GC.setLineWidth(0.5);
        GC.strokeArc(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius, startAngle, angle, ArcType.ROUND);
    }
    //for slices of N
    public void drawRect(GraphicsContext GC, int a) {
        GC.setFill(color.getJavaFxColor());
        GC.fillRect(5, 5 + 20 * a, 10, 10);
    }
    public void strokeRect(GraphicsContext GC, int a) {
        GC.setStroke(myColor.BLACK.getJavaFxColor());
        GC.setLineWidth(0.5);
        GC.strokeRect(5, 5 + 20 * a, 10, 10);
    }
    public void strokeText(GraphicsContext GC, int a, String s) {
        GC.setStroke(myColor.BLACK.getJavaFxColor());
        GC.strokeText(s, 20, 15 + 20 * a, 350);
    }
    //for other slices
    public void uniqueDrawRect(GraphicsContext GC, int a) {
        GC.setFill(color.getUniqueJavaFxColor());
        GC.fillRect(5, 5 + 20 * a, 10, 10);
    }
    public void uniqueStrokeRect(GraphicsContext GC, int a) {
        GC.setStroke(myColor.BLACK.getJavaFxColor());
        GC.setLineWidth(0.5);
        GC.strokeRect(5, 5 + 20 * a, 10, 10);
    }
    public void uniqueStrokeText(GraphicsContext GC, int a, String s) {
        GC.setStroke(myColor.BLACK.getJavaFxColor());
        GC.strokeText(s, 20, 15 + 20 * a, 350);
    }
}
