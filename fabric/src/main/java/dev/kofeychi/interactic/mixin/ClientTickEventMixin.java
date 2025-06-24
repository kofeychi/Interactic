package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.util.Scheduler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.tick.TickManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientTickEventMixin {
    @Inject(
            method = {"tickEntities"},
            at = {@At("HEAD")}
    )
    private void startWorldTick(CallbackInfo ci) {
        TickManager tickManager = ((ClientWorld)(Object)this).getTickManager();
        boolean bl = tickManager.shouldTick();
        if(bl){
            Scheduler.CLIENT.tick();
        }
    }
}
