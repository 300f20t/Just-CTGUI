/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.mcreator.justctgui;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.mcreator.justctgui.init.JustCtguiModProcedures;
import net.mcreator.justctgui.init.JustCtguiModMenus;
import net.mcreator.justctgui.init.JustCtguiModCommands;

import net.fabricmc.api.ModInitializer;

public class JustCtguiMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "just_ctgui";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing JustCtguiMod");

		JustCtguiModProcedures.load();
		JustCtguiModCommands.load();

		JustCtguiModMenus.load();

	}
}
