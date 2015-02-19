package net.minetrek.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockWarpcore extends Block {
	public BlockWarpcore(){
		super(Material.iron);
		setHarvestLevel("pickaxe",1);
		setLightLevel(2.0f);
	}
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(playerIn.isRiding()){
    		return false;
    	}
    	if(playerIn.riddenByEntity != null){
    		return false;
    	}
    	if(playerIn.dimension != 0 && playerIn.dimension != 7400){
    		return false;
    	}
    	if(playerIn.dimension == 0){
    	playerIn.travelToDimension(7400);
    	}else if(playerIn.dimension == 7400){
        playerIn.travelToDimension(0);
        }
    	return true;
    }
}
