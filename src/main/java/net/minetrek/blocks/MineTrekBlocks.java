package net.minetrek.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minetrek.blocks.machines.LaserElectronManipulator;
import net.minetrek.blocks.machines.Magnetizer;
import net.minetrek.blocks.machines.ParticleAccelerator;

public class MineTrekBlocks {
    public static List<String> blockList = new ArrayList<String>();
	public static Block transparent_aluminum;
	public static Block magnetizer;
	public static Block tritaniumBlock;
	public static Block laserElectronManipulator;
	public static Block particleAccelerator;
	public static Block antimaterbomb;
	public static Block warpcore;
	public static Block torpedo_launcher;
	public static Block deflector;
	public static Block phaser_bank;
	public static void initialize(CreativeTabs ct) {
		transparent_aluminum = new TransparentAluminum().setCreativeTab(ct);
		GameRegistry.registerBlock(transparent_aluminum, "transparent_aluminum");
		blockList.add("transparent_aluminum");
		magnetizer = new Magnetizer().setUnlocalizedName("magnetizer").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f);
		GameRegistry.registerBlock(magnetizer, "magnetizer");
		blockList.add("magnetizer");
		tritaniumBlock = new OreBlock().setCreativeTab(ct).setUnlocalizedName("tritaniumBlock").setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f);
		GameRegistry.registerBlock(tritaniumBlock, "tritaniumBlock");
		blockList.add("tritaniumBlock");
		laserElectronManipulator = new LaserElectronManipulator().setUnlocalizedName("laserElectronManipulator").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f);
		GameRegistry.registerBlock(laserElectronManipulator, "laserElectronManipulator");
		blockList.add("laserElectronManipulator");
		particleAccelerator = new ParticleAccelerator().setUnlocalizedName("particleAccelerator").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f);
		GameRegistry.registerBlock(particleAccelerator, "particleAccelerator");
		blockList.add("particleAccelerator");
		antimaterbomb = new BlockAntimaterbomb().setUnlocalizedName("antimaterbomb").setCreativeTab(ct).setStepSound(Block.soundTypeGrass).setHardness(0.0f).setResistance(0.1f);
		GameRegistry.registerBlock(antimaterbomb, "antimaterbomb");
		blockList.add("antimaterbomb");
		warpcore = new BlockWarpcore().setUnlocalizedName("warpcore").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(3.0f).setResistance(10.0f);
		GameRegistry.registerBlock(warpcore, "warpcore");
		blockList.add("warpcore");
		torpedo_launcher = new BlockTorpedoLauncher().setHardness(5.0F).setResistance(10.0F).setCreativeTab(ct).setUnlocalizedName("torpedo_launcher");
		GameRegistry.registerBlock(torpedo_launcher, "torpedo_launcher");
		blockList.add("torpedo_launcher");
		deflector = new BlockDeflector().setHardness(5.0F).setResistance(10.0F).setCreativeTab(ct).setUnlocalizedName("deflector");
		GameRegistry.registerBlock(deflector, "deflector");
		blockList.add("deflector");
		phaser_bank = new BlockPhaserBank().setHardness(5.0F).setResistance(10.0F).setCreativeTab(ct).setUnlocalizedName("phaser_bank");
		GameRegistry.registerBlock(phaser_bank, "phaser_bank");
		blockList.add("phaser_bank");
		
	}
}
