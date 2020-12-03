package mod.eugene.curiosbasicitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.client.screen.CuriosScreen;
import top.theillusivec4.curios.common.inventory.screen.CuriosScreenHandler;

@Environment(EnvType.CLIENT)
@Mixin(CuriosScreen.class)
public abstract class CuriosScreenMixin extends HandledScreen<CuriosScreenHandler> implements RecipeBookProvider {
    private static final Identifier EMPTY_SLOT_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_slot.png");
    private static final Identifier EMPTY_LEFT_BELT_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_left_belt_slot.png");
    // private static final Identifier EMPTY_RIGHT_BELT_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_right_belt_slot.png");
    private static final Identifier EMPTY_POTION_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_potion_belt_slot.png");
    @Shadow
    private final RecipeBookWidget recipeBook = new RecipeBookWidget();
    @Shadow
    public boolean isNarrow;
    @Shadow
    public boolean hasScrollBar;
    public CuriosScreenMixin(CuriosScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
        int neededWidth = 431;
        this.isNarrow = this.width < neededWidth;
    }

    @Inject(method = "drawBackground", at = @At(value = "RETURN"))
    public void drawBackgroundMixin(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo info) {
        CuriosApi.getCuriosHelper().findEquippedCurio(
            (itemStack) -> itemStack
                .getItem() instanceof BeltLeather, playerInventory.player)
                .ifPresent(found -> {
                    int scaledWidth = this.client.getWindow().getScaledWidth();
                    int scaledHeight = this.client.getWindow().getScaledHeight();

                    if (this.recipeBook.isOpen() && !this.isNarrow) {
                        scaledWidth = scaledWidth + 207;
                        if(this.hasScrollBar){
                            scaledWidth = scaledWidth + 30;
                        }
                    }
                    
                    ItemStack leftBeltSlotStack = this.playerInventory.getStack(CuriosBasicItems.LEFT_BELT_SLOT);
                    ItemStack rightBeltSlotStack = this.playerInventory.getStack(CuriosBasicItems.RIGHT_BELT_SLOT);
                    // ItemStack potionBeltSlotStack = this.playerInventory.getStack(43);

                    if (leftBeltSlotStack.isEmpty()) {
                        this.client.getTextureManager().bindTexture(EMPTY_LEFT_BELT_TEXTURE);
                        DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 40, 0.0F, 0.0F, 18, 18, 18, 18);
                    } else {
                        this.client.getTextureManager().bindTexture(EMPTY_SLOT_TEXTURE);
                        DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 40, 0.0F, 0.0F, 18, 18, 18, 18);
                    }

                    if (rightBeltSlotStack.isEmpty()) {
                        this.client.getTextureManager().bindTexture(EMPTY_POTION_TEXTURE);
                        DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 58, 0.0F, 0.0F, 18, 18, 18, 18);
                    } else {
                        this.client.getTextureManager().bindTexture(EMPTY_SLOT_TEXTURE);
                        DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 58, 0.0F, 0.0F, 18, 18, 18, 18);
                    }

                    // if (potionBeltSlotStack.isEmpty()) {
                    //     this.client.getTextureManager().bindTexture(EMPTY_POTION_TEXTURE);
                    //     DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 76, 0.0F, 0.0F, 18, 18, 18, 18);
                    // } else {
                    //     this.client.getTextureManager().bindTexture(EMPTY_SLOT_TEXTURE);
                    //     DrawableHelper.drawTexture(matrices, scaledWidth / 2 - 12, scaledHeight / 2 - 76, 0.0F, 0.0F, 18, 18, 18, 18);
                    // }

        });
    }

}