package mod.eugene.curiosbasicitems.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import mod.eugene.curiosbasicitems.items.amulets.AmuletsRegister;
import top.theillusivec4.curios.common.CuriosRegistry;
import mod.eugene.curiosbasicitems.items.mixin.ItemAccessor;

public class CuriosItemGroup {
    public static final ItemGroup CURIOS_BASIC_ITEMS = FabricItemGroupBuilder.build(
		new Identifier("curiosbasicitems", "groupname"),
		() -> new ItemStack(AmuletsRegister.IRON_HEALTH_AMULET));

	public static final void setDefaultItemGroup(){
		((ItemAccessor) CuriosRegistry.AMULET).setGroup(CURIOS_BASIC_ITEMS);
		((ItemAccessor) CuriosRegistry.CROWN).setGroup(CURIOS_BASIC_ITEMS);
		((ItemAccessor) CuriosRegistry.KNUCKLES).setGroup(CURIOS_BASIC_ITEMS);
		((ItemAccessor) CuriosRegistry.RING).setGroup(CURIOS_BASIC_ITEMS);
	}
}
