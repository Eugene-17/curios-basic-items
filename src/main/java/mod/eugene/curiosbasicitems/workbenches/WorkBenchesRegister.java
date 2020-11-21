package mod.eugene.curiosbasicitems.workbenches;
import mod.eugene.curiosbasicitems.CuriosBasicItems;
import mod.eugene.curiosbasicitems.NetworkPackets;
import top.theillusivec4.curios.api.CuriosApi;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WorkBenchesRegister {
    private static final Text CRAFTING_TABLE_TEXT = new TranslatableText("container.crafting", new Object[0]);
    public static final Item CURIOS_CRAFTING_TABLE = new CuriosCraftingTable(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    public static void register(){
        //Register new item
		Registry.register(Registry.ITEM, new Identifier(CuriosBasicItems.MODID, "curios_crafting_table"), CURIOS_CRAFTING_TABLE);

		//Curios crafting table event
		ServerSidePacketRegistry.INSTANCE.register(NetworkPackets.ACCESS_WORKBENCH,
        (((packetContext, packetByteBuf) -> packetContext.getTaskQueue().execute(() -> {
			PlayerEntity playerEntity = packetContext.getPlayer();

			if (playerEntity != null) {
			  CuriosApi.getCuriosHelper().findEquippedCurio((itemStack) -> itemStack
				  .getItem() instanceof CuriosCraftingTable, playerEntity)
				  .ifPresent(found -> {
					playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory(
						(int_, playerInventory_, playerEntity_) 
					-> 
						new CuriosCraftingTableScreen(int_, playerInventory_,
							ScreenHandlerContext.create(playerEntity.getEntityWorld(), playerEntity.getBlockPos())),
					CRAFTING_TABLE_TEXT));
				  });
			}
        }))));
    }
}
