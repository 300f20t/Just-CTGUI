
package net.mcreator.justctgui.network;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.justctgui.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.justctgui.procedures.Iteminslot1incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot0incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record BlastFurnaceCTGUISlotMessage(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(JustCtguiMod.MODID, "blast_furnace_ctgui_slots");
	public BlastFurnaceCTGUISlotMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	@Override
	public void write(final FriendlyByteBuf buffer) {
		buffer.writeInt(slotID);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(changeType);
		buffer.writeInt(meta);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handleData(final BlastFurnaceCTGUISlotMessage message, final PlayPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.workHandler().submitAsync(() -> {
				Player entity = context.player().get();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			}).exceptionally(e -> {
				context.packetHandler().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = BlastFurnaceCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			Iteminslot0incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 0) {

			Iteminslot1incraftingtableCTGUIProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		JustCtguiMod.addNetworkMessage(BlastFurnaceCTGUISlotMessage.ID, BlastFurnaceCTGUISlotMessage::new, BlastFurnaceCTGUISlotMessage::handleData);
	}
}
