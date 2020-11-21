package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.ItemsRegister;
import mod.eugene.curiosbasicitems.workbenches.WorkBenchesRegister;
import net.fabricmc.api.ModInitializer;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo.BuildScheme;
import top.theillusivec4.curios.api.SlotTypePreset;

public class CuriosBasicItems implements ModInitializer {

	public static final String MODID = "curiosbasicitems";

	@Override
	public void onInitialize() {
		//Initialize curios slot
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.RING.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.HANDS.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.NECKLACE.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.HEAD.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.BACK.getInfoBuilder().build());

		ItemsRegister.register();
		WorkBenchesRegister.register();
	}	
}