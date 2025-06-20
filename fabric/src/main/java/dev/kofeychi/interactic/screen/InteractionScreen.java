package dev.kofeychi.interactic.screen;

import dev.kofeychi.interactic.Events;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.figura.api.TaskAPI;
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
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static dev.kofeychi.interactic.Interactic.run;

@LuaWhitelist
@LuaTypeDoc(name = "Interactic",value = "inter")
public class InteractionScreen extends Screen {

    public Screen parent;

    public DrawAPI drawapi;
    public TaskAPI taskAPI;

    @LuaWhitelist
    public TaskAPI getTaskAPI() {
        return taskAPI;
    }
    @LuaWhitelist
    public DrawAPI getDrawAPI() {
        return drawapi;
    }

    public InteractionScreen(Text title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        taskAPI = new TaskAPI(drawapi);
        run(
                Events::InteracticAPI$SCREEN_INIT,
                taskAPI
        );
    }

    @Override
    public void removed() {
        run(
                Events::InteracticAPI$SCREEN_CLEAR,
                taskAPI
        );
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.client.setScreen(this.parent);
            return true;
        } else {
            run(
                    Events::InteracticAPI$KEY_PRESS,
                    new KeyPressEventData(new KeyPressData(keyCode, scanCode, modifiers)),
                    taskAPI
            );
        }
        return true;
    }

    public record KeyPressData(int keyCode, int scanCode, int modifiers) {}

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        run(
                Events::InteracticAPI$KEY_RELEASE,
                new KeyReleasedEventData(new KeyReleasedData(keyCode, scanCode, modifiers)),
                taskAPI
        );
        return true;
    }
    public record KeyReleasedData(int keyCode, int scanCode, int modifiers) {}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        run(
                Events::InteracticAPI$MOUSE_PRESS,
                new MousePressEventData(new MousePressData(mouseX, mouseY, button)),
                taskAPI
        );
        return true;
    }

    public record MousePressData(double mouseX, double mouseY, int button) {}

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        run(
                Events::InteracticAPI$MOUSE_DRAGGED,
                new MouseDraggedEventData(new MouseDraggedData(mouseX, mouseY, button, deltaX, deltaY)),
                taskAPI
        );
        return true;
    }
    public record MouseDraggedData(double mouseX, double mouseY, int button, double deltaX, double deltaY) {}

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        run(
                Events::InteracticAPI$MOUSE_RELEASE,
                new MouseReleasedEventData(new MouseReleasedData(mouseX, mouseY, button)),
                taskAPI
        );
        return true;
    }
    public record MouseReleasedData(double mouseX, double mouseY, int button) {}

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        run(
                Events::InteracticAPI$MOUSE_SCROLLED,
                new MouseScrollEventData(new MouseScrolledData(mouseX, mouseY, horizontalAmount, verticalAmount)),
                taskAPI
        );
        return true;
    }
    public record MouseScrolledData(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {}

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        run(
                Events::InteracticAPI$MOUSE_MOVED,
                new MouseMovedEventData(new MouseMovedData(mouseX,mouseY)),
                taskAPI
        );
    }
    public record MouseMovedData(double mouseX, double mouseY) {}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawapi = new DrawAPI(context,this.client.getRenderTickCounter());
        taskAPI.parent = drawapi;
        drawapi.task = taskAPI;
        run(
                Events::InteracticAPI$SCREEN_RENDER,
                drawapi,
                new ScreenRenderEventData(new RenderData(mouseX,mouseY,delta)),
                taskAPI
        );
        taskAPI.tasks.values().forEach((task) -> {
            task.parent = drawapi;
            task.render();
        });
    }
    public record RenderData(double mouseX, double mouseY, double delta) {}


    @Override
    public void tick() {
        run(
                Events::InteracticAPI$SCREEN_TICK,
                taskAPI
        );
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
}