package main.java;

import main.java.mbeans.PointCounter;
import main.java.mbeans.SquareCalculator;
import main.java.model.Point;
import org.springframework.stereotype.Service;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Service
public class PointService {
    private PointCounter pointCounter;
    private SquareCalculator squareCalculator;


    PointService(PointCounter pointCounter, SquareCalculator squareCalculator) {
        this.pointCounter = pointCounter;
        this.squareCalculator = squareCalculator;
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            server.registerMBean(pointCounter, new ObjectName("lab4:type=PointCounter"));
            server.registerMBean(squareCalculator, new ObjectName("lab4:type=SquareCalculator"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh(Point point) {
        pointCounter.refresh(point);
    }

    public void calculateSquare(double r) {
        squareCalculator.setSquare(r);
    }
}
