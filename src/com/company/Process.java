package com.company;

public class Process {

    public Process() {
    }

    public Process(Integer id, Integer prioritas, Integer lamaProses, Integer burstTime, Integer aksesIO, Integer clockAwal) {
        this.id = id;
        this.prioritas = prioritas;
        this.lamaProses = lamaProses;
        this.burstTime = burstTime;
        this.aksesIO = aksesIO;
        this.clockAwal = clockAwal;
        this.waitingClock = 0;
        this.round = 0;
    }

    private Integer id;

    private Integer prioritas;

    private Integer lamaProses;

    private Integer burstTime;

    private Integer aksesIO;

    private Integer clockAwal;

    private Integer waitingClock;

    private Integer round;

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getWaitingClock() {
        return waitingClock;
    }

    public void setWaitingClock(Integer waitingClock) {
        this.waitingClock = waitingClock;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(Integer prioritas) {
        this.prioritas = prioritas;
    }

    public Integer getLamaProses() {
        return lamaProses;
    }

    public void setLamaProses(Integer lamaProses) {
        this.lamaProses = lamaProses;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public Integer getAksesIO() {
        return aksesIO;
    }

    public void setAksesIO(Integer aksesIO) {
        this.aksesIO = aksesIO;
    }

    public Integer getClockAwal() {
        return clockAwal;
    }

    public void setClockAwal(Integer clockAwal) {
        this.clockAwal = clockAwal;
    }
}
