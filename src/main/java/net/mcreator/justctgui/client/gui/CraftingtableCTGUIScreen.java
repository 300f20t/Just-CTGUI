package net.mcreator.justctgui.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.VerticalispressedProcedure;
import net.mcreator.justctgui.procedures.NoneispressedProcedure;
import net.mcreator.justctgui.procedures.HorizontalispressedProcedure;
import net.mcreator.justctgui.procedures.DiagonalispressedProcedure;
import net.mcreator.justctgui.procedures.AllispressedProcedure;
import net.mcreator.justctgui.procedures.ATTENTIONProcedure;
import net.mcreator.justctgui.network.CraftingtableCTGUIButtonMessage;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class CraftingtableCTGUIScreen extends AbstractContainerScreen<CraftingtableCTGUIMenu> {
	private final static HashMap<String, Object> guistate = CraftingtableCTGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox recipe_name;
	EditBox file_name;
	Checkbox Is_shapeless;
	Checkbox Is_mirrored;
	Button button_all;
	Button button_diagonal;
	Button button_horizontal;
	Button button_none;
	Button button_vertical;
	Button button_generate;
	Button button_save;
	Button button_close;

	public CraftingtableCTGUIScreen(CraftingtableCTGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("just_ctgui:textures/screens/craftingtable_ctgui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		recipe_name.render(ms, mouseX, mouseY, partialTicks);
		file_name.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		if (ATTENTIONProcedure.execute())
			if (mouseX > leftPos + -121 && mouseX < leftPos + -97 && mouseY > topPos + 129 && mouseY < topPos + 153)
				this.renderTooltip(ms, Component.translatable("gui.just_ctgui.craftingtable_ctgui.tooltip_attention_this_configuration_wil"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		if (DiagonalispressedProcedure.execute()) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/the_button_is_pressed.png"));
			this.blit(ms, this.leftPos + 186, this.topPos + -29, 0, 0, 16, 16, 16, 16);
		}
		if (VerticalispressedProcedure.execute()) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/the_button_is_pressed.png"));
			this.blit(ms, this.leftPos + 276, this.topPos + -29, 0, 0, 16, 16, 16, 16);
		}
		if (HorizontalispressedProcedure.execute()) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/the_button_is_pressed.png"));
			this.blit(ms, this.leftPos + 96, this.topPos + -29, 0, 0, 16, 16, 16, 16);
		}
		if (NoneispressedProcedure.execute()) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/the_button_is_pressed.png"));
			this.blit(ms, this.leftPos + -2, this.topPos + -29, 0, 0, 16, 16, 16, 16);
		}
		if (AllispressedProcedure.execute()) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/the_button_is_pressed.png"));
			this.blit(ms, this.leftPos + -74, this.topPos + -29, 0, 0, 16, 16, 16, 16);
		}

		RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/crafting_table.png"));
		this.blit(ms, this.leftPos + 90, this.topPos + 34, 0, 0, 24, 17, 24, 17);

		if (ATTENTIONProcedure.execute()) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("just_ctgui:textures/screens/popup_hint.png"));
			this.blit(ms, this.leftPos + -117, this.topPos + 133, 0, 0, 16, 16, 16, 16);
		}
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
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		recipe_name.tick();
		file_name.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.just_ctgui.craftingtable_ctgui.label_recipe_name"), -123, -2, -3355393);
		this.font.draw(poseStack, Component.translatable("gui.just_ctgui.craftingtable_ctgui.label_file_name"), -123, 34, -3355393);
		this.font.draw(poseStack, Component.translatable("gui.just_ctgui.craftingtable_ctgui.label_empty"), -119, -38, -4610825);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		recipe_name = new EditBox(this.font, this.leftPos + -123, this.topPos + 7, 120, 20, Component.translatable("gui.just_ctgui.craftingtable_ctgui.recipe_name")) {
			{
				setSuggestion(Component.translatable("gui.just_ctgui.craftingtable_ctgui.recipe_name").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.craftingtable_ctgui.recipe_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.craftingtable_ctgui.recipe_name").getString());
				else
					setSuggestion(null);
			}
		};
		recipe_name.setMaxLength(32767);
		guistate.put("text:recipe_name", recipe_name);
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -123, this.topPos + 43, 120, 20, Component.translatable("gui.just_ctgui.craftingtable_ctgui.file_name")) {
			{
				setSuggestion(Component.translatable("gui.just_ctgui.craftingtable_ctgui.file_name").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.craftingtable_ctgui.file_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.craftingtable_ctgui.file_name").getString());
				else
					setSuggestion(null);
			}
		};
		file_name.setMaxLength(32767);
		guistate.put("text:file_name", file_name);
		this.addWidget(this.file_name);
		button_all = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_all"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(0, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + -119, this.topPos + -29, 40, 20).build();
		guistate.put("button:button_all", button_all);
		this.addRenderableWidget(button_all);
		button_diagonal = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_diagonal"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(1, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 114, this.topPos + -29, 67, 20).build();
		guistate.put("button:button_diagonal", button_diagonal);
		this.addRenderableWidget(button_diagonal);
		button_horizontal = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_horizontal"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(2, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + -29, 77, 20).build();
		guistate.put("button:button_horizontal", button_horizontal);
		this.addRenderableWidget(button_horizontal);
		button_none = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_none"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(3, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + -56, this.topPos + -29, 46, 20).build();
		guistate.put("button:button_none", button_none);
		this.addRenderableWidget(button_none);
		button_vertical = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_vertical"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(4, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 204, this.topPos + -29, 67, 20).build();
		guistate.put("button:button_vertical", button_vertical);
		this.addRenderableWidget(button_vertical);
		button_generate = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_generate"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(5, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_save"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(6, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.just_ctgui.craftingtable_ctgui.button_close"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(7, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 51, 20).build();
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
		Is_shapeless = new Checkbox(this.leftPos + -119, this.topPos + 97, 20, 20, Component.translatable("gui.just_ctgui.craftingtable_ctgui.Is_shapeless"), false);
		guistate.put("checkbox:Is_shapeless", Is_shapeless);
		this.addRenderableWidget(Is_shapeless);
		Is_mirrored = new Checkbox(this.leftPos + -119, this.topPos + 70, 20, 20, Component.translatable("gui.just_ctgui.craftingtable_ctgui.Is_mirrored"), false);
		guistate.put("checkbox:Is_mirrored", Is_mirrored);
		this.addRenderableWidget(Is_mirrored);
	}
}
