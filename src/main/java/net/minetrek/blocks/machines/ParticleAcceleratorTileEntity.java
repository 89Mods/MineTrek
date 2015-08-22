package net.minetrek.blocks.machines;

import java.util.ArrayList;

import cofh.api.energy.IEnergyReceiver;

import net.minecraft.entity.player.EntityPlayer;
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

public class ParticleAcceleratorTileEntity extends TileEntity implements ISidedInventory,IUpdatePlayerListBox,IEnergyReceiver {
	private final ItemStack[] inventory;
	private final static int COOK_TIME = 300;
	private int time_remaining;
	private boolean isCooking;
	private int power = 0;
	private int bufferSize = 2000;
	private final int ENERGY_PER_TICK = 10;
	private static final ArrayList<ItemStack> recipeIngredients = new ArrayList<ItemStack>(), recipeProducts = new ArrayList<ItemStack>();
	public ParticleAcceleratorTileEntity() {
		super();

		inventory = new ItemStack[2];

		time_remaining = 0;
		isCooking = false;
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
	public int getSizeInventory() {
		return inventory.length;
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		BlockPos posss = new BlockPos(this.getPos().getX() + 0.5D,this.getPos().getY() + 0.5D,this.getPos().getZ() + 0.5D);
		return player.getDistanceSq(posss) <= 64;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return itemstack == null || (i != 1 && recipeIngredients.contains(itemstack));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack itemstack = getStackInSlot(i);
			if (itemstack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("LEMSlot", (byte) i);
				itemstack.writeToNBT(item);
				list.appendTag(item);
			}
		}
		compound.setTag("LEMItems", list);

		compound.setBoolean("cooking", isCooking);
		compound.setInteger("remaining", time_remaining);
		compound.setInteger("power",power);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagList list = compound.getTagList("LEMItems", 0);
		for (int i = 0; i < list.tagCount(); i++) {

			NBTTagCompound item = list.getCompoundTagAt(i);

			int slot = item.getByte("LEMSlot");
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}

		isCooking = compound.getBoolean("cooking");
		time_remaining = compound.getInteger("remaining");
		power = compound.getInteger("power");
	}

	public static void addRecipe(ItemStack in, ItemStack out) {
		recipeIngredients.add(in);
		recipeProducts.add(out);
	}
	public void updateEntity(){
		this.update();
	}
	@Override
	public void update() {

		if (this.worldObj.isRemote)
			return;

		if (!validIngredient()) {
			isCooking = false;
			time_remaining = 0;
			return;
		}

		if (time_remaining > 0) {
			addEnergy(ENERGY_PER_TICK * -1,false);
			time_remaining--;
		}
		if(this.power > bufferSize){
			this.power = bufferSize;
		}
		if(this.power < 0){
			this.power = 0;
		}
		if (isCooking && time_remaining == 0) {

			{

				ItemStack tmp = inventory[1];

				ItemStack product = getProduct(inventory[0]);

				if (tmp == null) {
					isCooking = false;
					inventory[1] = product.copy();
				} else if (tmp.getMaxStackSize() >= tmp.stackSize + product.stackSize && tmp.getItem().equals(product.getItem())) {
					isCooking = false;
					inventory[1].stackSize += product.stackSize;
				}

				if (!isCooking)
					if (inventory[0].stackSize <= 1)
						inventory[0] = null;
					else
						inventory[0].stackSize--;
			}

		}

		if (!isCooking && validIngredient()) {

			time_remaining = COOK_TIME;
			isCooking = true;

		}

		worldObj.markBlockForUpdate(this.getPos());

	}

	private boolean validIngredient() {
		if(power >= 500){
		if (inventory[0] != null)
			for (ItemStack is : recipeIngredients)
				if (inventory[0].getItem().equals(is.getItem()))
					return true;
		}
		return false;
	}

	private ItemStack getProduct(ItemStack ingredient) {
		for (int i = 0; i < recipeIngredients.size(); i++)
			if (recipeIngredients.get(i).getItem().equals(ingredient.getItem()))
				return recipeProducts.get(i);
		return null;
	}

	public int getBurnTimeRemainingScaled(int outOf) {
		return outOf - outOf * time_remaining / COOK_TIME;
	}

	public boolean isCooking() {
		return isCooking;
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
		return "Particle Accelerator";
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
	public boolean canInsertItem(int index, ItemStack itemStackIn,
			EnumFacing direction) {
		return isItemValidForSlot(index, itemStackIn);
	}
	@Override
	public boolean canExtractItem(int index, ItemStack stack,
			EnumFacing direction) {
		return true;
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
