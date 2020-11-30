package mod.eugene.curiosbasicitems.workbenches;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class CuriosCraftingTableScreenHandler extends CraftingScreenHandler {

    public CuriosCraftingTableScreenHandler(int int_, PlayerInventory playerInventory, ScreenHandlerContext blockContext) {
        super(int_, playerInventory, blockContext);
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return true;
    }
}
