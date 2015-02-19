package net.minetrek.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCable extends Block {
	public BlockCable(){
		super(Material.circuits);
		setStepSound(soundTypeMetal);
		setHardness(0.0f);
		setResistance(1.0f);
		
	}
	public boolean isOpaqueCube(){
		return false;
	}
	
}
