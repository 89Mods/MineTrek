package net.minetrek.blocks.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class Ore extends Block {
	public String name;
	public Ore(String name, int harvestLevel) {
		super(Material.rock);

		setStepSound(Block.soundTypeStone);
		setUnlocalizedName(this.name = (name + "Ore"));
		setHarvestLevel("pickaxe", harvestLevel);
		
	}
	public abstract int getNumberPerChunk(int dimension);
	public abstract int getMaxGenHeight(int dimension);
	public int getMinGenHeight(int dimension) {
		return 0;
	}
	public abstract int blocksPerVein(int dimension);
	
}
