package net.minetrek.entities.projectiles;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPhotonTorpedo extends EntityThrowable {
	public Entity throwe;
	public EntityPhotonTorpedo(World world){
		super(world);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
		init();
	}
	public EntityPhotonTorpedo(World par1World, EntityLivingBase entity) {
		super(par1World, entity);
		throwe = entity;
        this.setSize(0.5F, 0.5F);
		init();
	}
	public EntityPhotonTorpedo(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		init();
	}
	private void init() {

		setVelocity(this.motionX * 3, this.motionY * 3, this.motionZ * 3);
		this.throwableShake = 0;
	}
	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if(!worldObj.isRemote){
			this.worldObj.playSoundAtEntity(this, "minetrek:explosion", 10.0F, 1.0F);
		}
		this.setDead();

		if (worldObj.isRemote)
			return;
		
		if (mop.entityHit != null){
			this.worldObj.createExplosion(mop.entityHit, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ, 10.7F, true);
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1000);
		}else{
		this.worldObj.createExplosion(this, mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ(), 10.7F, true);
		}
	}
	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	@Override
	public void setLocationAndAngles(double d1, double d2, double d3, float f1, float f2) {
		super.setPositionAndRotation(d1, d2, d3, f1, f2);
		float closest = 5.0F;
		Entity thisOne = null;
		for (int i = 0; i < worldObj.loadedEntityList.size(); i++) {
			if (((Entity) worldObj.loadedEntityList.get(i)).getDistanceToEntity(this) < closest) {
				closest = ((Entity) worldObj.loadedEntityList.get(i)).getDistanceToEntity(this);
				thisOne = ((Entity) worldObj.loadedEntityList.get(i));
			}
		}

		if (thisOne != null) {
			throwe = thisOne;
		}
	}
}
