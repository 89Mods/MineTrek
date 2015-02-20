package net.minetrek.items;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAntimatterBattery extends Item implements IEnergyContainerItem {
	private int energyStored;
	 public ItemAntimatterBattery(){
		 super();
	 }

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive,
			boolean simulate) {
		if(maxReceive < 500){
			return maxReceive;
		}else{
		return 500;
		}
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract,
			boolean simulate) {
		if(maxExtract < 500){
			return maxExtract;
		}else{
		return 500;
		}
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return energyStored;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return 100000;
	}
}
