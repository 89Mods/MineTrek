package net.minetrek.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TransparentAluminum extends Block {
	public TransparentAluminum() {
		super(Material.glass);
		setHardness(20.0F); // 33% harder than diamond
		setStepSound(Block.soundTypeMetal);
		setUnlocalizedName("transparent_aluminum");
		this.setResistance(500.0F);
		
	}
    public boolean isOpaqueCube()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
    public boolean isFullCube()
    {
        return false;
    }
    
}
