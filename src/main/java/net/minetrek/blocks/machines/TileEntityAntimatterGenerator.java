package net.minetrek.blocks.machines;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minetrek.items.MineTrekItems;

public class TileEntityAntimatterGenerator extends TileEntityLockable implements IEnergyProvider, IUpdatePlayerListBox, ISidedInventory {
	private ItemStack[] inventory;
	private int furnaceBurnTime;
	private int currentItemBurnTime;
	private String furnaceCustomName;
	private int buffer = 0;
	private int bufferSize;
	private int energyPerTick;
	public boolean dirty = false;
	private boolean isCooking = false;
	//private int consumeRate;
    private static final int[] slotsBottom = new int[] {0};
    private static final int[] slotsSides = new int[] {0};
	public TileEntityAntimatterGenerator(){
		super();
		inventory = new ItemStack[1];
		setEnergyStorage(160000, 4);
	}
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}
	public void setEnergyStorage(int size, float rate) {
		bufferSize = size;
		energyPerTick = (int) ((float)40 * rate);
		//consumeRate = (int) ((float)40 * rate);
	}
	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}
	public boolean isDirty(){
		return dirty;
	}
	@Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.inventory[index] != null)
        {
            ItemStack itemstack;

            if (this.inventory[index].stackSize <= count)
            {
                itemstack = this.inventory[index];
                this.inventory[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[index].splitStack(count);

                if (this.inventory[index].stackSize == 0)
                {
                    this.inventory[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
	
	public void updateEntity(){
		this.update();
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
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.inventory[index] != null)
        {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.inventory[index]) && ItemStack.areItemStackTagsEqual(stack, this.inventory[index]);
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
        	
            this.markDirty();
            dirty = true;
        }
    }
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return itemstack == null || this.isItemFuel(itemstack);
	}
    public static int getItemBurnTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
            Item item = p_145952_0_.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                
            }
            
            if (item == MineTrekItems.antimaterstorage_full) return 1237;
            return 0;
        }
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
	        
	        compound.setShort("BurnTime", (short)this.furnaceBurnTime);
	        compound.setBoolean("IsCooking", isCooking);
	        compound.setInteger("power", this.buffer);
	        compound.setTag("Items", nbttaglist);
	        
	        if (this.hasCustomName())
	        {
	            compound.setString("CustomName", this.furnaceCustomName);
	        }
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
        
        this.furnaceBurnTime = compound.getShort("BurnTime");
        this.isCooking = compound.getBoolean("IsCooking");
        this.buffer = compound.getInteger("power");
        
        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
	}
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory p_174903_0_)
    {
        return p_174903_0_.getField(0) > 0;
    }
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if(this.buffer > this.bufferSize){
        	this.buffer = this.bufferSize;
        }
        if(this.buffer < 0){
        	this.buffer = 0;
        }
            /*--this.furnaceBurnTime;
            if(this.buffer < this.bufferSize){
            	int buffer2 = this.buffer;
            	buffer2 += energyPerTick;
            	if(buffer2 < this.bufferSize + 1){
            		this.buffer = buffer2;
            	}else if(buffer2 > 4990 || buffer2 < bufferSize){
            		this.buffer = this.bufferSize;
            	}
            }*/
        	if (!worldObj.isRemote) {
    			if (isBurning()) {
    				changeCharge(energyPerTick);
    			}
    			outputEnergy();
        	}else{
        		checkUpdate();
        	}
        if (!this.worldObj.isRemote)
        {
    		if (furnaceBurnTime > 0) {
    			furnaceBurnTime--;
    		}
        	if (isCooking && furnaceBurnTime == 0) {
        		ItemStack tmp = inventory[0];
        		if (tmp == null) {
        			isCooking = false;
        		}
        	}
                if (!this.isBurning() && this.inventory[0] != null && this.buffer < this.bufferSize)
                {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.inventory[0]);

                    if (this.isBurning())
                    {
                        flag1 = true;
                        if (this.inventory[0] != null)
                        {
                            --this.inventory[0].stackSize;

                            if (this.inventory[0].stackSize == 0)
                            {
                                this.inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
                            }
                        }
                    }
                }

            if (flag != this.isBurning())
            {
                flag1 = true;
                
            }
        }

        if (flag1)
        {
            this.markDirty();
            dirty = true;
        }
        worldObj.markBlockForUpdate(this.getPos());
    }
	protected void checkUpdate() {
			
			worldObj.notifyBlockOfStateChange(pos, worldObj.getBlockState(pos).getBlock());
			worldObj.markBlockForUpdate(this.pos);
	}
    public int func_174904_a(ItemStack p_174904_1_)
    {
        return 200;
    }
    public static boolean isItemFuel(ItemStack p_145954_0_)
    {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(p_145954_0_) > 0;
    }
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
    public int[] getSlotsForFace(EnumFacing side)
    {
        if(side == EnumFacing.UP){
        	return null;
        }
        if(side == EnumFacing.DOWN){
        	return slotsBottom;
        }
        return slotsSides;
    }
	@Override
	public void openInventory(EntityPlayer player) {
	}
	@Override
	public void closeInventory(EntityPlayer player) {	
	}
	@Override
	public int getField(int id) {
		switch(id){
		case 0:
			return furnaceBurnTime;
		case 1:
			return currentItemBurnTime;
		case 2:
			return this.buffer;
		case 3:
			return this.bufferSize;
		default:
				return 0;
		}
	}
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }
	@Override
	public void setField(int id, int value) {
		switch(id){
		case 0:
			furnaceBurnTime = value;
		break;
		case 1:
			currentItemBurnTime = value;
			break;
		case 2:
			if(value > this.bufferSize){
				value = this.bufferSize;
			}
			this.buffer = value;
			break;
		case 3:
			//Nothing Here. LOL
		break;
		}
	}
	@Override
	public int getFieldCount() {
		return 4;
	}
	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "Antimatter Generator";
	}
	@Override
    public boolean hasCustomName()
    {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
    }
    public void setCustomInventoryName(String p_145951_1_)
    {
        this.furnaceCustomName = p_145951_1_;
    }
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }
    
	@Override
	public Container createContainer(InventoryPlayer playerInventory,
			EntityPlayer playerIn) {
		return new ContainerAntimatterGenerator(playerInventory, this);
	}
    public String getGuiID()
    {
        return "minetrek:antimatterGenerator";
    }
    public void clear()
    {
        for (int i = 0; i < this.inventory.length; ++i)
        {
            this.inventory[i] = null;
        }
    }
	@Override
	public boolean canConnectEnergy(EnumFacing facing) {
		return true;
	}
	@Override
	public IChatComponent getDisplayName() {
		return null;
	}
	@Override
	public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
		int energyExtracted = Math.min(buffer, maxExtract);
		if (!simulate) {
			changeCharge((-energyExtracted));
		}
		return energyExtracted;
	}
	@Override
	public int getEnergyStored(EnumFacing facing) {
		return this.buffer;
	}
	@Override
	public int getMaxEnergyStored(EnumFacing facing) {
		return this.bufferSize;
	}
	public void changeCharge(int amount) {
		int prevAmount = buffer;
		
		buffer += amount;
		if (buffer >= bufferSize) buffer = bufferSize;
		if (buffer < 0) buffer = 0;
		
		if (buffer != prevAmount){ this.markDirty();
		dirty = true;
		}
	}
	public void outputEnergy() {
		//Lets go around the world and try and give it to someone!
		for(EnumFacing facing : EnumFacing.values()) {
			//Do we have any energy up for grabs?
			if (buffer>0) {
				TileEntity entity = worldObj.getTileEntity(pos.offset(facing));
				if (entity instanceof IEnergyReceiver) {
					IEnergyReceiver energy = (IEnergyReceiver) entity;
					if (energy.canConnectEnergy(facing.getOpposite())) {
						int giveAmount = energy.receiveEnergy(facing.getOpposite(), buffer, false);
						if (giveAmount>0) {
							//System.out.println(giveAmount);
							changeCharge(giveAmount * -1);
						}
					}
				}
			}
		}
	}
	
}
