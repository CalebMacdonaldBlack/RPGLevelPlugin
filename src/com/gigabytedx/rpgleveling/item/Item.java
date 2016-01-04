package com.gigabytedx.rpgleveling.item;

import java.util.List;

import org.bukkit.Material;

import com.gigabytedx.rpgleveling.modifiers.Modifier;

public class Item {
	private String name;
	private String lore;
	private double cost;
	private Material type;
	private boolean enchanted;
	private List<Modifier> buffs;
	private List<Modifier> debuffs;
	
	public Item(String name, String lore, double cost, Material type, boolean enchanted, List<Modifier> buffs,
			List<Modifier> debuffs) {
		super();
		this.name = name;
		this.lore = lore;
		this.cost = cost;
		this.type = type;
		this.enchanted = enchanted;
		this.buffs = buffs;
		this.debuffs = debuffs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Material getType() {
		return type;
	}

	public void setType(Material type) {
		this.type = type;
	}

	public boolean isEnchanted() {
		return enchanted;
	}

	public void setEnchanted(boolean enchanted) {
		this.enchanted = enchanted;
	}

	public List<Modifier> getBuffs() {
		return buffs;
	}

	public void setBuffs(List<Modifier> buffs) {
		this.buffs = buffs;
	}

	public List<Modifier> getDebuffs() {
		return debuffs;
	}

	public void setDebuffs(List<Modifier> debuffs) {
		this.debuffs = debuffs;
	}
	
}
