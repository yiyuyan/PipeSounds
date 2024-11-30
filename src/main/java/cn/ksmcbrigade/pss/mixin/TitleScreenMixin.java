package cn.ksmcbrigade.pss.mixin;

import cn.ksmcbrigade.pss.Config;
import cn.ksmcbrigade.pss.PipeSounds;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique
    private boolean pipeSounds$first = true;

    @Inject(method = "init",at = @At("HEAD"))
    public void init(CallbackInfo ci) throws IOException {
        if(pipeSounds$first){
            pipeSounds$first = false;
            if(Config.START_PIPE.get()){
                PipeSounds.play();
            }
        }
    }
}
