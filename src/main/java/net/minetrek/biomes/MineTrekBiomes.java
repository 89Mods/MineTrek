package net.minetrek.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class MineTrekBiomes {
    public static BiomeGenBase spaceBiome;
    public static void initialize(){
    	spaceBiome = new BiomeGenSpace(55).setColor(0).setBiomeName("MineTrek Space").setHeight(new BiomeGenBase.Height(0f, 0f));  
    	
    }
}
