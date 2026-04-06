package mett.palemannie.squakeported.event;

import mett.palemannie.squakeported.ModConfig;
import mett.palemannie.squakeported.SquakePorted;
import mett.palemannie.squakeported.util.KeyBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = SquakePorted.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.SQUAKE_TOGGLE_KEY.consumeClick()) {
                ModConfig.setEnabled(!ModConfig.isEnabled());
                var message = MutableComponent.create(new TranslatableContents("squake.key.toggle.message", null, new Object[0]));
                var onOrOff = MutableComponent.create(new TranslatableContents(ModConfig.isEnabled() ? "squake.key.toggle.enabled" : "squake.key.toggle.disabled", null, new Object[0])).withStyle(ModConfig.isEnabled() ? ChatFormatting.GREEN : ChatFormatting.DARK_RED);
                var t1 = MutableComponent.create(new PlainTextContents.LiteralContents("["));
                var t2 = MutableComponent.create(new PlainTextContents.LiteralContents("Squake")).withStyle(ChatFormatting.GOLD);
                var t3 = MutableComponent.create(new PlainTextContents.LiteralContents("] "));
                Minecraft.getInstance().gui.getChat().addClientSystemMessage(t1.append(t2).append(t3).append(message).append(onOrOff).append(t3));
            }
        }

        @Mod.EventBusSubscriber(modid = SquakePorted.MODID, value = Dist.CLIENT)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event){
                event.register(KeyBinding.SQUAKE_TOGGLE_KEY);
            }
        }
    }
}
