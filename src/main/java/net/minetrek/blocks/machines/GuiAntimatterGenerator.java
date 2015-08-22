package net.minetrek.blocks.machines;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class GuiAntimatterGenerator extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation("minetrek", "textures/gui/antimatterGenerator.png");
	private IInventory entity;
	private InventoryPlayer playerInventory;
	public GuiAntimatterGenerator(InventoryPlayer invPlayer, IInventory generator) {
		super(new ContainerAntimatterGenerator(invPlayer, generator));
		this.playerInventory = invPlayer;
		this.entity = generator;
	}
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s2 = "Power: " + Integer.toString(entity.getField(2)) + " RF";
    	//String s = "Coal Generator";
        this.fontRendererObj.drawString(s2, this.xSize / 2 - this.fontRendererObj.getStringWidth(s2) / 2 + 36, 57, 4215752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4215752);
    }
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int k1, int j) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        //drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        if (TileEntityAntimatterGenerator.isBurning(entity))
        {
            i1 = this.func_175382_i(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }
		
	}
    private int func_175382_i(int p_175382_1_)
    {
        int j = this.entity.getField(1);

        if (j == 0)
        {
            j = 200;
        }

        return this.entity.getField(0) * p_175382_1_ / j;
    }
}
