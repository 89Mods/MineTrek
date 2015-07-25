package net.minetrek.blocks;

import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDeflector extends TileEntity implements SimpleComponent {
	
	public TileEntityDeflector(){
		super();
		
	}
	@Override
	public String getComponentName() {
		return "minetrek_deflector";
	}
}
