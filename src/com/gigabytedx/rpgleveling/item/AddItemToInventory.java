package com.gigabytedx.rpgleveling.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.modifiers.Modifier;

public class AddItemToInventory {

	public static Inventory addItem(Inventory inv, Item item, Main plugin) {
		ItemStack itemStack = new ItemStack(item.getType());
		if (item.isEnchanted()) {
		}
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(ChatColor.BLUE + item.getName());
		String loreText = item.getLore();
		List<String> lore = new ArrayList<>();

		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.DARK_PURPLE + item.getCost());

		String[] words = loreText.split("\\s+");

		int count = 0;
		String sentence = "";
		for (String word : words) {
			sentence = sentence.concat(word + " ");
			if (++count > plugin.loreLength) {
				lore.add(sentence);
				sentence = "";
				count = 0;
			}
		}
		lore.add(sentence);
		lore.add(" ");
		if (item.getBuffs().size() > 0) {
			lore.add(ChatColor.GOLD + "Buffs");

			for (Modifier buff : item.getBuffs()) {
				try {
					lore.add(ChatColor.DARK_PURPLE + " - " + buff.getName());
				} catch (NullPointerException e) {
					try {
						throw new Exception("Buff for item " + item.getName()
								+ " was not found. Please ensure buffs listed in item is spelt correctly (case-sensitive)");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			lore.add(" ");
		}
		if (item.getDebuffs().size() > 0) {
			lore.add(ChatColor.RED + "Debuffs");
			for (Modifier buff : item.getDebuffs()) {
				lore.add(ChatColor.DARK_PURPLE + " - " + buff.getName());
			}
		}
		meta.setLore(lore);
		itemStack.setItemMeta(meta);
		inv.addItem(itemStack);
		return inv;
	}
}
