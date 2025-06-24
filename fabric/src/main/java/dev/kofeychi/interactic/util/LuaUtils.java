package dev.kofeychi.interactic.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.FiguraLuaPrinter;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.luaj.vm2.LuaError;

import java.awt.*;

public class LuaUtils {
    public static FiguraVec2 mouse(){
        var mc = MinecraftClient.getInstance();
        var mouse = mc.mouse;
        var factor = mc.getWindow().getScaleFactor();
        return FiguraVec2.of(mouse.getX(), mouse.getY()).multiply(factor/4,factor/4);
    }
    public static void sendTranslatable(String message){
        FiguraLuaPrinter.sendLuaMessage(
                Text.translatable(message),
                "Interactic"
        );
    }
    public static void sendError(LuaError error){
        FiguraLuaPrinter.sendLuaError(
                error,
                AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID())
        );
    }
}
