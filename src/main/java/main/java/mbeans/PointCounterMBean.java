package main.java.mbeans;


import javax.management.NotificationEmitter;

public interface PointCounterMBean extends NotificationEmitter {
    int getCountPoints();
    int getCountTruePoints();
}
