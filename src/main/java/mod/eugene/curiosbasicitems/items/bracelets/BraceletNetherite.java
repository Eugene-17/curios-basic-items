package mod.eugene.curiosbasicitems.items.bracelets;

import java.util.UUID;

import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import net.minecraft.item.Item;


public class BraceletNetherite extends Bracelet {
    public static final UUID ARMOR_UUID = UUID.fromString("1e0cdefc-c468-4f41-b95f-895726c1c76c");
    public static final UUID ATTACK_UUID = UUID.fromString("99032c5c-6f89-4265-a580-831bd71eeb5d");

    public BraceletNetherite() {
        super(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1).maxDamage(2000));
    }
}   
