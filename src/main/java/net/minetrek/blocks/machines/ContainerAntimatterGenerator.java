package net.minetrek.blocks.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAntimatterGenerator extends Container {
	private final IInventory entity;
    private int field_178155_i;
    private int field_178154_h;
	public ContainerAntimatterGenerator(InventoryPlayer invPlayer, IInventory entity) {
		this.entity = entity;
		// Generator Inventory
		addSlotToContainer(new SlotAntimatterGeneratorFual(entity, 0, 56, 53));
		// Hotbar
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
		}

		// Player Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
			}
		}
	}
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return entity.isUseableByPlayer(entityplayer);
	}
    public void addCraftingToCrafters(ICrafting listener)
    {
        super.addCraftingToCrafters(listener);
        listener.func_175173_a(this, this.entity);
    }
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.field_178154_h != this.entity.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.entity.getField(0));
            }

            if (this.field_178155_i != this.entity.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.entity.getField(1));
            }
        }

        this.field_178154_h = this.entity.getField(0);
        this.field_178155_i = this.entity.getField(1);
    }
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.entity.setField(id, data);
    }
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack = slot.getStack();
			ItemStack result = itemstack.copy();
			if (i >= 36) {
				if (!mergeItemStack(itemstack, 0, 36, false)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack, 36, 36 + entity.getSizeInventory(), false)) {
				return null;
			}
			if (itemstack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			slot.onPickupFromSlot(player, itemstack);
			return result;
		}
		return null;
	}
}
