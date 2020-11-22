package mod.eugene.curiosbasicitems.items;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;

import java.util.UUID;

import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsRegister {
    public static final IronHealthAmulet IRON_HEALTH_AMULET = new IronHealthAmulet();
	public static final NetheriteHealthAmulet NETHERITE_HEALTH_AMULET = new NetheriteHealthAmulet();
    public static final StarHealthAmulet STAR_HEALTH_AMULET = new StarHealthAmulet();

    private static final void resetHealth(UUID UUID_, LivingEntity livingEntity){
        if (livingEntity instanceof PlayerEntity) {
            EntityAttributeModifier modifier = new EntityAttributeModifier(
                UUID_, "Health bonus", 0, Operation.ADDITION);
            EntityAttributeInstance maxHealthAttr = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            maxHealthAttr.removeModifier(modifier);
            maxHealthAttr.addPersistentModifier(modifier);
        }
        if (livingEntity.getHealth() > 20) livingEntity.setHealth(livingEntity.getMaxHealth());
    }

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "iron_health_amulet"), IRON_HEALTH_AMULET);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "netherite_health_amulet"), NETHERITE_HEALTH_AMULET);
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "star_health_amulet"), STAR_HEALTH_AMULET);

        //Register items event
		ItemComponentCallbackV2.event(IRON_HEALTH_AMULET).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {

                    @Override
					public void onEquip(String identifier, int index, LivingEntity livingEntity) {
						if (livingEntity instanceof PlayerEntity) {
                            EntityAttributeModifier modifier = new EntityAttributeModifier(
                                    IronHealthAmulet.HEALTH_UUID, "Health bonus", 4, Operation.ADDITION);
                            EntityAttributeInstance maxHealthAttr = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                            maxHealthAttr.removeModifier(modifier);
                            maxHealthAttr.addPersistentModifier(modifier);
						}
                    }
                    
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
						resetHealth(IronHealthAmulet.HEALTH_UUID, livingEntity);
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
					public void onEquip(String identifier, int index, LivingEntity livingEntity) {
						if (livingEntity instanceof PlayerEntity) {
                            EntityAttributeModifier modifier = new EntityAttributeModifier(
                                NetheriteHealthAmulet.HEALTH_UUID, "Health bonus", 10, Operation.ADDITION);
                            EntityAttributeInstance maxHealthAttr = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                            maxHealthAttr.removeModifier(modifier);
                            maxHealthAttr.addPersistentModifier(modifier);
						}
                    }
                    
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
						resetHealth(NetheriteHealthAmulet.HEALTH_UUID, livingEntity);
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
					public void onEquip(String identifier, int index, LivingEntity livingEntity) {
						if (livingEntity instanceof PlayerEntity) {
                            EntityAttributeModifier modifier = new EntityAttributeModifier(
                                StarHealthAmulet.HEALTH_UUID, "Health bonus", 20, Operation.ADDITION);
                            EntityAttributeInstance maxHealthAttr = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                            maxHealthAttr.removeModifier(modifier);
                            maxHealthAttr.addPersistentModifier(modifier);
						}
                    }
                    
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
						resetHealth(StarHealthAmulet.HEALTH_UUID, livingEntity);
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
    }
    
}
