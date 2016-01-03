package com.gigabytedx.rpgleveling;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main pluginInstance;

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
		// getCommand("getlandblock").setExecutor(new GetLandBlock());
	}

	private void registerConfig() {
		saveDefaultConfig();
	}
}
