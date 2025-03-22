package it.heron4gf.inventoryDrops.handlers;

import it.heron4gf.inventoryDrops.ChestEntity;
import it.heron4gf.inventoryDrops.InventoryDrops;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class GUIHandler {

    private final HashMap<ChestEntity, Inventory> openedGUIs = new HashMap<>();

    public static final Component GUI_NAME = Component.empty()
            .color(TextColor.fromHexString("#" + UUID.randomUUID().toString().substring(0, 6)))  // Random unique color
            .append(Component.text("")).decoration(TextDecoration.ITALIC, false)  // No text added, ensuring invisibility
            .append(Component.empty());

    public void openGUI(ChestEntity chestEntity, Player player) {
        if(openedGUIs.containsKey(chestEntity)) {
            player.openInventory(openedGUIs.get(chestEntity));
            return;
        }

        Inventory gui = Bukkit.createInventory(null, InventoryType.CHEST, GUI_NAME);
        gui.setContents(chestEntity.getInventory().getContents().clone());
        openedGUIs.put(chestEntity, gui);
        openGUI(chestEntity, player);
    }

    public ChestEntity getChestEntity(Inventory inventory) {
        for(ChestEntity chestEntity : openedGUIs.keySet()) {
            if(openedGUIs.get(chestEntity).equals(inventory)) {
                return chestEntity;
            }
        }
        return null;
    }

    public void closeGUI(Inventory inventory) {
        ChestEntity chestEntity = getChestEntity(inventory);
        if(chestEntity == null) return;
        Bukkit.getScheduler().runTaskLater(InventoryDrops.getInstance(), () -> {
            if(inventory.getViewers().isEmpty()) {
                openedGUIs.remove(chestEntity);
            }
        }, 1L);
    }


    public boolean getItemFromGUI(Inventory inventory, int index) {
        if(index < 0 || index >= 36) return false;
        ChestEntity chestEntity = getChestEntity(inventory);
        if(chestEntity.isExpired()) return false;
        ItemStack itemStack = chestEntity.getInventory().getItem(index);
        if(itemStack == null || itemStack.isEmpty()) return false;

        // update both chestEntity inventory and the cloned inventory you're looking at
        chestEntity.getInventory().setItem(index, null);
        inventory.setItem(index, null);

        if(chestEntity.getInventory().isEmpty()) {
            chestEntity.killChest();
        }
        return true;
    }
}
