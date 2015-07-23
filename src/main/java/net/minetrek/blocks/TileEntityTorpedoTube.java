package net.minetrek.blocks;

import java.util.Random;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minetrek.entities.projectiles.EntityPhaserBolt;
import net.minetrek.entities.projectiles.EntityPhotonTorpedo;

public class TileEntityTorpedoTube extends TileEntity implements SimpleComponent {
	
	public TileEntityTorpedoTube(){
		
	}

	@Override
	public String getComponentName() {
		return "minetrek_torpedo_tube";
	}
	@Callback
	public Object[] fire(Context context, Arguments args) {
		BlockTorpedoTube block = (BlockTorpedoTube)this.worldObj.getBlockState(this.pos).getBlock();
		IBlockState state = this.worldObj.getBlockState(this.pos);
		boolean success = false;
		if((EnumFacing)state.getValue(block.FACING) == EnumFacing.NORTH){
			this.shoot(this.worldObj, 0, 0, -10, this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1);
			success = true;
		}else if((EnumFacing)state.getValue(block.FACING) == EnumFacing.SOUTH){
			this.shoot(this.worldObj, 0, 0, 10, this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1);
			success = true;
		}else if((EnumFacing)state.getValue(block.FACING) == EnumFacing.EAST){
			this.shoot(this.worldObj, 10, 0, 0, this.pos.getX() + 1, this.pos.getY(), this.pos.getZ());
			success = true;
		}else if((EnumFacing)state.getValue(block.FACING) == EnumFacing.WEST){
			this.shoot(this.worldObj, -10, 0, 0, this.pos.getX() - 1, this.pos.getY(), this.pos.getZ());
			success = true;
		}
		return new Object[] {success, "FIRE" };
	}
	public void shoot(World world, int x, int y, int z, int fromX, int fromY, int fromZ) {
		EntityPhotonTorpedo e = new EntityPhotonTorpedo(world, fromX, fromY, fromZ);
		e.setThrowableHeading(x, y,  z, 7.0F, 0.0F);
		//world.playSound(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "minetrek:photonTorpedo", 10F, 1.0F, true);
		world.spawnEntityInWorld(e);
		world.playSoundAtEntity(e, "minetrek:photonTorpedo", 10F, 1.0F);
	}
}
