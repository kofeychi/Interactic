package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.utils.TextUtils;

@LuaWhitelist
@LuaTypeDoc(name = "TextRenderer", value = "textr")
public class TextRendererAPI extends RenderAPIContainer{
    public final TextRenderer textRenderer;

    public TextRendererAPI(DrawAPI parent) {
        super(parent);
        textRenderer = MinecraftClient.getInstance().textRenderer;
    }

    @Override
    protected void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer) {

    }

    @LuaWhitelist
    public int getTextWidth(String text) {
        return textRenderer.getWidth(TextUtils.tryParseJson(text));
    }

    @LuaWhitelist
    public TextRendererAPI drawText(String text, FiguraVec2 pos, int color, boolean shadow) {
        if(!rendering) {
            return this;
        }
        parent.ctx.drawText(
                textRenderer,
                TextUtils.tryParseJson(text),
                (int) pos.x,
                (int) pos.y,
                color,
                shadow
        );
        return this;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
