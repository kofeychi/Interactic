package dev.kofeychi.interactic.util;


import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;

@LuaWhitelist
@LuaTypeDoc(name = "ClampedInt",value = "cint")
public class ClampedInt extends ClampedNumber<Integer> {
    @LuaWhitelist
    public int value = 0;
    @LuaWhitelist
    public int bottomBound = 0;
    @LuaWhitelist
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

    @Override
    public String toString() {
        return "ClampedInt{" +
                "value=" + value +
                ", bottomBound=" + bottomBound +
                ", topBound=" + topBound +
                '}';
    }
}
