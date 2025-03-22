package it.heron4gf.inventoryDrops.listeners;

import it.heron4gf.inventoryDrops.ChestEntity;
import it.heron4gf.inventoryDrops.InventoryDrops;
import it.heron4gf.inventoryDrops.Utils;
import it.heron4gf.inventoryDrops.handlers.GUIHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class InteractWithChestEntity implements Listener {

    @EventHandler
    void onPlayerInteractWithEntity(PlayerInteractAtEntityEvent event) {
        Entity clicked = event.getRightClicked();
        ChestEntity chestEntity = InventoryDrops.getInstance().getChestHandler().getChestEntity(clicked);
        if(chestEntity == null) return;

        InventoryDrops.getInstance().getGuiHandler().openGUI(chestEntity, event.getPlayer());
    }

    @EventHandler
    void onEntityPunch(EntityDamageByEntityEvent event) {
        Entity punched = event.getEntity();
        if(!(event.getDamager() instanceof Player killer)) return;
        ChestEntity chestEntity = InventoryDrops.getInstance().getChestHandler().getChestEntity(punched);
        if(chestEntity == null) return;

        ItemStack[] contents = chestEntity.getInventory().getContents();
        if(Utils.hasFreeSpace(contents, killer)) {
            Utils.giveEverything(contents, killer);
        } else {
            InventoryDrops.getInstance().getGuiHandler().openGUI(chestEntity, killer);
        }

    }
}
