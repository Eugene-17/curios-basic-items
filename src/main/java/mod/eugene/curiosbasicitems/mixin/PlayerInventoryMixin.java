package mod.eugene.curiosbasicitems.mixin;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.collection.DefaultedList;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory {
    @Shadow
    @Final
    @Mutable
    private List<DefaultedList<ItemStack>> combinedInventory;
    @Shadow
    @Final
    public DefaultedList<ItemStack> main;
    @Shadow
    @Final
    public DefaultedList<ItemStack> armor;
    @Shadow
    @Final
    public DefaultedList<ItemStack> offHand;

    private DefaultedList<ItemStack> leftBeltSlot;
    private DefaultedList<ItemStack> rightBeltSlot;
    private DefaultedList<ItemStack> potionBeltSlot;

    public PlayerInventoryMixin(PlayerEntity player) {
    }

    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onConstructed(PlayerEntity playerEntity, CallbackInfo info) {
        this.leftBeltSlot = DefaultedList.ofSize(1, ItemStack.EMPTY);
        this.rightBeltSlot = DefaultedList.ofSize(1, ItemStack.EMPTY);
        this.potionBeltSlot = DefaultedList.ofSize(1, ItemStack.EMPTY);

        this.combinedInventory = new ArrayList<>(combinedInventory);
        this.combinedInventory.add(leftBeltSlot);
        this.combinedInventory.add(rightBeltSlot);
        this.combinedInventory.add(potionBeltSlot);

        this.combinedInventory = ImmutableList.copyOf(this.combinedInventory);
    }

    @Inject(method = "serialize", at = @At("TAIL"))
    public void serializeMixin(ListTag tag, CallbackInfoReturnable<ListTag> info) {
        if (!this.leftBeltSlot.get(0).isEmpty()) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putByte("Slot", (byte) (110));
            this.leftBeltSlot.get(0).toTag(compoundTag);
            tag.add(compoundTag);
        }
        if (!this.rightBeltSlot.get(0).isEmpty()) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putByte("Slot", (byte) (111));
            this.rightBeltSlot.get(0).toTag(compoundTag);
            tag.add(compoundTag);
        }
        if (!this.potionBeltSlot.get(0).isEmpty()) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putByte("Slot", (byte) (112));
            this.potionBeltSlot.get(0).toTag(compoundTag);
            tag.add(compoundTag);
        }
    }

    @Inject(method = "deserialize", at = @At("TAIL"))
    public void deserializeMixin(ListTag tag, CallbackInfo info) {
        this.leftBeltSlot.clear();
        this.rightBeltSlot.clear();
        for (int i = 0; i < tag.size(); ++i) {
            CompoundTag compoundTag = tag.getCompound(i);
            int slot = compoundTag.getByte("Slot") & 255;
            ItemStack itemStack = ItemStack.fromTag(compoundTag);
            if (!itemStack.isEmpty()) {
                if (slot >= 110 && slot < this.leftBeltSlot.size() + 110) this.leftBeltSlot.set(slot - 110, itemStack);
                else if (slot >= 111 && slot < this.rightBeltSlot.size() + 111) this.rightBeltSlot.set(slot - 111, itemStack);
                else if (slot >= 112 && slot < this.potionBeltSlot.size() + 112) this.potionBeltSlot.set(slot - 112, itemStack);
            }
        }
    }

    @Inject(method = "size", at = @At("HEAD"), cancellable = true)
    public void sizeMixin(CallbackInfoReturnable<Integer> info) {
        int size = 0;
        for (DefaultedList<ItemStack> list : combinedInventory) {
            size += list.size();
        }
        info.setReturnValue(size);
    }

    @Inject(method = "isEmpty", at = @At("HEAD"), cancellable = true)
        public void isEmptyMixin(CallbackInfoReturnable<Boolean> info) {
        if (!this.leftBeltSlot.isEmpty() || !this.rightBeltSlot.isEmpty() || !this.potionBeltSlot.isEmpty()) {
            info.setReturnValue(false);
        }
    }
}
