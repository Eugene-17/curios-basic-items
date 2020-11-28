package mod.eugene.curiosbasicitems.items.belt;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeltsRegister {
    public static final BeltLeather LEATHER_WORK_BELT = new BeltLeather();
    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "belt_leather"), LEATHER_WORK_BELT);
    }
}
