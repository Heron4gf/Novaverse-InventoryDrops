package it.heron4gf.inventoryDrops.handlers;

import it.heron4gf.inventoryDrops.ChestEntity;
import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.HashMap;

public class ChestHandler {

    private final HashMap<Entity,ChestEntity> spawnedChests = new HashMap<>();

    public ChestEntity getChestEntity(Entity entity) {
        removeExpiredChestEntities();
        return spawnedChests.get(entity);
    }

    public Collection<ChestEntity> chestEntities() {
        removeExpiredChestEntities();
        return spawnedChests.values();
    }

    private void removeExpiredChestEntities() {
        for(Entity e : spawnedChests.keySet()) {
            if(spawnedChests.get(e).isExpired()) spawnedChests.remove(e);
        }
    }

    public void addChestEntity(ChestEntity chestEntity) {
        spawnedChests.put(chestEntity.getEntity(), chestEntity);
    }

    public void removeChestEntity(Entity entity) {
        ChestEntity chestEntity = spawnedChests.get(entity);
        chestEntity.killChest();
        this.spawnedChests.remove(entity);
    }

}
