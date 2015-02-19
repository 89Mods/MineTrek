package net.minetrek.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class OreBlock extends Block {
	public OreBlock(){
		super(Material.iron);
		setHarvestLevel("pickaxe", 2);
	}
}
