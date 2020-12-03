package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.OnAStickItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShearsItem;
// import net.minecraft.item.TridentItem;
import net.minecraft.item.PotionItem;

public class NetworkPackets {
    public static final Identifier ACCESS_BACKSLOT = new Identifier(CuriosBasicItems.MODID, "access_backslot");
    public static final Identifier USE_PACKET = new Identifier(CuriosBasicItems.MODID, "switch_item");
    // public static final Identifier EAT_PACKET = new Identifier(CuriosBasicItems.MODID, "eat_item");
    public static final Identifier VISIBILITY_UPDATE_PACKET = new Identifier(CuriosBasicItems.MODID, "visibility_update");

    public static void usePacketInit() {
        ServerSidePacketRegistry.INSTANCE.register(USE_PACKET, (context, buffer) -> {
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
                }
                if (isItemAllowed(selectedStack, slot)) {
                    if(BeltLeather.allowInstantEat(player)){
                        if(!slotStack.isEmpty()){
                            if (player.canConsume(slotStack.getItem().getFoodComponent().isAlwaysEdible())) {
                                player.eatFood(player.getEntityWorld(), slotStack);
                                return;
                            }
                        }
                    }
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

    // public static void eatPacketinit() {
    //     ServerSidePacketRegistry.INSTANCE.register(EAT_PACKET, (context, buffer) -> {
    //         PlayerEntity player = context.getPlayer();
    //         int slot = buffer.readInt();
    //         ItemStack itemStack = player.inventory.getStack(slot);
    //         if(BeltLeather.allowInstantEat(player)){
    //             if(!itemStack.isEmpty()){
    //                 if (player.canConsume(itemStack.getItem().getFoodComponent().isAlwaysEdible())) {
    //                     player.eatFood(player.getEntityWorld(), itemStack);
    //                 }
    //             }
    //         }
    //     });
    // }

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


    private static boolean isItemAllowed(ItemStack stack, int slot) {
        if (stack.isEmpty()) return true;

        if ((slot == 41 || slot == 42) 
        && (stack.getItem() instanceof PotionItem
        || stack.getItem().isFood()
        || stack.getItem() instanceof ToolItem 
        || stack.getItem() instanceof RangedWeaponItem
        || stack.getItem() instanceof FishingRodItem 
        || stack.getItem() instanceof OnAStickItem
        // || stack.getItem() instanceof TridentItem 
        || stack.getItem() instanceof FlintAndSteelItem
        || stack.getItem() instanceof ShearsItem)
        ) return true;
        
        // if (slot == 43 
        // && (stack.getItem() instanceof PotionItem
        // || stack.getItem().isFood()
        // )) return true;
        return false;
    }
}
