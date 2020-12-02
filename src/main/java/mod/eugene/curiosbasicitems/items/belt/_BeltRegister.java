package mod.eugene.curiosbasicitems.items.belt;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import mod.eugene.curiosbasicitems.items.charm.CharmHunger;
import mod.eugene.curiosbasicitems.items.hat.HatKing;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import net.minecraft.world.World;


public class _BeltRegister {
    public static final BeltLeather LEATHER_WORK_BELT = new BeltLeather();
    public static final BeltGluttony GLUTTONY_BELT = new BeltGluttony();
    public static final BeltChampion CHAMPION_BELT = new BeltChampion();

    private static final void dropAllBeltItem(PlayerInventory playerInventory, World playerWorld){
        playerInventory.offerOrDrop(playerWorld, playerInventory.getStack(41));
        playerInventory.offerOrDrop(playerWorld, playerInventory.getStack(42));
        playerInventory.offerOrDrop(playerWorld, playerInventory.getStack(43));
    }

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "belt_leather"), LEATHER_WORK_BELT);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "belt_gluttony"), GLUTTONY_BELT);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "belt_champion"), CHAMPION_BELT);

        //Register items event
		ItemComponentCallbackV2.event(LEATHER_WORK_BELT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                        if (livingEntity instanceof PlayerEntity) {
                            PlayerEntity player = (PlayerEntity) livingEntity;
                            dropAllBeltItem(player.inventory, player.getEntityWorld());
                            player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                    }
        })));

        ItemComponentCallbackV2.event(GLUTTONY_BELT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                        if (livingEntity instanceof PlayerEntity) {
                            PlayerEntity player = (PlayerEntity) livingEntity;
                            dropAllBeltItem(player.inventory, player.getEntityWorld());
                            player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                    }
                    @Override
                    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                        // Decrese a hunger per 20 seconds
                        if (!livingEntity.getEntityWorld().isClient() && livingEntity.age % 400 == 0 ) {
                            if(!CharmHunger.isWearingHungerCharm(livingEntity) && !HatKing.isWearingKingCrown(livingEntity)){
                                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 21, 80, true, true));
                            }
                        }
                    }
        })));

        ItemComponentCallbackV2.event(CHAMPION_BELT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                        if (livingEntity instanceof PlayerEntity) {
                            PlayerEntity player = (PlayerEntity) livingEntity;
                            dropAllBeltItem(player.inventory, player.getEntityWorld());
                            player.world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                    }

                    @Override
                    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
                    String identifier) {
                        Multimap<EntityAttribute, EntityAttributeModifier> attributes = HashMultimap.create();
                        if (CuriosApi.getCuriosHelper().getCurioTags(itemStack.getItem()).contains(identifier)) {
                            attributes.put(EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(BeltChampion.ARMOR_UUID, "Armor bonus", 1,
                                Operation.ADDITION));

                            attributes.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BeltChampion.ATTACK_UUID, "Attack bonus", 1,
                                Operation.ADDITION));
                        }
                        return attributes;
                    }
        })));
    }
}
