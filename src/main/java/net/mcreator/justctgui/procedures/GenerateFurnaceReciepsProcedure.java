package net.mcreator.justctgui.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class GenerateFurnaceReciepsProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		JustCtguiModVariables.Pre_generated_recipe = JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", " + new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:XP") ? ((TextFieldWidget) guistate.get("text:XP")).getText() : "") + ", " + Math.round(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:time") ? ((TextFieldWidget) guistate.get("text:time")).getText() : "")) + ");";
		if ((guistate.containsKey("text:recipe_name") ? ((TextFieldWidget) guistate.get("text:recipe_name")).getText() : "").isEmpty()) {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager;" + "furnace.addRecipe(\"" + "no_name" + "\", " + JustCtguiModVariables.Pre_generated_recipe;
		} else {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager;" + "furnace.addRecipe(\"" + (guistate.containsKey("text:recipe_name") ? ((TextFieldWidget) guistate.get("text:recipe_name")).getText() : "")
					+ "\", " + JustCtguiModVariables.Pre_generated_recipe;
		}
		if (entity instanceof PlayerEntity && !((PlayerEntity) entity).world.isRemote())
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(JustCtguiModVariables.Generated_recipe), false);
	}
}
