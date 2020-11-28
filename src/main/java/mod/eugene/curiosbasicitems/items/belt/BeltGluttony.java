package mod.eugene.curiosbasicitems.items.belt;

import java.util.List;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class BeltGluttony extends BeltLeather {

    public BeltGluttony() {
        super();
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.allow_instant_eat.tooltip"));
        tooltip.add(new TranslatableText("item.curiosbasicitems.belt_gluttony.tooltip"));
    }
}   
