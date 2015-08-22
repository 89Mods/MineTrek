package net.minetrek.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class RenderWire extends TileEntitySpecialRenderer {
	ResourceLocation pipeTexture = (new ResourceLocation("minetrek:textures/blocks/wire.png"));
	boolean drawInside = true;
	float pixel = 1F/16F;
	float texturePixel = 1F/32F;
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double translationX, double translationY, double translationZ, float f,int iinteger) {
		//System.out.println("YOLO");
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(pipeTexture);
		{
			TileEntityWire pipe = (TileEntityWire) tileentity;
			if(!pipe.onlyOneOpposite(pipe.connections)){
				drawCore(tileentity);
				for(int i = 0; i < pipe.connections.length; i++){
					if(pipe.connections[i] != null){
						drawConnector(pipe.connections[i]);
					}
				}
			}
			else{
				for(int i = 0; i < pipe.connections.length; i++){
					if(pipe.connections[i] != null){
						drawStraight(pipe.connections[i]);
						break;
					}
				}
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}
	public void drawStraight(EnumFacing direction){
		WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
		worldrenderer.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if(direction.equals(EnumFacing.UP) || direction.equals(EnumFacing.DOWN)){
				//ROTATE
			}else if(direction.equals(EnumFacing.SOUTH) || direction.equals(EnumFacing.NORTH)){
				GL11.glRotatef(90, 1, 0, 0);
			}else if(direction.equals(EnumFacing.WEST) || direction.equals(EnumFacing.EAST)){
				GL11.glRotatef(90, 0, 0, 1);
			}
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			
			worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			
			if(drawInside){
				worldrenderer.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				
				worldrenderer.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			}
			
		}
		Tessellator.getInstance().draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(EnumFacing.UP) || direction.equals(EnumFacing.DOWN)){
			//ROTATE
		}else if(direction.equals(EnumFacing.SOUTH) || direction.equals(EnumFacing.NORTH)){
			GL11.glRotatef(-90, 1, 0, 0);
		}else if(direction.equals(EnumFacing.WEST) || direction.equals(EnumFacing.EAST)){
			GL11.glRotatef(-90, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}
	public void drawConnector(EnumFacing direction){
		WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
		worldrenderer.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if(direction.equals(EnumFacing.UP)){
				//ROTATE
			}else if(direction.equals(EnumFacing.DOWN)){
				GL11.glRotatef(180, 1, 0, 0);
			}else if(direction.equals(EnumFacing.SOUTH)){
				GL11.glRotatef(90, 1, 0, 0);
			}else if(direction.equals(EnumFacing.NORTH)){
				GL11.glRotatef(270, 1, 0, 0);
			}else if(direction.equals(EnumFacing.WEST)){
				GL11.glRotatef(90, 0, 0, 1);
			}else if(direction.equals(EnumFacing.EAST)){
				GL11.glRotatef(270, 0, 0, 1);
			}
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			
			if(drawInside){
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			}
			
		}
		Tessellator.getInstance().draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(EnumFacing.UP)){
			//ROTATE
		}else if(direction.equals(EnumFacing.DOWN)){
			GL11.glRotatef(-180, 1, 0, 0);
		}else if(direction.equals(EnumFacing.SOUTH)){
			GL11.glRotatef(-90, 1, 0, 0);
		}else if(direction.equals(EnumFacing.NORTH)){
			GL11.glRotatef(-270, 1, 0, 0);
		}else if(direction.equals(EnumFacing.WEST)){
			GL11.glRotatef(-90, 0, 0, 1);
		}else if(direction.equals(EnumFacing.EAST)){
			GL11.glRotatef(-270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}
	public void drawCore(TileEntity tileentity){
		WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
		worldrenderer.startDrawingQuads();
		{
			worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			
			worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
			
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			
			worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
			worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
			worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
			
			if(drawInside){
				worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 5*texturePixel);
				
				worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
				
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
				
				worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturePixel, 5*texturePixel);
				worldrenderer.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturePixel, 0*texturePixel);
				worldrenderer.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturePixel, 5*texturePixel);
				
			}
			
		}
		Tessellator.getInstance().draw();;
		
	}
}
