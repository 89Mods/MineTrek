package cofh.lib.world;

import static cofh.lib.world.WorldGenMinableCluster.*;

import cofh.lib.util.WeightedRandomBlock;

import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenStalactite extends WorldGenStalagmite {

	public WorldGenStalactite(List<WeightedRandomBlock> resource, List<WeightedRandomBlock> block, List<WeightedRandomBlock> gblock) {
		super(resource, block, gblock);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos start) {

		int end = world.getActualHeight();
		while (world.isAirBlock(start) && start.getY() < end) {
			start = new BlockPos(start.getX(),start.getY() + 1, start.getZ());
		}

		start = new BlockPos(start.getX(),start.getY() - 1, start.getZ());
		if (!canGenerateInBlock(world, start.getX(), start.getY(), start.getZ(), baseBlock)) {
			return false;
		}

		int maxHeight = rand.nextInt(heightVariance) + minHeight;

		int size = genSize > 0 ? genSize : maxHeight / heightMod + rand.nextInt(sizeVariance);
		boolean r = false;
		for (int x = -size; x <= size; ++x) {
			for (int z = -size; z <= size; ++z) {
				if (!canGenerateInBlock(world, start.getX() + x, start.getY() + 1, start.getZ() + z, baseBlock)) {
					continue;
				}
				int height = getHeight(x, z, size, rand, maxHeight);
				for (int y = 0; y < height; ++y) {
					r |= generateBlock(world, start.getX() + x, start.getY() - y, start.getZ() + z, genBlock, cluster);
				}
			}
		}
		
		return r;
	}
}
