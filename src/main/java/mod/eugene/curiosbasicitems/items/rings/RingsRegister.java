package mod.eugene.curiosbasicitems.items.rings;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;


public class RingsRegister {
    public static final RingSimple SIMPLE_RING = new RingSimple();
    public static final RingCombat COMBAT_RING = new RingCombat();
    public static final RingLevitation LEVITATION_RING = new RingLevitation();
    public static final RingLuck LUCK_RING = new RingLuck();
    public static final RingLuckier LUCKIER_RING = new RingLuckier();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_simple"), SIMPLE_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_combat"), COMBAT_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_levitation"), LEVITATION_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_luck"), LUCK_RING);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "ring_luckier"), LUCKIER_RING);

        //Register items event
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

        ItemComponentCallbackV2.event(LUCK_RING).register(
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

        ItemComponentCallbackV2.event(LUCKIER_RING).register(
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
    }
}
