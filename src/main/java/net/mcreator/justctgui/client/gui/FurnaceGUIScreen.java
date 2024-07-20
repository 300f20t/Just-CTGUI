
package net.mcreator.justctgui.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.justctgui.world.inventory.FurnaceGUIMenu;
import net.mcreator.justctgui.network.FurnaceGUIButtonMessage;
import net.mcreator.justctgui.JustCtguiMod;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class FurnaceGUIScreen extends AbstractContainerScreen<FurnaceGUIMenu> {
	private final static HashMap<String, Object> guistate = FurnaceGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox recipe_name;
	EditBox file_name;
	EditBox burn_time;
	EditBox XP;
	Button button_save;
	Button button_generate;
	Button button_close;

	public FurnaceGUIScreen(FurnaceGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("just_ctgui:textures/screens/furnace_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		recipe_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		file_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		burn_time.render(guiGraphics, mouseX, mouseY, partialTicks);
		XP.render(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("just_ctgui:textures/screens/crafting_table.png"), this.leftPos + 78, this.topPos + 34, 0, 0, 24, 17, 24, 17);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (recipe_name.isFocused())
			return recipe_name.keyPressed(key, b, c);
		if (file_name.isFocused())
			return file_name.keyPressed(key, b, c);
		if (burn_time.isFocused())
			return burn_time.keyPressed(key, b, c);
		if (XP.isFocused())
			return XP.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		recipe_name.tick();
		file_name.tick();
		burn_time.tick();
		XP.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.just_ctgui.furnace_gui.label_recipe_name"), -123, -3, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.just_ctgui.furnace_gui.label_file_name"), -123, 33, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.just_ctgui.furnace_gui.label_burn_time"), -123, 131, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.just_ctgui.furnace_gui.label_xp"), -123, 91, -3355393, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		recipe_name = new EditBox(this.font, this.leftPos + -123, this.topPos + 7, 120, 20, Component.translatable("gui.just_ctgui.furnace_gui.recipe_name")) {
			{
				setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.recipe_name").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.recipe_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.recipe_name").getString());
				else
					setSuggestion(null);
			}
		};
		recipe_name.setMaxLength(32767);
		guistate.put("text:recipe_name", recipe_name);
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -123, this.topPos + 43, 120, 20, Component.translatable("gui.just_ctgui.furnace_gui.file_name")) {
			{
				setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.file_name").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.file_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.file_name").getString());
				else
					setSuggestion(null);
			}
		};
		file_name.setMaxLength(32767);
		guistate.put("text:file_name", file_name);
		this.addWidget(this.file_name);
		burn_time = new EditBox(this.font, this.leftPos + -123, this.topPos + 141, 120, 20, Component.translatable("gui.just_ctgui.furnace_gui.burn_time")) {
			{
				setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.burn_time").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.burn_time").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.burn_time").getString());
				else
					setSuggestion(null);
			}
		};
		burn_time.setMaxLength(32767);
		guistate.put("text:burn_time", burn_time);
		this.addWidget(this.burn_time);
		XP = new EditBox(this.font, this.leftPos + -123, this.topPos + 101, 120, 20, Component.translatable("gui.just_ctgui.furnace_gui.XP")) {
			{
				setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.XP").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.XP").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.furnace_gui.XP").getString());
				else
					setSuggestion(null);
			}
		};
		XP.setMaxLength(32767);
		guistate.put("text:XP", XP);
		this.addWidget(this.XP);
		button_save = Button.builder(Component.translatable("gui.just_ctgui.furnace_gui.button_save"), e -> {
			if (true) {
				ClientPlayNetworking.send(new ResourceLocation(JustCtguiMod.MODID, "furnacegui_button_0"), new FurnaceGUIButtonMessage(0, x, y, z));
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_generate = Button.builder(Component.translatable("gui.just_ctgui.furnace_gui.button_generate"), e -> {
			if (true) {
				ClientPlayNetworking.send(new ResourceLocation(JustCtguiMod.MODID, "furnacegui_button_1"), new FurnaceGUIButtonMessage(1, x, y, z));
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_close = Button.builder(Component.translatable("gui.just_ctgui.furnace_gui.button_close"), e -> {
			if (true) {
				ClientPlayNetworking.send(new ResourceLocation(JustCtguiMod.MODID, "furnacegui_button_2"), new FurnaceGUIButtonMessage(2, x, y, z));
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 51, 20).build();
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
	}
}
