package net.insane96mcp.vulcanite.network;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Vulcanite.MOD_ID);
	private static int discriminator = 0;
	
	public static void Init() {
		INSTANCE.registerMessage(BlockBreak.Handler.class, BlockBreak.class, discriminator++, Side.CLIENT);
	}
	
	public static void SendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}
	
	public static void SendToClient(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}
	
	public static void SendToAll(IMessage message) {
		INSTANCE.sendToAll(message);
	}
}
