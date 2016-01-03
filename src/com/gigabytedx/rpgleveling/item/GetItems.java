package com.gigabytedx.rpgleveling.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.modifiers.Buff;
import com.gigabytedx.rpgleveling.modifiers.Debuff;

public class GetItems {
	private List<Item> items;
	Main main;

	public GetItems(Main main) {
		this.main = main;
		items = new ArrayList<>();
		getItemsFromConfig(main);
	}

	@SuppressWarnings("unchecked")
	private void getItemsFromConfig(Main main) {
		// this gets all the Item configuration section key names and adds them
		// to a list
		Set<String> itemConfigSectionNames = main.getConfig().getConfigurationSection("Items").getKeys(false);

		// iterate through skill names
		for (String itemName : itemConfigSectionNames) {
			ConfigurationSection itemConfSection = main.getConfig().getConfigurationSection("Items").getConfigurationSection(itemName);
			String lore = itemConfSection.getString("Lore");
			Material type = Material.getMaterial(itemConfSection.getString("Type"));
			double cost = itemConfSection.getDouble("Cost");
			boolean enchanted = itemConfSection.getBoolean("Enchanted");
			
			List<String> buffNames =  (List<String>) itemConfSection.getList("Buffs");
			List<Buff> buffs = new ArrayList<>();
			for(String buffName : buffNames){
				try{
					buffs.add(Main.buffs.get(buffName));
				}catch(NullPointerException e){
					//e.printStackTrace();
				}
			}
			List<String> debuffNames = (List<String>) itemConfSection.getList("Debuffs");
			List<Debuff> debuffs = new ArrayList<>();
			for(String debuffName : debuffNames){
				try{
					buffs.add(Main.buffs.get(debuffName));
				}catch(NullPointerException e){
					//e.printStackTrace();
				}
			}
			// add new skill to list
			items.add(new Item(itemName, lore, cost, type, enchanted, buffs, debuffs));
		}

	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
