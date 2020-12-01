package mod.eugene.curiosbasicitems.items.rings;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import net.minecraft.entity.damage.DamageSource;


public class RingsRegister {
    public static final RingSimple SIMPLE_RING = new RingSimple();
    public static final RingCombat COMBAT_RING = new RingCombat();
    public static final RingLevitation LEVITATION_RING = new RingLevitation();
    public static final RingBerserk BERSERK_RING = new RingBerserk();


    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_simple"), SIMPLE_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_combat"), COMBAT_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_levitation"), LEVITATION_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_berserk"), BERSERK_RING);
        

        //Register items event

        ItemComponentCallbackV2.event(SIMPLE_RING).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
					public void onEquip(String identifier, int index, LivingEntity livingEntity) {
						if (livingEntity instanceof PlayerEntity) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 200, 0, true, true));
						}
                    }
        })));

        ItemComponentCallbackV2.event(COMBAT_RING).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
					public void onEquip(String identifier, int index, LivingEntity livingEntity) {
						if (livingEntity instanceof PlayerEntity) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 200, 0, true, true));
						}
                    }
        })));

        ItemComponentCallbackV2.event(LEVITATION_RING).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.LEVITATION, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(BERSERK_RING).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 100 == 0) {
                            livingEntity.damage(DamageSource.MAGIC, 2);
                        }
                    }

                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                        String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap
                            .create();

                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem())
                            .contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                                new EntityAttributeModifier(RingBerserk.SPEED_UUID, "Speed bonus", 0.2,
                                    Operation.MULTIPLY_TOTAL));
                            attributes.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                                new EntityAttributeModifier(RingBerserk.ATTACK_UUID, "Attack bonus", 4,
                                    Operation.ADDITION));
                        }
                        return attributes;
                    }
        })));
    }
}
