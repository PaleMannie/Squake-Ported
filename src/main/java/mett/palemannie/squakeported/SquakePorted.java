package mett.palemannie.squakeported;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SquakePorted.MODID)
public class SquakePorted
{
    public static final String MODID = "squakeported";
    public static final String MODNAME = "SquakePorted";
    public static SquakePorted instance;
    public static final Logger LOGGER = LogManager.getLogger(MODNAME);

    public SquakePorted(FMLJavaModLoadingContext context){

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