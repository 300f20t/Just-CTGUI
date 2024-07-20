
package net.mcreator.justctgui.network;

import net.minecraft.world.level.Level;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.Iteminslot9incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot8incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot7incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot6incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot5incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot4incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot3incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot2incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot1incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot0incraftingtableCTGUIProcedure;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class CraftingtableCTGUISlotMessage extends FriendlyByteBuf {
	public CraftingtableCTGUISlotMessage(int slot, int x, int y, int z, int changeType, int meta) {
		super(Unpooled.buffer());
		writeInt(slot);
		writeInt(x);
		writeInt(y);
		writeInt(z);
		writeInt(changeType);
		writeInt(meta);
	}

	public static void apply(MinecraftServer server, ServerPlayer entity, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int slot = buf.readInt();
		double x = buf.readInt();
		double y = buf.readInt();
		double z = buf.readInt();
		int changeType = buf.readInt();
		int meta = buf.readInt();
		server.execute(() -> {
			Level world = entity.level();
			HashMap guistate = CraftingtableCTGUIMenu.guistate;
			if (slot == 0 && changeType == 0) {

				Iteminslot0incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 1 && changeType == 0) {

				Iteminslot1incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 2 && changeType == 0) {

				Iteminslot2incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 3 && changeType == 0) {

				Iteminslot3incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 4 && changeType == 0) {

				Iteminslot4incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 5 && changeType == 0) {

				Iteminslot5incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 6 && changeType == 0) {

				Iteminslot6incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 7 && changeType == 0) {

				Iteminslot7incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 8 && changeType == 0) {

				Iteminslot8incraftingtableCTGUIProcedure.execute(world, entity);
			}
			if (slot == 9 && changeType == 0) {

				Iteminslot9incraftingtableCTGUIProcedure.execute(world, entity);
			}
		});
	}
}
