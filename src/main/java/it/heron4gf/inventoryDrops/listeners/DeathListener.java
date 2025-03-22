package it.heron4gf.inventoryDrops.listeners;

import it.heron4gf.inventoryDrops.ChestEntity;
import it.heron4gf.inventoryDrops.InventoryDrops;
import it.heron4gf.inventoryDrops.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {
    @EventHandler
    void onPlayerDie(PlayerDeathEvent event) {
        event.getDrops().clear();
        event.setShouldDropExperience(false);

        Player dead = event.getPlayer();
        Player killer = dead.getKiller();
        if(killer == null) return;

        ItemStack[] contents = dead.getInventory().getContents();
        if(Utils.hasFreeSpace(contents, killer)) {
            Utils.giveEverything(contents, killer);
        } else {
            ChestEntity chestEntity = new ChestEntity(dead, dead.getInventory());
            InventoryDrops.getInstance().getChestHandler().addChestEntity(chestEntity);
            chestEntity.spawnChest();
        }

    }
}
