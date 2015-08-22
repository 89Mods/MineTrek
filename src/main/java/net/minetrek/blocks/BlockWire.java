package net.minetrek.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWire extends BlockContainer {
	float pixel = 1F/16F;
	public BlockWire(){
		super(Material.circuits);
		
	}
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state){
		TileEntityWire pipe = (TileEntityWire)world.getTileEntity(pos);
		if(pipe != null){
			float minX = 11*pixel/2-(pipe.connections[5]!=null?(11*pixel/2):0);
			float minY = 11*pixel/2-(pipe.connections[1]!=null?(11*pixel/2):0);
			float maxX = 1-11*pixel/2+(pipe.connections[3]!=null?(11*pixel/2):0);
			float maxY = 1-11*pixel/2+(pipe.connections[0]!=null?(11*pixel/2):0);
			float minZ = 11*pixel/2-(pipe.connections[2]!=null?(11*pixel/2):0);
			float maxZ = 1-11*pixel/2+(pipe.connections[4]!=null?(11*pixel/2):0);
			
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		return AxisAlignedBB.fromBounds(pos.getX()+this.minX, pos.getY()+this.minY, pos.getZ()+this.minZ, pos.getX()+this.maxX, pos.getY()+this.maxY, pos.getZ()+this.maxZ);
	}
	public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos){
		TileEntityWire pipe = (TileEntityWire)world.getTileEntity(pos);
		if(pipe != null){
			float minX = 11*pixel/2-(pipe.connections[5]!=null?(11*pixel/2):0);
			float minY = 11*pixel/2-(pipe.connections[1]!=null?(11*pixel/2):0);
			float maxX = 1-11*pixel/2+(pipe.connections[3]!=null?(11*pixel/2):0);
			float maxY = 1-11*pixel/2+(pipe.connections[0]!=null?(11*pixel/2):0);
			float minZ = 11*pixel/2-(pipe.connections[2]!=null?(11*pixel/2):0);
			float maxZ = 1-11*pixel/2+(pipe.connections[4]!=null?(11*pixel/2):0);
			
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		return AxisAlignedBB.fromBounds(pos.getX()+this.minX, pos.getY()+this.minY, pos.getZ()+this.minZ, pos.getX()+this.maxX, pos.getY()+this.maxY, pos.getZ()+this.maxZ);
		
	}
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityWire();
	}
	@Override
	public int getRenderType(){
		return -1;
	}
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
}
