package main.java.mbeans;

import org.springframework.stereotype.Component;

@Component
public class SquareCalculator implements SquareCalculatorMBean {
    private double square;
    @Override
    public void setSquare(double r){
        square = r*r*(Math.PI/4 + 1);
    }
    @Override
    public double getSquare() {
        return square;
    }
}
