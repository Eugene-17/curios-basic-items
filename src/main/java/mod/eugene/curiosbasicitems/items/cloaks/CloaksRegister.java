package mod.eugene.curiosbasicitems.items.cloaks;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;


public class CloaksRegister {
    public static final CloakInvisibility INVISIBILITY_CLOAK = new CloakInvisibility();
    public static final CloakFire FIRE_CLOAK = new CloakFire();

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "cloak_invisible"), INVISIBILITY_CLOAK);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "cloak_fire"), FIRE_CLOAK);

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

    }
}
