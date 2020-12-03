package mod.eugene.curiosbasicitems;

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
import mod.eugene.curiosbasicitems.items.belt.BeltLeather;
import mod.eugene.curiosbasicitems.models.ModelRegister;

public class ClientRegister implements ClientModInitializer {

    public static final String CONFIG_OPEN_DESC = "key.curiosbasicitems.opencraft.desc";
    public static final String CONFIG_LBELT_DESC = "key.curiosbasicitems.left_belt.desc";
    public static final String CONFIG_RBELT_DESC = "key.curiosbasicitems.right_belt.desc";
    public static final String CONFIG_PBELT_DESC = "key.curiosbasicitems.potion_belt.desc";
    public static final String CONFIG_EATBELT_DESC = "key.curiosbasicitems.eat_belt.desc";
    public static final String CONFIG_CATEGORY = "key.curiosbasicitems.category";

    public static KeyBinding useBackItemSlot;
    public static KeyBinding useLeftBeltSlot;
    public static KeyBinding useRightBeltSlot;
    public static KeyBinding switchPotionBeltSlot;
    public static KeyBinding eatPotionBeltSlot;

    public static boolean leftBeltSlotKeyLock;
    public static boolean rightBeltSlotKeyLock;
    public static boolean potionBeltSlotKeyLock;

    public static int leftBeltDelayCountdown = 0;
    public static int rightBeltDelayCountdown = 0;

    private static int targetSlot = CuriosBasicItems.RIGHT_BELT_SLOT;
    

    @Override
    public void onInitializeClient() {
        
        useBackItemSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_OPEN_DESC, GLFW.GLFW_KEY_V, CONFIG_CATEGORY));
        useLeftBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_LBELT_DESC, GLFW.GLFW_KEY_Z, CONFIG_CATEGORY));
        useRightBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_RBELT_DESC, GLFW.GLFW_KEY_X, CONFIG_CATEGORY));
        // switchPotionBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_PBELT_DESC, GLFW.GLFW_KEY_C, CONFIG_CATEGORY));
        // eatPotionBeltSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(CONFIG_EATBELT_DESC, GLFW.GLFW_KEY_E, CONFIG_CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register((minecraftClient -> {
            if(leftBeltDelayCountdown > 0) leftBeltDelayCountdown -=1;
            if(rightBeltDelayCountdown > 0) rightBeltDelayCountdown -=1;
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
            if (clientPlayerEntity != null){
                if (useBackItemSlot.wasPressed()) {
                    ClientSidePacketRegistry.INSTANCE.sendToServer(NetworkPackets.USE_BACK_ITEM, new PacketByteBuf(Unpooled.buffer()));
                }
                
                if (useLeftBeltSlot.isPressed()){
                    if(leftBeltDelayCountdown == 0) sendPacket(CuriosBasicItems.LEFT_BELT_SLOT, NetworkPackets.SWITCH_LEFT_BELT_ITEM);
                    leftBeltDelayCountdown = 2;
                }
                //Potion slot
                //Press
                if(useRightBeltSlot.wasPressed() && BeltLeather.allowInstantEat(clientPlayerEntity)) sendPacket(CuriosBasicItems.RIGHT_BELT_SLOT, NetworkPackets.INSTANT_EAT_PACKET);

                //Hold
                if (useRightBeltSlot.isPressed() && !BeltLeather.allowInstantEat(clientPlayerEntity)) {
                    if (rightBeltDelayCountdown == 0){
                        targetSlot = clientPlayerEntity.inventory.selectedSlot;
                        sendPacket(targetSlot, NetworkPackets.SWITCH_RIGHT_BELT_ITEM);
                    }
                    minecraftClient.options.keyUse.setPressed(true);
                    rightBeltDelayCountdown = 4;
                } 
                
                if (rightBeltDelayCountdown == 1 || 
                (targetSlot != clientPlayerEntity.inventory.selectedSlot && rightBeltDelayCountdown >0)){
                    minecraftClient.options.keyUse.setPressed(false);
                    sendPacket(targetSlot, NetworkPackets.SWITCH_RIGHT_BELT_ITEM);
                    rightBeltDelayCountdown = 0;
                }
            }
        }));

        
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((spriteAtlasTexture, registry) -> {
            registry.register(new Identifier(CuriosBasicItems.MODID, "item/empty_mask_slot"));
            registry.register(new Identifier(CuriosBasicItems.MODID, "item/empty_left_belt_slot"));
            registry.register(new Identifier(CuriosBasicItems.MODID, "item/empty_right_belt_slot"));
        }));


        NetworkPackets.clientRegister();
        ModelRegister.register();
    }

    public static void sendPacket(int slot, Identifier type) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(slot);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(type, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }
}