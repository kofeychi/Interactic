package dev.kofeychi.interactic.util;

import java.util.ArrayList;
import java.util.Collection;

public class ContiniousArrayList<T> extends ArrayList<T> {
    public ContiniousArrayList(int initialCapacity) {
        super(initialCapacity);
    }
    public ContiniousArrayList() {
        super();
    }
    public ContiniousArrayList(Collection<? extends T> c) {
        super(c);
    }
    public ContiniousArrayList<T> addThen(T entry){
        super.add(entry);
        return this;
    }
}
