package mod.eugene.curiosbasicitems.items.bracelets;

import java.util.List;
import java.util.UUID;

import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class BraceletGildedNetherite extends Item {
    public static final UUID ARMOR_UUID = UUID.fromString("a118d149-5626-40d4-9fd0-e7d5e80b87ff");
    public static final UUID ATTACK_UUID = UUID.fromString("a364bcc2-8bf2-45db-85df-9f51f423a03d");

    public BraceletGildedNetherite() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.piglin_friend_curios.tooltip"));
    }   
}   
