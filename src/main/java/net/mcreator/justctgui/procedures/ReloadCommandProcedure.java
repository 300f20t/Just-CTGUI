package net.mcreator.justctgui.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

public class ReloadCommandProcedure {
	public static void execute(IWorld world, double x, double y, double z) {
		if (world instanceof ServerWorld)
			((ServerWorld) world).getServer().getCommandManager()
					.handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, ((ServerWorld) world), 4, "", new StringTextComponent(""), ((ServerWorld) world).getServer(), null).withFeedbackDisabled(), "reload");
	}
}
