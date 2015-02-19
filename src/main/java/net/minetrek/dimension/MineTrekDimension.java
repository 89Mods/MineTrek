package net.minetrek.dimension;

import net.minecraftforge.common.DimensionManager;
import net.minetrek.dimension.space.WorldProviderSpace;

public class MineTrekDimension {
	public static int spaceDimID = 7400;
	public MineTrekDimension(){
        
	}
	public static void initialize(){
		DimensionManager.registerProviderType(spaceDimID, WorldProviderSpace.class, true);
        DimensionManager.registerDimension(spaceDimID, spaceDimID);
	}
	
}
