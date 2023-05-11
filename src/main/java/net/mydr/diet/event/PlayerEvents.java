package net.mydr.diet.event;

import io.github.fabricators_of_create.porting_lib.event.common.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.event.common.LivingEntityUseItemEvents;
import io.github.fabricators_of_create.porting_lib.event.common.PlayerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mydr.diet.tags.Tags;
import net.mydr.diet.world.test.IntComponent;

import static net.mydr.diet.world.test.MyComponents.*;

public class PlayerEvents {
    private static int prevHungerLevel = 20;
    public static void init() {
        LivingEntityUseItemEvents.LIVING_USE_ITEM_FINISH.register(PlayerEvents::eat_finish);
        PlayerTickEvents.END.register(PlayerEvents::hunger_down);
        LivingEntityEvents.ACTUALLY_HURT.register(PlayerEvents::diet_hurt);
    }

    private static ItemStack eat_finish(LivingEntity livingEntity, ItemStack itemStack, int i, ItemStack itemStack1) {
        if(livingEntity instanceof Player player){
            if(!player.getLevel().isClientSide()){
                int diet1 = DIET1.maybeGet(player).map(IntComponent::getValue).orElse(0);
                int diet2 = DIET2.maybeGet(player).map(IntComponent::getValue).orElse(0);
                int diet3 = DIET3.maybeGet(player).map(IntComponent::getValue).orElse(0);
                int diet4 = DIET4.maybeGet(player).map(IntComponent::getValue).orElse(0);
                int diet5 = DIET5.maybeGet(player).map(IntComponent::getValue).orElse(0);
                if(itemStack.getItem().isEdible()){
                    int nutrition = itemStack.getItem().getFoodProperties().getNutrition();
                    int count = 0;
                    nutrition = 2 + nutrition*3;
                    if(itemStack.is(Tags.Items.FRUITS)){
                        count++;
                    }
                    if(itemStack.is(Tags.Items.GRAINS)){
                        count++;
                    }
                    if(itemStack.is(Tags.Items.PROTEINS)){
                        count++;
                    }
                    if(itemStack.is(Tags.Items.VEGETABLES)){
                        count++;
                    }
                    if(itemStack.is(Tags.Items.SUGARS)){
                        count++;
                    }
                    if(count != 0){
                        nutrition = nutrition/count;
                    }
                    if(nutrition == 0){
                        nutrition = 2;
                    }
                    if(itemStack.is(Tags.Items.FRUITS)){
                        if(diet1 < 100){
                            if(diet1 + nutrition > 100){
                                diet1 = 100;
                            }else {
                                diet1 += nutrition;
                            }
//                            player.sendSystemMessage(Component.literal("diet1:"+nutrition));
                            player.getComponent(DIET1).setVitality(diet1);
                            DIET1.sync(player);
                        }
                    }
                    if (itemStack.is(Tags.Items.GRAINS)) {
                        if(diet2 < 100){
                            if(diet2 + nutrition > 100){
                                diet2 = 100;
                            }else {
                                diet2 += nutrition;
                            }
//                            player.sendSystemMessage(Component.literal("diet1:"+nutrition));
                            player.getComponent(DIET2).setVitality(diet2);
                            DIET2.sync(player);
                        }
                    }
                    if (itemStack.is(Tags.Items.PROTEINS)) {
                        if(diet3 < 100){
                            if(diet3 + nutrition > 100){
                                diet3 = 100;
                            }else {
                                diet3 += nutrition;
                            }
//                            player.sendSystemMessage(Component.literal("diet1:"+nutrition));
                            player.getComponent(DIET3).setVitality(diet3);
                            DIET3.sync(player);
                        }
                    }
                    if (itemStack.is(Tags.Items.VEGETABLES)) {
                        if(diet4 < 100){
                            if(diet4 + nutrition > 100){
                                diet4 = 100;
                            }else {
                                diet4 += nutrition;
                            }
//                            player.sendSystemMessage(Component.literal("diet1:"+nutrition));
                            player.getComponent(DIET4).setVitality(diet4);
                            DIET4.sync(player);
                        }
                    }
                    if (itemStack.is(Tags.Items.SUGARS)) {
                        if(diet5 < 100){
                            if(diet5 + nutrition > 100){
                                diet5 = 100;
                            }else {
                                diet5 += nutrition;
                            }
//                            player.sendSystemMessage(Component.literal("diet1:"+nutrition));
                            player.getComponent(DIET5).setVitality(diet5);
                            DIET5.sync(player);
                        }
                    }
                } else {
//                    player.sendSystemMessage(Component.literal("no"));
                }
            }
        }
        return itemStack;
    }

    private static void hunger_down(Player player) {
        if(!player.getLevel().isClientSide()){
            int diet1 = DIET1.maybeGet(player).map(IntComponent::getValue).orElse(0);
            int diet2 = DIET2.maybeGet(player).map(IntComponent::getValue).orElse(0);
            int diet3 = DIET3.maybeGet(player).map(IntComponent::getValue).orElse(0);
            int diet4 = DIET4.maybeGet(player).map(IntComponent::getValue).orElse(0);
            int diet5 = DIET5.maybeGet(player).map(IntComponent::getValue).orElse(0);
            int diet_level = diet_level(diet1,diet2,diet3,diet4,diet5);
            MobEffectInstance regenrationEffect = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2*20, 1,false,false);
            MobEffectInstance digEffect = new MobEffectInstance(MobEffects.DIG_SPEED, 2*20, 1,false,false);
            MobEffectInstance speedEffect = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2*20, 1,false,false);
            if(diet_level >= 3){
                player.addEffect(regenrationEffect);
                player.addEffect(digEffect);
                player.addEffect(speedEffect);
            } else if (diet_level == 2) {
                player.addEffect(digEffect);
                player.addEffect(speedEffect);
            } else if (diet_level == 1) {
                player.addEffect(speedEffect);
            }
            FoodData foodData = player.getFoodData();
            int currentHungerLevel = foodData.getFoodLevel();
            if (currentHungerLevel != prevHungerLevel) {
                if(currentHungerLevel < prevHungerLevel){
                    int det = 1;
                    if(currentHungerLevel > 16){
                        det = 1;
                    } else if (currentHungerLevel > 12) {
                        det = 2;
                    } else if (currentHungerLevel > 8) {
                        det = 3;
                    } else if (currentHungerLevel > 4) {
                        det = 4;
                    } else {
                        det = 5;
                    }
                    if(diet1 > 0 ){
                        if(diet1 - det < 0){
                            diet1 = 0;
                        }else{
                            diet1 -= det;
                        }
                        player.getComponent(DIET1).setVitality(diet1);
                        DIET1.sync(player);
                    }
                    if(diet2 > 0 ){
                        if(diet2 - det < 0){
                            diet2 = 0;
                        }else{
                            diet2 -= det;
                        }
                        player.getComponent(DIET2).setVitality(diet2);
                        DIET2.sync(player);
                    }
                    if(diet3 > 0 ){
                        if(diet3 - det < 0){
                            diet3 = 0;
                        }else{
                            diet3 -= det;
                        }
                        player.getComponent(DIET3).setVitality(diet3);
                        DIET3.sync(player);
                    }
                    if(diet4 > 0 ){
                        if(diet4 - det < 0){
                            diet4 = 0;
                        }else{
                            diet4 -= det;
                        }
                        player.getComponent(DIET4).setVitality(diet4);
                        DIET4.sync(player);
                    }
                    if(diet5 > 0 ){
                        if(diet5 - det < 0){
                            diet5 = 0;
                        }else{
                            diet5 -= det;
                        }
                        player.getComponent(DIET5).setVitality(diet5);
                        DIET5.sync(player);
                    }
                }
                prevHungerLevel = currentHungerLevel;
            }
        }
    }

    public static float diet_hurt(DamageSource source, LivingEntity living, float amount) {
        Entity trueSource = source.getEntity();
        // fire react and chill aura
        if (source instanceof EntityDamageSource && trueSource != null && amount > 0) {
            int diet1_i = DIET1.maybeGet(trueSource).map(IntComponent::getValue).orElse(0);
            int diet2_i = DIET2.maybeGet(trueSource).map(IntComponent::getValue).orElse(0);
            int diet3_i = DIET3.maybeGet(trueSource).map(IntComponent::getValue).orElse(0);
            int diet4_i = DIET4.maybeGet(trueSource).map(IntComponent::getValue).orElse(0);
            int diet5_i = DIET5.maybeGet(trueSource).map(IntComponent::getValue).orElse(0);
            int diet1_o = DIET1.maybeGet(living).map(IntComponent::getValue).orElse(0);
            int diet2_o = DIET2.maybeGet(living).map(IntComponent::getValue).orElse(0);
            int diet3_o = DIET3.maybeGet(living).map(IntComponent::getValue).orElse(0);
            int diet4_o = DIET4.maybeGet(living).map(IntComponent::getValue).orElse(0);
            int diet5_o = DIET5.maybeGet(living).map(IntComponent::getValue).orElse(0);
            int diet_level_i = diet_level(diet1_i,diet2_i,diet3_i,diet4_i,diet5_i);
            int diet_level_o = diet_level(diet1_o,diet2_o,diet3_o,diet4_o,diet5_o);
            if(diet_level_i >= 4){
                amount = (float) (amount * 1.20);
            }
            if(diet_level_o >= 5){
                amount = (float) (amount * 0.80);
            }
        }
        return amount;
    }

    public static int diet_level(int diet1,int diet2,int diet3,int diet4,int diet5){
        int level5_goal = 90;
        int level4_goal = 80;
        int level3_goal = 60;
        int level2_goal = 40;
        int level1_goal = 20;
        if(diet1 >= level5_goal && diet2 >= level5_goal && diet3 >= level5_goal && diet4 >= level5_goal && diet5 >= level5_goal){
            return 5;
        } else if (diet1 >= level4_goal && diet2 >= level4_goal && diet3 >= level4_goal && diet4 >= level4_goal && diet5 >= level4_goal) {
            return 4;
        } else if (diet1 >= level3_goal && diet2 >= level3_goal && diet3 >= level3_goal && diet4 >= level3_goal && diet5 >= level3_goal) {
            return 3;
        } else if (diet1 >= level2_goal && diet2 >= level2_goal && diet3 >= level2_goal && diet4 >= level2_goal && diet5 >= level2_goal) {
            return 2;
        } else if (diet1 >= level1_goal && diet2 >= level1_goal && diet3 >= level1_goal && diet4 >= level1_goal && diet5 >= level1_goal) {
            return 1;
        } else {
            return 0;
        }
    }

}
