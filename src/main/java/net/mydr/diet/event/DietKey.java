package net.mydr.diet.event;

import com.google.common.collect.Lists;
import com.ibm.icu.impl.Relation;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.mydr.diet.gui.DietScreen;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class DietKey {
    private static boolean keyWasPressed = false;
    public static KeyMapping keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.mydr.diet",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "key.category.mydr.diet"
    ));
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(keyBinding.isDown()){
                if(keyWasPressed){
                }else{
                    Minecraft.getInstance().setScreen(new DietScreen());
                    keyWasPressed = true;
                }
            }else{
                keyWasPressed = false;
            }
        });
    }
}
