package mod.eugene.curiosbasicitems.items.belt;

import java.util.List;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.OnAStickItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.entity.player.PlayerEntity;
public class BeltLeather extends Item {

    public BeltLeather() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.belt_leather.tooltip"));
    }

    public static boolean isWearingBelt(PlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(
            (itemStack) -> itemStack
                .getItem() instanceof BeltLeather, playerEntity)
                .isPresent();
    }

    public static boolean allowInstantEat(PlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(
            (itemStack) -> itemStack
                .getItem() instanceof BeltGluttony || itemStack
                .getItem() instanceof BeltChampion, playerEntity)
                .isPresent();
    }

    public static boolean allowItem(Item item){
        if (item instanceof PotionItem
        || item.isFood()
        || item instanceof ToolItem 
        || item instanceof FishingRodItem 
        || item instanceof OnAStickItem
        || item instanceof FlintAndSteelItem
        || item instanceof ShearsItem
        ) return true;
        else return false;
    }
}   
