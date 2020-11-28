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
public class GloveNetherite extends Item {
    public static final UUID ARMOR_UUID = UUID.fromString("76e89689-9a33-40db-8bb3-c0cd3e5770aa");

    public GloveNetherite() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.gauntlet_common.tooltip"));
    }
}   
