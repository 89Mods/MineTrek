package net.minetrek.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import net.minetrek.entities.mob.EntityBorg;

public class RenderBorg extends RenderLiving {
	private static final ResourceLocation borgTextures = new ResourceLocation("textures/entity/borg/borg.png");
    public RenderBorg(RenderManager p_i46187_1_, ModelBase p_i46187_2_, float p_i46187_3_)
    {
        super(p_i46187_1_, p_i46187_2_, p_i46187_3_);
    }

    protected ResourceLocation func_180572_a(EntityBorg p_180572_1_)
    {
        return borgTextures;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.func_180572_a((EntityBorg)entity);
    }
    
}
