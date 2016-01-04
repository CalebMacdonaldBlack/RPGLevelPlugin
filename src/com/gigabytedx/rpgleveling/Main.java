package com.gigabytedx.rpgleveling;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gigabytedx.rpgleveling.events.EnitityDeath;
import com.gigabytedx.rpgleveling.events.Interact;
import com.gigabytedx.rpgleveling.events.Join;
import com.gigabytedx.rpgleveling.item.GetItems;
import com.gigabytedx.rpgleveling.item.Item;
import com.gigabytedx.rpgleveling.modifiers.Modifier;
import com.gigabytedx.rpgleveling.modifiers.GetBuffs;
import com.gigabytedx.rpgleveling.skills.GetSkills;

import commands.GetXP;
import commands.PrintSkills;
import commands.ViewItems;

public class Main extends JavaPlugin {
	public static Main pluginInstance;
	public static GetSkills skills;
	public static GetBuffs buffs;
	public static GetItems items;
	public static Map<String, Item> itemMap = new HashMap<>();
	public static Map<String, Modifier> buffsMap= new HashMap<>();
	public static Map<String, Modifier > debuffsMap= new HashMap<>();
	public static int loreLength = 6;
	public File playerLevelData = new File(getDataFolder()+"/Data/playerData.yml");
	public FileConfiguration playerExperience = YamlConfiguration.loadConfiguration(playerLevelData);
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		registerCommands();
		registerEvents();
		registerConfig();
		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");
		pluginInstance = this;
		loadFiles();
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");
	}

	private void registerEvents() {
		 PluginManager pm = getServer().getPluginManager();
		 pm.registerEvents(new Interact(), this);
		 pm.registerEvents(new Join(this), this);
		 pm.registerEvents(new EnitityDeath(this), this);
	}

	private void registerCommands() {
		 getCommand("printskills").setExecutor(new PrintSkills());
		 getCommand("viewitems").setExecutor(new ViewItems());
		 getCommand("getxp").setExecutor(new GetXP(this));
	}

	private void registerConfig() {
		saveDefaultConfig();
		
		//get skills from config
		skills = new GetSkills(this);
		buffs = new GetBuffs(this);
		items = new GetItems(this);
	}
	
	public void savePlayerExperienceConfig(){
		try{
			playerExperience.save(playerLevelData);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadFiles(){
		if(playerLevelData.exists()){
			try{
			playerExperience.load(playerLevelData);
			}catch(IOException | InvalidConfigurationException e){
				e.printStackTrace();
			}
		}else{
			try {
				playerExperience.set("XP from Generic kill", 50);
				playerExperience.save(playerLevelData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
