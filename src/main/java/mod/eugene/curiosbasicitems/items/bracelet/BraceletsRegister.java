package mod.eugene.curiosbasicitems.items.bracelet;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BraceletsRegister {
    public static final BraceletIron IRON_BRACELET = new BraceletIron();
	public static final BraceletGold GOLD_BRACELET = new BraceletGold();
    public static final BraceletDiamond DIAMOND_BRACELET = new BraceletDiamond();
    public static final BraceletNetherite NETHERITE_BRACELET = new BraceletNetherite();
    public static final BraceletGildedNetherite GILDED_NETHERITE_BRACELET = new BraceletGildedNetherite();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "bracelet_iron"), IRON_BRACELET);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "bracelet_gold"), GOLD_BRACELET);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "bracelet_diamond"), DIAMOND_BRACELET);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "bracelet_netherite"), NETHERITE_BRACELET);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "bracelet_gilded_netherite"), GILDED_NETHERITE_BRACELET);

        //Register items event
		ItemComponentCallbackV2.event(IRON_BRACELET).register(
            ((item, itemStack, componentContainer) -> componentContainer.put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(BraceletIron.ARMOR_UUID, "Armor bonus", 1,
                                Operation.ADDITION));
                        }
                        return attributes;
                    }

                    @Override
                    public void playRightClickEquipSound(LivingEntity livingEntity) {
                          livingEntity.world
                              .playSound(null, livingEntity.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                                  SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    }
      
                    @Override
                    public boolean canRightClickEquip() {
                          return true;
                    }
            }))
        );

        ItemComponentCallbackV2.event(GOLD_BRACELET).register(
            ((item, itemStack, componentContainer) -> componentContainer.put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(BraceletGold.ARMOR_UUID, "Armor bonus", 1,
                                Operation.ADDITION));
                        }
                        return attributes;
                    }

                    @Override
                    public void playRightClickEquipSound(LivingEntity livingEntity) {
                          livingEntity.world
                              .playSound(null, livingEntity.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
                                  SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    }
      
                    @Override
                    public boolean canRightClickEquip() {
                          return true;
                    }
            }))
        );

        ItemComponentCallbackV2.event(DIAMOND_BRACELET).register(
            ((item, itemStack, componentContainer) -> componentContainer.put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BraceletDiamond.ATTACK_UUID, "Attack bonus", 1,
                                Operation.ADDITION));
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
            }))
        );

        ItemComponentCallbackV2.event(NETHERITE_BRACELET).register(
            ((item, itemStack, componentContainer) -> componentContainer.put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(BraceletNetherite.ARMOR_UUID, "Armor bonus", 1,
                                Operation.ADDITION));

                            attributes.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BraceletNetherite.ATTACK_UUID, "Attack bonus", 1,
                                Operation.ADDITION));
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
            }))
        );

        ItemComponentCallbackV2.event(GILDED_NETHERITE_BRACELET).register(
            ((item, itemStack, componentContainer) -> componentContainer.put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(BraceletNetherite.ARMOR_UUID, "Armor bonus", 1,
                                Operation.ADDITION));

                            attributes.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BraceletNetherite.ATTACK_UUID, "Attack bonus", 1,
                                Operation.ADDITION));
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
            }))
        );
    }
}
