package net.mydr.diet;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.mydr.diet.event.DietKey;
import net.mydr.diet.gui.DietScreen;

public class DietClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DietKey.registerKeyInputs();
    }
}
