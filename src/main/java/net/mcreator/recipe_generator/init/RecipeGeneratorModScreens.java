
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.recipe_generator.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingTableRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceCTGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RecipeGeneratorModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(RecipeGeneratorModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.FURNACE_CTGUI.get(), FurnaceCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.CRAFTING_TABLE_REMOVING_CTGUI.get(), CraftingTableRemovingCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.FURNACE_REMOVING_CTGUI.get(), FurnaceRemovingCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.BLAST_FURNACE_CTGUI.get(), BlastFurnaceCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.BLAST_FURNACE_REMOVING_CTGUI.get(), BlastFurnaceRemovingCTGUIScreen::new);
		});
	}
}
