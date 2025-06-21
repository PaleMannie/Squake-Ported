package mett.palemannie.squakeport_1_21_6;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Squakeport_1_21_6.MODID)
public class Squakeport_1_21_6
{
    public static final String MODID = "squakeport_1_21_6";
    public static final String MODNAME = "Squakeport_1_21_6";
    public static Squakeport_1_21_6 instance;
    public static final Logger LOGGER = LogManager.getLogger(MODNAME);

    public Squakeport_1_21_6(FMLJavaModLoadingContext context){

        var modBusGroup = context.getModBusGroup();

        context.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.commonSpec);
    }

    /// not needed anymore at all
    /*@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent e)
    {
        /// No longer needed with Eventbus 7 (since 1.21.6)
        //ToggleKeyHandler.setup();

        // no config gui screen here.
        // ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((mc, parent) -> parent));
    }*/
}