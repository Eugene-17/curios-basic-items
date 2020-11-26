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
import top.theillusivec4.curios.api.SlotTypeInfo.Builder;

public class CuriosBasicItems implements ModInitializer {

	public static final String MODID = "curiosbasicitems";
	public static final Logger LOGGER = LogManager.getLogger();
	public static Tag<Item> PIGLIN_SAFE_CURIOS;
	public static Builder MASK_BUILDER = new Builder("mask").priority(1001).icon(new Identifier(MODID, "item/empty_mask_slot"));
	public static Builder LEFT_BELT_BUILDER = new Builder("left_belt").priority(1002).icon(new Identifier(MODID, "item/empty_left_belt_slot"));
	public static Builder RIGHT_BELT_BUILDER = new Builder("right_belt").priority(1003).icon(new Identifier(MODID, "item/empty_right_belt_slot"));
	
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
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, MASK_BUILDER.build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, LEFT_BELT_BUILDER.build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, RIGHT_BELT_BUILDER.build());

		PIGLIN_SAFE_CURIOS = TagRegistry.item(new Identifier(MODID, "piglin_safe_curios"));

		AmuletsRegister.register();
		BraceletsRegister.register();
		WorkBenchesRegister.register();

		CuriosItemGroup.setDefaultItemGroup();
	}	
}