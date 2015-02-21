package cofh.lib.world;

import static cofh.lib.world.WorldGenMinableCluster.*;

import cofh.lib.util.WeightedRandomBlock;

import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoulder extends WorldGenerator
{
	private final List<WeightedRandomBlock> cluster;
	private final WeightedRandomBlock[] genBlock;
	private final int size;
	public int sizeVariance = 2;
	public int clusters = 3;
	public int clusterVariance = 0;
	public boolean hollow = false;
	public float hollowAmt = 0.1665f;
	public float hollowVar = 0;
	private BlockPos center2;

	public WorldGenBoulder(List<WeightedRandomBlock> resource, int minSize, List<WeightedRandomBlock> block) {

		cluster = resource;
		size = minSize;
		genBlock = block.toArray(new WeightedRandomBlock[block.size()]);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos center) {

		final int minSize = size, var = sizeVariance;
		boolean r = false;
		int i = clusterVariance > 0 ? clusters + rand.nextInt(clusterVariance + 1) : clusters;
		while (i --> 0) {

			while (center.getY() > minSize && world.isAirBlock(center)) {
				center = new BlockPos(center.getX(),center.getY() - 1,center.getZ());
			}
			if (center.getY() <= (minSize + var + 1)) {
				return false;
			}

			if (canGenerateInBlock(world, center.getX(), center.getY() - 1, center.getZ(), genBlock)) {

				int xWidth = minSize + (var > 1 ? rand.nextInt(var) : 0);
				int yWidth = minSize + (var > 1 ? rand.nextInt(var) : 0);
				int zWidth = minSize + (var > 1 ? rand.nextInt(var) : 0);
				float maxDist = (xWidth + yWidth + zWidth) * 0.333F + 0.5F;
				maxDist *= maxDist;
				float minDist = hollow ? (xWidth + yWidth + zWidth) * (hollowAmt * (1 - rand.nextFloat() * hollowVar)) : 0;
				minDist *= minDist;

				for (int x = -xWidth; x <= xWidth; ++x) {
					final int xDist = x * x;

					for (int z = -zWidth; z <= zWidth; ++z) {
						final int xzDist = xDist + z * z;

						for (int y = -yWidth; y <= yWidth; ++y) {
							final int dist = xzDist + y * y;

							if (dist <= maxDist) {
								if (dist >= minDist)
									r |= generateBlock(world, center.getX() + x, center.getY() + y, center.getZ() + z, cluster);
								else
									
									center2 = new BlockPos(center.getX() + x, center.getY() + y, center.getZ() + z);
									r |= world.setBlockToAir(center2);
							}
						}
					}
				}
			}
      center = new BlockPos(center.getX() + rand.nextInt(var + minSize * 2) - (minSize + var/2),center.getY() + rand.nextInt((var+1) * 3) - (var+1), center.getZ() + rand.nextInt(var + minSize * 2) - (minSize + var/2));
		}

		return r;
	}
}