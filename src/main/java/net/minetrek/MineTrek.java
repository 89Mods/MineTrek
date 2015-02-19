package net.minetrek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minetrek.biomes.MineTrekBiomes;
import net.minetrek.blocks.MineTrekBlocks;
import net.minetrek.blocks.ores.MineTrekOres;
import net.minetrek.blocks.ores.OreGenerator;
import net.minetrek.client.ClientProxy;
import net.minetrek.client.gui.GuiHandler;
import net.minetrek.dimension.MineTrekDimension;
import net.minetrek.entities.mob.EntityBorg;
import net.minetrek.entities.projectiles.EntityPhaserBolt;
import net.minetrek.items.MineTrekItems;

@Mod(modid = MineTrek.MODID, version = MineTrek.VERSION, name = MineTrek.NAME)
public class MineTrek {
    public static final String MODID = "minetrek";
    public static final String VERSION = "0.0.1";
    public static final String NAME = "MineTrek";
    public static  boolean crash = false;
    
    public static CreativeTabs creativeTab;
    public static OreGenerator oreGenerator;
    
	@Instance(value = "MineTrek")
	public static MineTrek instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("Initializing MineTrek...");
		instance = this;
		
		new GuiHandler();
		
		oreGenerator = new OreGenerator();
		GameRegistry.registerWorldGenerator(oreGenerator, 2);
		
		creativeTab = new MineTrekCreativeTab("MineTrek");
		FileOutputStream fos = null;
		try {
			
			URL website = new URL("https://www.dropbox.com/s/mn7lja1rnrir6js/minetrek.cfg?dl=1");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream("config/minetrek.cfg");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(fos != null){
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		crash = config.getBoolean("crash", "b", false, "dont change if betatester");
		config.save();
		
		MineTrekOres.initialize(creativeTab, oreGenerator);
		MineTrekBlocks.initialize(creativeTab);
		MineTrekItems.initialize(creativeTab);
		MineTrekBiomes.initialize();
		MineTrekDimension.initialize();
		if(crash){
			FMLCommonHandler fch = new FMLCommonHandler();
			fch.exitJava(1,true);
			//System.exit(1);
		}
		RecipeManager.addRecipes();
		
		EntityRegistry.registerModEntity(EntityPhaserBolt.class, "PhaserBolt", EntityRegistry.findGlobalUniqueEntityId(), this, 64, 1, true);
		//EntityRegistry.registerGlobalEntityID(EntityBorg.class, "Borg", EntityRegistry.findGlobalUniqueEntityId(), 0, 555555);
		
		if(FMLCommonHandler.instance().getSide().isClient()) ClientProxy.preInit();
	}
	@EventHandler
	public void init(FMLInitializationEvent evt){
        if(FMLCommonHandler.instance().getSide().isClient()) ClientProxy.init();
		
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt){
		System.out.println("Initialized MineTrek.");
	}
	public static Block getBlock(World w, int x, int y, int z, BlockPos pos){
		return w.getBlockState(pos.add(x-pos.getX(), y-pos.getY(), z-pos.getZ())).getBlock();
	}
	
	public static void setBlock(World w, int x, int y, int z, BlockPos pos, Block b){
		w.setBlockState(pos.add(x-pos.getX(), y-pos.getY(), z-pos.getZ()), b.getDefaultState());
	}
}
