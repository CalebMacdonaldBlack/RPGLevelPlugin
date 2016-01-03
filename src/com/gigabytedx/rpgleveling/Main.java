package com.gigabytedx.rpgleveling;

import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.gigabytedx.rpgleveling.item.GetItems;
import com.gigabytedx.rpgleveling.modifiers.Buff;
import com.gigabytedx.rpgleveling.modifiers.Debuff;
import com.gigabytedx.rpgleveling.skills.GetSkills;

import commands.PrintSkills;
import commands.ViewItems;

public class Main extends JavaPlugin {
	public static Main pluginInstance;
	public static GetSkills skills;
	public static GetItems items;
	public static Map<String, Buff> buffs;
	public static Map<String, Debuff > debuffs;
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
		// PluginManager pm = getServer().getPluginManager();
		// pm.registerEvents(new PrepareItemCraft(), this);
	}

	private void registerCommands() {
		 getCommand("printskills").setExecutor(new PrintSkills());
		 getCommand("viewitems").setExecutor(new ViewItems());
	}

	private void registerConfig() {
		saveDefaultConfig();
		
		//get skills from config
		skills = new GetSkills(this);
		items = new GetItems(this);
	}
}
