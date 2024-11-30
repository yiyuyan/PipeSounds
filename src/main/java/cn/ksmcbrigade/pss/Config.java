package cn.ksmcbrigade.pss;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue START_PIPE = BUILDER.define("start",false);
    public static final ForgeConfigSpec.BooleanValue QUIT_PIPE = BUILDER.define("quit",false);
    public static final ForgeConfigSpec.BooleanValue CRASH_PIPE = BUILDER.define("crash",true);

    public static final ForgeConfigSpec.BooleanValue JOIN_PIPE = BUILDER.define("join",false);
    public static final ForgeConfigSpec.BooleanValue LEAVE_PIPE = BUILDER.define("leave_world",false);

    public static final ForgeConfigSpec.BooleanValue DISCONNECTED_PIPE = BUILDER.define("disconnect",true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
