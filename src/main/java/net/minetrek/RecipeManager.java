package net.minetrek;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minetrek.blocks.MineTrekBlocks;
import net.minetrek.blocks.machines.LaserElectronManipulatorTileEntity;
import net.minetrek.blocks.machines.MagnetizerTileEntity;
import net.minetrek.blocks.machines.ParticleAcceleratorTileEntity;
import net.minetrek.blocks.ores.MineTrekOres;
import net.minetrek.items.MineTrekItems;

public class RecipeManager {
	public RecipeManager() {
	}
	public static void addRecipes() {
		//Smelting
				GameRegistry.addSmelting(MineTrekOres.bauxite_ore, new ItemStack(MineTrekItems.aluminum_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.tritanium_ore, new ItemStack(MineTrekItems.tritanium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.dilithium_ore, new ItemStack(MineTrekItems.dilithium_crystal), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.rawAdvancedCircuitBoard, new ItemStack(MineTrekItems.advancedPCB), 1.0F);
		//crafting
				 //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineTrekItems.coil,1), new Object[] {"###", "#X#", "###", '#', "ingotCopper", 'X', Items.iron_ingot}));
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.magnetizer,1),new Object[] {"#X#", "Y Y", "#X#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.coil, 'Y', Items.redstone});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.antimaterstorage,1),new Object[] {"#X#", "#Y#", "#X#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.magnet, 'Y', Blocks.glass_pane});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.tritaniumBlock,1),new Object[] {"###", "###", "###", '#', MineTrekItems.tritanium_ingot});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.tritanium_ingot,9),new Object[] {"#", '#', MineTrekBlocks.tritaniumBlock});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.laserElectronManipulator,1),new Object[] {"#X#", "#Y#", "# #", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.coil, 'Y', Items.diamond});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.coil,1),new Object[] {"  #", " X ", "#  ", '#', Items.redstone, 'X', MineTrekItems.nitrium_ingot});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.particleAccelerator,1),new Object[] {"###", "Y X", "###", '#', MineTrekItems.tritanium_ingot, 'X', Blocks.piston, 'Y', MineTrekItems.coil});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.phaser,1),new Object[] {" #X", "#Y#", "Z# ", '#', MineTrekItems.aluminum_ingot, 'Z', Blocks.stone_button, 'X', Blocks.glass, 'Y', MineTrekItems.antimaterstorage_full});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.tritanium_pickaxe,1),new Object[] {"###", " X ", " X ", '#', MineTrekItems.tritanium_ingot, 'X', Items.stick});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.antimaterbomb,1),new Object[] {"X#X", "#Y#", "X#X", '#', MineTrekItems.antimaterstorage_full, 'X', Items.gunpowder, 'Y', Blocks.tnt});
				 GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineTrekItems.antimatterBattery,1),new Object[] {" # ", "XXX", "YYY", 'Y', Items.redstone, '#', MineTrekItems.antimaterstorage_full, 'X', "ingotCopper"}));
				 GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.bucket,1),new Object[] {"# #", " # ", '#', "ingotTin"}));
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.nitrium_nugget,9),new Object[] {"#", '#', MineTrekItems.nitrium_ingot});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.nitrium_ingot,1),new Object[] {"###", "###", "###", '#', MineTrekItems.nitrium_nugget});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.rawAdvancedCircuitBoard,1),new Object[] {"#X", "Y ", '#', MineTrekItems.nitrium_nugget, 'X', Items.clay_ball, Character.valueOf('Y'), new ItemStack(Items.dye,1,2)});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.advanced_empty_card,1),new Object[] {"#  ", "#X ", "#Y ", '#', MineTrekItems.nitrium_nugget, 'X', MineTrekItems.advancedPCB, 'Y', Items.gold_nugget});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.lol_card,1),new Object[] {"XYX", " Z ", 'X', Items.redstone, 'Y', Blocks.dirt, 'Z', MineTrekItems.advanced_empty_card});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.torpedo_launcher,1), new Object[] {"#X#", "#Y#", "#C#", '#', MineTrekItems.tritanium_ingot, 'X', Blocks.piston, 'Y', MineTrekItems.coil, 'C', MineTrekItems.advancedPCB});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.phaser_bank,1), new Object[] {"#X#", "#Y#", "#C#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.phaser, 'Y', MineTrekItems.coil, 'C', MineTrekItems.advancedPCB});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.photon_torpedo,10),new Object[] {"#X#", "#Y#", "#C#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.antimaterstorage_full, 'Y', Blocks.tnt, 'C', MineTrekItems.coil});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.wire,10),new Object[] {"#X#", '#', MineTrekItems.tritanium_ingot, 'X', Blocks.glass});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.antimatterGenerator,1),new Object[] {"#X#","#Y#","#Z#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.antimaterstorage, 'Z', MineTrekBlocks.wire, 'Y', MineTrekItems.coil});
	   //machines
				 MagnetizerTileEntity.addRecipe(new ItemStack(Items.iron_ingot,1), new ItemStack(MineTrekItems.magnet,1));
				 LaserElectronManipulatorTileEntity.addRecipe(new ItemStack(MineTrekItems.antimaterstorage_water,1), new ItemStack(MineTrekItems.antimaterstorage_deuterium));
				 LaserElectronManipulatorTileEntity.addRecipe(new ItemStack(MineTrekItems.aluminum_ingot,1), new ItemStack(MineTrekBlocks.transparent_aluminum,1));
				 ParticleAcceleratorTileEntity.addRecipe(new ItemStack(MineTrekItems.antimaterstorage_deuterium,1), new ItemStack(MineTrekItems.antimaterstorage_full,1));
				 
	}
}
