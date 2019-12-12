package main.java.model;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    private boolean isInArea(double x, double y, double r) {
        if (x>=0) return y>=0 && x+2*y<=r;
        if (y>0)  return Math.hypot(x,y)<=r/2;
        return Math.abs(2*x)<=r && Math.abs(y)<=r;
    }

    public boolean isInArea(Point point) {
        return isInArea(point.getX(), point.getY(), point.getR());
    }
}
