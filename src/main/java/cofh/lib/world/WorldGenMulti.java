package cofh.lib.world;

import cofh.lib.util.WeightedRandomWorldGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMulti extends WorldGenerator {

	private final WeightedRandomWorldGenerator[] generators;
	private Collection collection = new ArrayList<WeightedRandomWorldGenerator>();

	public WorldGenMulti(ArrayList<WeightedRandomWorldGenerator> values) {

		generators = values.toArray(new WeightedRandomWorldGenerator[values.size()]);
		for(int i = 0; i < generators.length; i++){
			collection.add(generators[i]);
		}
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos) {

		WeightedRandomWorldGenerator gen = (WeightedRandomWorldGenerator)WeightedRandom.getRandomItem(random, collection);
		return gen.generator.generate(world, random, pos);
	}

}
