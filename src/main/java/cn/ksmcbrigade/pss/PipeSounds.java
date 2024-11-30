package cn.ksmcbrigade.pss;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Mod(PipeSounds.MOD_ID)
@Mod.EventBusSubscriber
public class PipeSounds {
    
    public static final String MOD_ID = "pss";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static boolean init = false;
    private static final File configWav = new File("config/pipe.wav");

    public PipeSounds() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT,Config.SPEC);

        LOGGER.info("PipeSounds mod loaded.");
    }

    public static void init() throws IOException {
        if(!configWav.exists() || !init){
            FileUtils.writeByteArrayToFile(configWav, IOUtils.toByteArray(Objects.requireNonNull(PipeSounds.class.getResourceAsStream("/sound.wav"))));
        }
        init = true;
    }

    public static void play() throws IOException {
        init();
        try {
            long start = System.currentTimeMillis();

            File file = new File("config/pipe.wav");
            if(file.exists()){
                AudioInputStream as;
                as = AudioSystem.getAudioInputStream(file);
                AudioFormat format = as.getFormat();
                SourceDataLine sdl;
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                sdl = (SourceDataLine) AudioSystem.getLine(info);
                sdl.open(format);
                sdl.start();
                int nBytesRead = 0;
                byte[] abData = new byte[512];
                while (nBytesRead != -1) {
                    nBytesRead = as.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        sdl.write(abData, 0, nBytesRead);
                }
                sdl.drain();
                sdl.close();
            }

            LOGGER.info("Sound use: {} s", (System.currentTimeMillis() - start) / 1000);
        }
        catch (Exception e){
            LOGGER.error("Failed to play the pipe sound.");
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void join(EntityJoinLevelEvent event) throws IOException {
        Minecraft MC = Minecraft.getInstance();
        if(Config.JOIN_PIPE.get() && MC.player!=null && MC.player.equals(event.getEntity())){
            play();
        }
    }

    @SubscribeEvent
    public static void leave(EntityLeaveLevelEvent event) throws IOException {
        Minecraft MC = Minecraft.getInstance();
        if(Config.LEAVE_PIPE.get() && MC.player!=null && MC.player.equals(event.getEntity())){
            play();
        }
    }
}
