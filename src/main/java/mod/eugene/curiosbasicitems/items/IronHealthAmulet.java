package mod.eugene.curiosbasicitems.items;

import java.util.UUID;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class IronHealthAmulet extends Item {
    public static final UUID HEALTH_UUID = UUID.fromString("7119a370-1fc4-4cb3-b7e3-4747788f0785");

    public IronHealthAmulet() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(2000));
    }
}   
