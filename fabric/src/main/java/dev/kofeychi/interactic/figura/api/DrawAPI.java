package dev.kofeychi.interactic.figura.api;

import dev.kofeychi.interactic.figura.api.task.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.utils.TextUtils;

@LuaWhitelist
@LuaTypeDoc(name = "DrawAPI", value = "draw")
public class DrawAPI {
    public final Avatar owner;
    public final DrawContext ctx;
    public final RenderTickCounter counter;
    public final TextRenderer textRenderer;

    public final TextRendererAPI TextRenderer;
    public final BlockRendererAPI BlockRenderer;
    public final DrawContextAPI DrawContext;
    public final ItemRendererAPI ItemRenderer;
    public final SpriteRendererAPI SpriteRenderer;

    public DrawAPI(DrawContext context, RenderTickCounter counter) {
        ctx = context;
        this.counter = counter;
        textRenderer = MinecraftClient.getInstance().textRenderer;
        this.owner = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        this.TextRenderer = new TextRendererAPI(this);
        this.BlockRenderer = new BlockRendererAPI(this);
        this.DrawContext = new DrawContextAPI(this);
        this.ItemRenderer = new ItemRendererAPI(this);
        this.SpriteRenderer = new SpriteRendererAPI(this);
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "draw.getTextRenderer")
    public TextRendererAPI getTextRenderer(){
        return TextRenderer;
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "draw.getBlockRenderer")
    public BlockRendererAPI getBlockRenderer(){
        return BlockRenderer;
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "draw.getDrawContext")
    public DrawContextAPI getDrawContext(){
        return DrawContext;
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "draw.getItemRenderer")
    public ItemRendererAPI getItemRenderer(){
        return ItemRenderer;
    }
    @LuaWhitelist
    @LuaMethodDoc(value = "draw.getSpriteRenderer")
    public SpriteRendererAPI getSpriteRenderer(){
        return SpriteRenderer;
    }
    @LuaWhitelist
    public float getLastFrameDuration() {
        return counter.getLastFrameDuration();
    }
    @LuaWhitelist
    public float getTickDelta(boolean bl) {
        return counter.getTickDelta(bl);
    }
    @LuaWhitelist
    public float getLastDuration() {
        return counter.getLastDuration();
    }
    @LuaWhitelist
    public DrawAPI drawItem(String item,FiguraVec2 pos,int seed,int z) {
        try {
            ctx.drawItem(
                    Registries.ITEM.get(Identifier.tryParse(item)).getDefaultStack(),
                    (int) pos.x,
                    (int) pos.y,
                    seed,
                    z
            );
        } catch (Exception ignored) {
        }
        return this;
    }
    @LuaWhitelist
    public DrawAPI drawItem(String item,FiguraVec2 pos,int seed) {
        return drawItem(item,pos,seed,0);
    }
    @LuaWhitelist
    public DrawAPI drawItem(String item,FiguraVec2 pos) {
        return drawItem(item,pos,0,0);
    }

    @Override
    public String toString() {
        return "DrawAPI";
    }
}
