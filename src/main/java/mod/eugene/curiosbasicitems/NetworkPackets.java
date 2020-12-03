package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import mod.eugene.curiosbasicitems.items.back.BackItem;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;

public class NetworkPackets {
    public static final Identifier USE_BACK_ITEM = new Identifier(CuriosBasicItems.MODID, "use_back_item");
    public static final Identifier SWITCH_LEFT_BELT_ITEM = new Identifier(CuriosBasicItems.MODID, "switch_left_belt_item");
    public static final Identifier SWITCH_RIGHT_BELT_ITEM = new Identifier(CuriosBasicItems.MODID, "force_switch_right_belt_item");
    public static final Identifier INSTANT_EAT_PACKET = new Identifier(CuriosBasicItems.MODID, "instant_eat_belt_item");
    public static final Identifier VISIBILITY_UPDATE_PACKET = new Identifier(CuriosBasicItems.MODID, "visibility_update");

    public static void serverRegister(){
        ServerSidePacketRegistry.INSTANCE.register(INSTANT_EAT_PACKET, (context, buffer) -> {
            PlayerEntity player = context.getPlayer();
            int slot = buffer.readInt();
            ItemStack slotStack = (ItemStack) player.inventory.getStack(slot);
            if (BeltLeather.allowInstantEat(player)){
                if(!slotStack.isEmpty()){
                    if (player.canConsume(slotStack.getItem().getFoodComponent().isAlwaysEdible())) {
                        player.eatFood(player.getEntityWorld(), slotStack);
                        return;
                    }
                }
            }
        });

        ServerSidePacketRegistry.INSTANCE.register(SWITCH_LEFT_BELT_ITEM, (context, buffer) -> {
            PlayerEntity player = context.getPlayer();
            int slot = CuriosBasicItems.LEFT_BELT_SLOT;
            int selectedSlot = player.inventory.selectedSlot;
            ItemStack targetStack = (ItemStack) player.inventory.getStack(selectedSlot);
            ItemStack slotStack = (ItemStack) player.inventory.getStack(slot);
            if(BeltLeather.isWearingBelt(player)){
                if (BeltLeather.allowItem(targetStack.getItem()) || targetStack.isEmpty()) {
                    player.inventory.setStack(slot, targetStack);
                    player.inventory.setStack(selectedSlot, slotStack);
                    player.inventory.markDirty();
                    player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
        });

        ServerSidePacketRegistry.INSTANCE.register(SWITCH_RIGHT_BELT_ITEM, (context, buffer) -> {
            PlayerEntity player = context.getPlayer();
            int slot = buffer.readInt();
            ItemStack targetStack = (ItemStack) player.inventory.getStack(slot);
            ItemStack slotStack = (ItemStack) player.inventory.getStack(CuriosBasicItems.RIGHT_BELT_SLOT);
            if(BeltLeather.isWearingBelt(player)){
                player.inventory.setStack(CuriosBasicItems.RIGHT_BELT_SLOT, targetStack);
                player.inventory.setStack(slot, slotStack);
                player.inventory.markDirty();
                player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        });

        ServerSidePacketRegistry.INSTANCE.register(NetworkPackets.USE_BACK_ITEM,
        (((packetContext, packetByteBuf) -> packetContext.getTaskQueue().execute(() -> {
			PlayerEntity playerEntity = packetContext.getPlayer();
			if (playerEntity != null) {
			  	CuriosApi.getCuriosHelper().findEquippedCurio((itemStack) -> itemStack
					.getItem() instanceof BackItem, playerEntity)
					.ifPresent(found -> {
						((BackItem) found.right.getItem()).useBackItem(playerEntity.getEntityWorld(), playerEntity);
					});
			}
        }))));
    }

    public static void clientRegister(){
        ClientSidePacketRegistry.INSTANCE.register(VISIBILITY_UPDATE_PACKET, (context, buffer) -> {
            int[] bufferArray = buffer.readIntArray();
            int entityId = bufferArray[0];
            int slot = bufferArray[1];
            ItemStack itemStack = buffer.readItemStack();
            context.getTaskQueue().execute(() -> {
                if (context.getPlayer().world.getEntityById(entityId) != null) {
                    PlayerEntity player = (PlayerEntity) context.getPlayer().world.getEntityById(entityId);
                    player.inventory.setStack(slot, itemStack.copy());
                }
                CuriosBasicItems.LOGGER.info("update visual");
            });
        });
    }
}
