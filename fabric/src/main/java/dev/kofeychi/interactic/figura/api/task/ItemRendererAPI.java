package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;

@LuaWhitelist
@LuaTypeDoc(name = "ItemRenderer", value = "itemr")
public class ItemRendererAPI extends RenderAPIContainer {
    public ItemRendererAPI(DrawAPI parent) {
        super(parent);
    }

    @Override
    protected void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer) {

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
