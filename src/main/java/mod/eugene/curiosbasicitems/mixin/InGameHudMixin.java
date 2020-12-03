package mod.eugene.curiosbasicitems.mixin;

import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
  @Shadow
  @Final
  @Mutable
  private MinecraftClient client;
  @Shadow
  private static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");
  @Shadow
  private int scaledWidth;
  @Shadow
  private int scaledHeight;

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderHotbar", at = @At(value = "RETURN"))
    public void renderHotbarMixin(float f, MatrixStack matrixStack, CallbackInfo info) {
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity != null) {
            ItemStack leftBeltStack = playerEntity.inventory.getStack(41);
            ItemStack rightBeltStack = playerEntity.inventory.getStack(42);
            // ItemStack potionBeltStack = playerEntity.inventory.getStack(43);
    
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            int i = this.scaledWidth / 2;
            int p = this.scaledHeight - 16 - 3;
            Arm arm = playerEntity.getMainArm().getOpposite();
    
            // if (!leftBeltStack.isEmpty() || !rightBeltStack.isEmpty() || !potionBeltStack.isEmpty()) {
            if (!leftBeltStack.isEmpty() || !rightBeltStack.isEmpty()) {
                if (arm == Arm.LEFT) {
                    RenderSystem.enableBlend();
                    this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
                    this.drawTexture(matrixStack, i - 112 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    this.renderHotbarItem(i - 112 - 26, p, f, playerEntity, rightBeltStack);
    
                    RenderSystem.enableBlend();
                    this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
                    this.drawTexture(matrixStack, i - 133 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    this.renderHotbarItem(i - 133 - 26, p, f, playerEntity, leftBeltStack);
                    
                    // RenderSystem.enableBlend();
                    // this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
                    // this.drawTexture(matrixStack, i - 154 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    // this.renderHotbarItem(i - 154 - 26, p, f, playerEntity, potionBeltStack);
                } else {
                    RenderSystem.enableBlend();
                    this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
                    this.drawTexture(matrixStack, i + 112 + 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    this.renderHotbarItem(i + 112 + 26, p, f, playerEntity, rightBeltStack);
    
                    RenderSystem.enableBlend();
                    this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
                    this.drawTexture(matrixStack, i + 133 + 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    this.renderHotbarItem(i + 133 + 26, p, f, playerEntity, leftBeltStack);
                    
                    // RenderSystem.enableBlend();
                    // this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
                    // this.drawTexture(matrixStack, i + 154 + 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    // this.renderHotbarItem(i + 154 + 26, p, f, playerEntity, potionBeltStack);
                }
                RenderSystem.disableBlend();
            }
        }
    }

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return (PlayerEntity) this.client.getCameraEntity();
    }

    @Shadow
        private void renderHotbarItem(int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack) {
    }

}