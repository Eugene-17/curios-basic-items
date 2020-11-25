package mod.eugene.curiosbasicitems.workbenches;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import mod.eugene.curiosbasicitems.NetworkPackets;
import top.theillusivec4.curios.api.CuriosApi;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import mod.eugene.curiosbasicitems.items.CuriosItemGroup;

public class WorkBenchesRegister {
    public static final Item CURIOS_CRAFTING_TABLE = new CuriosCraftingTable(new Item.Settings().group(CuriosItemGroup.CURIOS_BASIC_ITEMS).maxCount(1));

    public static void register(){
        //Register new item
		Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "curios_crafting_table"), CURIOS_CRAFTING_TABLE);

		//Curios crafting table event
		ServerSidePacketRegistry.INSTANCE.register(NetworkPackets.ACCESS_BACKSLOT,
        (((packetContext, packetByteBuf) -> packetContext.getTaskQueue().execute(() -> {
			PlayerEntity playerEntity = packetContext.getPlayer();
			if (playerEntity != null) {
			  	CuriosApi.getCuriosHelper().findEquippedCurio((itemStack) -> itemStack
					.getItem() instanceof CuriosWorkbenches, playerEntity)
					.ifPresent(found -> {
						((CuriosWorkbenches) found.right.getItem()).useWorkbench(playerEntity.getEntityWorld(), playerEntity);
					});
			}
        }))));
    }
}
