package dev.kofeychi.interactic.figura.api.task;

import com.mojang.logging.LogUtils;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.lua.LuaNotNil;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.world.ItemStackAPI;
import org.figuramc.figura.lua.api.world.WorldAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.model.FiguraModelPart;
import org.figuramc.figura.model.rendertasks.ItemTask;
import org.figuramc.figura.utils.LuaUtils;
import org.luaj.vm2.LuaError;

import java.util.Locale;

@LuaWhitelist
@LuaTypeDoc(name = "ItemRenderer", value = "itemr")
public class ItemRendererAPI extends RenderAPIContainer {
    public ItemRendererAPI(DrawAPI parent,String name) {
        super(parent,name);
        this.displayMode = ModelTransformationMode.NONE;
        this.left = false;
    }

    private ItemStack item;
    private ModelTransformationMode displayMode;
    private boolean left;

    @Override
    public void render(MatrixStack poseStack, VertexConsumerProvider buffer) {
        Entity newOverlay = this.owner.renderer.entity;
        LivingEntity var10000;
        if (newOverlay instanceof LivingEntity living) {
            var10000 = living;
        } else {
            var10000 = null;
        }
        LivingEntity entity = var10000;
        int seed = entity != null ? entity.getId() + this.displayMode.ordinal() : 0;
        MinecraftClient.getInstance().getItemRenderer().renderItem(entity, this.item, this.displayMode, this.left,poseStack, buffer, WorldAPI.getCurrentWorld(), light, overlay, seed);
    }

    @Override
    public boolean shouldRender() {
        return this.item != null && !this.item.isEmpty();
    }


    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {@LuaMethodOverload(
                    argumentTypes = {String.class},
                    argumentNames = {"item"}
            ), @LuaMethodOverload(
                    argumentTypes = {ItemStackAPI.class},
                    argumentNames = {"item"}
            )},
            value = "itemr.set_item"
    )
    public ItemRendererAPI setItem(Object item) {
        this.item = LuaUtils.parseItemStack("item", item);
        return this;
    }

    @LuaWhitelist
    @LuaMethodDoc("itemr.get_display_mode")
    public String getDisplayMode() {
        return this.displayMode.name();
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {@LuaMethodOverload(
                    argumentTypes = {String.class},
                    argumentNames = {"displayMode"}
            )},
            value = "itemr.set_display_mode"
    )
    public ItemRendererAPI setDisplayMode(@LuaNotNil String mode) {
        try {
            this.displayMode = ModelTransformationMode.valueOf(mode.toUpperCase(Locale.US));
            this.left = this.displayMode == ModelTransformationMode.FIRST_PERSON_LEFT_HAND || this.displayMode == ModelTransformationMode.THIRD_PERSON_LEFT_HAND;
            return this;
        } catch (Exception var3) {
            throw new LuaError("Illegal display mode: \"" + mode + "\".");
        }
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
