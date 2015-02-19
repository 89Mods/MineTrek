package net.minetrek.dimension.space;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minetrek.biomes.MineTrekBiomes;
import net.minetrek.dimension.MineTrekDimension;

public class WorldProviderSpace extends WorldProvider {
	@Override
	public void registerWorldChunkManager(){
		this.worldChunkMgr = new WorldChunkManagerHell(MineTrekBiomes.spaceBiome, 0.8f);
		this.dimensionId = MineTrekDimension.spaceDimID;
		this.hasNoSky = true;
	}
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3(0D, 0D, 0D);
    }
	public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderSpace(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled(), worldObj.getWorldInfo().getGeneratorOptions());
    }
    public boolean isSurfaceWorld()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 8.0F;
    }
    public BlockPos getSpawnCoordinate()
    {
        return new BlockPos(100, 50, 0);
    }
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }
	@Override
	public String getDimensionName() {
		return "MineTrek Space";
	}
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
    {
        return null;
    }
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }
    
	@Override
	public boolean canRespawnHere(){
		return false;
	}

	@Override
	public String getInternalNameSuffix() {
		return "minetrekspace";
	}
    
}
