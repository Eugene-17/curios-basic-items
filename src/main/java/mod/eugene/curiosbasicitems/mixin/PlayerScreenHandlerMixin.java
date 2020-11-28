package mod.eugene.curiosbasicitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.spongepowered.asm.mixin.injection.At;

import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.OnAStickItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.PotionItem;
import net.minecraft.enchantment.EnchantmentHelper;

@Mixin(PlayerScreenHandler.class)
public abstract class PlayerScreenHandlerMixin extends AbstractRecipeScreenHandler<CraftingInventory> {

    public PlayerScreenHandlerMixin(ScreenHandlerType<PlayerScreenHandler> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onConstructed(PlayerInventory inventory, boolean onServer, PlayerEntity owner, CallbackInfo info) {
        Slot leftBeltSlot = new Slot(inventory, 41, 77, 44) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                if(BeltLeather.isWearingBelt(owner)){
                    if (stack.getItem() instanceof ToolItem 
                    || stack.getItem() instanceof RangedWeaponItem
                    || stack.getItem() instanceof FishingRodItem 
                    || stack.getItem() instanceof OnAStickItem
                    || stack.getItem() instanceof TridentItem 
                    || stack.getItem() instanceof FlintAndSteelItem
                    || stack.getItem() instanceof ShearsItem
                    ) return true;
                    else return false;
                }
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

        Slot rightBeltSlot = new Slot(inventory, 42, 77, 26) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                if(BeltLeather.isWearingBelt(owner)){
                    if (stack.getItem() instanceof ToolItem 
                    || stack.getItem() instanceof RangedWeaponItem
                    || stack.getItem() instanceof FishingRodItem 
                    || stack.getItem() instanceof OnAStickItem
                    || stack.getItem() instanceof TridentItem 
                    || stack.getItem() instanceof FlintAndSteelItem
                    || stack.getItem() instanceof ShearsItem
                    ) return true;
                    else return false;
                }
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
        
        Slot potionBeltSlot = new Slot(inventory, 43, 77, 8) {
            @Override
            public int getMaxItemCount() {
                return 64;
            }
 
            @Override
            public boolean canInsert(ItemStack stack) {
                if(BeltLeather.isWearingBelt(owner)){
                    if (stack.getItem() instanceof PotionItem
                    || stack.getItem().isFood()
                    ) return true;
                    else return false;
                }
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

        this.addSlot(leftBeltSlot);
        this.addSlot(rightBeltSlot);
        this.addSlot(potionBeltSlot);

    }
}