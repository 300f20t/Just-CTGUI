package net.mcreator.justctgui.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Iteminslot5incraftingtableCTGUIProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		new Object() {
			private int ticks = 0;

			public void startDelay(LevelAccessor world) {
				ServerTickEvents.END_SERVER_TICK.register((server) -> {
					this.ticks++;
					if (this.ticks == 1) {
						JustCtguiModVariables.item_in_slot_5_crafting_table = "<item:"
								+ (BuiltInRegistries.ITEM.getKey((entity instanceof ServerPlayer _plrSlotItem ? _plrSlotItem.containerMenu.getSlot(5).getItem() : ItemStack.EMPTY).getItem()).toString()) + ">";
						return;
					}
				});
			}
		}.startDelay(world);
	}
}
