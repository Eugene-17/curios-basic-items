package mod.eugene.curiosbasicitems.items.ring;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
public class RingCombat extends Item {
    public static final UUID ATTACK_UUID = UUID.fromString("c7223702-d74f-4e8f-85ea-da2dac804411");
    public RingCombat() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.ring_combat.tooltip"));
    }
}   
