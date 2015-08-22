package net.minetrek.items;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAntimatterBattery extends Item implements IEnergyContainerItem {
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	 public ItemAntimatterBattery(){
		 super();
			this.capacity = 10000000;
			this.maxReceive = 100000;
			this.maxExtract = 100000;
	 }

		@Override
		public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

			if (container.getTagCompound() == null) {
				container.setTagCompound(new NBTTagCompound());
			}
			int energy = container.getTagCompound().getInteger("Energy");
			int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

			if (!simulate) {
				energy += energyReceived;
				container.getTagCompound().setInteger("Energy", energy);
			}
			return energyReceived;
		}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
			return 0;
		}
		int energy = container.getTagCompound().getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			container.getTagCompound().setInteger("Energy", energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
			return 0;
		}
		return container.getTagCompound().getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return capacity;
	}
}
