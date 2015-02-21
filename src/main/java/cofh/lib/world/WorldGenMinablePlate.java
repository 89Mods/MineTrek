package cofh.lib.world;

import static cofh.lib.world.WorldGenMinableCluster.generateBlock;

import cofh.lib.util.WeightedRandomBlock;

import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMinablePlate extends WorldGenerator {

	private final List<WeightedRandomBlock> cluster;
	private final WeightedRandomBlock[] genBlock;
	private final int radius;
	public byte height = 1;
	public byte variation = 2;
	public boolean slim = false;

	public WorldGenMinablePlate(List<WeightedRandomBlock> resource, int clusterSize, List<WeightedRandomBlock> block) {

		cluster = resource;
		radius = clusterSize;
		genBlock = block.toArray(new WeightedRandomBlock[block.size()]);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {

		pos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		int size = radius;
		if (radius > variation + 1)
			size = rand.nextInt(radius - variation) + variation;
		final int dist = size * size;
		byte height = this.height;

		boolean r = false;
		for (int posX = pos.getX() - size; posX <= pos.getX() + size; ++posX) {
			int xDist = posX - pos.getX();
			xDist *= xDist;
			for (int posZ = pos.getZ() - size; posZ <= pos.getZ() + size; ++posZ) {
				int zSize = posZ - pos.getZ();

				if (zSize * zSize + xDist <= dist) {
					for (int posY = pos.getY() - height;
							slim ? posY < pos.getY() + height : posY <= pos.getY() + height;
							++posY) {
						r |= generateBlock(world, posX, posY, posZ, genBlock, cluster);
					}
				}
			}
		}

		return r;
	}

}
