package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.item.AddItemToInventory;
import com.gigabytedx.rpgleveling.item.Item;

public class OpenShop implements CommandExecutor {
	Main plugin;

	public OpenShop(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			List<Item> items = plugin.items.getItems();
			player.sendMessage("size of items is: " + items.size());
			Inventory inv = Bukkit.createInventory(player, 27, ChatColor.DARK_BLUE + "Shop");

			List<String> unlockedItemNames = new ArrayList<>();
			for (String skillName : plugin.getConfig().getConfigurationSection("skills").getKeys(false)) {
				int currentLevel = plugin.playerExperience.getInt(player.getUniqueId().toString() + "." + skillName)
						/ plugin.playerExperience.getInt("Level Upgrade at");
				for (String levelName : plugin.getConfig().getConfigurationSection("skills." + skillName + ".levels")
						.getKeys(false)) {
					
					if (plugin.getConfig()
							.getInt("skills." + skillName + ".levels." + levelName + ".levelNumber") <= currentLevel) {
						System.out.println("current lvl: " + currentLevel);
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
				if(unlockedItemNames.contains(item.getName())){
					inv = AddItemToInventory.addItem(inv, item, plugin);
				}
			}
			player.openInventory(inv);
		}
		return false;
	}
}