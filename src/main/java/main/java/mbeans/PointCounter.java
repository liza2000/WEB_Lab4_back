package main.java.mbeans;

import main.java.model.Point;
import org.springframework.stereotype.Component;

import javax.management.*;
@Component
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean {
    private int countPoints;
    private int countTruePoints;
    private int sq;

    public void count(Point point){
        countPoints++;
        if(point.isInArea()) countTruePoints++;
        if(Math.abs(point.getX())>5.8||Math.abs(point.getY())>5.8)
            sendNotification(new Notification("PointOutside",this,++sq,"Point with coordinates ("+point.getX()+";"+point.getY()+") can't be drawn on canvas"));
    }
    @Override
    public int getCountPoints() {
        return countPoints;
    }
    @Override
    public int getCountTruePoints() {
        return countTruePoints;
    }
}
