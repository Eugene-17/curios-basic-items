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
    public static final Identifier FORCE_SWITCH_BELT_ITEM = new Identifier(CuriosBasicItems.MODID, "force_switch_belt_item");
    public static final Identifier SWITCH_BELT_ITEM = new Identifier(CuriosBasicItems.MODID, "switch_belt_item");
    public static final Identifier INSTANT_EAT_PACKET = new Identifier(CuriosBasicItems.MODID, "instant_eat_belt_item");
    public static final Identifier VISIBILITY_UPDATE_PACKET = new Identifier(CuriosBasicItems.MODID, "visibility_update");

    public static void instantEatPacketInit(){
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
    }

    public static void switchBeltItemPacketInit() {
        ServerSidePacketRegistry.INSTANCE.register(SWITCH_BELT_ITEM, (context, buffer) -> {
            PlayerEntity player = context.getPlayer();
            int slot = buffer.readInt();
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
    }

    public static void forceSwitchBeltItemPacketInit() {
        ServerSidePacketRegistry.INSTANCE.register(FORCE_SWITCH_BELT_ITEM, (context, buffer) -> {
            PlayerEntity player = context.getPlayer();
            int slot = buffer.readInt();
            int selectedSlot = player.inventory.selectedSlot;
            ItemStack targetStack = (ItemStack) player.inventory.getStack(selectedSlot);
            ItemStack slotStack = (ItemStack) player.inventory.getStack(slot);
            if(BeltLeather.isWearingBelt(player)){
                player.inventory.setStack(slot, targetStack);
                player.inventory.setStack(selectedSlot, slotStack);
                player.inventory.markDirty();
                player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
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
