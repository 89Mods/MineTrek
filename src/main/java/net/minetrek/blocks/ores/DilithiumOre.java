package net.minetrek.blocks.ores;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minetrek.items.MineTrekItems;

public class DilithiumOre extends Ore {
	public DilithiumOre() {
		super("dilithium", 3);

		setHardness(5.0F);
		setLightLevel(.4F);
	}

	@Override
	public int getNumberPerChunk(int dimension) {
		return 4;
	}

	@Override
	public int getMaxGenHeight(int dimension) {
		return 20;
	}

	@Override
	public int blocksPerVein(int dimension) {
		return 5;
	}

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MineTrekItems.dilithium_crystal;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }
}
