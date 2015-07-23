package net.minetrek.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Date: 7/21/2015 8:47:47 AM
//Template version 1.1
//Java generated by Techne
//Keep in mind that you still need to fill in some blanks
//- ZeuX

public class ModelTorpedo extends ModelBase
{
//fields
ModelRenderer Shape1;
ModelRenderer Shape2;
ModelRenderer Shape3;
ModelRenderer Shape4;
ModelRenderer Shape5;

public ModelTorpedo()
{
textureWidth = 128;
textureHeight = 64;

Shape1 = new ModelRenderer(this, 0, 26);
Shape1.addBox(0F, 0F, 0F, 12, 8, 30);
Shape1.setRotationPoint(-6F, 11F, -15F);
Shape1.setTextureSize(128, 64);
Shape1.mirror = true;
setRotation(Shape1, 0F, 0F, 0F);
Shape2 = new ModelRenderer(this, 0, 0);
Shape2.addBox(0F, 0F, 0F, 10, 1, 30);
Shape2.setRotationPoint(-5F, 19F, -15F);
Shape2.setTextureSize(128, 64);
Shape2.mirror = true;
setRotation(Shape2, 0F, 0F, 0F);
Shape3 = new ModelRenderer(this, 48, 0);
Shape3.addBox(0F, 0F, 0F, 10, 1, 30);
Shape3.setRotationPoint(-5F, 10F, -15F);
Shape3.setTextureSize(128, 64);
Shape3.mirror = true;
setRotation(Shape3, 0F, 0F, 0F);
Shape4 = new ModelRenderer(this, 0, 0);
Shape4.addBox(0F, 0F, 0F, 10, 8, 2);
Shape4.setRotationPoint(-5F, 11F, 15F);
Shape4.setTextureSize(128, 64);
Shape4.mirror = true;
setRotation(Shape4, 0F, 0F, 0F);
Shape5 = new ModelRenderer(this, 0, 0);
Shape5.addBox(0F, 0F, 0F, 10, 8, 2);
Shape5.setRotationPoint(-5F, 11F, -17F);
Shape5.setTextureSize(128, 64);
Shape5.mirror = true;
setRotation(Shape5, 0F, 0F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
super.render(entity, f, f1, f2, f3, f4, f5);
setRotationAngles(f, f1, f2, f3, f4, f5);
Shape1.render(f5);
Shape2.render(f5);
Shape3.render(f5);
Shape4.render(f5);
Shape5.render(f5);
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
model.rotateAngleX = x;
model.rotateAngleY = y;
model.rotateAngleZ = z;
}

public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
{
super.setRotationAngles(f, f1, f2, f3, f4, f5,null);
}

}
