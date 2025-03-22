# InventoryDrops Documentation

## Overview
InventoryDrops is a Minecraft plugin that allows for creating temporary chests for players when they die. The contents of the player's inventory are stored in these chests for a configurable period, allowing the killer to access the items.

## Table of Contents
- [Features](#features)
- [Configuration](#configuration)
- [Plugin Structure](#plugin-structure)
- [Classes and Their Responsibilities](#classes-and-their-responsibilities)
- [Event Listeners](#event-listeners)
- [Building and Running the Plugin](#building-and-running-the-plugin)
- [Dependencies](#dependencies)

## Features
- Automatically drops the player's inventory into a chest when they die.
- Configurable parameters for chest display name, expiration time, and size.
- Integrated with PlaceholderAPI for custom display names.

## Configuration
The plugin configuration is located in `src/main/resources/config.yml`. The default parameters are:

```yaml
chest:
  displayname: "{red}%player_name% items" # Support for MiniMessages and PlaceholderAPI
  expire: 300 # Chest expiration time in seconds
  size: 1     # Size of the chest
```

## Classes and Their Responsibilities

### `InventoryDrops`
Main class extending `JavaPlugin`. Responsible for initializing the plugin and handling the lifecycle.

### `ChestEntity`
Handles the creation and management of temporary chest entities that contain items from players who have died.

### `Utils`
Utility class providing helpful methods for inventory management, like checking free inventory space.

### `ChestHandler`
Manages the collection of active `ChestEntity` instances, including adding, removing, and cleaning up expired chests.

### `GUIHandler`
Manages the graphical user interface for chests, ensuring players can interact with the chest contents safely.

## Event Listeners

### `DeathListener`
Handles player death events.
- Clears the player's inventory and determines whether to drop items or create a temporary chest.

### `InteractWithChestEntity`
Handles interactions with chest entities.
- Opens the GUI for the chests and allows players to obtain items.

### `InventoryListener`
Handles inventory interactions specific to the plugin.
- Cancels events when interacting with the plugin-specific GUI.

## Building and Running the Plugin
To build the plugin:
1. Ensure you have Gradle installed.
2. Navigate to the project directory.
3. Run the command:

   ```sh
   ./gradlew build
   ```

The compiled plugin will be located in the `build/libs` directory.

## Dependencies
- **PlaceholderAPI**: For placeholder functionality in display names.
- **Kyori Adventure**: For modern text formatting and component handling.

Assure to include all external dependencies in your server for the plugin to function correctly.