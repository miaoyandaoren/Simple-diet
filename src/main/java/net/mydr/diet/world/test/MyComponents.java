package net.mydr.diet.world.test;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.mydr.diet.diet;
import org.intellij.lang.annotations.Identifier;

public class MyComponents implements EntityComponentInitializer, WorldComponentInitializer{


    public static final ComponentKey<IntComponent> DIET1 =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("diet:diet1"), IntComponent.class);
    public static final ComponentKey<IntComponent> DIET2 =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("diet:diet2"), IntComponent.class);
    public static final ComponentKey<IntComponent> DIET3 =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("diet:diet3"), IntComponent.class);
    public static final ComponentKey<IntComponent> DIET4 =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("diet:diet4"), IntComponent.class);
    public static final ComponentKey<IntComponent> DIET5 =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("diet:diet5"), IntComponent.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        // Add the component to every PlayerEntity instance, and copy it on respawn with keepInventory
        registry.registerForPlayers(DIET1, player -> new SetIntComponent(0), RespawnCopyStrategy.INVENTORY);
        registry.registerForPlayers(DIET2, player -> new SetIntComponent(0), RespawnCopyStrategy.INVENTORY);
        registry.registerForPlayers(DIET3, player -> new SetIntComponent(0), RespawnCopyStrategy.INVENTORY);
        registry.registerForPlayers(DIET4, player -> new SetIntComponent(0), RespawnCopyStrategy.INVENTORY);
        registry.registerForPlayers(DIET5, player -> new SetIntComponent(0), RespawnCopyStrategy.INVENTORY);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        // Add the component to every World instance
//        registry.register(DIET1, world -> new SetIntComponent(0));
    }
}
