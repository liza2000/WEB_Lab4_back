package main.java.model;


public class Calculator {

    private boolean isInArea(double x, double y, double r) {
        if (x >= 0) return y >= 0 && Math.hypot(x, y) <= r || (x == 0 && Math.abs(y) <= r);
        if (y > 0) return Math.abs(2 * y) <= r && Math.abs(x) <= r;
        return Math.abs(x) + Math.abs(y) <= r;
    }

    public boolean isInArea(Point point) {
        return isInArea(point.getX(), point.getY(), point.getR());
    }
}
