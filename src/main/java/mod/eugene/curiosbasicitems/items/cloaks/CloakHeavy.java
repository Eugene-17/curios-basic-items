package mod.eugene.curiosbasicitems.items.cloaks;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
public class CloakHeavy extends Item {
    public static final UUID ARMOR_UUID = UUID.fromString("23acd5dd-27d4-43c0-bf3e-42f29e5a928d");
    public static final UUID SPEED_UUID = UUID.fromString("90718008-4288-44ad-98aa-9cad35d904d7");
    public static final UUID KNOCKBACK_UUID = UUID.fromString("be9ca815-bf12-4b5e-bb99-4dd6a9d8c8b1");

    public CloakHeavy() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.cloak_heavy.tooltip"));
    }
}   
