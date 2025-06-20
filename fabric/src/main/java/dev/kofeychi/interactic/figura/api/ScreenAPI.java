package dev.kofeychi.interactic.figura.api;

import dev.kofeychi.interactic.screen.InteractionScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.lua.FiguraLuaRuntime;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.luaj.vm2.LuaNil;

@LuaWhitelist
@LuaTypeDoc(name = "ScreenAPI", value = "screen")
public class ScreenAPI {
    private final Avatar owner;


    public ScreenAPI(FiguraLuaRuntime runtime) {
        this.owner = runtime.owner;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentScreen"
    )
    public String getCurrentScreen() {
        if(!canExecute()){
            return LuaNil.NIL.toString();
        }
        if(!hasScreen()){
            return LuaNil.NIL.toString();
        }
        return MinecraftClient.getInstance().currentScreen.getTitle().getLiteralString();
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.isCurrentScreenInteractic"
    )
    public boolean isCurrentScreenInteractic() {
        if(!canExecute()){
            return false;
        }
        return MinecraftClient.getInstance().currentScreen instanceof InteractionScreen&&MinecraftClient.getInstance().currentScreen != null;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.setScreenToInteractic"
    )
    public ScreenAPI setScreenToInteractic() {
        if(!canExecuteWS()){
            return this;
        }
        MinecraftClient.getInstance().setScreen(new InteractionScreen(
                Text.empty(),
                MinecraftClient.getInstance().currentScreen
        ));
        return this;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentScreenWidth"
    )
    public int getCurrentScreenWidth() {
        if(!canExecute()){
            return 0;
        }
        return MinecraftClient.getInstance().currentScreen.width;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentScreenHeight"
    )
    public int getCurrentScreenHeight() {
        if(!canExecute()){
            return 0;
        }
        return MinecraftClient.getInstance().currentScreen.height;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentWindowHeight"
    )
    public int getCurrentWindowHeight() {
        if(!canExecute()){
            return 0;
        }
        return MinecraftClient.getInstance().getWindow().getHeight();
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentWindowWidth"
    )
    public int getCurrentWindowWidth() {
        if(!canExecute()){
            return 0;
        }
        return MinecraftClient.getInstance().getWindow().getWidth();
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentWindowScaledHeight"
    )
    public int getCurrentWindowScaledHeight() {
        if(!canExecute()){
            return 0;
        }
        return MinecraftClient.getInstance().getWindow().getScaledHeight();
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentWindowScaledWidth"
    )
    public int getCurrentWindowScaledWidth() {
        if(!canExecute()){
            return 0;
        }
        return MinecraftClient.getInstance().getWindow().getScaledWidth();
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getCurrentWindowPosition"
    )
    public FiguraVec2 getCurrentWindowPosition() {
        if(!canExecute()){
            return FiguraVec2.of(0,0);
        }
        var win = MinecraftClient.getInstance().getWindow();
        return FiguraVec2.of(win.getX(),win.getY());
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
            },
            value = "screen.getInteractionScreen"
    )
    public InteractionScreen getInteractionScreen() {
        if(!canExecute()){
            return null;
        }
        return (InteractionScreen)MinecraftClient.getInstance().currentScreen;
    }


    public boolean hasScreen() {
        return MinecraftClient.getInstance().currentScreen != null;
    }
    public boolean canExecute() {
        return MinecraftClient.getInstance().isWindowFocused() && hasScreen();
    }
    public boolean canExecuteWS() {
        return MinecraftClient.getInstance().isWindowFocused();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
