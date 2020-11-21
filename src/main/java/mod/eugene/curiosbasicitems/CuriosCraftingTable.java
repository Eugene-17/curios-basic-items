package mod.eugene.curiosbasicitems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CuriosCraftingTable extends Item {
    private static final Text CRAFTING_TABLE_TEXT = new TranslatableText("container.crafting", new Object[0]);
    
    public CuriosCraftingTable(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world_, PlayerEntity player_, Hand hand_) {
        if (!world_.isClient()) {
            player_.openHandledScreen(openCuriosCraftingTable(world_, player_.getBlockPos()));
        }
        return new TypedActionResult<>(ActionResult.PASS, player_.getStackInHand(hand_));
    }

    public NamedScreenHandlerFactory openCuriosCraftingTable(World world_, BlockPos blockPos_){
		return new SimpleNamedScreenHandlerFactory(
					(int_, playerInventory_, playerEntity_) 
				-> 
					new CuriosCraftingTableScreen(int_, playerInventory_,
						ScreenHandlerContext.create(world_, blockPos_)),
				CRAFTING_TABLE_TEXT
		);
    }
}
