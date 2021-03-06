package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.back._CloakRegister;
import mod.eugene.curiosbasicitems.items.belt._BeltRegister;
import mod.eugene.curiosbasicitems.items.bracelet._BraceletRegister;
import mod.eugene.curiosbasicitems.items.charm._CharmRegister;
import mod.eugene.curiosbasicitems.items.hand._GloveRegister;
import mod.eugene.curiosbasicitems.items.hat._HatRegister;
import mod.eugene.curiosbasicitems.items.necklace._AmuletRegister;
import mod.eugene.curiosbasicitems.items.ring._RingRegister;
import mod.eugene.curiosbasicitems.items.back._BackItemRegister;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo.BuildScheme;
import top.theillusivec4.curios.api.SlotTypePreset;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.util.registry.Registry;

public class CuriosBasicItems implements ModInitializer {

	public static final String MODID = "curiosbasicitems";
	public static final Logger LOGGER = LogManager.getLogger();
	public static Tag<Item> PIGLIN_SAFE_CURIOS;
    public static final Identifier PACK_UP_ITEM = new Identifier(MODID, "pack_up_item");
    public static SoundEvent PACK_UP_ITEM_EVENT = new SoundEvent(PACK_UP_ITEM);
    public static final Identifier SHEATH_SWORD = new Identifier(MODID, "sheath_sword");
    public static SoundEvent SHEATH_SWORD_EVENT = new SoundEvent(SHEATH_SWORD);

    public static int LEFT_BELT_SLOT = 41;
    public static int RIGHT_BELT_SLOT = 42;
	
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
        Registry.register(Registry.SOUND_EVENT, PACK_UP_ITEM, PACK_UP_ITEM_EVENT);
        Registry.register(Registry.SOUND_EVENT, SHEATH_SWORD, SHEATH_SWORD_EVENT);  

        NetworkPackets.serverRegister();
        _AmuletRegister.register();
        _BeltRegister.register();
        _CloakRegister.register();
        _GloveRegister.register();
        _HatRegister.register();
        _RingRegister.register();
        _BraceletRegister.register();
        _CharmRegister.register();
		_BackItemRegister.register();
        CuriosItemGroup.setDefaultItemGroup();
	}	
}