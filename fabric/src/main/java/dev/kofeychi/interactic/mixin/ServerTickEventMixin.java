package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.util.Scheduler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.tick.TickManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerTickEventMixin {
    private static int val = 0;
    @Inject(at = @At(value = "HEAD"),method = "tick",cancellable = true)
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        TickManager tickManager = ((ServerWorld)(Object)this).getTickManager();
        boolean bl = tickManager.shouldTick();
        if(bl){
            if(val == 2) {
                Scheduler.SERVER.tick();
                val = 0;
            } else {
                val++;
            }
        }
    }
}
