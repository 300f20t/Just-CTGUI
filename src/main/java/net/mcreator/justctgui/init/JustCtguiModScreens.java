
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.justctgui.client.gui.FurnaceGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingtableCTGUIScreen;

public class JustCtguiModScreens {
	public static void load() {
		MenuScreens.register(JustCtguiModMenus.FURNACE_GUI, FurnaceGUIScreen::new);
		MenuScreens.register(JustCtguiModMenus.CRAFTINGTABLE_CTGUI, CraftingtableCTGUIScreen::new);
	}
}
