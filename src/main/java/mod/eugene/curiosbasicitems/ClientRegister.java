package mod.eugene.curiosbasicitems;

import mod.eugene.curiosbasicitems.CuriosBasicItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;
import mod.eugene.curiosbasicitems.models.ModelRegister;

public class ClientRegister implements ClientModInitializer {

    public static final String CONFIG_OPEN_DESC = "key.curiosbasicitems.opencraft.desc";
    public static final String CONFIG_LBELT_DESC = "key.curiosbasicitems.left_belt.desc";
    public static final String CONFIG_RBELT_DESC = "key.curiosbasicitems.right_belt.desc";
    public static final String CONFIG_PBELT_DESC = "key.curiosbasicitems.potion_belt.desc";
    public static final String CONFIG_EATBELT_DESC = "key.curiosbasicitems.eat_belt.desc";
    public static final String CONFIG_CATEGORY = "key.curiosbasicitems.category";

    public static KeyBinding openCuriosCraftingTable;
    public static KeyBinding switchLeftBeltSlot;
    public static KeyBinding switchRightBeltSlot;
    public static KeyBinding switchPotionBeltSlot;
    public static KeyBinding eatPotionBeltSlot;

    public static boolean leftBeltSlotKeyLock;
    public static boolean rightBeltSlotKeyLock;
    public static boolean potionBeltSlotKeyLock;


    @Override
    public void onInitializeClient() {
        openCuriosCraftingTable = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_OPEN_DESC, GLFW.GLFW_KEY_V, CONFIG_CATEGORY));
        switchLeftBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_LBELT_DESC, GLFW.GLFW_KEY_Z, CONFIG_CATEGORY));
        switchRightBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_RBELT_DESC, GLFW.GLFW_KEY_X, CONFIG_CATEGORY));
        switchPotionBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_PBELT_DESC, GLFW.GLFW_KEY_C, CONFIG_CATEGORY));
        eatPotionBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_EATBELT_DESC, GLFW.GLFW_KEY_E, CONFIG_CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register((minecraftClient -> {
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
            if (clientPlayerEntity != null){
                if (openCuriosCraftingTable.wasPressed()) {
                    ClientSidePacketRegistry.INSTANCE.sendToServer(NetworkPackets.ACCESS_BACKSLOT, new PacketByteBuf(Unpooled.buffer()));
                }

                if (switchLeftBeltSlot.wasPressed()) {
                    if (!leftBeltSlotKeyLock) {
                        switchItem(41);
                    }
                    leftBeltSlotKeyLock = true;
                } else {
                    leftBeltSlotKeyLock = false;
                }

                if (switchRightBeltSlot.wasPressed()) {
                    if (!leftBeltSlotKeyLock) {
                        switchItem(42);
                    }
                    rightBeltSlotKeyLock = true;
                } else {
                    rightBeltSlotKeyLock = false;
                }

                if (switchPotionBeltSlot.wasPressed()) {
                    if (!leftBeltSlotKeyLock) {
                        switchItem(43);
                    }
                    potionBeltSlotKeyLock = true;
                } else {
                    potionBeltSlotKeyLock = false;
                }

                if (eatPotionBeltSlot.wasPressed()){
                    eatItem(43);
                }
            }
        }));

        
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((spriteAtlasTexture, registry) -> {
            registry.register(new Identifier(CuriosBasicItems.MODID, "item/empty_mask_slot"));
            registry.register(new Identifier(CuriosBasicItems.MODID, "item/empty_left_belt_slot"));
            registry.register(new Identifier(CuriosBasicItems.MODID, "item/empty_right_belt_slot"));
        }));


        NetworkPackets.visibilityUpdatePacketinit();
        ModelRegister.register();
    }

    public static void switchItem(int slot) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(slot);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(NetworkPackets.SWITCH_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void eatItem(int slot) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(slot);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(NetworkPackets.EAT_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }
}