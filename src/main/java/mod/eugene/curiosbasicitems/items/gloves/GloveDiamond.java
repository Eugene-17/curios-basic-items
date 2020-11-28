package mod.eugene.curiosbasicitems.items.gloves;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
public class GloveDiamond extends Item {
    public static final UUID ARMOR_UUID = UUID.fromString("f4ca4796-7bd2-4e8d-bea5-a9bcef225f2f");

    public GloveDiamond() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.gauntlet_common.tooltip"));
    }
}   
