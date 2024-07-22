package net.mcreator.justctgui.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class GenerateRemovingRecipesProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		JustCtguiModVariables.Generated_recipe = "craftingTable.remove(" + JustCtguiModVariables.item_in_slot_0_crafting_table + ");";
		if (entity instanceof PlayerEntity && !((PlayerEntity) entity).world.isRemote())
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(JustCtguiModVariables.Generated_recipe), false);
	}
}
