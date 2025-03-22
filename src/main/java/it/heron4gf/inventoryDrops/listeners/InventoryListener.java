package it.heron4gf.inventoryDrops.listeners;

import it.heron4gf.inventoryDrops.InventoryDrops;
import it.heron4gf.inventoryDrops.handlers.GUIHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {


    @EventHandler
    void onInventoryInteract(InventoryInteractEvent event) {
        if(!isInventoryDropsInventory(event.getView())) return;
        event.setCancelled(true);
    }

    @EventHandler
    void onItemClick(InventoryClickEvent event) {
        if(!isInventoryDropsInventory(event.getView())) return;

        int slot = event.getSlot();
        if(event.getCurrentItem() == null) return;
        ItemStack clickedStack = event.getCurrentItem().clone();

        Player player = (Player) event.getWhoClicked();
        if(InventoryDrops.getInstance().getGuiHandler().getItemFromGUI(event.getInventory(), slot)) {
            player.getInventory().addItem(clickedStack);
        } else {
            player.closeInventory();
        }
    }

    @EventHandler
    void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if(!isInventoryDropsInventory(event.getView())) return;
        InventoryDrops.getInstance().getGuiHandler().closeGUI(inventory);
    }

    public boolean isInventoryDropsInventory(InventoryView inventoryView) {
        return inventoryView.title().equals(GUIHandler.GUI_NAME);
    }
}
