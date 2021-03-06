package net.minetrek.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minetrek.MineTrek;
import net.minetrek.blocks.MineTrekBlocks;
import net.minetrek.blocks.RenderWire;
import net.minetrek.blocks.TileEntityWire;
import net.minetrek.client.render.ModelTorpedo;
import net.minetrek.client.render.PhaserBoltRenderer;
import net.minetrek.client.render.RenderAntimaterTNT;
import net.minetrek.client.render.RenderBorg;
import net.minetrek.client.render.RenderPhotonTorpedo;
import net.minetrek.entities.explosives.EntityAntimaterTNT;
import net.minetrek.entities.mob.EntityBorg;
import net.minetrek.entities.projectiles.EntityPhaserBolt;
import net.minetrek.entities.projectiles.EntityPhotonTorpedo;
import net.minetrek.items.MineTrekItems;

public class ClientProxy {
	public static void preInit(){
		
	}
	public static void init(){
	RenderingRegistry.registerEntityRenderingHandler(EntityAntimaterTNT.class, new RenderAntimaterTNT(null));
		for(String name : MineTrekItems.itemList){
		    registerItem(GameRegistry.findItem("minetrek", name), "minetrek:"+name);
		}
		for(String name : MineTrekBlocks.blockList){
			Item i = GameRegistry.findItem("minetrek", name);
			registerItem(i, "minetrek:"+name);
        }
		RenderingRegistry.registerEntityRenderingHandler(EntityPhaserBolt.class, new PhaserBoltRenderer(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPhotonTorpedo.class, new RenderPhotonTorpedo(Minecraft.getMinecraft().getRenderManager()));
		ClientRegistry.registerTileEntity(TileEntityWire.class, "wireTileEntity", new RenderWire());
	}
	public static void registerItem(Item item, int metadata, String itemName)
    {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(item, metadata, new ModelResourceLocation(itemName, "inventory"));
    }
    public static void registerItem(Item item, String itemName)
    {
        registerItem(item, 0, itemName);
    }
}
