package com.gigabytedx.rpgleveling;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gigabytedx.rpgleveling.interact.Interact;
import com.gigabytedx.rpgleveling.item.GetItems;
import com.gigabytedx.rpgleveling.item.Item;
import com.gigabytedx.rpgleveling.modifiers.Buff;
import com.gigabytedx.rpgleveling.modifiers.Debuff;
import com.gigabytedx.rpgleveling.modifiers.GetBuffs;
import com.gigabytedx.rpgleveling.skills.GetSkills;

import commands.PrintSkills;
import commands.ViewItems;

public class Main extends JavaPlugin {
	public static Main pluginInstance;
	public static GetSkills skills;
	public static GetBuffs buffs;
	public static GetItems items;
	public static Map<String, Item> itemMap = new HashMap<>();
	public static Map<String, Buff> buffsMap= new HashMap<>();
	public static Map<String, Debuff > debuffsMap= new HashMap<>();
	public static int loreLength = 6;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		registerCommands();
		registerEvents();
		registerConfig();
		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");
		pluginInstance = this;
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");
	}

	private void registerEvents() {
		 PluginManager pm = getServer().getPluginManager();
		 pm.registerEvents(new Interact(), this);
	}

	private void registerCommands() {
		 getCommand("printskills").setExecutor(new PrintSkills());
		 getCommand("viewitems").setExecutor(new ViewItems());
	}

	private void registerConfig() {
		saveDefaultConfig();
		
		//get skills from config
		skills = new GetSkills(this);
		buffs = new GetBuffs(this);
		items = new GetItems(this);
	}
}
