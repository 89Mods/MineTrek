package net.minetrek.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockComputer extends Block {
	public BlockComputer(){
		super(Material.iron);
		setHarvestLevel("pickaxe",1);
		
	}
	
}
