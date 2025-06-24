package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.screen.InteractionScreen;
import dev.kofeychi.interactic.util.Scheduler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.avatar.local.LocalAvatarLoader;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.Optional;

@Mixin(LocalAvatarLoader.class)
@Debug(export = true)
public class AvatarReloadListener {
    @Shadow private static Path lastLoadedPath;
    @Inject(at = @At(value = "INVOKE",target = "Lorg/figuramc/figura/avatar/AvatarManager;loadLocalAvatar(Ljava/nio/file/Path;)V"),method = "tick",cancellable = true,remap = false)
    private static void onClearAvatars(CallbackInfo ci) {
        var client = MinecraftClient.getInstance();
        if(client.currentScreen instanceof InteractionScreen inter) {
            inter.leave("interactic.figura.avatar.reload",true);
        }
    }
}
