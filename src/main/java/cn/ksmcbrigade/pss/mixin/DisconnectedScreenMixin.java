package cn.ksmcbrigade.pss.mixin;

import cn.ksmcbrigade.pss.Config;
import cn.ksmcbrigade.pss.PipeSounds;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin {

    @Inject(method = "init",at = @At("HEAD"))
    public void init(CallbackInfo ci) throws IOException {
        if(Config.DISCONNECTED_PIPE.get()){
            PipeSounds.play();
        }
    }
}
