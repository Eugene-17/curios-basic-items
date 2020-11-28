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
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;

@Environment(EnvType.CLIENT)
@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {
    private static final Identifier EMPTY_LEFT_BELT_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_left_belt_slot.png");
    private static final Identifier EMPTY_RIGHT_BELT_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_right_belt_slot.png");
    private static final Identifier EMPTY_POTION_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/item/empty_potion_belt_slot.png");
    @Shadow
    public T handler;
    @Shadow
    protected final PlayerInventory playerInventory;

    public HandledScreenMixin(T handler, PlayerInventory inventory, Text title) {
        super(title);
        this.playerInventory = inventory;
        this.handler = handler;
    }

    @Inject(method = "drawSlot", at = @At(value = "RETURN"))
    public void drawSlotMixin(MatrixStack matrices, Slot slot, CallbackInfo info) {
        CuriosApi.getCuriosHelper().findEquippedCurio(
				(itemStack) -> itemStack
					.getItem() instanceof BeltLeather, playerInventory.player)
					.ifPresent(found -> {
						ItemStack leftBeltSlotStack = this.playerInventory.getStack(41);
                        ItemStack rightBeltSlotStack = this.playerInventory.getStack(42);
                        ItemStack potionBeltSlotStack = this.playerInventory.getStack(43);

                        if (this.handler instanceof PlayerScreenHandler) {
                            if (leftBeltSlotStack.isEmpty()) {
                                this.client.getTextureManager().bindTexture(EMPTY_LEFT_BELT_TEXTURE);
                                DrawableHelper.drawTexture(matrices, 77, 44, 0.0F, 0.0F, 16, 16, 16, 16);
                            }
                            if (rightBeltSlotStack.isEmpty()) {
                                this.client.getTextureManager().bindTexture(EMPTY_RIGHT_BELT_TEXTURE);
                                DrawableHelper.drawTexture(matrices, 77, 26, 0.0F, 0.0F, 16, 16, 16, 16);
                            }
                            if (potionBeltSlotStack.isEmpty()) {
                                this.client.getTextureManager().bindTexture(EMPTY_POTION_TEXTURE);
                                DrawableHelper.drawTexture(matrices, 77, 8, 0.0F, 0.0F, 16, 16, 16, 16);
                            }
                        }
					});
    }
}