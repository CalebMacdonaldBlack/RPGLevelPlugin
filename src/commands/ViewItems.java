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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.item.Item;
import com.gigabytedx.rpgleveling.modifiers.Buff;

public class ViewItems implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			List<Item> items = Main.items.getItems();
			player.sendMessage("size of items is: " + items.size());
			Inventory inv = Bukkit.createInventory(player, 9);
			for(Item item: items){
				ItemStack itemStack = new ItemStack(item.getType());
				if(item.isEnchanted()){
				}
				ItemMeta meta = itemStack.getItemMeta();
				
				meta.setDisplayName(ChatColor.BLUE + item.getName());
				String loreText = item.getLore();
				List<String> lore = new ArrayList<>();
				
				lore.add(ChatColor.GOLD + "Cost: " + ChatColor.DARK_PURPLE + item.getCost());
				
				String[] words = loreText.split("\\s+");
//				for (int i = 0; i < words.length; i++) {
//				    // You may want to check for a non-word character before blindly
//				    // performing a replacement
//				    // It may also be necessary to adjust the character class
//				    words[i] = words[i].replaceAll("[^\\w]", "");
//				}
				
				int count = 0;
				String sentence = "";
				for (String word: words){
					sentence = sentence.concat(word + " ");
					if(++count > Main.loreLength){
						lore.add(sentence);
						sentence = "";
						count = 0;
					}
				}
				lore.add(sentence);
				lore.add(" ");
				lore.add(ChatColor.GOLD + "Buffs");
				for(Buff buff: item.getBuffs()){
					lore.add(ChatColor.DARK_PURPLE + " - " +  buff.getName());
				}
				meta.setLore(lore);
				itemStack.setItemMeta(meta);
				inv.addItem(itemStack);
			}
			player.openInventory(inv);
		}
		return false;
	}
}
