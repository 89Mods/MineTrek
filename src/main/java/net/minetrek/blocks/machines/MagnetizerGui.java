package net.minetrek.blocks.machines;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class MagnetizerGui extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation("minetrek", "textures/gui/magnetizer.png");
	private final MagnetizerTileEntity entity;
	public MagnetizerGui(InventoryPlayer invPlayer, MagnetizerTileEntity entity) {
		super(new MagnetizerContainer(invPlayer, entity));
		xSize = 176;
		ySize = 165;
		this.entity = entity;
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int k, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (entity.isCooking()) {
			int bars = entity.getBurnTimeRemainingScaled(6);

			int start = 95;

			for (int i = 0; i < bars; i++) {

				this.drawRect(guiLeft + start, guiTop + 55, guiLeft + start + 4, guiTop + 62, Integer.MAX_VALUE);
				start += 5;
			}
			
		}
		this.fontRendererObj.drawString("Power: " + entity.getEnergyStored(null) + "/2000" + " RF", 170, 50, 4215752);
	}
	
}
