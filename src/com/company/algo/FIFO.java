package com.company.algo;

import com.company.Process;

import java.util.List;
import java.util.stream.Collectors;

public class FIFO {

    public List<Process> processes;

    public List<Process> waitingProcesses;

    private Integer completed;

    private Integer clockTime;

    public FIFO(List<Process> processes) {
        this.processes = processes.stream()
                .filter(p -> (p.getPrioritas() == 1) && (p.getClockAwal() == 0))
                .collect(Collectors.toList());
        this.waitingProcesses = processes.stream()
                .filter(p -> p.getPrioritas() == 1 && p.getClockAwal() != 0)
                .collect(Collectors.toList());
        this.clockTime = 0;
        this.completed = 0;
    }

    public void tick() {
        clockTime++;
        completed = 0;
        waitingProcesses.forEach(p -> {
            if (p.getClockAwal().equals(clockTime)) {
                processes.add(p);
            }
        });

        Process process = null;
        processes.forEach(p -> {
            if (p.getBurstTime() == 0) completed++;
        });
        for (int i=0;i<processes.size();i++) {
            if (processes.get(i).getBurstTime() != 0) {
                process = processes.get(i);
                break;
            }
        }


        if (process != null) {
            process.setBurstTime(process.getBurstTime() - 1);
        }
    }
}
