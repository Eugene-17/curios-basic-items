package mod.eugene.curiosbasicitems.items.back;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CuriosCraftingTable extends BackItem {
    private static final Text CRAFTING_TABLE_TEXT = new TranslatableText("container.crafting", new Object[0]);
    
    public CuriosCraftingTable(Settings settings) {
        super(settings);
    }

    @Override
    public void useBackItem(World world_, PlayerEntity player_) {
        if (!world_.isClient()) {
            player_.openHandledScreen(openCuriosCraftingTable(world_, player_.getBlockPos()));
        }
    }

    public NamedScreenHandlerFactory openCuriosCraftingTable(World world_, BlockPos blockPos_){
		return new SimpleNamedScreenHandlerFactory(
					(int_, playerInventory_, playerEntity_) 
				-> 
					new CuriosCraftingTableScreenHandler(int_, playerInventory_,
						ScreenHandlerContext.create(world_, blockPos_)),
				CRAFTING_TABLE_TEXT
		);
    }
}
