package com.gigabytedx.rpgleveling.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gigabytedx.rpgleveling.Main;

public class Join implements Listener{
	
	private Main plugin;
	
	public Join(Main plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = (Player) event.getPlayer();
		
		//initialize player with 0 xp if they aren't already saved in the xp file
		if(plugin.playerExperience.getString(player.getUniqueId().toString()) == null){
			plugin.playerExperience.set(player.getUniqueId().toString() + ".totalXP", 0);
			for(String skillName: plugin.getConfig().getConfigurationSection("skills").getKeys(false)){
				plugin.playerExperience.set(player.getUniqueId().toString() + "." + skillName, 0);
			}
			plugin.savePlayerExperienceConfig();
		}
		
	}
}
