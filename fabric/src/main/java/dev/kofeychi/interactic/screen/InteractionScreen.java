package dev.kofeychi.interactic.screen;

import dev.kofeychi.interactic.Events;
import dev.kofeychi.interactic.figura.event.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.lwjgl.glfw.GLFW;

public class InteractionScreen extends Screen {

    public Screen parent;

    public InteractionScreen(Text title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.client.setScreen(this.parent);
            return true;
        } else {
            Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
            if(avatar.luaRuntime == null) {
                return true;
            }
            LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$KEY_PRESS();
            avatar.run(keyEvent, avatar.tick, new KeyPressEventData(new KeyPressData(keyCode, scanCode, modifiers)));
        }
        return true;
    }
    public record KeyPressData(int keyCode, int scanCode, int modifiers) {}

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return true;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$KEY_RELEASE();
        avatar.run(keyEvent, avatar.tick, new KeyReleasedEventData(new KeyReleasedData(keyCode, scanCode, modifiers)));
        return true;
    }
    public record KeyReleasedData(int keyCode, int scanCode, int modifiers) {}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return true;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$MOUSE_PRESS();
        avatar.run(keyEvent, avatar.tick, new MousePressEventData(new MousePressData(mouseX, mouseY, button)));
        return true;
    }

    public record MousePressData(double mouseX, double mouseY, int button) {}

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return true;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$MOUSE_DRAGGED();
        avatar.run(keyEvent, avatar.tick, new MouseDraggedEventData(new MouseDraggedData(mouseX, mouseY, button, deltaX, deltaY)));
        return true;
    }
    public record MouseDraggedData(double mouseX, double mouseY, int button, double deltaX, double deltaY) {}

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return true;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$MOUSE_RELEASE();
        avatar.run(keyEvent, avatar.tick, new MouseReleasedEventData(new MouseReleasedData(mouseX, mouseY, button)));
        return true;
    }
    public record MouseReleasedData(double mouseX, double mouseY, int button) {}

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return true;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$MOUSE_SCROLLED();
        avatar.run(keyEvent, avatar.tick, new MouseScrollEventData(new MouseScrolledData(mouseX, mouseY, horizontalAmount, verticalAmount)));
        return true;
    }
    public record MouseScrolledData(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {}

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$MOUSE_MOVED();
        avatar.run(keyEvent, avatar.tick, new MouseMovedEventData(new MouseMovedData(mouseX,mouseY)));
    }
    public record MouseMovedData(double mouseX, double mouseY) {}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return;
        }
        LuaEvent keyEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$SCREEN_RENDER();
        avatar.run(keyEvent, avatar.tick, new ScreenRenderEventData(new RenderData(mouseX,mouseY,delta)));
    }
    public record RenderData(double mouseX, double mouseY, double delta) {}


    @Override
    public void tick() {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null) {
            return;
        }
        LuaEvent legacyMicrophoneEvent = ((Events) avatar.luaRuntime.events).InteracticAPI$SCREEN_TICK();
        avatar.run(legacyMicrophoneEvent, avatar.tick);
    }


    @Override
    public void blur() {
    }

    @Override
    protected void applyBlur(float delta) {
    }


    @Override
    protected void setInitialFocus() {
    }

    @Override
    protected void setInitialFocus(Element element) {
    }

    @Override
    public void renderInGameBackground(DrawContext context) {
    }

    @Override
    protected void renderDarkening(DrawContext context) {
    }

    @Override
    protected void renderDarkening(DrawContext context, int x, int y, int width, int height) {
    }

    @Override
    protected void renderPanoramaBackground(DrawContext context, float delta) {
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
    }
}