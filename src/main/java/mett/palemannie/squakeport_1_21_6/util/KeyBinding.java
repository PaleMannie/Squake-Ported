package mett.palemannie.squakeport_1_21_6.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_SQUAKE = "key.category.squake";
    public static final String KEY_TOGGLE_SQUAKEMODE = "squake.key.toggle";

    public static final KeyMapping SQUAKE_TOGGLE_KEY = new KeyMapping(KEY_TOGGLE_SQUAKEMODE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, KEY_CATEGORY_SQUAKE);
    }