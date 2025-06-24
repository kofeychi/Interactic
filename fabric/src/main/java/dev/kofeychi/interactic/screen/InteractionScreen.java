package dev.kofeychi.interactic.screen;

import dev.kofeychi.interactic.Events;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.figura.api.TaskAPI;
import dev.kofeychi.interactic.figura.api.screen.ButtonContainer;
import dev.kofeychi.interactic.figura.api.screen.Frame;
import dev.kofeychi.interactic.figura.api.screen.MainFrame;
import dev.kofeychi.interactic.figura.api.task.RenderAPIContainer;
import dev.kofeychi.interactic.figura.event.*;
import dev.kofeychi.interactic.util.shape.AbstractShape;
import dev.kofeychi.interactic.util.shape.Box;
import dev.kofeychi.interactic.util.shape.Circle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.FiguraLuaPrinter;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static dev.kofeychi.interactic.Interactic.run;

@LuaWhitelist
@LuaTypeDoc(name = "Interactic",value = "inter")
public class InteractionScreen extends Screen {

    public Screen parent;

    public DrawAPI drawapi;
    public TaskAPI taskAPI;
    public MainFrame mainFrame;

    @LuaWhitelist
    @LuaMethodDoc(value = "inter.getTaskAPI")
    public TaskAPI getTaskAPI() {
        return taskAPI;
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "inter.getDrawAPI")
    public DrawAPI getDrawAPI() {
        return drawapi;
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "inter.getMainFrame")
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public InteractionScreen(Text title, Screen parent) {
        super(title);
        this.parent = parent;
        mainFrame = new MainFrame(FiguraVec2.of(),FiguraVec2.of());
    }

    @Override
    protected void init() {
        taskAPI = new TaskAPI(drawapi);
        run(
                Events::InteracticAPI$SCREEN_INIT,
                (a) -> a.init,
                taskAPI,
                this,
                mainFrame
        );
    }

    @Override
    public void removed() {
        run(
                Events::InteracticAPI$SCREEN_CLEAR,
                (a) -> a.init,
                taskAPI,
                this,
                mainFrame
        );
        taskAPI.tasks.clear();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.client.setScreen(this.parent);
            return true;
        } else {
            run(
                    Events::InteracticAPI$KEY_PRESS,
                    (a) -> a.tick,
                    new KeyPressEventData(new KeyPressData(keyCode, scanCode, modifiers)),
                    taskAPI,
                    this
            );
        }
        return true;
    }

    public record KeyPressData(int keyCode, int scanCode, int modifiers) {}

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        run(
                Events::InteracticAPI$KEY_RELEASE,
                (a) -> a.tick,
                new KeyReleasedEventData(new KeyReleasedData(keyCode, scanCode, modifiers)),
                taskAPI,
                this
        );
        return true;
    }
    public record KeyReleasedData(int keyCode, int scanCode, int modifiers) {}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        run(
                Events::InteracticAPI$MOUSE_PRESS,
                (a) -> a.tick,
                new MousePressEventData(new MousePressData(mouseX, mouseY, button)),
                taskAPI,
                this
        );
        if(button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            mainFrame.onLeftClick();
        } else if(button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
            mainFrame.onRightClick();
        }
        return true;
    }

    public record MousePressData(double mouseX, double mouseY, int button) {}

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        run(
                Events::InteracticAPI$MOUSE_DRAGGED,
                (a) -> a.tick,
                new MouseDraggedEventData(new MouseDraggedData(mouseX, mouseY, button, deltaX, deltaY)),
                taskAPI,
                this
        );
        return true;
    }
    public record MouseDraggedData(double mouseX, double mouseY, int button, double deltaX, double deltaY) {}

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        run(
                Events::InteracticAPI$MOUSE_RELEASE,
                (a) -> a.tick,
                new MouseReleasedEventData(new MouseReleasedData(mouseX, mouseY, button)),
                taskAPI,
                this
        );
        return true;
    }
    public record MouseReleasedData(double mouseX, double mouseY, int button) {}

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        run(
                Events::InteracticAPI$MOUSE_SCROLLED,
                (a) -> a.tick,
                new MouseScrollEventData(new MouseScrolledData(mouseX, mouseY, horizontalAmount, verticalAmount)),
                taskAPI,
                this
        );
        return true;
    }
    public record MouseScrolledData(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {}

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        run(
                Events::InteracticAPI$MOUSE_MOVED,
                (a) -> a.tick,
                new MouseMovedEventData(new MouseMovedData(mouseX,mouseY)),
                taskAPI,
                this
        );
    }
    public record MouseMovedData(double mouseX, double mouseY) {}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawapi = new DrawAPI(context,this.client.getRenderTickCounter());
        taskAPI.parent = drawapi;
        drawapi.task = taskAPI;
        drawapi.owner = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        run(
                Events::InteracticAPI$SCREEN_RENDER,
                (a) -> a.render,
                drawapi,
                new ScreenRenderEventData(new RenderData(mouseX,mouseY,delta)),
                taskAPI,
                this
        );
        mainFrame.render(drawapi, FiguraVec2.of(mouseX,mouseY),delta);
        taskAPI.tasks.values().forEach((task) -> {
            task.parent = drawapi;
            task.render();
        });
        taskAPI.parent = null;
        drawapi.task = null;
    }
    public record RenderData(double mouseX, double mouseY, double delta) {}


    @Override
    public void tick() {
        run(
                Events::InteracticAPI$SCREEN_TICK,
                (a) -> a.tick,
                taskAPI
        );
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar == null) {
            return;
        }
        if(avatar.scriptError){
            leave(null,false);
        }
    }

    public void leave(String customMsg,boolean complete){
        removed();
        if(complete){
            this.client.setScreen(null);
        } else {
            this.client.setScreen(this.parent);
        }
        if(customMsg != null) {
            FiguraLuaPrinter.sendLuaMessage(
                    Text.translatable(customMsg),
                    "Interactic"
            );
        } else {
            FiguraLuaPrinter.sendLuaMessage(
                    Text.translatable("interactic.figura.unexpected"),
                    "Interactic"
            );
        }
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

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}