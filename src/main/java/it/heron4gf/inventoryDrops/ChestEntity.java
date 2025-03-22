package it.heron4gf.inventoryDrops;

import lombok.Data;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

@Data
public class ChestEntity {



    private UUID dead;
    private Inventory inventory;
    private Location location;

    private Entity entity;
    private boolean expired = false;


    public ChestEntity(Player dead, Inventory inventory) {
        this.dead = dead.getUniqueId();
        this.inventory = inventory;
        this.location = dead.getLocation();
    }

    public void spawnChest() {
        TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class);
        BlockDisplay blockDisplay = location.getWorld().spawn(location, BlockDisplay.class);

        textDisplay.addPassenger(blockDisplay);

        blockDisplay.setBlock(Material.CHEST.createBlockData());

        float size = (float)InventoryDrops.getInstance().getConfig().getDouble("chest.size", 1f);
        blockDisplay.setDisplayWidth(size);
        blockDisplay.setDisplayHeight(size);

        String displayNameFormat = InventoryDrops.getInstance().getConfig().getString("chest.displayname", "%player_name% items");

        Player dead = Bukkit.getPlayer(this.dead);
        String displayName = PlaceholderAPI.setPlaceholders(dead, displayNameFormat);

        textDisplay.customName(MiniMessage.miniMessage().deserialize(displayName));

        ItemStack chestItem = new ItemStack(Material.CHEST);
        ItemMeta meta = chestItem.getItemMeta();
        chestItem.setItemMeta(meta);

        scheduleKill();
    }

    private void scheduleKill() {
        long expire = InventoryDrops.getInstance().getConfig().getInt("chest.expire", 300)*20L;
        Bukkit.getScheduler().runTaskLater(InventoryDrops.getInstance(), this::killChest, expire);
    }

    public void killChest() {
        this.entity.remove();
        this.expired = true;
    }

}
