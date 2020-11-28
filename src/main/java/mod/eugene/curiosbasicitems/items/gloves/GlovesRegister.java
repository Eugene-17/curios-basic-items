package mod.eugene.curiosbasicitems.items.gloves;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import top.theillusivec4.curios.api.CuriosApi;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;


public class GlovesRegister {
    public static final GloveLeather LEATHER_GLOVE = new GloveLeather();
    public static final GloveDiamond DIAMOND_GLOVE = new GloveDiamond();
    public static final GloveNetherite NETHERITE_GLOVE = new GloveNetherite();
    public static final GlovePower POWER_GLOVE = new GlovePower();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "glove_leather"), LEATHER_GLOVE);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "glove_diamond"), DIAMOND_GLOVE);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "glove_netherite"), NETHERITE_GLOVE);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "glove_power"), POWER_GLOVE);

        //Register items event
        ItemComponentCallbackV2.event(LEATHER_GLOVE).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        Item activeItem = livingEntity.getMainHandStack().getItem();
                        if(!(activeItem instanceof ToolItem || activeItem instanceof TridentItem )){
                            if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                                livingEntity.addStatusEffect(
                                    new StatusEffectInstance(StatusEffects.STRENGTH, 20, 0, true, true));
                            }
                        }
                    }
        })));

        ItemComponentCallbackV2.event(DIAMOND_GLOVE).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        Item activeItem = livingEntity.getMainHandStack().getItem();
                        if(!(activeItem instanceof ToolItem || activeItem instanceof TridentItem )){
                            if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                                livingEntity.addStatusEffect(
                                    new StatusEffectInstance(StatusEffects.STRENGTH, 20, 1, true, true));
                            }
                        }
                    }

                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(GloveDiamond.ARMOR_UUID, "Armor bonus", 1,
                                Operation.ADDITION));
                        }
                        return attributes;
                    }
        })));

        ItemComponentCallbackV2.event(NETHERITE_GLOVE).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        Item activeItem = livingEntity.getMainHandStack().getItem();
                        if(!(activeItem instanceof ToolItem || activeItem instanceof TridentItem )){
                            if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                                livingEntity.addStatusEffect(
                                    new StatusEffectInstance(StatusEffects.STRENGTH, 20, 2, true, true));
                            }
                        }
                    }

                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(GloveNetherite.ARMOR_UUID, "Armor bonus", 2,
                                Operation.ADDITION));
                        }
                        return attributes;
                    }
        })));

        ItemComponentCallbackV2.event(POWER_GLOVE).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        Item activeItem = livingEntity.getMainHandStack().getItem();
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0){
                            if(!(activeItem instanceof ToolItem || activeItem instanceof TridentItem )){
                                livingEntity.addStatusEffect( new StatusEffectInstance(StatusEffects.STRENGTH, 20, 2, true, true));
                            }
                            else livingEntity.addStatusEffect( new StatusEffectInstance(StatusEffects.STRENGTH, 20, 1, true, true));
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20, 0, true, true));
                        }
                    }

                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(GlovePower.ARMOR_UUID, "Armor bonus", 2,
                                Operation.ADDITION));
                        }
                        return attributes;
                    }
        })));
    }
}
