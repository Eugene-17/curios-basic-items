package mod.eugene.curiosbasicitems.models;
import mod.eugene.curiosbasicitems.CuriosBasicItems;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;
import top.theillusivec4.curios.client.render.model.AmuletModel;
import mod.eugene.curiosbasicitems.items.ItemsRegister;

public class ModelRegister {
    private static final Identifier IRON_AMULET_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/entity/iron_amulet.png");
    private static final Identifier NETHERITE_AMULET_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/entity/netherite_amulet.png");
    private static final Identifier STAR_AMULET_TEXTURE = new Identifier(CuriosBasicItems.MODID, "textures/entity/star_amulet.png");

    private static void register_amulet(Item amulet_, Identifier texture_){
        ItemComponentCallbackV2.event(amulet_).register(
        ((item, itemStack, componentContainer) -> componentContainer
            .put(CuriosComponent.ITEM_RENDER, new IRenderableCurio() {
              AmuletModel<LivingEntity> model = new AmuletModel<>();

              @Override
              public void render(String identifier, int index, MatrixStack matrixStack,
                  VertexConsumerProvider vertexConsumerProvider, int light,
                  LivingEntity livingEntity, float limbSwing, float limbSwingAmount,
                  float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                IRenderableCurio.RenderHelper.translateIfSneaking(matrixStack, livingEntity);
                IRenderableCurio.RenderHelper.rotateIfSneaking(matrixStack, livingEntity);
                VertexConsumer consumer = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, model.getLayer(texture_), false, itemStack.hasGlint());
                model.render(matrixStack, consumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
              }
            })));
    }
    public static void register() {
        register_amulet(ItemsRegister.IRON_HEALTH_AMULET, IRON_AMULET_TEXTURE);
        register_amulet(ItemsRegister.NETHERITE_HEALTH_AMULET, NETHERITE_AMULET_TEXTURE);
        register_amulet(ItemsRegister.STAR_HEALTH_AMULET, STAR_AMULET_TEXTURE);
    }
}
