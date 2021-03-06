package net.minetrek.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minetrek.blocks.machines.MagnetizerContainer;
import net.minetrek.blocks.machines.MagnetizerTileEntity;

public class GuiTorpedoLauncher extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation("minetrek", "textures/gui/torpedo_launcher.png");
	private final TileEntityTorpedoLauncher entity;
	public GuiTorpedoLauncher(InventoryPlayer invPlayer, TileEntityTorpedoLauncher entity) {
		super(new ContainerTorpedoLauncher(invPlayer, entity));
		xSize = 176;
		ySize = 165;
		this.entity = entity;
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int k, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		this.fontRendererObj.drawString("Power: " + entity.getEnergyStored(null) + "/2000" + " RF", 170, 50, 4215752);
	}
}
