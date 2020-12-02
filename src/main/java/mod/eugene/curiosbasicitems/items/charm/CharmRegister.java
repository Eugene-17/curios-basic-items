package mod.eugene.curiosbasicitems.items.charm;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;

public class CharmRegister {
    public static final CharmLuck LUCK_CHARM = new CharmLuck();
    public static final CharmLuckier LUCKIER_CHARM = new CharmLuckier();
    public static final CharmPoison POISON_CHARM = new CharmPoison();
    public static final CharmHunger HUNGER_CHARM = new CharmHunger();
    public static final CharmWither WITHER_CHARM = new CharmWither();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "charm_luck"), LUCK_CHARM);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "charm_luckier"), LUCKIER_CHARM);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "charm_poison"), POISON_CHARM);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "charm_hunger"), HUNGER_CHARM);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "charm_wither"), WITHER_CHARM);

        //Register items event

        ItemComponentCallbackV2.event(LUCK_CHARM).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.LUCK, 21, 0, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(LUCKIER_CHARM).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 20 == 0) {
                            livingEntity.addStatusEffect(
                                new StatusEffectInstance(StatusEffects.LUCK, 21, 1, true, true));
                        }
                    }
        })));

        ItemComponentCallbackV2.event(POISON_CHARM).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient()) {
                            if(livingEntity.hasStatusEffect(StatusEffects.POISON)) {
                                livingEntity.removeStatusEffect(StatusEffects.POISON);
                            }
                        }
                    }
        })));

        ItemComponentCallbackV2.event(HUNGER_CHARM).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient()) {
                            if(livingEntity.hasStatusEffect(StatusEffects.HUNGER)) {
                                livingEntity.removeStatusEffect(StatusEffects.HUNGER);
                            }
                        }
                    }
        })));

        ItemComponentCallbackV2.event(WITHER_CHARM).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        if (!livingEntity.getEntityWorld().isClient()) {
                            if(livingEntity.hasStatusEffect(StatusEffects.WITHER)) {
                                livingEntity.removeStatusEffect(StatusEffects.WITHER);
                            }
                        }
                    }
        })));
        
    }
}
