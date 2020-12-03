package mod.eugene.curiosbasicitems.items.back;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;

public class _BackItemRegister {
    public static final Item CURIOS_CRAFTING_TABLE = new CuriosCraftingTable(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1));

    public static void register(){
        //Register new item
		Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "curios_crafting_table"), CURIOS_CRAFTING_TABLE);
    }
}
