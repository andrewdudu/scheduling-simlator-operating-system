package com.company;

import com.company.algo.FIFO;
import com.company.algo.RR;
import com.company.algo.SJF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Run {

    private List<Process> processes = new ArrayList<>();

    FIFO fifo;

    RR rr;

    SJF sjf;

    private Integer clockTime;

    public Run() {
        try {
            FileInputStream fstream = new FileInputStream("C:/Users/junqu/Documents/Java/Scheduling/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                List<String> items = Arrays.asList(strLine.split(" "));
                Process process = new Process(
                        Integer.parseInt(items.get(0)),
                        Integer.parseInt(items.get(1)),
                        Integer.parseInt(items.get(2)),
                        Integer.parseInt(items.get(3)),
                        Integer.parseInt(items.get(4)),
                        Integer.parseInt(items.get(5))
                );
                processes.add(process);
            }
            // Close the input stream
            fstream.close();

        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        fifo = new FIFO(processes);
        rr = new RR(processes);
        sjf = new SJF(processes);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String readString = scanner.nextLine();
        clockTime = 0;
        do {
            System.out.println("CLOCK TIME = " + clockTime);
            System.out.println("QUEUE A : ");
            fifo.processes.forEach(p -> {
                if (p.getBurstTime() > 0) {
                    System.out.println("  ||-- PROSES ID : " + p.getId() + " => " + p.getBurstTime() + " clock time tersisa.");
                } else {
                    System.out.println("  ||-- PROSES ID : " + p.getId() + " selesai.");
                }
            });
            System.out.println("QUEUE B : ");
            rr.processes.forEach(p -> {
                if (p.getBurstTime() > 0) {
                    System.out.println("  ||-- PROSES ID : " + p.getId() + " => " + p.getBurstTime() + " clock time tersisa.");
                } else {
                    System.out.println("  ||-- PROSES ID : " + p.getId() + " selesai.");
                }
            });
            System.out.println("QUEUE C : ");
            sjf.processes.forEach(p -> {
                if (p.getBurstTime() > 0) {
                    System.out.println("  ||-- PROSES ID : " + p.getId() + " => " + p.getBurstTime() + " clock time tersisa.");
                } else {
                    System.out.println("  ||-- PROSES ID : " + p.getId() + " selesai.");
                }
            });
            System.out.println("Silahkan tekan tombol Enter untuk lanjut ke clock selanjutnya.");

            fifo.tick();
            Process rrDemo = rr.tick();
            List<Process> sjfDemo = sjf.tick();
            if (sjfDemo.size() > 0) {
                sjfDemo.stream()
                        .map(p -> {
                            p.setWaitingClock(0);
                            return p;
                        });
                while (fifo.processes.size() < 10 && sjfDemo.size() > 0) {
                    System.out.println("Promosi dari Qc ke Qa dengan ID : " + sjfDemo.get(0).getId());
                    fifo.processes.add(sjfDemo.get(0));
                    sjfDemo.remove(0);
                }
            }

            if (rrDemo.getId() != null) {
                System.out.println("Demosi dari Qb ke Qc dengan ID : " + rrDemo.getId());
                rrDemo.setWaitingClock(0);
                sjf.processes.add(rrDemo);
            }

            if (sjfDemo.size() > 0) {
                sjfDemo.forEach(p -> System.out.println("Demosi dari Qa ke Qb dengan ID : " + p.getId()));
                rr.processes.addAll(sjfDemo);
            }

            clockTime++;
            if (scanner.hasNextLine()) {
                readString = scanner.nextLine();
            } else {
                readString = null;
            }
        } while (readString!=null);
    }
}
