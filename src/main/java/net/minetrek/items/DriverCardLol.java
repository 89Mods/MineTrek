package net.minetrek.items;

import net.minecraft.item.ItemStack;
import net.minetrek.MineTrek;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.EnvironmentHost;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Component;
import li.cil.oc.api.prefab.ManagedEnvironment;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.DriverItem;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.machine.Callback;

public class DriverCardLol extends DriverItem {
	public DriverCardLol(){
		super(new ItemStack(MineTrekItems.lol_card));
	}

	@Override
	public ManagedEnvironment createEnvironment(ItemStack stack,
			EnvironmentHost host) {
		return new EnvironmentLol(host);
	}
    @Override
    public int tier(final ItemStack stack) {
        return 0;
    }
	@Override
	public String slot(ItemStack stack) {
		return Slot.Card;
	}
    public class EnvironmentLol extends ManagedEnvironment {
        protected final EnvironmentHost container;

        public EnvironmentLol(EnvironmentHost container) {
            this.container = container;
    		this.setNode(Network.newNode(this, Visibility.Neighbors).
    				withComponent("lol").
    				create());
        }
        
        @Callback(direct = true, limit = 16)
        public Object[] lol(Context context,Arguments args) {
        	int num = args.checkInteger(0);
    		if(num > 4346) {
    			return new Object[] { false, "Number to big" };
    		}
    		String output = "L";
    		if(num < 1){
    			return new Object[] { false, "Number to small" };
    		}
    		for(int i = 0; i < num; i++){
    			output = output + "O";
    		}
    		output = output + "L";
			return new Object[]{true,output};
        	
        }
    }
}
