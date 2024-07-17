package net.mcreator.justctgui.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class GenerateRemovingFurnaceRecipesProcedure {
	public static void execute(LevelAccessor world) {
		JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager; " + "furnace.remove(" + JustCtguiModVariables.item_in_slot_0_crafting_table + ");";
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(JustCtguiModVariables.Generated_recipe), false);
	}
}
