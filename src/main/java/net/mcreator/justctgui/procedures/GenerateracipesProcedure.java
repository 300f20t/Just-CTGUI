package net.mcreator.justctgui.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class GenerateracipesProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		JustCtguiModVariables.Pre_generated_recipe = (guistate.containsKey("checkbox:Is_shapeless") ? ((CheckboxButton) guistate.get("checkbox:Is_shapeless")).isChecked() : false)
				? (JustCtguiModVariables.item_in_slot_9_crafting_table + ", [" + JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_2_crafting_table + ", " + JustCtguiModVariables.item_in_slot_3_crafting_table + ", " + JustCtguiModVariables.item_in_slot_4_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_5_crafting_table + ", " + JustCtguiModVariables.item_in_slot_6_crafting_table + ", " + JustCtguiModVariables.item_in_slot_7_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_8_crafting_table + "]);").replace("<item:minecraft:air>, ", "")
				: JustCtguiModVariables.item_in_slot_9_crafting_table + ", [[" + JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_2_crafting_table + "], [" + JustCtguiModVariables.item_in_slot_3_crafting_table + ", " + JustCtguiModVariables.item_in_slot_4_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_5_crafting_table + "], [" + JustCtguiModVariables.item_in_slot_6_crafting_table + ", " + JustCtguiModVariables.item_in_slot_7_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_8_crafting_table + "]]);";
		JustCtguiModVariables.Generated_recipe = ((guistate.containsKey("checkbox:Is_mirrored") ? ((CheckboxButton) guistate.get("checkbox:Is_mirrored")).isChecked() : false)
				? "import crafttweaker.api.recipe.MirrorAxis;" + "craftingTable.addShapedMirrored(\""
				: "")
				+ "" + ((guistate.containsKey("checkbox:Is_shapeless") ? ((CheckboxButton) guistate.get("checkbox:Is_shapeless")).isChecked() : false) ? "craftingTable.addShapeless(\"" : "")
				+ (!((guistate.containsKey("checkbox:Is_mirrored") ? ((CheckboxButton) guistate.get("checkbox:Is_mirrored")).isChecked() : false)
						|| (guistate.containsKey("checkbox:Is_shapeless") ? ((CheckboxButton) guistate.get("checkbox:Is_shapeless")).isChecked() : false)) ? "craftingTable.addShaped(\"" : "")
				+ ((guistate.containsKey("text:recipe_name") ? ((TextFieldWidget) guistate.get("text:recipe_name")).getText() : "").isEmpty()
						? "no_name"
						: (guistate.containsKey("text:recipe_name") ? ((TextFieldWidget) guistate.get("text:recipe_name")).getText() : ""))
				+ "\", " + JustCtguiModVariables.Pre_generated_recipe;
		if (entity instanceof PlayerEntity && !((PlayerEntity) entity).world.isRemote())
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(JustCtguiModVariables.Generated_recipe), false);
	}
}
