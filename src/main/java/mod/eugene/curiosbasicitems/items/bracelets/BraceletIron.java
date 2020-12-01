package mod.eugene.curiosbasicitems.items.bracelets;

import java.util.UUID;

import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.item.Item;

public class BraceletIron extends Bracelet {
    public static final UUID ARMOR_UUID = UUID.fromString("2d38a82b-3975-4957-b173-997ed669704c");

    public BraceletIron() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
}   
