package net.minetrek.blocks.ores;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minetrek.blocks.MineTrekBlocks;

public class MineTrekOres {
	public static Ore dilithium_ore;
	public static Ore tritanium_ore;
	public static Ore bauxite_ore;
	public static Ore nitrium_ore;
	public static void initialize(CreativeTabs ct, OreGenerator gen) {
		dilithium_ore = new DilithiumOre();
		registerOre(dilithium_ore, ct, gen);
		
		tritanium_ore = new TritaniumOre();
		registerOre(tritanium_ore, ct, gen);

		bauxite_ore = new BauxiteOre();
		registerOre(bauxite_ore, ct, gen);
		
		nitrium_ore = new NitriumOre();
		registerOre(nitrium_ore,ct,gen);
	}
	private static void registerOre(Ore o, CreativeTabs ct, OreGenerator gen) {
		o.setCreativeTab(ct);
		GameRegistry.registerBlock(o, o.name);
		gen.addOre(o);
		MineTrekBlocks.blockList.add(o.name);
		
	}
}
