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

public class IronHealthAmulet extends Item {
    public static final UUID HEALTH_UUID = UUID.fromString("7119a370-1fc4-4cb3-b7e3-4747788f0785");

    public IronHealthAmulet() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.iron_health_amulet.tooltip"));
    }
}   
