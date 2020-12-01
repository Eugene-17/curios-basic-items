package mod.eugene.curiosbasicitems.items.hats;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import mod.eugene.curiosbasicitems.items.charms.CharmHunger;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;


public class HatsRegister {
    public static final HatChicken CHICKEN_HAT = new HatChicken();
    public static final HatSlime SLIME_HAT = new HatSlime();
    public static final HatConduit CONDUIT_HAT = new HatConduit();
    public static final HatZombie ZOMBIE_HAT = new HatZombie();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "hat_chicken"), CHICKEN_HAT);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "hat_slime"), SLIME_HAT);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "hat_conduit"), CONDUIT_HAT);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "hat_zombie"), ZOMBIE_HAT); 

        //Register items events

        ItemComponentCallbackV2.event(CHICKEN_HAT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.SLOW_FALLING, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(SLIME_HAT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.JUMP_BOOST, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(CONDUIT_HAT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(ZOMBIE_HAT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0 ) {
                            if(!CharmHunger.isWearingHungerCharm(livingEntity)){
                                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 21, 0, true, true));
                            }
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
                                new EntityAttributeModifier(HatZombie.SPEED_UUID, "Speed bonus", 0.1,
                                    Operation.MULTIPLY_TOTAL));
                        }
                        return attributes;
                    }
        })));
    }
}
