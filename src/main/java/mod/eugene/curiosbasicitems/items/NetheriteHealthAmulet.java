package mod.eugene.curiosbasicitems.items;

import java.util.List;
import java.util.UUID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class NetheriteHealthAmulet extends Item {
    public static final UUID HEALTH_UUID = UUID.fromString("66bbc8c0-def8-404d-9687-eb39a6be5927");

    public NetheriteHealthAmulet() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(2000));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.netherite_health_amulet.tooltip"));
    }
}   
