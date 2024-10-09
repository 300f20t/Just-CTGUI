package net.mcreator.recipe_generator.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.function.Supplier;
import java.util.Map;

public class Iteminslot6incraftingtableCTGUIProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		RecipeGeneratorMod.queueServerWork(1, () -> {
			RecipeGeneratorModVariables.item_in_slot_6_crafting_table = "<item:" + (ForgeRegistries.ITEMS
					.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(6)).getItem() : ItemStack.EMPTY).getItem()).toString()) + ">";
		});
	}
}
