package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import mod.eugene.curiosbasicitems.items.back.BackItem;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;

public class NetworkPackets {
    public static final Identifier USE_BACK_ITEM = new Identifier(CuriosBasicItems.MODID, "USE_BACK_ITEM");
    public static final Identifier USE_BELT_ITEM = new Identifier(CuriosBasicItems.MODID, "switch_item");
    public static final Identifier VISIBILITY_UPDATE_PACKET = new Identifier(CuriosBasicItems.MODID, "visibility_update");

    public static void useBeltItemPacketInit() {
        ServerSidePacketRegistry.INSTANCE.register(USE_BELT_ITEM, (context, buffer) -> {
            PlayerEntity player = context.getPlayer();
            int slot = buffer.readInt();
            boolean force = false;
            if (slot > 1000){
                slot -=1000;
                force = true;
            }
            int selectedSlot = player.inventory.selectedSlot;
            ItemStack selectedStack = (ItemStack) player.inventory.getStack(selectedSlot);
            ItemStack slotStack = (ItemStack) player.inventory.getStack(slot);
            if(BeltLeather.isWearingBelt(player)){
                if(force){
                    player.inventory.setStack(slot, selectedStack);
                    player.inventory.setStack(selectedSlot, slotStack);
                    player.inventory.markDirty();
                } else if (BeltLeather.allowInstantEat(player)){
                    if(!slotStack.isEmpty()){
                        if (player.canConsume(slotStack.getItem().getFoodComponent().isAlwaysEdible())) {
                            player.eatFood(player.getEntityWorld(), slotStack);
                            return;
                        }
                    }
                } else if (BeltLeather.allowItem(selectedStack.getItem())) {
                    player.inventory.setStack(slot, selectedStack);
                    player.inventory.setStack(selectedSlot, slotStack);
                    player.inventory.markDirty();
                    if (selectedStack.isEmpty() && !slotStack.isEmpty()) {
                        if (slotStack.getItem() instanceof SwordItem) {
                            player.world.playSound(null, player.getBlockPos(), CuriosBasicItems.SHEATH_SWORD_EVENT,
                                SoundCategory.PLAYERS, 1.0F, 1.0F);
                        } else {
                            player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
                                SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                        } else if (selectedStack.getItem() instanceof SwordItem) {
                            player.world.playSound(null, player.getBlockPos(), CuriosBasicItems.PACK_UP_ITEM_EVENT, SoundCategory.PLAYERS,
                            1.0F, 1.0F);
                        } else if (!selectedStack.isEmpty()) {
                            player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
                            SoundCategory.PLAYERS, 1.0F, 1.0F);
                    }   
                }
            }
        });
    }

    public static void visibilityUpdatePacketinit() {
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
    public static void useBackItemPacketInit(){
        //Curios crafting table event
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
}
