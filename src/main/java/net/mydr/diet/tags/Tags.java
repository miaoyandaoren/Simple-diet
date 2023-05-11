package net.mydr.diet.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.mydr.diet.diet;

public class Tags {
    public static class Items{
        public static final TagKey<Item> FRUITS = Tags.Items.createTag("fruits");
        public static final TagKey<Item> GRAINS = Tags.Items.createTag("grains");
        public static final TagKey<Item> PROTEINS = Tags.Items.createTag("proteins");
        public static final TagKey<Item> SUGARS = Tags.Items.createTag("sugars");
        public static final TagKey<Item> VEGETABLES = Tags.Items.createTag("vegetables");
        private static TagKey<Item> createTag(String name){
            return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(diet.MOD_ID, name));
        }
    }
}
