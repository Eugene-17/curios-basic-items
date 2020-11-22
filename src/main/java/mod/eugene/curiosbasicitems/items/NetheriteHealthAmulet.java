package mod.eugene.curiosbasicitems.items;

import java.util.UUID;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class NetheriteHealthAmulet extends Item {
    public static final UUID HEALTH_UUID = UUID.fromString("66bbc8c0-def8-404d-9687-eb39a6be5927");

    public NetheriteHealthAmulet() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(2000));
    }
}   
