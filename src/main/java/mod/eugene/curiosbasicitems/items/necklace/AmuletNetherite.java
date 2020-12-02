package mod.eugene.curiosbasicitems.items.necklace;

import java.util.List;
import java.util.UUID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class AmuletNetherite extends Item {
    public static final UUID HEALTH_UUID = UUID.fromString("66bbc8c0-def8-404d-9687-eb39a6be5927");

    public AmuletNetherite() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.amulet_netherite.tooltip"));
    }
}   
