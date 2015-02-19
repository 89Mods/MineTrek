package net.minetrek;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minetrek.items.MineTrekItems;

public class MineTrekCreativeTab extends CreativeTabs {

	public MineTrekCreativeTab(String label) {
		super(label);
	}

	public MineTrekCreativeTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return MineTrekItems.phaser;
	}

}