package net.minetrek.blocks.ores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator {
	private final ArrayList<Ore> ores;
	public OreGenerator() {
		ores = new ArrayList<Ore>();
	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionId()) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}
	private void generateEnd(World world, Random random, int i, int j) {
	}
	private void generateNether(World world, Random random, int i, int j) {
	}
	private void generateSurface(World world, Random random, int i, int j) {
		for (Ore o : ores)
			for (int k = 0; k < o.getNumberPerChunk(0); k++) {
				int firstBlockXCoord = i + random.nextInt(16);
				int firstBlockYCoord = random.nextInt(o.getMaxGenHeight(0));
				int firstBlockZCoord = j + random.nextInt(16);

				(new WorldGenMinable(o.getDefaultState(), o.blocksPerVein(0))).generate(world, random, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));

			}
		//4311813251654730919
	}
	public void addOre(Ore g) {
		ores.add(g);
	}
	
	
}
