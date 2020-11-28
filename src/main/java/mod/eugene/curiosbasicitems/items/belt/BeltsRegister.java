package mod.eugene.curiosbasicitems.items.belt;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import net.minecraft.world.World;


public class BeltsRegister {
    public static final BeltLeather LEATHER_WORK_BELT = new BeltLeather();

    private static final void dropAllBeltItem(PlayerInventory playerInventory, World playerWorld){
        playerInventory.offerOrDrop(playerWorld, playerInventory.getStack(41));
        playerInventory.offerOrDrop(playerWorld, playerInventory.getStack(42));
        playerInventory.offerOrDrop(playerWorld, playerInventory.getStack(43));
    }

    public static void register() {
        //Register new item
        Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "belt_leather"), LEATHER_WORK_BELT);

        //Register items event
		ItemComponentCallbackV2.event(LEATHER_WORK_BELT).register(
            ((item, itemStack, componentContainer) -> componentContainer
                .put(CuriosComponent.ITEM, new ICurio() {
                    @Override
					public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                        if (livingEntity instanceof PlayerEntity) {
                            PlayerEntity player = (PlayerEntity) livingEntity;
                            dropAllBeltItem(player.inventory, player.getEntityWorld());
                        }
                    }
        })));
    }
}
