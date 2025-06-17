package dev.kofeychi.interactic.util;


public class ClampedInt extends ClampedNumber<Integer> {
    public int value = 0;

    public int bottomBound = 0;
    public int topBound = 0;

    public ClampedInt(int bottomBound, int topBound) {
        this.bottomBound = bottomBound;
        this.topBound = topBound;
    }

    @Override
    public Integer get() {
        verify();
        return value;
    }

    @Override
    public void set(Integer value) {
        this.value = value;
        verify();
    }
    private void verify(){
        if(topBound < bottomBound){
            int temp = topBound;
            topBound = bottomBound;
            bottomBound = temp;
        }
        if(value < bottomBound){
            value = bottomBound;
        }
        if(value > topBound){
            value = topBound;
        }
    }
}
