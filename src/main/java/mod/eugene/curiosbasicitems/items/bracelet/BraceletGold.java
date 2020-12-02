package mod.eugene.curiosbasicitems.items.bracelet;

import java.util.List;
import java.util.UUID;

import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class BraceletGold extends Bracelet {
    public static final UUID ARMOR_UUID = UUID.fromString("3a552885-f757-4230-a876-f7288d759925");
    
    public BraceletGold() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.piglin_friend_curios.tooltip"));
    }
}   
