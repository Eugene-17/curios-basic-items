package mod.eugene.curiosbasicitems.items.bracelets;

import java.util.UUID;

import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.item.Item;

public class BraceletDiamond extends Item {
    public static final UUID ATTACK_UUID = UUID.fromString("804011ab-a9cc-425b-bd92-d6ea67fd2206");

    public BraceletDiamond() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
}   
