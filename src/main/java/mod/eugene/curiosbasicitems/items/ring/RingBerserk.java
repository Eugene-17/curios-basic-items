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
public class RingBerserk extends Item {
    public static final UUID ATTACK_UUID = UUID.fromString("ac0c2be0-a2ac-43d8-b800-3ccdbf77a3e6");
    public static final UUID SPEED_UUID = UUID.fromString("dbd847a5-a7e2-4153-b80a-785f50ed7327");

    public RingBerserk() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.ring_berserk.tooltip"));
    }
}   
