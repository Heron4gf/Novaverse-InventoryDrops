package it.heron4gf.inventoryDrops;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Utils {

    private static final int INVENTORY_SIZE = 36;

    public boolean hasFreeSpace(ItemStack[] contents, Player destination) {
        int required_slots = getNonNullSlots(contents);
        int available_slots = INVENTORY_SIZE - getNonNullSlots(destination.getInventory().getContents());
        return available_slots >= required_slots;
    }

    public int getNonNullSlots(ItemStack[] contents) {
        int i = 0;
        for (ItemStack itemStack : contents) {
            if(itemStack != null && !itemStack.isEmpty()) {
                i++;
            }
        }
        return i;
    }


    public void giveEverything(ItemStack[] contents, Player killer) {
        for(ItemStack itemStack : contents) {
            killer.getInventory().addItem(itemStack);
        }
    }

}
