package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.util.RenderUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.utils.LuaUtils;
import org.figuramc.figura.utils.RenderUtils;
import org.figuramc.figura.utils.TextUtils;

@LuaWhitelist
@LuaTypeDoc(name = "BlockRenderer", value = "blockr")
public class BlockRendererAPI extends RenderAPIContainer {
    public BlockState block;


    public BlockRendererAPI(DrawAPI parent) {
        super(parent);
    }

    @Override
    protected void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer) {

    }


    @LuaWhitelist
    public BlockRendererAPI setBlock(Object block) {
        this.block = LuaUtils.parseBlockState("setBlock",block);
        return this;
    }

    @LuaWhitelist
    public BlockRendererAPI drawBlock(FiguraMat4 mat) {
        if(!rendering) {return this;}
        var stack = new MatrixStack();
        stack.push();
        stack.peek().getPositionMatrix().set(mat.toMatrix4f());
        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                this.block,
                stack,
                parent.ctx.getVertexConsumers(),
                light,
                overlay
        );
        return this;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
