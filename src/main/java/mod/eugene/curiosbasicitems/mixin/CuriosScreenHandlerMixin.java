package mod.eugene.curiosbasicitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.slot.Slot;
import top.theillusivec4.curios.common.inventory.screen.CuriosScreenHandler;
@Environment(EnvType.CLIENT)
@Mixin(CuriosScreenHandler.class)
public abstract class CuriosScreenHandlerMixin extends CraftingScreenHandler {

    protected final PlayerInventory playerInventory;
    public CuriosScreenHandlerMixin(int syncId, PlayerInventory playerInventory) {
        super(syncId, playerInventory);
        this.playerInventory = playerInventory;
    }

    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onConstructed(int syncId, PlayerInventory inventory, CallbackInfo info) {
        PlayerEntity owner = inventory.player;
        Slot leftBeltSlot = new Slot(inventory, CuriosBasicItems.LEFT_BELT_SLOT, 77, 44) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                if(BeltLeather.isWearingBelt(owner)) return BeltLeather.allowItem(stack.getItem());
                else return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                ItemStack itemStack = this.getStack();
                return !itemStack.isEmpty() && !playerEntity.isCreative() && EnchantmentHelper.hasBindingCurse(itemStack)
                        ? false
                        : super.canTakeItems(playerEntity);
            }
            
            @Override
            public boolean doDrawHoveringEffect() {
                if(BeltLeather.isWearingBelt(owner)) return true;
                else return false;
            }
        };

        Slot rightBeltSlot = new Slot(inventory, CuriosBasicItems.RIGHT_BELT_SLOT, 77, 26) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                if(BeltLeather.isWearingBelt(owner)) return BeltLeather.allowItem(stack.getItem());
                else return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                ItemStack itemStack = this.getStack();
                return !itemStack.isEmpty() && !playerEntity.isCreative() && EnchantmentHelper.hasBindingCurse(itemStack)
                        ? false
                        : super.canTakeItems(playerEntity);
            }

            @Override
            public boolean doDrawHoveringEffect() {
                if(BeltLeather.isWearingBelt(owner)) return true;
                else return false;
            }
        };
        
        // Slot potionBeltSlot = new Slot(inventory, 43, 77, 8) {
        //     @Override
        //     public int getMaxItemCount() {
        //         return 64;
        //     }
 
        //     @Override
        //     public boolean canInsert(ItemStack stack) {
        //         if(BeltLeather.isWearingBelt(owner)){
        //             if (stack.getItem() instanceof PotionItem
        //             || stack.getItem().isFood()
        //             ) return true;
        //             else return false;
        //         }
        //         else return false;
        //     }

        //     @Override
        //     public boolean canTakeItems(PlayerEntity playerEntity) {
        //         ItemStack itemStack = this.getStack();
        //         return !itemStack.isEmpty() && !playerEntity.isCreative() && EnchantmentHelper.hasBindingCurse(itemStack)
        //                 ? false
        //                 : super.canTakeItems(playerEntity);
        //     }

        //     @Override
        //     public boolean doDrawHoveringEffect() {
        //         if(BeltLeather.isWearingBelt(owner)) return true;
        //         else return false;
        //     }
        // };

        this.addSlot(leftBeltSlot);
        this.addSlot(rightBeltSlot);
        // this.addSlot(potionBeltSlot);

    }
}
