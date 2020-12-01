package mod.eugene.curiosbasicitems.items.cloaks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;


public class CloaksRegister {
    public static final CloakInvisibility INVISIBILITY_CLOAK = new CloakInvisibility();
    public static final CloakFire FIRE_CLOAK = new CloakFire();
    public static final CloakHeavy HEAVY_CLOAK = new CloakHeavy();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "cloak_invisible"), INVISIBILITY_CLOAK);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "cloak_fire"), FIRE_CLOAK);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "cloak_heavy"), HEAVY_CLOAK);

        //Register items event

        ItemComponentCallbackV2.event(INVISIBILITY_CLOAK).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.INVISIBILITY, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(FIRE_CLOAK).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(HEAVY_CLOAK).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {

                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(CloakHeavy.ARMOR_UUID, "Armor bonus", 5,
                                Operation.ADDITION));

                            attributes.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                            new EntityAttributeModifier(CloakHeavy.KNOCKBACK_UUID, "Knockback resistance bonus", 1,
                                Operation.ADDITION));

                            attributes.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            new EntityAttributeModifier(CloakHeavy.SPEED_UUID, "Knockback resistance bonus", -0.1,
                                Operation.MULTIPLY_TOTAL));
                        }
                        return attributes;
                    }
        })));
    }
}
