package net.minetrek.blocks;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minetrek.blocks.machines.TileEntityAntimatterGenerator;
import net.minetrek.blocks.machines.TileEntityCoalGenerator;

public class TileEntityWire extends TileEntity implements IUpdatePlayerListBox,IEnergyHandler {
	  public static int fdTransfer = 320;
	  EnergyStorage[] storageBuffers = new EnergyStorage[6];
	/**
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public EnumFacing[] connections = new EnumFacing[6];
	
	public TileEntityWire(){
	    for (int n = 0; n < 6; n++)
	    {
	      this.storageBuffers[n] = new EnergyStorage(fdTransfer);
	    }
	}
	public void updateConnections(){
		connections[0] = null;
		if((this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY()+1,this.pos.getZ())) instanceof IEnergyReceiver) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY()+1,this.pos.getZ())) instanceof TileEntityAntimatterGenerator) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY()+1,this.pos.getZ())) instanceof IEnergyProvider)) connections[0] = EnumFacing.UP;
		connections[1] = null;
		if((this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY()-1,this.pos.getZ())) instanceof IEnergyReceiver) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY()-1,this.pos.getZ())) instanceof TileEntityAntimatterGenerator) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY()-1,this.pos.getZ())) instanceof IEnergyProvider)) connections[1] = EnumFacing.DOWN; 
		connections[2] = null;
		if((this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(),this.pos.getZ()-1)) instanceof IEnergyReceiver) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(),this.pos.getZ()-1)) instanceof TileEntityAntimatterGenerator) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(),this.pos.getZ()-1)) instanceof IEnergyProvider)) connections[2] = EnumFacing.NORTH;
		connections[3] = null;
		if((this.worldObj.getTileEntity(new BlockPos(this.pos.getX()+1, this.pos.getY(),this.pos.getZ())) instanceof IEnergyReceiver) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX()+1, this.pos.getY(),this.pos.getZ())) instanceof TileEntityAntimatterGenerator) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX()+1, this.pos.getY(),this.pos.getZ())) instanceof IEnergyProvider)) connections[3] = EnumFacing.EAST;
		connections[4] = null;
		if((this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(),this.pos.getZ()+1)) instanceof IEnergyReceiver) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(),this.pos.getZ()+1)) instanceof TileEntityAntimatterGenerator) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(),this.pos.getZ()+1)) instanceof IEnergyProvider)) connections[4] = EnumFacing.SOUTH;
		connections[5] = null;
		if((this.worldObj.getTileEntity(new BlockPos(this.pos.getX()-1, this.pos.getY(),this.pos.getZ())) instanceof IEnergyReceiver) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX()-1, this.pos.getY(),this.pos.getZ())) instanceof TileEntityAntimatterGenerator) || (this.worldObj.getTileEntity(new BlockPos(this.pos.getX()-1, this.pos.getY(),this.pos.getZ())) instanceof IEnergyProvider)) connections[5] = EnumFacing.WEST;
	}
	public boolean onlyOneOpposite(EnumFacing[] directions){
		EnumFacing mainDirection = null;
		boolean isOpposite = false;
		for(int i = 0; i < directions.length; i++){
			if(mainDirection == null && directions[i] != null) mainDirection = directions[i];
			if(directions[i] != null && mainDirection != directions[i]){
				if(!isOpposite(mainDirection, directions[i])) return false;
				else isOpposite = true;
			}
		}
		return isOpposite;
	}
	public boolean isOpposite(EnumFacing firstDirection, EnumFacing secondDirection){
		if((firstDirection.equals(EnumFacing.NORTH) && secondDirection.equals(EnumFacing.SOUTH)) || (firstDirection.equals(EnumFacing.SOUTH) && secondDirection.equals(EnumFacing.NORTH)))return true;
		if((firstDirection.equals(EnumFacing.DOWN) && secondDirection.equals(EnumFacing.UP)) || (firstDirection.equals(EnumFacing.UP) && secondDirection.equals(EnumFacing.DOWN)))return true;
		if((firstDirection.equals(EnumFacing.EAST) && secondDirection.equals(EnumFacing.WEST)) || (firstDirection.equals(EnumFacing.WEST) && secondDirection.equals(EnumFacing.SOUTH)))return true;
		
		return false;
	}
	@Override
	public void update() {
		this.updateConnections();
		if (this.worldObj.isRemote) return;
		int leftThisTick = fdTransfer;
	    for (EnumFacing face : EnumFacing.values())
	    {
	      EnergyStorage buffer = this.storageBuffers[face.ordinal()];
	      if (buffer.getEnergyStored() < 1)
	        continue;
	      BlockPos currentPos = this.pos.offset(face);
	      TileEntity te = this.worldObj.getTileEntity(currentPos);
	      if ((te instanceof IEnergyReceiver))
	      {
	        IEnergyReceiver receiver = (IEnergyReceiver)te;
	        if (!receiver.canConnectEnergy(face.getOpposite()))
	          continue;
	        int availableToSend = buffer.extractEnergy(leftThisTick, true);
	        if (availableToSend < 1)
	          continue;
	        int transferred = receiver.receiveEnergy(face.getOpposite(), availableToSend, false);
	        if (transferred == 0)
	          continue;
	        buffer.extractEnergy(transferred, false);
	      }

	      if (leftThisTick < 1)
	        break;
	    }
		
	}
	@Override
	public boolean canConnectEnergy(EnumFacing facing) {
		return true;
	}
	  public void writeToNBT(NBTTagCompound nbt)
	  {
	    super.writeToNBT(nbt);

	    int[] energyarray = new int[6];
	    for (int n = 0; n < 6; n++)
	    {
	      energyarray[n] = this.storageBuffers[n].getEnergyStored();
	    }
	    NBTTagIntArray energy = new NBTTagIntArray(energyarray);
	    nbt.setTag("buffers", energy);
	  }

	  public void readFromNBT(NBTTagCompound nbt)
	  {
	    super.readFromNBT(nbt);

	    int[] energy = nbt.getIntArray("buffers");
	    if ((energy == null) || (energy.length != 6)) return;

	    for (int n = 0; n < 6; n++)
	    {
	      this.storageBuffers[n].setEnergyStored(energy[n]);
	    }
	  }
	@Override
	public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
	    int left = maxReceive;
	    int total = 0;

	    List sides = new ArrayList();
	    for (int n = 0; n < 6; n++) sides.add(Integer.valueOf(n));

	    while (true)
	    {
	      int startLeft = left;
	      int share = left / 5;
	      if (share < 1) share = left;

	      for (int n = 0; n < 6; n++)
	      {
	        if (((Integer)sides.get(n)).intValue() != facing.ordinal()) {
	          int result = this.storageBuffers[((Integer)sides.get(n)).intValue()].receiveEnergy(share, simulate);
	          left -= result;
	          total += result;
	          if (left < 1) break;
	        }
	      }
	      if ((left == startLeft) || (left < 1))
	        break;
	    }
	    return total;
	}
	@Override
	public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
		return 0;
	}
	@Override
	public int getEnergyStored(EnumFacing facing) {
		return this.storageBuffers[facing.ordinal()].getEnergyStored();
	}
	@Override
	public int getMaxEnergyStored(EnumFacing facing) {
		return this.fdTransfer;
	}
}
