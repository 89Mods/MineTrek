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
import net.minetrek.blocks.machines.CrusherTileEntity;
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
				GameRegistry.addSmelting(MineTrekOres.duranium_ore, new ItemStack(MineTrekItems.duranium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.chromite_ore, new ItemStack(MineTrekItems.chromium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.iridium_ore, new ItemStack(MineTrekItems.iridium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.platinum_ore, new ItemStack(MineTrekItems.platinum_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.plutonium_ore, new ItemStack(MineTrekItems.plutonium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.silicon_ore, new ItemStack(MineTrekItems.silicon_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.uranium_ore, new ItemStack(MineTrekItems.uranium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.invisible_ore, new ItemStack(MineTrekItems.invisible_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.tin_ore, new ItemStack(MineTrekItems.tin_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.copper_ore, new ItemStack(MineTrekItems.copper_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.titanium_ore, new ItemStack(MineTrekItems.titanium_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.tungsten_ore, new ItemStack(MineTrekItems.tungsten_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekOres.dilithium_ore, new ItemStack(MineTrekItems.dilithium_crystal), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.bronze_dust, new ItemStack(MineTrekItems.bronze_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.copper_dust, new ItemStack(MineTrekItems.copper_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.tin_dust, new ItemStack(MineTrekItems.tin_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.aluminum_dust, new ItemStack(MineTrekItems.aluminum_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.steel_dust, new ItemStack(MineTrekItems.steel_ingot), 1.0F);
				GameRegistry.addSmelting(MineTrekItems.invisible_dust, new ItemStack(MineTrekItems.invisible_ingot), 1.0F);
		//crafting
				 //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineTrekItems.coil,1), new Object[] {"###", "#X#", "###", '#', "ingotCopper", 'X', Items.iron_ingot}));
				 GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineTrekBlocks.cable,4),new Object[] {"###", "XXX", "###", '#', MineTrekItems.silicon_ingot, 'X', "ingotCopper"}));
				 GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineTrekItems.bronze_dust,3), new Object[] {"#X#", '#', "dustCopper", 'X', "dustTin"}));
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.magnetizer,1),new Object[] {"#X#", "Y Y", "#X#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.coil, 'Y', Items.redstone});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.antimaterstorage,1),new Object[] {"#X#", "#Y#", "#X#", '#', MineTrekItems.tritanium_ingot, 'X', MineTrekItems.magnet, 'Y', Blocks.glass_pane});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.tritaniumBlock,1),new Object[] {"###", "###", "###", '#', MineTrekItems.tritanium_ingot});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.tritanium_ingot,9),new Object[] {"#", '#', MineTrekBlocks.tritaniumBlock});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.laserElectronManipulator,1),new Object[] {"#X#", "#Y#", "# #", '#', MineTrekItems.tritanium_ingot, 'X', Items.redstone, 'Y', Items.diamond});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.coil,1),new Object[] {"###", "#X#", "###", '#', MineTrekBlocks.cable, 'X', Items.iron_ingot});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.crusher,1),new Object[] {"#X#", "Y Y", "YYY", 'Y', MineTrekItems.tritanium_ingot, '#', Items.redstone, 'X', Blocks.piston});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.particleAccelerator,1),new Object[] {"###", "# X", "###", '#', MineTrekItems.tritanium_ingot, 'X', Blocks.piston});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.phaser,1),new Object[] {" #X", "#Y#", "Z# ", '#', MineTrekItems.aluminum_ingot, 'Z', Blocks.stone_button, 'X', Blocks.glass, 'Y', MineTrekItems.antimaterstorage_full});
				 GameRegistry.addRecipe(new ItemStack(MineTrekItems.tritanium_pickaxe,1),new Object[] {"###", " X ", " X ", '#', MineTrekItems.tritanium_ingot, 'X', Items.stick});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.antimaterbomb,1),new Object[] {"X#X", "#Y#", "X#X", '#', MineTrekItems.antimaterstorage_full, 'X', Items.gunpowder, 'Y', Blocks.tnt});
				 GameRegistry.addRecipe(new ItemStack(MineTrekBlocks.computer,1),new Object[] {"#X#", "#Y#", "#X#", '#', Items.iron_ingot, Character.valueOf('X'), new ItemStack(Items.dye,1,4), 'Y', Items.redstone});
	   //machines
				 MagnetizerTileEntity.addRecipe(new ItemStack(Items.iron_ingot,1), new ItemStack(MineTrekItems.magnet,1));
				 LaserElectronManipulatorTileEntity.addRecipe(new ItemStack(MineTrekItems.antimaterstorage_water,1), new ItemStack(MineTrekItems.antimaterstorage_deuterium));
				 LaserElectronManipulatorTileEntity.addRecipe(new ItemStack(MineTrekItems.aluminum_ingot,1), new ItemStack(MineTrekBlocks.transparent_aluminum,1));
				 CrusherTileEntity.addRecipe(new ItemStack(MineTrekItems.copper_ingot,1), new ItemStack(MineTrekItems.copper_dust,1));
				 CrusherTileEntity.addRecipe(new ItemStack(MineTrekItems.tin_ingot,1), new ItemStack(MineTrekItems.tin_dust,1));
				 CrusherTileEntity.addRecipe(new ItemStack(MineTrekItems.invisible_ingot,1), new ItemStack(MineTrekItems.invisible_dust,1));
				 CrusherTileEntity.addRecipe(new ItemStack(MineTrekItems.steel_ingot,1), new ItemStack(MineTrekItems.steel_dust,1));
				 CrusherTileEntity.addRecipe(new ItemStack(MineTrekItems.aluminum_ingot,1), new ItemStack(MineTrekItems.aluminum_dust,1));
				 CrusherTileEntity.addRecipe(new ItemStack(MineTrekItems.bronze_ingot,1), new ItemStack(MineTrekItems.bronze_dust,1));
				 ParticleAcceleratorTileEntity.addRecipe(new ItemStack(MineTrekItems.antimaterstorage_deuterium,1), new ItemStack(MineTrekItems.antimaterstorage_full,1));
				 
	}
}
