
package net.mcreator.justctgui.network;

import net.minecraft.world.level.Level;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.VerticalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.ScriptsWriterProcedure;
import net.mcreator.justctgui.procedures.NonemirroraxisProcedure;
import net.mcreator.justctgui.procedures.HorizontalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.GenerateracipesProcedure;
import net.mcreator.justctgui.procedures.GUIcloseProcedure;
import net.mcreator.justctgui.procedures.DiagonalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.AllmirroraxisProcedure;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class CraftingtableCTGUIButtonMessage extends FriendlyByteBuf {
	public CraftingtableCTGUIButtonMessage(int buttonID, int x, int y, int z) {
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
			HashMap guistate = CraftingtableCTGUIMenu.guistate;
			if (buttonID == 0) {

				AllmirroraxisProcedure.execute();
			}
			if (buttonID == 1) {

				DiagonalmirroraxisProcedure.execute();
			}
			if (buttonID == 2) {

				HorizontalmirroraxisProcedure.execute();
			}
			if (buttonID == 3) {

				NonemirroraxisProcedure.execute();
			}
			if (buttonID == 4) {

				VerticalmirroraxisProcedure.execute();
			}
			if (buttonID == 5) {

				GenerateracipesProcedure.execute(entity, guistate);
			}
			if (buttonID == 6) {

				ScriptsWriterProcedure.execute();
			}
			if (buttonID == 7) {

				GUIcloseProcedure.execute(entity);
			}
		});
	}
}
