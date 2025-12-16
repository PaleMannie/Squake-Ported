package mett.palemannie.squakeported.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_SQUAKE = "squake";
    public static final String KEY_TOGGLE_SQUAKEMODE = "squake.key.toggle";

    public static final KeyMapping SQUAKE_TOGGLE_KEY = new KeyMapping(KEY_TOGGLE_SQUAKEMODE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, KeyMapping.Category.register(Identifier.parse(KEY_CATEGORY_SQUAKE)), 0);
    }