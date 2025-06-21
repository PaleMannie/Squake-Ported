package mett.palemannie.squakeport_1_21_6.event;

import mett.palemannie.squakeport_1_21_6.ModConfig;
import mett.palemannie.squakeport_1_21_6.Squakeport_1_21_6;
import mett.palemannie.squakeport_1_21_6.util.KeyBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Squakeport_1_21_6.MODID, value = Dist.CLIENT)
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
                Minecraft.getInstance().gui.getChat().addMessage(t1.append(t2).append(t3).append(message).append(onOrOff).append(t3));
            }
        }

        @Mod.EventBusSubscriber(modid = Squakeport_1_21_6.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event){
                event.register(KeyBinding.SQUAKE_TOGGLE_KEY);
            }
        }
    }
}
