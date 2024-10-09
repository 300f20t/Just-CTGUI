package net.mcreator.recipe_generator.procedures;

import java.util.HashMap;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		String fileName = "";
		FileNameCreatorProcedure.execute(guistate);
	}
}
