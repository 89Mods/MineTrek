package net.minetrek.entities.projectiles;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class PhaserBoltRenderer extends Render {
	private static final ResourceLocation arrowTextures = new ResourceLocation("minetrek:textures/entity/arrow.png");
    public PhaserBoltRenderer(RenderManager manager)
    {
        super(manager);
    }
	public void renderArrow(EntityPhaserBolt entity, double par2, double par4, double par6, float par8, float par9) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)par2, (float)par4, (float)par6);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float)(0 + b0 * 10) / 32.0F;
        float f5 = (float)(5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float)(5 + b0 * 10) / 32.0F;
        float f9 = (float)(10 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GlStateManager.enableRescaleNormal();
        float f11 = -par9;
        worldrenderer.setColorRGBA(255, 0, 0, 230);
        if (f11 > 0.0F)
        {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GlStateManager.rotate(f12, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(f10, f10, f10);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        double len = 180D;
		if (entity.throwe != null)
			len = entity.throwe.getDistanceToEntity(entity) * 17.5;
		for (int i = 0; i < 4; ++i) {
			GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glNormal3f(0.0f, 0.0F, f10);
			worldrenderer.startDrawingQuads();
			worldrenderer.setColorRGBA(255, 0, 0, 230);
			worldrenderer.addVertex(-len, -2.0D, 0.0D);
			worldrenderer.addVertex(len, -2.0D, 0.0D);
			worldrenderer.addVertex(len, 2.0D, 0.0D);
			worldrenderer.addVertex(-len, 2.0D, 0.0D);
			tessellator.draw();
		}
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        //super.doRender(entity, par2, par4, par6, par8, par9);
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return arrowTextures;
	}
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderArrow((EntityPhaserBolt) par1Entity, par2, par4, par6, par8, par9);
	}
}
