package cn.mob.anlystic.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/3
 */
public class MetricsStat {

    private static MetricsStat instance;
    private AtomicInteger ips = new AtomicInteger(0);

    private MetricsStat() {

        Runnable statThread = new Runnable() {
            int preNum = 0;

            @Override
            public void run() {
                int ipsNum = ips.get();
                System.out.println("[ ips/s = " + (ipsNum - preNum) + " ]");
                preNum = ipsNum;
            }
        };
        Threads.scheduleWithFixedDelay(statThread, 1, 1, TimeUnit.SECONDS);

    }

    public static MetricsStat getInstance() {
        if (instance == null) {
            instance = new MetricsStat();
        }
        return instance;
    }

    public int incrementAndGet() {
        return ips.incrementAndGet();
    }
}
