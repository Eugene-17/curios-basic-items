package mod.eugene.curiosbasicitems.items.hat;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
public class HatKing extends Item {

    public static final UUID ARMOR_UUID = UUID.fromString("91c2c640-2f27-478b-a259-73608be04a8e");
    public static final UUID ATTACK_UUID = UUID.fromString("ab7092f3-353a-4278-a26a-3942a1b85d36");
    public static final UUID SPEED_UUID = UUID.fromString("dba8c680-6ce1-4c8d-bb2f-a740c5a8ff5d");

    public HatKing() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.hat_king.tooltip"));
        tooltip.add(new TranslatableText("item.curiosbasicitems.hat_king.tooltip2"));
    }
    
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static boolean isWearingKingCrown(LivingEntity livingEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(
            (itemStack) -> itemStack
                .getItem() instanceof HatKing, livingEntity)
                .isPresent();
    }
}   
