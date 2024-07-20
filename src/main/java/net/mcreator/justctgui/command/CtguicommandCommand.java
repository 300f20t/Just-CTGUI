
package net.mcreator.justctgui.command;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import net.mcreator.justctgui.procedures.OpencraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.OpenFurnaceGUIProcedure;

import com.mojang.brigadier.CommandDispatcher;

public class CtguicommandCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
		dispatcher.register(Commands.literal("ctgui").requires(s -> s.hasPermission(4)).then(Commands.literal("craftingTable").then(Commands.literal("addRecipe").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpencraftingtableCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))).then(Commands.literal("furnace").then(Commands.literal("addRecipe").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenFurnaceGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))));
	}
}
