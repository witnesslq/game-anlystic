package cn.mob.anlystic.game.web.report.usage.bean;

/**
 * @version 1.0 date: 2014/8/25
 * @author: Dempe
 */
public class Usage {
    private String date;
    private int newer;
    private int run;
    private int active;
    private long duration;
    private float payment;
    private int back;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNewer() {
        return newer;
    }

    public void setNewer(int newer) {
        this.newer = newer;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }
}
