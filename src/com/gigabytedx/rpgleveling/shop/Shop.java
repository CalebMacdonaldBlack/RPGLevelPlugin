package com.gigabytedx.rpgleveling.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.item.AddItemToInventory;
import com.gigabytedx.rpgleveling.item.Item;

public class Shop {
	public static void openShop(Main plugin, Player player, String location){
		List<Item> items = plugin.items.getItems();
		player.sendMessage("size of items is: " + items.size());
		Inventory inv = Bukkit.createInventory(player, 27,
				ChatColor.DARK_BLUE + "Shop: " + ChatColor.DARK_BLUE + location);

		List<String> unlockedItemNames = new ArrayList<>();
		for (String skillName : plugin.getConfig().getConfigurationSection("skills").getKeys(false)) {
			int currentLevel = plugin.playerExperience.getInt(player.getUniqueId().toString() + "." + skillName)
					/ plugin.playerExperience.getInt("Level Upgrade at");
			for (String levelName : plugin.getConfig()
					.getConfigurationSection("skills." + skillName + ".levels").getKeys(false)) {

				if (plugin.getConfig().getInt(
						"skills." + skillName + ".levels." + levelName + ".levelNumber") <= currentLevel) {
					System.out.println("current lvl: " + currentLevel);
					@SuppressWarnings("unchecked")
					List<String> itemNames = (List<String>) plugin.getConfig()
							.getList("skills." + skillName + ".levels." + levelName + ".itemUnlock");
					for (String itemName : itemNames) {
						unlockedItemNames.add(itemName);
					}
				} else {
					break;
				}
			}
		}

		for (Item item : items) {
			try {
				if (unlockedItemNames.contains(item.getName())
						&& item.getLocationName().toLowerCase().equals(location.toLowerCase())) {
					inv = AddItemToInventory.addItem(inv, item, plugin, true);
				}
			} catch (NullPointerException e) {
				System.out.println("Doesnt have a location name");
			}
		}
		for (Item item : items) {
			try {
				if (!(unlockedItemNames.contains(item.getName()))
						&& item.getLocationName().toLowerCase().equals(location.toLowerCase())) {
					inv = AddItemToInventory.addItem(inv, item, plugin, false);
				}
			} catch (NullPointerException e) {
				System.out.println("Doesnt have a location name");
			}
		}
		player.openInventory(inv);
	}
}
