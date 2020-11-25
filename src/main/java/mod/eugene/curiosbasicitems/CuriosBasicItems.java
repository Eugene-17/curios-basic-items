package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.amulets.AmuletsRegister;
import mod.eugene.curiosbasicitems.items.bracelets.BraceletsRegister;
import mod.eugene.curiosbasicitems.workbenches.WorkBenchesRegister;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo.BuildScheme;
import top.theillusivec4.curios.api.SlotTypePreset;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.fabricmc.fabric.api.tag.TagRegistry;

public class CuriosBasicItems implements ModInitializer {

	public static final String MODID = "curiosbasicitems";
	public static final Logger LOGGER = LogManager.getLogger();
	public static Tag<Item> PIGLIN_SAFE_CURIOS;
	
	@Override
	public void onInitialize() {
		//Initialize curios slot
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.RING.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.HANDS.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.NECKLACE.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.HEAD.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.BACK.getInfoBuilder().build());
        CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.CHARM.getInfoBuilder().build());
        CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.BRACELET.getInfoBuilder().build());
        CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.BELT.getInfoBuilder().build());
		
		PIGLIN_SAFE_CURIOS = TagRegistry.item(new Identifier(MODID, "piglin_safe_curios"));

		AmuletsRegister.register();
		BraceletsRegister.register();
		WorkBenchesRegister.register();

		CuriosItemGroup.setDefaultItemGroup();
	}	
}