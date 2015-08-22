package net.minetrek.blocks;

import java.util.Random;

import cofh.api.energy.IEnergyReceiver;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minetrek.entities.projectiles.EntityPhaserBolt;
import net.minetrek.entities.projectiles.EntityPhotonTorpedo;
import net.minetrek.items.MineTrekItems;

public class TileEntityTorpedoLauncher extends TileEntity implements SimpleComponent, ISidedInventory, IUpdatePlayerListBox,IEnergyReceiver {
	private ItemStack[] inventory;
	private int power = 0;
	private int bufferSize = 2000;
	public TileEntityTorpedoLauncher(){
		super();
		inventory = new ItemStack[1];
		
	}
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.getPos(), 1, nbtTag);
	}
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}
	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}
	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack itemstack = getStackInSlot(slot);
		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(slot, null);
			} else {
				itemstack = itemstack.splitStack(count);
				this.updateEntity();
			}
		}
		return itemstack;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemstack;
	}
	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		inventory[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

		updateEntity();

	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return itemstack == null || itemstack.getItem() == MineTrekItems.photon_torpedo;
	}
	@Override
	public String getComponentName() {
		return "minetrek_torpedo_launcher";
	}
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
	       NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < this.inventory.length; ++i)
	        {
	            if (this.inventory[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.inventory[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        compound.setTag("Items", nbttaglist);
	        compound.setInteger("power",power);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventory.length)
            {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        power = compound.getInteger("power");
	}
	public void updateEntity(){
		this.update();
	}
	@Override
	public void update() {
		
		if (this.worldObj.isRemote)
			return;
		if(this.power > bufferSize){
			this.power = bufferSize;
		}
		if(this.power < 0){
			this.power = 0;
		}
		
		worldObj.markBlockForUpdate(this.getPos());

	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		BlockPos posss = new BlockPos(this.getPos().getX() + 0.5D,this.getPos().getY() + 0.5D,this.getPos().getZ() + 0.5D);
		return player.getDistanceSq(posss) <= 64;
	}
	@Override
	public void openInventory(EntityPlayer player) {
	}
	@Override
	public void closeInventory(EntityPlayer player) {	
	}
	@Override
	public int getField(int id) {
		return 0;
	}
	@Override
	public void setField(int id, int value) {
	}
	@Override
	public int getFieldCount() {
		return 0;
	}
	@Override
	public void clear() {
	}
	@Override
	public String getName() {
		return "Torpedo Tube";
	}
	@Override
	public boolean hasCustomName() {
		return false;
	}
	@Override
	public IChatComponent getDisplayName() {
		return null;
	}
	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		int[] tmp = new int[1];
		if (var1 == EnumFacing.UP)
			tmp[0] = 0;
		else if (var1 == EnumFacing.WEST)
			tmp[0] = 1;
		else
			tmp = new int[0];
		return tmp;
	}
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return isItemValidForSlot(index, itemStackIn);
	}
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}
	@Callback
	public Object[] fire(Context context, Arguments args) {
		BlockTorpedoLauncher block = (BlockTorpedoLauncher)this.worldObj.getBlockState(this.pos).getBlock();
		IBlockState state = this.worldObj.getBlockState(this.pos);
		if(this.inventory[0] == null){
			return new Object[] {false, "No Ammo" };
		}
		if(this.inventory[0].stackSize < 1){
			return new Object[] {false, "No Ammo" };
		}
		if(this.power < 200){
			return new Object[] {false, "Not Enough Energy" };
		}
		if(this.inventory[0].getItem() != MineTrekItems.photon_torpedo){
			return new Object[] {false, "Invalid Ammo" };
		}
		boolean success = false;
		if((EnumFacing)state.getValue(block.FACING) == EnumFacing.NORTH){
			this.shoot(this.worldObj, 0, 0, -10, this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1);
			success = true;
		}else if((EnumFacing)state.getValue(block.FACING) == EnumFacing.SOUTH){
			this.shoot(this.worldObj, 0, 0, 10, this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1);
			success = true;
		}else if((EnumFacing)state.getValue(block.FACING) == EnumFacing.EAST){
			this.shoot(this.worldObj, 10, 0, 0, this.pos.getX() + 1, this.pos.getY(), this.pos.getZ());
			success = true;
		}else if((EnumFacing)state.getValue(block.FACING) == EnumFacing.WEST){
			this.shoot(this.worldObj, -10, 0, 0, this.pos.getX() - 1, this.pos.getY(), this.pos.getZ());
			success = true;
		}
		if(success){
			this.inventory[0].stackSize--;
			if(this.inventory[0].stackSize == 0){
				this.inventory[0] = null;
			}
			addEnergy(-200,false);
		}else{
			return new Object[] {success, "An unknow error occured. This should be impossible!"};
		}
		this.updateEntity();
		this.worldObj.markBlockForUpdate(this.getPos());
		return new Object[] {success, "FIRE" };
	}
	public void shoot(World world, int x, int y, int z, int fromX, int fromY, int fromZ) {
		EntityPhotonTorpedo e = new EntityPhotonTorpedo(world, fromX, fromY, fromZ);
		e.setThrowableHeading(x, y,  z, 7.0F, 0.0F);
		//world.playSound(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "minetrek:photonTorpedo", 10F, 1.0F, true);
		world.spawnEntityInWorld(e);
		world.playSoundAtEntity(e, "minetrek:photonTorpedo", 10F, 1.0F);
	}
	@Override
	public boolean canConnectEnergy(EnumFacing facing) {
		if (worldObj.isRemote) return false;
		return true;
	}
	@Override
	public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
		if (worldObj.isRemote) return 0;
		worldObj.markBlockForUpdate(this.getPos());
		return addEnergy(maxReceive, simulate);
	}
	protected int addEnergy(int amount, boolean simulate) {
		
		int energyReceived = Math.min(this.bufferSize - this.power, amount);

		if (!simulate) {
			int amountUsed = amount;
			if ((power + energyReceived)>bufferSize) amountUsed = (bufferSize-power);
			if ((power + energyReceived)<0) amountUsed = (power);
			power += energyReceived;
			if (power>=bufferSize) power = bufferSize;
			if (power<0) power = 0;
			
		}
		return energyReceived;
	}
	@Override
	public int getEnergyStored(EnumFacing facing) {
		return power;
	}
	@Override
	public int getMaxEnergyStored(EnumFacing facing) {
		return bufferSize;
	}
}
