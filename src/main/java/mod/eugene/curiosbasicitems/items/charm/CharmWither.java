package mod.eugene.curiosbasicitems.items.charm;

import java.util.List;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
public class CharmWither extends Item {

    public CharmWither() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.charm_wither.tooltip"));
    }

    public static boolean isWearingWitherCharm(LivingEntity livingEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(
            (itemStack) -> itemStack
                .getItem() instanceof CharmWither, livingEntity)
                .isPresent();
    }
}   
