
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.mcreator.justctgui.command.CtguicommandCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class JustCtguiModCommands {
	public static void load() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			CtguicommandCommand.register(dispatcher, commandBuildContext, environment);
		});
	}
}
