package net.minetrek.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemAntimaterstorage extends Item {
	public ItemAntimaterstorage(){
		
	}
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

        if (movingobjectposition == null)
        {
            return itemStackIn;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = movingobjectposition.getBlockPos();

                if (!worldIn.isBlockModifiable(playerIn, blockpos))
                {
                    return itemStackIn;
                }

                if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn))
                {
                    return itemStackIn;
                }

                if (worldIn.getBlockState(blockpos).getBlock().getMaterial() == Material.water)
                {
                    --itemStackIn.stackSize;

                    if (itemStackIn.stackSize <= 0)
                    {
                        return new ItemStack(MineTrekItems.antimaterstorage_water);
                    }

                    if (!playerIn.inventory.addItemStackToInventory(new ItemStack(MineTrekItems.antimaterstorage_water)))
                    {
                        playerIn.dropPlayerItemWithRandomChoice(new ItemStack(MineTrekItems.antimaterstorage_water, 1, 0), false);
                    }
                }
            }

            return itemStackIn;
        }
    }
}
