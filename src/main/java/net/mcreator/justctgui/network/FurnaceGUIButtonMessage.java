
package net.mcreator.justctgui.network;

import net.minecraft.world.level.Level;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.justctgui.world.inventory.FurnaceGUIMenu;
import net.mcreator.justctgui.procedures.ScriptsWriterProcedure;
import net.mcreator.justctgui.procedures.GUIcloseProcedure;
import net.mcreator.justctgui.procedures.FurnaceGenerateRecipesProcedure;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class FurnaceGUIButtonMessage extends FriendlyByteBuf {
	public FurnaceGUIButtonMessage(int buttonID, int x, int y, int z) {
		super(Unpooled.buffer());
		writeInt(buttonID);
		writeInt(x);
		writeInt(y);
		writeInt(z);
	}

	public static void apply(MinecraftServer server, ServerPlayer entity, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int buttonID = buf.readInt();
		double x = buf.readInt();
		double y = buf.readInt();
		double z = buf.readInt();
		server.execute(() -> {
			Level world = entity.level();
			HashMap guistate = FurnaceGUIMenu.guistate;
			if (buttonID == 0) {

				ScriptsWriterProcedure.execute();
			}
			if (buttonID == 1) {

				FurnaceGenerateRecipesProcedure.execute(world, guistate);
			}
			if (buttonID == 2) {

				GUIcloseProcedure.execute(entity);
			}
		});
	}
}
