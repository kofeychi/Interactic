package dev.kofeychi.interactic.util;

public class RangeUtil {
    public static boolean isInRange(int val,int bound){
        return bound >= val && val >= -bound;
    }
}
