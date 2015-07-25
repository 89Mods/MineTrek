package net.minetrek.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minetrek.MineTrek;
import net.minetrek.items.tools.MineTrekPickaxe;
import net.minetrek.items.tools.Phaser;

public class MineTrekItems {
	public static List<String> itemList = new ArrayList<String>();
	public static Item dilithium_crystal;
	public static Item tritanium_ingot;
	public static Item aluminum_ingot;
	public static Item phaser;
	public static Item coil;
	public static Item magnet;
	public static Item antimaterstorage;
	public static Item antimaterstorage_water;
	public static Item antimaterstorage_deuterium;
	public static Item antimaterstorage_full;
	public static Item tritanium_pickaxe;
	public static Item antimatterBattery;
	public static Item lol_card;
	public static Item advanced_empty_card;
	public static Item rawAdvancedCircuitBoard;
	public static Item advancedPCB;
	public static Item nitrium_ingot;
	public static Item nitrium_nugget;
	public static void initialize(CreativeTabs tab) {
		//ingots
		aluminum_ingot = registerIngot("aluminumIngot");
		tritanium_ingot = registerIngot("tritaniumIngot");
		nitrium_ingot = registerIngot("nitriumIngot");
		OreDictionary.registerOre("ingotTritanium", tritanium_ingot);
		OreDictionary.registerOre("ingotNitrium", nitrium_ingot);
		OreDictionary.registerOre("ingotAluminum", aluminum_ingot);
		//tools
		phaser = new Phaser().setCreativeTab(tab);
		GameRegistry.registerItem(phaser, "phaser");
		itemList.add("phaser");
		tritanium_pickaxe = new MineTrekPickaxe(ToolMaterial.EMERALD).setCreativeTab(tab).setUnlocalizedName("tritanium_pickaxe");
		GameRegistry.registerItem(tritanium_pickaxe, "tritanium_pickaxe");
		itemList.add("tritanium_pickaxe");
		//computer
		lol_card = new ItemCardLol().setCreativeTab(tab);
		GameRegistry.registerItem(lol_card, "lol_card");
		itemList.add("lol_card");
		//other
		dilithium_crystal = new DilithiumCrystal().setCreativeTab(tab);
		GameRegistry.registerItem(dilithium_crystal, "dilithiumCrystal");
		itemList.add("dilithiumCrystal");
		coil = new Item().setCreativeTab(tab).setUnlocalizedName("coil");
		GameRegistry.registerItem(coil, "coil");
		itemList.add("coil");
		magnet = new Item().setCreativeTab(tab).setUnlocalizedName("magnet");
		GameRegistry.registerItem(magnet, "magnet");
		itemList.add("magnet");
		antimaterstorage = new ItemAntimaterstorage().setCreativeTab(tab).setUnlocalizedName("antimaterstorage").setMaxStackSize(3);
		GameRegistry.registerItem(antimaterstorage, "antimaterstorage");
		itemList.add("antimaterstorage");
		antimaterstorage_water = new Item().setCreativeTab(tab).setUnlocalizedName("antimaterstorage_water").setMaxStackSize(1);
		GameRegistry.registerItem(antimaterstorage_water, "antimaterstorage_water");
		itemList.add("antimaterstorage_water");
		antimaterstorage_deuterium = new Item().setCreativeTab(tab).setUnlocalizedName("antimaterstorage_deuterium").setMaxStackSize(1);
		GameRegistry.registerItem(antimaterstorage_deuterium, "antimaterstorage_deuterium");
		itemList.add("antimaterstorage_deuterium");
		antimaterstorage_full = new Item().setCreativeTab(tab).setUnlocalizedName("antimaterstorage_full").setMaxStackSize(1);
		GameRegistry.registerItem(antimaterstorage_full, "antimaterstorage_full");
		itemList.add("antimaterstorage_full");
		antimatterBattery = new ItemAntimatterBattery().setCreativeTab(tab).setUnlocalizedName("antimatterBattery").setMaxStackSize(1);
		GameRegistry.registerItem(antimatterBattery, "antimatterBattery");
		itemList.add("antimatterBattery");
		advanced_empty_card = new Item().setUnlocalizedName("advanced_empty_card").setCreativeTab(tab);
		GameRegistry.registerItem(advanced_empty_card, "advanced_empty_card");
		itemList.add("advanced_empty_card");
		nitrium_nugget = new Item().setUnlocalizedName("nitrium_nugget").setCreativeTab(tab);
		GameRegistry.registerItem(nitrium_nugget, "nitrium_nugget");
		itemList.add("nitrium_nugget");
		rawAdvancedCircuitBoard = new Item().setUnlocalizedName("rawAdvancedCircuitBoard").setCreativeTab(tab);
		GameRegistry.registerItem(rawAdvancedCircuitBoard, "rawAdvancedCircuitBoard");
		itemList.add("rawAdvancedCircuitBoard");
		advancedPCB = new Item().setUnlocalizedName("advancedPCB").setCreativeTab(tab);
		GameRegistry.registerItem(advancedPCB, "advancedPCB");
		itemList.add("advancedPCB");
		//Isolinear Chips
		IsolinearChips.initialize(tab);
	}
	private static Item registerIngot(String name) {
		Item ingot = new Item().setCreativeTab(MineTrek.creativeTab).setUnlocalizedName(name);
		GameRegistry.registerItem(ingot, name);
		itemList.add(name);
		return ingot;
	}
	private static Item registerDust(String name) {
		Item dust = new Item().setCreativeTab(MineTrek.creativeTab).setUnlocalizedName(name);
		GameRegistry.registerItem(dust, name);
		itemList.add(name);
		return dust;
	}
}
