package mod.eugene.curiosbasicitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.buffer.Unpooled;
import mod.eugene.curiosbasicitems.NetworkPackets;

import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {
  @Shadow
  private final Entity entity;

  public EntityTrackerEntryMixin(Entity entity) {
    this.entity = entity;
  }

  @Inject(method = "startTracking", at = @At(value = "TAIL"))
  public void startTrackingMixin(ServerPlayerEntity serverPlayer, CallbackInfo info) {
    if (entity instanceof PlayerEntity) {
        PlayerEntity player = (PlayerEntity) entity;
        for (int i = 41; i < 43; i++) {
            if (!serverPlayer.inventory.getStack(i).isEmpty()) {
                PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
                data.writeIntArray(new int[] { serverPlayer.getEntityId(), i });
                data.writeItemStack(serverPlayer.inventory.getStack(i));
                ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, NetworkPackets.VISIBILITY_UPDATE_PACKET, data);
            }
            if (!player.inventory.getStack(i).isEmpty()) {
                PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
                data.writeIntArray(new int[] { player.getEntityId(), i });
                data.writeItemStack(player.inventory.getStack(i));
                ServerSidePacketRegistry.INSTANCE.sendToPlayer(serverPlayer, NetworkPackets.VISIBILITY_UPDATE_PACKET, data);
            }
        }
    }
  }

}
