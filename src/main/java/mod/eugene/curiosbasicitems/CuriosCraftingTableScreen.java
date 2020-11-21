package mod.eugene.curiosbasicitems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class CuriosCraftingTableScreen extends CraftingScreenHandler {

    public CuriosCraftingTableScreen(int int_, PlayerInventory playerInventory, ScreenHandlerContext blockContext) {
        super(int_, playerInventory, blockContext);
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return true;
    }
}
