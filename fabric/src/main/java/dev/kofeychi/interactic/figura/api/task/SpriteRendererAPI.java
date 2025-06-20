package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;

@LuaWhitelist
@LuaTypeDoc(name = "SpriteRenderer", value = "spriter")
public class SpriteRendererAPI extends RenderAPIContainer {
    public SpriteRendererAPI(DrawAPI parent,String name) {
        super(parent,name);
    }

    @Override
    protected void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer) {

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
