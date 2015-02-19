package net.minetrek.blocks.ores;

import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InvisibleOre extends Ore {

	public InvisibleOre() {
		super("invisible", 3);
		setHardness(5.0F);

	}
	
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    public boolean isFullCube()
    {
        return false;
    }

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getNumberPerChunk(int dimension) {
		return 4;
	}

	@Override
	public int getMaxGenHeight(int dimension) {
		return 14;
	}

	@Override
	public int blocksPerVein(int dimension) {
		return 5;
	}

}
