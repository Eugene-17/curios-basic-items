package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.items.IronHealthAmulet;
import mod.eugene.curiosbasicitems.items.NetheriteHealthAmulet;
import mod.eugene.curiosbasicitems.items.StarHealthAmulet;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.fabricmc.api.ModInitializer;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.SlotTypeInfo.BuildScheme;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.SlotTypePreset;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CuriosBasicItems implements ModInitializer {

	public static final String MODID = "curiosbasicitems";
	private static final Text CRAFTING_TABLE_TEXT = new TranslatableText("container.crafting", new Object[0]);
	public static final Item CURIOS_CRAFTING_TABLE = new CuriosCraftingTable(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
	public static final IronHealthAmulet IRON_HEALTH_AMULET = new IronHealthAmulet();
	public static final NetheriteHealthAmulet NETHERITE_HEALTH_AMULET = new NetheriteHealthAmulet();
	public static final StarHealthAmulet STAR_HEALTH_AMULET = new StarHealthAmulet();

	@Override
	public void onInitialize() {
		//Initialize curios slot
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.RING.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.HANDS.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.NECKLACE.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.HEAD.getInfoBuilder().build());
		CuriosApi.enqueueSlotType(BuildScheme.REGISTER, SlotTypePreset.BACK.getInfoBuilder().build());

		//Register new item
		Registry.register(Registry.ITEM, new Identifier(MODID, "curios_crafting_table"), CURIOS_CRAFTING_TABLE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "iron_health_amulet"), IRON_HEALTH_AMULET);
		Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_health_amulet"), NETHERITE_HEALTH_AMULET);
		Registry.register(Registry.ITEM, new Identifier(MODID, "star_health_amulet"), STAR_HEALTH_AMULET);

		//Register items event
		ItemComponentCallbackV2.event(IRON_HEALTH_AMULET).register(
        ((item, itemStack, componentContainer) -> componentContainer
            .put(CuriosComponent.ITEM, new ICurio() {

				@Override
				public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
					String identifier) {
				  	Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap
					  	.create();
  
				  	if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem())
					  	.contains(identifier)) {
					attributes.put(EntityAttributes.GENERIC_MAX_HEALTH,
						new EntityAttributeModifier(IronHealthAmulet.HEALTH_UUID, "Health bonus",
													4, Operation.ADDITION));
				  	}
				  return attributes;
				}

				@Override
				public void playRightClickEquipSound(LivingEntity livingEntity) {
				  	livingEntity.world
					  	.playSound(null, livingEntity.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
						  	SoundCategory.NEUTRAL, 1.0F, 1.0F);
				}
  
				@Override
				public boolean canRightClickEquip() {
				  	return true;
				}
		})));

		ItemComponentCallbackV2.event(NETHERITE_HEALTH_AMULET).register(
        ((item, itemStack, componentContainer) -> componentContainer
            .put(CuriosComponent.ITEM, new ICurio() {

				@Override
				public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
					String identifier) {
				  	Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap
					  	.create();
  
				  	if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem())
					  	.contains(identifier)) {
					attributes.put(EntityAttributes.GENERIC_MAX_HEALTH,
						new EntityAttributeModifier(NetheriteHealthAmulet.HEALTH_UUID, "Health bonus",
													10, Operation.ADDITION));
				  	}
				  return attributes;
				}

				@Override
				public void playRightClickEquipSound(LivingEntity livingEntity) {
				  	livingEntity.world
					  	.playSound(null, livingEntity.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
						  	SoundCategory.NEUTRAL, 1.0F, 1.0F);
				}
  
				@Override
				public boolean canRightClickEquip() {
				  	return true;
				}
		})));

		ItemComponentCallbackV2.event(STAR_HEALTH_AMULET).register(
        ((item, itemStack, componentContainer) -> componentContainer
            .put(CuriosComponent.ITEM, new ICurio() {

				@Override
				public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
					String identifier) {
				  	Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap
					  	.create();
  
				  	if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem())
					  	.contains(identifier)) {
					attributes.put(EntityAttributes.GENERIC_MAX_HEALTH,
						new EntityAttributeModifier(NetheriteHealthAmulet.HEALTH_UUID, "Health bonus",
													20, Operation.ADDITION));
				  	}
				  return attributes;
				}

				@Override
				public void playRightClickEquipSound(LivingEntity livingEntity) {
				  	livingEntity.world
					  	.playSound(null, livingEntity.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
						  	SoundCategory.NEUTRAL, 1.0F, 1.0F);
				}
  
				@Override
				public boolean canRightClickEquip() {
				  	return true;
				}
		})));

		//Curios crafting table event
		ServerSidePacketRegistry.INSTANCE.register(NetworkPackets.ACCESS_WORKBENCH,
        (((packetContext, packetByteBuf) -> packetContext.getTaskQueue().execute(() -> {
			PlayerEntity playerEntity = packetContext.getPlayer();

			if (playerEntity != null) {
			  CuriosApi.getCuriosHelper().findEquippedCurio((itemStack) -> itemStack
				  .getItem() instanceof CuriosCraftingTable, playerEntity)
				  .ifPresent(found -> {
					playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory(
						(int_, playerInventory_, playerEntity_) 
					-> 
						new CuriosCraftingTableScreen(int_, playerInventory_,
							ScreenHandlerContext.create(playerEntity.getEntityWorld(), playerEntity.getBlockPos())),
					CRAFTING_TABLE_TEXT));
				  });
			}
        }))));
	}	
}