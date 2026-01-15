package com.kssjw.railwayswitchcontroller.client.key;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.KeyBinding.Category;
import net.minecraft.util.Identifier;

public class Keyboard {

    public static KeyBinding switchKey;

    public static void init() {
        
        // 注册快捷键
        switchKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.railway-switch-controller.switch_rail",   // 翻译键
            GLFW.GLFW_KEY_G,    // 默认按键
            Category.create(Identifier.of("category.railway-switch-controller"))
        ));
    }
}