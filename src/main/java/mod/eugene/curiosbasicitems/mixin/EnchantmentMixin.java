package mod.eugene.curiosbasicitems.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import mod.eugene.curiosbasicitems.items.bracelet.Bracelet;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(
            method = "isAcceptableItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void BraceletProtection(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if((Object) this instanceof ProtectionEnchantment && stack.getItem() instanceof Bracelet) {
            cir.setReturnValue(true);
        }
    }
}
