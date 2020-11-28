package mod.eugene.curiosbasicitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import mod.eugene.curiosbasicitems.CuriosBasicItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider {
    private static final Identifier EMPTY_SLOT_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_slot.png");
    @Shadow
    private final RecipeBookWidget recipeBook = new RecipeBookWidget();

    public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(method = "drawBackground", at = @At(value = "RETURN"))
    public void drawBackgroundMixin(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo info) {
        CuriosApi.getCuriosHelper().findEquippedCurio(
            (itemStack) -> itemStack
                .getItem() instanceof BeltLeather, playerInventory.player)
                .ifPresent(found -> {
                    int scaledWidth = this.client.getWindow().getScaledWidth();
                    int scaledHeight = this.client.getWindow().getScaledHeight();

                    if (this.recipeBook.isOpen()) {
                        scaledWidth = scaledWidth + 154;
                    }
                    this.client.getTextureManager().bindTexture(EMPTY_SLOT_TEXTURE);
                    DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 76, 0.0F, 0.0F, 18, 18, 18, 18);
                    DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 58, 0.0F, 0.0F, 18, 18, 18, 18);
                    DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 40, 0.0F, 0.0F, 18, 18, 18, 18);
        });
    }

}