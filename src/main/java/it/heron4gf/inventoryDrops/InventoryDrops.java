package it.heron4gf.inventoryDrops;

import it.heron4gf.inventoryDrops.handlers.ChestHandler;
import it.heron4gf.inventoryDrops.handlers.GUIHandler;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryDrops extends JavaPlugin {

    @Getter
    private static InventoryDrops instance;

    @Getter
    private final ChestHandler chestHandler = new ChestHandler();

    @Getter
    private final GUIHandler guiHandler = new GUIHandler();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        for(ChestEntity chestEntity : chestHandler.chestEntities()) {
            chestEntity.killChest(); // kill all chests on shutdown
        }
    }

}
