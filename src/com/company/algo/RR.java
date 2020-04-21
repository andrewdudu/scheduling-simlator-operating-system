package com.company.algo;

import com.company.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RR {

    public List<Process> processes;

    public List<Process> waitingProcesses;

    private Integer completed;

    private Integer clockTime;

    private Integer elapsedClockTime;

    public RR(List<Process> processes) {
        this.processes = processes.stream()
                .filter(p -> (p.getPrioritas() == 2) && (p.getClockAwal() == 0))
                .collect(Collectors.toList());
        this.waitingProcesses = processes.stream()
                .filter(p -> p.getPrioritas() == 2 && p.getClockAwal() != 0)
                .collect(Collectors.toList());
        this.clockTime = 0;
        this.elapsedClockTime = 0;
        this.completed = 0;
    }

    public Process tick() {
        clockTime++;
        waitingProcesses.forEach(p -> {
            if (p.getClockAwal().equals(clockTime)) {
                processes.add(p);
            }
        });

        Process process = null;
        int count = 0;
        List<Integer> indexedUncomplete = new ArrayList<>();
        for (int i=0;i<processes.size();i++) {
            if (processes.get(i).getBurstTime() != 0) {
                count++;
                indexedUncomplete.add(i);
            }
        }

        if (count > 0) {
            int index = indexedUncomplete.get((elapsedClockTime / 3) % count);
            process = processes.get(index);

            elapsedClockTime++;
            Integer totalRound = 0;
            if (process.getBurstTime() > 0) {
                process.setBurstTime(process.getBurstTime() - 1);
                totalRound = process.getRound() + 1;
                process.setRound(totalRound);
            }
            if (totalRound == 3 * 3)  {
                processes.remove(index);
                return process;
            }
        }

        return new Process();
    }
}
