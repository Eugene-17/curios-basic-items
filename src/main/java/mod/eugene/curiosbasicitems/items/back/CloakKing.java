package mod.eugene.curiosbasicitems.items.back;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
public class CloakKing extends Item {

    public static final UUID ARMOR_UUID = UUID.fromString("0a1732e9-f9ff-4ee0-b0f0-fab7c2efe8af");
    public static final UUID KNOCKBACK_UUID = UUID.fromString("91c2c640-2f27-478b-a259-73608be04a8e");

    public CloakKing() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.curiosbasicitems.cloak_king.tooltip"));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}   
