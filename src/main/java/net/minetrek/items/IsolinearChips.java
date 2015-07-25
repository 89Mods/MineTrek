package net.minetrek.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minetrek.blocks.MineTrekBlocks;

public class IsolinearChips{
	private static List<Item> chips = new ArrayList<Item>();
	public static Item isolinear_chip;
	public static Item isolinear_chip_black;
	public static Item isolinear_chip_blue;
	public static Item isolinear_chip_brown;
	public static Item isolinear_chip_cyan;
	public static Item isolinear_chip_gray;
	public static Item isolinear_chip_green;
	public static Item isolinear_chip_light_blue;
	public static Item isolinear_chip_lime;
	public static Item isolinear_chip_magenta;
	public static Item isolinear_chip_orange;
	public static Item isolinear_chip_pink;
	public static Item isolinear_chip_purple;
	public static Item isolinear_chip_red;
	public static Item isolinear_chip_silver;
	public static Item isolinear_chip_yellow;
	public static void initialize(CreativeTabs tab){
		isolinear_chip =  new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip, "isolinear_chip");
		MineTrekItems.itemList.add("isolinear_chip");
		chips.add(isolinear_chip);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip,1),new Object[] {"#X#", "#Y#", "#C#", '#', MineTrekBlocks.transparent_aluminum, 'Y', MineTrekItems.advancedPCB, 'C', MineTrekItems.nitrium_nugget, 'X', Items.paper});
		isolinear_chip_blue = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_blue, "isolinear_chip_blue");
		MineTrekItems.itemList.add("isolinear_chip_blue");
		chips.add(isolinear_chip_blue);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_blue,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,4)});
		isolinear_chip_black = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_black, "isolinear_chip_black");
		MineTrekItems.itemList.add("isolinear_chip_black");
		chips.add(isolinear_chip_black);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_black,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,0)});
		isolinear_chip_brown = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_brown, "isolinear_chip_brown");
		MineTrekItems.itemList.add("isolinear_chip_brown");
		chips.add(isolinear_chip_brown);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_cyan,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,3)});
		isolinear_chip_cyan = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_cyan, "isolinear_chip_cyan");
		MineTrekItems.itemList.add("isolinear_chip_cyan");
		chips.add(isolinear_chip_cyan);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_cyan,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,6)});
		isolinear_chip_gray = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_gray, "isolinear_chip_gray");
		MineTrekItems.itemList.add("isolinear_chip_gray");
		chips.add(isolinear_chip_gray);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_gray,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,8)});
		isolinear_chip_green = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_green, "isolinear_chip_green");
		MineTrekItems.itemList.add("isolinear_chip_green");
		chips.add(isolinear_chip_green);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_green,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,2)});
		isolinear_chip_light_blue = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_light_blue, "isolinear_chip_light_blue");
		MineTrekItems.itemList.add("isolinear_chip_light_blue");
		chips.add(isolinear_chip_blue);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_light_blue,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,12)});
		isolinear_chip_lime = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_lime, "isolinear_chip_lime");
		MineTrekItems.itemList.add("isolinear_chip_lime");
		chips.add(isolinear_chip_lime);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_lime,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,10)});
		isolinear_chip_magenta = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_magenta, "isolinear_chip_magenta");
		MineTrekItems.itemList.add("isolinear_chip_magenta");
		chips.add(isolinear_chip_magenta);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_magenta,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,13)});
		isolinear_chip_orange = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_orange, "isolinear_chip_orange");
		MineTrekItems.itemList.add("isolinear_chip_orange");
		chips.add(isolinear_chip_orange);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_orange,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,14)});
		isolinear_chip_pink = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_pink, "isolinear_chip_pink");
		MineTrekItems.itemList.add("isolinear_chip_pink");
		chips.add(isolinear_chip_pink);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_pink,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,9)});
		isolinear_chip_purple = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_purple, "isolinear_chip_purple");
		MineTrekItems.itemList.add("isolinear_chip_purple");
		chips.add(isolinear_chip_purple);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_purple,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,5)});
		isolinear_chip_red = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_red, "isolinear_chip_red");
		MineTrekItems.itemList.add("isolinear_chip_red");
		chips.add(isolinear_chip_red);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_red,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,1)});
		isolinear_chip_silver = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_silver, "isolinear_chip_silver");
		MineTrekItems.itemList.add("isolinear_chip_silver");
		chips.add(isolinear_chip_silver);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_silver,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,7)});
		isolinear_chip_yellow = new ItemIsolinearChip().setCreativeTab(tab);
		GameRegistry.registerItem(isolinear_chip_yellow, "isolinear_chip_yellow");
		MineTrekItems.itemList.add("isolinear_chip_yellow");
		chips.add(isolinear_chip_yellow);
		GameRegistry.addRecipe(new ItemStack(isolinear_chip_yellow,1),new Object[] {"#X", '#', isolinear_chip, Character.valueOf('X'), new ItemStack(Items.dye,1,11)});
		for(Item item : chips){
			if(item != isolinear_chip){
				GameRegistry.addRecipe(new ItemStack(isolinear_chip,1),new Object[] {"#X", Character.valueOf('#'), new ItemStack(item,1), Character.valueOf('X'), new ItemStack(Items.dye,1,15)});
			}
		}
		
	}
}
