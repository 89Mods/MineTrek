package net.minetrek.dimension.space;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderSpace implements IChunkProvider {
	private World worldObj;
	private Random rand;
    private final boolean mapFeaturesEnabled;
    private WorldType theWorldType;
    private BiomeGenBase[] biomesForGeneration;
    public ChunkProviderSpace(World worldIn, long p_i45636_2_, boolean p_i45636_4_, String p_i45636_5_)
    {
    	this.worldObj = worldIn;
    	this.theWorldType = worldIn.getWorldInfo().getTerrainType();
    	this.rand = new Random(p_i45636_2_);
    	this.mapFeaturesEnabled = p_i45636_4_;
    }
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}
	
	private int chunkX=0, chunkZ=0;
	@Override
	public Chunk provideChunk(int x, int z) {
		/*this.rand.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
        Chunk chunk = new Chunk(this.worldObj, chunkprimer, p_73154_1_, p_73154_2_);
        byte[] abyte = chunk.getBiomeArray();
        chunk.generateSkylightMap();
        return chunk;*/
        chunkX = x; chunkZ = z;
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        //this.func_180520_a(x, z, chunkprimer);
        //this.func_180519_a(chunkprimer);
        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
	}
	@Override
	public Chunk provideChunk(BlockPos p_177459_1_) {
		return this.provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
	}
	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
		BlockFalling.fallInstantly = true;
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BlockPos blockpos = new BlockPos(k, 0, l);
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)p_73153_2_ * i1 + (long)p_73153_3_ * j1 ^ this.worldObj.getSeed());
        boolean flag = false;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
        biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(k, 0, l));
        BlockFalling.fallInstantly = false;
        
	}
	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
		return false;
	}
	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
		return true;
	}
	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}
	@Override
	public boolean canSave() {
		return true;
	}
	@Override
	public String makeString() {
		return "RandomLevelSource";
	}
	@Override
	public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_177458_2_);
		return biomegenbase.getSpawnableList(p_177458_1_);
	}
	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
		return null;
	}
	@Override
	public int getLoadedChunkCount() {
		return 0;
	}
	@Override
	public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {}
	@Override
	public void saveExtraData() {}
    
}
