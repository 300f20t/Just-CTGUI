
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.mcreator.justctgui.world.inventory.FurnaceGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.JustCtguiMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

public class JustCtguiModMenus {
	public static MenuType<FurnaceGUIMenu> FURNACE_GUI;
	public static MenuType<CraftingtableCTGUIMenu> CRAFTINGTABLE_CTGUI;

	public static void load() {
		FURNACE_GUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(JustCtguiMod.MODID, "furnace_gui"), new ExtendedScreenHandlerType<>(FurnaceGUIMenu::new));
		FurnaceGUIMenu.screenInit();
		CRAFTINGTABLE_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(JustCtguiMod.MODID, "craftingtable_ctgui"), new ExtendedScreenHandlerType<>(CraftingtableCTGUIMenu::new));
		CraftingtableCTGUIMenu.screenInit();
	}
}
