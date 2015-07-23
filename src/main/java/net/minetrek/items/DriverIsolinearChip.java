package net.minetrek.items;

import net.minecraft.item.ItemStack;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.EnvironmentHost;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverItem;

public class DriverIsolinearChip extends DriverItem {

	public DriverIsolinearChip(){
		super(new ItemStack(MineTrekItems.isolinear_chip));
	}
	
	@Override
	public ManagedEnvironment createEnvironment(ItemStack stack,
			EnvironmentHost host) {
		return new EnvironmentIsolinearChip(host);
	}

	@Override
	public String slot(ItemStack stack) {
		return Slot.HDD;
	}
	public class EnvironmentIsolinearChip extends ManagedEnvironment {
		protected final EnvironmentHost container;
		
		public EnvironmentIsolinearChip(EnvironmentHost container){
            this.container = container;
    		this.setNode(Network.newNode(this, Visibility.Neighbors).
    				withComponent("isolinear_chip").
    				create());
		}
	}
}
