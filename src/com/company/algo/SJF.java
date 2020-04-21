package com.company.algo;

import com.company.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SJF {

    public List<Process> processes;

    public List<Process> waitingProcesses;

    private Integer completed;

    private Integer clockTime;

    public SJF(List<Process> processes) {
        this.processes = processes.stream()
                .filter(p -> (p.getPrioritas() == 3) && (p.getClockAwal() == 0))
                .collect(Collectors.toList());
        this.processes.sort(Comparator.comparingInt(Process::getBurstTime));
        this.waitingProcesses = processes.stream()
                .filter(p -> p.getPrioritas() == 3 && p.getClockAwal() != 0)
                .collect(Collectors.toList());
        this.clockTime = 0;
        this.completed = 0;
    }

    public List<Process> tick() {
        clockTime++;
        completed = 0;
        waitingProcesses.forEach(p -> {
            if (p.getClockAwal().equals(clockTime)) {
                processes.add(p);
            }
        });

        Process process = null;
        int selected = 0;
        processes.forEach(p -> {
            if (p.getBurstTime() == 0) completed++;
        });
        for (int i=0;i<processes.size();i++) {
            if (processes.get(i).getBurstTime() != 0) {
                process = processes.get(i);
                selected = i;
                break;
            }
        }

        List<Process> demo = new ArrayList<>();

        if (process != null) {
            for (int i=selected+1;i<processes.size();i++) {
                if (processes.get(i).getBurstTime() != 0) {
                    processes.get(i).setWaitingClock(processes.get(i).getWaitingClock() + 1);
                    if (processes.get(i).getWaitingClock() >= 25) {
                        demo.add(processes.get(i));
                        processes.remove(i);
                        i--;
                    }
                }
            }
        }

        if (process != null) {
            process.setBurstTime(process.getBurstTime() - 1);
        }

        if (demo.size() > 0) {
            return demo;
        }
        return new ArrayList<>();
    }
}
