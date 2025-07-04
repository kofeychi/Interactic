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
import net.minecraft.util.math.BlockPos;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.world.BlockStateAPI;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.utils.LuaUtils;
import org.figuramc.figura.utils.RenderUtils;
import org.figuramc.figura.utils.TextUtils;
import org.luaj.vm2.LuaError;

@LuaWhitelist
@LuaTypeDoc(name = "BlockRenderer", value = "blockr")
public class BlockRendererAPI extends RenderAPIContainer {
    public BlockState block;

    public BlockRendererAPI(DrawAPI parent, String name) {
        super(parent, name);
    }

    @Override
    protected void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer) {
        try {
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                    this.block,
                    matrixStack,
                    vertexConsumer,
                    light,
                    overlay
            );
        } catch (Exception e) {
            throw new LuaError("Failed to render block: " + this.block + " : " + e.getMessage());
        }
    }

    @Override
    public boolean shouldRender() {
        return block != null&&!block.isAir();
    }


    @LuaWhitelist
    public BlockRendererAPI setBlock(Object block) {
        this.block = LuaUtils.parseBlockState("setBlock",block);
        return this;
    }
    @LuaWhitelist
    public BlockStateAPI getBlock() {
        return new BlockStateAPI(block,new BlockPos(0,0,0));
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
