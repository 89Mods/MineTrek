package net.minetrek.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minetrek.MineTrek;
import net.minetrek.blocks.ContainerPhaserBank;
import net.minetrek.blocks.ContainerTorpedoLauncher;
import net.minetrek.blocks.GuiPhaserBank;
import net.minetrek.blocks.GuiTorpedoLauncher;
import net.minetrek.blocks.TileEntityPhaserBank;
import net.minetrek.blocks.TileEntityTorpedoLauncher;
import net.minetrek.blocks.machines.LaserElectronManipulatorContainer;
import net.minetrek.blocks.machines.LaserElectronManipulatorGui;
import net.minetrek.blocks.machines.LaserElectronManipulatorTileEntity;
import net.minetrek.blocks.machines.MagnetizerContainer;
import net.minetrek.blocks.machines.MagnetizerGui;
import net.minetrek.blocks.machines.MagnetizerTileEntity;
import net.minetrek.blocks.machines.ParticleAcceleratorContainer;
import net.minetrek.blocks.machines.ParticleAcceleratorGui;
import net.minetrek.blocks.machines.ParticleAcceleratorTileEntity;

public class GuiHandler implements IGuiHandler{
	public static final int LEM_GUI = 0;
	public static final int TORPEDO_LAUNCHER_GUI = 1;
	public static final int PARTICLEACCELERATOR_GUI = 2;
	public static final int PHASER_BANK_GUI = 3;
	public static final int MAGNETIZER_GUI = 4;
	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(MineTrek.instance, this);
	}
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x,y,z);
		TileEntity entity = world.getTileEntity(pos);
		switch (id) {
		case MAGNETIZER_GUI:
			if (entity != null && entity instanceof MagnetizerTileEntity)
				return new MagnetizerContainer(player.inventory, (MagnetizerTileEntity) entity);
			break;
		case LEM_GUI:
			if (entity != null && entity instanceof LaserElectronManipulatorTileEntity)
				return new LaserElectronManipulatorContainer(player.inventory, (LaserElectronManipulatorTileEntity) entity);
			break;
		case PARTICLEACCELERATOR_GUI:
			if (entity != null && entity instanceof ParticleAcceleratorTileEntity)
				return new ParticleAcceleratorContainer(player.inventory, (ParticleAcceleratorTileEntity) entity);
			break;
		case TORPEDO_LAUNCHER_GUI:
			if(entity != null && entity instanceof TileEntityTorpedoLauncher)
				return new ContainerTorpedoLauncher(player.inventory, (TileEntityTorpedoLauncher) entity);
			break;
		case PHASER_BANK_GUI:
			if(entity != null && entity instanceof TileEntityPhaserBank)
				return new ContainerPhaserBank(player.inventory, (TileEntityPhaserBank) entity);
			break;
		}
		return null;
	}
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x,y,z);
		TileEntity entity = world.getTileEntity(pos);
		switch (id) {
		case MAGNETIZER_GUI:
			if (entity != null && entity instanceof MagnetizerTileEntity)
				return new MagnetizerGui(player.inventory, (MagnetizerTileEntity) entity);
			break;
		case LEM_GUI:
			if (entity != null && entity instanceof LaserElectronManipulatorTileEntity)
				return new LaserElectronManipulatorGui(player.inventory, (LaserElectronManipulatorTileEntity) entity);
			break;
		case PARTICLEACCELERATOR_GUI:
			if (entity != null && entity instanceof ParticleAcceleratorTileEntity)
				return new ParticleAcceleratorGui(player.inventory, (ParticleAcceleratorTileEntity) entity);
			break;
		case TORPEDO_LAUNCHER_GUI:
			if(entity != null && entity instanceof TileEntityTorpedoLauncher)
				return new GuiTorpedoLauncher(player.inventory, (TileEntityTorpedoLauncher) entity);
			break;
		case PHASER_BANK_GUI:
			if(entity != null && entity instanceof TileEntityPhaserBank)
				return new GuiPhaserBank(player.inventory, (TileEntityPhaserBank) entity);
			break;
		}
		return null;
	}
}
