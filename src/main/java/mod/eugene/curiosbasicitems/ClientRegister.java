package mod.eugene.curiosbasicitems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import io.netty.buffer.Unpooled;
import mod.eugene.curiosbasicitems.models.ModelRegister;

public class ClientRegister implements ClientModInitializer {

    public static final String CONFIG_OPEN_DESC = "key.curiosbasicitems.opencraft.desc";
    public static final String CONFIG_CATEGORY = "key.curiosbasicitems.category";

    public static KeyBinding openCuriosCraftingTable;

    @Override
    public void onInitializeClient() {
        openCuriosCraftingTable = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_OPEN_DESC, GLFW.GLFW_KEY_C, CONFIG_CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register((minecraftClient -> {
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        
            if (clientPlayerEntity != null && ClientRegister.openCuriosCraftingTable.wasPressed()) {
                ClientSidePacketRegistry.INSTANCE.sendToServer(NetworkPackets.ACCESS_WORKBENCH, new PacketByteBuf(Unpooled.buffer()));
            }
        }));

        ModelRegister.register();
    }
}