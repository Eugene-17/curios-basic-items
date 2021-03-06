package mod.eugene.curiosbasicitems.items.belt;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class BeltChampion extends BeltLeather {
    public static final UUID ATTACK_UUID = UUID.fromString("12a19318-acb7-4da9-9a71-dcae09b90c8a");
    public static final UUID ARMOR_UUID = UUID.fromString("87d1f4d0-f155-490e-a340-8971046db3b4");

    public BeltChampion() {
        super();
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.allow_instant_eat.tooltip"));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}   
