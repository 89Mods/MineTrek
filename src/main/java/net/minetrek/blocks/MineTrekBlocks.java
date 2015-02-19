package net.minetrek.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minetrek.blocks.machines.Crusher;
import net.minetrek.blocks.machines.LaserElectronManipulator;
import net.minetrek.blocks.machines.Magnetizer;
import net.minetrek.blocks.machines.ParticleAccelerator;

public class MineTrekBlocks {
    public static List<String> blockList = new ArrayList<String>();
	public static Block transparent_aluminum;
	public static Block magnetizer;
	public static Block tritaniumBlock;
	public static Block laserElectronManipulator;
	public static Block cable;
	public static Block crusher;
	public static Block particleAccelerator;
	public static Block antimaterbomb;
	public static Block warpcore;
	public static Block computer;
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
		cable = new BlockCable().setCreativeTab(ct).setUnlocalizedName("cable");
		GameRegistry.registerBlock(cable, "cable");
		blockList.add("cable");
		crusher = new Crusher().setUnlocalizedName("crusher").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f);
		GameRegistry.registerBlock(crusher, "crusher");
		blockList.add("crusher");
		particleAccelerator = new ParticleAccelerator().setUnlocalizedName("particleAccelerator").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f);
		GameRegistry.registerBlock(particleAccelerator, "particleAccelerator");
		blockList.add("particleAccelerator");
		antimaterbomb = new BlockAntimaterbomb().setUnlocalizedName("antimaterbomb").setCreativeTab(ct).setStepSound(Block.soundTypeGrass).setHardness(0.0f).setResistance(0.1f);
		GameRegistry.registerBlock(antimaterbomb, "antimaterbomb");
		blockList.add("antimaterbomb");
		warpcore = new BlockWarpcore().setUnlocalizedName("warpcore").setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(3.0f).setResistance(10.0f);
		GameRegistry.registerBlock(warpcore, "warpcore");
		blockList.add("warpcore");
		computer = new BlockComputer().setCreativeTab(ct).setStepSound(Block.soundTypeMetal).setHardness(1.0f).setResistance(5.0f).setUnlocalizedName("computer");
		GameRegistry.registerBlock(computer, "computer");
		blockList.add("computer");
		
	}
}
