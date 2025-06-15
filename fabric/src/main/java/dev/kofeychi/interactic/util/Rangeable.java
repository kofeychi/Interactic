package dev.kofeychi.interactic.util;

import java.util.ArrayList;
import java.util.Collections;

public class Rangeable {
    public ArrayList<Integer> range;

    public Rangeable(int start,int end) {
        range = new ArrayList<>();
        for(int i=start;i<end;i++) {
            range.add(i);
        }
    }
    public Rangeable(int start,int end,int cycles) {
        this(start,end);
        cycle(cycles);
    }
    public void cycle(int cycles) {
        Collections.rotate(range, cycles);
    }
}
