package net.mydr.diet;

import net.fabricmc.api.ModInitializer;
import net.mydr.diet.event.PlayerEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class diet implements ModInitializer {
	public static final String MOD_ID = "diet";

	public static final String MODEL_DIR = "textures/model/";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final diet INSTANCE = new diet();

	@Override
	public void onInitialize() {
		PlayerEvents.init();

//		UseItemCallback.EVENT.register(((player, world, hand) -> {
//			ItemStack itemStack = player.getItemInHand(hand);
//			Item item = itemStack.getItem();
//			if(!world.isClientSide()){
//				if(item.isEdible()){
//					player.sendSystemMessage(Component.literal("ok2"));
//				}
//				if(itemStack.is(Tags.Items.GRAINS)){
//					player.sendSystemMessage(Component.literal("ok"));
//				}
//			}
//			return InteractionResultHolder.pass(itemStack);
//		}));
	}
}
