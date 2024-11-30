package cn.ksmcbrigade.pss.mixin;

import cn.ksmcbrigade.pss.Config;
import cn.ksmcbrigade.pss.PipeSounds;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "close",at = @At("HEAD"))
    public void close(CallbackInfo ci) throws IOException {
        if(Config.QUIT_PIPE.get()){
            PipeSounds.play();
        }
    }

    @Inject(method = "crash",at = @At("HEAD"))
    private static void crash(CallbackInfo ci) throws IOException {
        if(Config.CRASH_PIPE.get()){
            PipeSounds.play();
        }
    }
}
