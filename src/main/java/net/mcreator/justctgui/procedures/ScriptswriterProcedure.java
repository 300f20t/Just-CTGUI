package net.mcreator.justctgui.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraft.client.gui.components.EditBox;
import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ScriptswriterProcedure {
    public static void execute(HashMap<String, Object> guistate) {
        if (guistate == null)
            return;
        
        File generated;
        String fileName = guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "";
        
        if (fileName.isEmpty()) {
            generated = new File(FMLPaths.GAMEDIR.get().toString() + "/scripts/", "generated" + String.format("%.2f", JustCtguiModVariables.generated_count) + ".zs");
        } else {
            generated = new File(FMLPaths.GAMEDIR.get().toString() + "/scripts/", fileName + ".zs");
        }
        
        try {
            generated.getParentFile().mkdirs();
            generated.createNewFile();
            
            BufferedWriter generatedbw = new BufferedWriter(new FileWriter(generated));
            generatedbw.write(JustCtguiModVariables.Generated_recipe);
            generatedbw.newLine();
            generatedbw.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
        JustCtguiModVariables.generated_count = JustCtguiModVariables.generated_count + 1;
    }
}
