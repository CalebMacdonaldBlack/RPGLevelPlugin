package com.gigabytedx.rpgleveling.events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.gigabytedx.rpgleveling.Main;

public class EnitityDeath implements Listener {

	private Main plugin;

	public EnitityDeath(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		System.out.println("EVENT FIRED");
		if (event.getEntity().getKiller() instanceof Player) {
			System.out.println("YEP PLAYER");
			Player damager = (Player) event.getEntity().getKiller();
			addXPToPlayer(damager, event.getEntity());
		}

	}

	private void addXPToPlayer(Player damager, Entity entity) {
		for (String skillName : plugin.getConfig().getConfigurationSection("skills").getKeys(false)) {
			@SuppressWarnings("unchecked")
			List<String> itemsForXPGain = (List<String>) plugin.getConfig()
					.getList("skills." + skillName + ".experienceGainedThough");
			for (String itemForExperienceGain : itemsForXPGain) {
				if (damager.getItemInHand() != null
						&& damager.getItemInHand().getType().toString().toLowerCase().contains(itemForExperienceGain.toLowerCase())) {
					plugin.playerExperience.set(damager.getUniqueId().toString() + "." + skillName,
							plugin.playerExperience.getInt(damager.getUniqueId().toString() + "." + skillName)
									+ plugin.playerExperience.getInt("XP from Generic kill"));
					plugin.playerExperience.set(damager.getUniqueId().toString() + ".totalXP",
							plugin.playerExperience.getInt(damager.getUniqueId().toString() + ".totalXP")
									+ plugin.playerExperience.getInt("XP from Generic kill"));
					damager.sendMessage(ChatColor.GOLD + "You Gained " + ChatColor.RED
							+ plugin.playerExperience.getInt("XP from Generic kill") + "xp " + ChatColor.GOLD
							+ "for killing a " + entity.getType());

				}
			}

		}
		plugin.savePlayerExperienceConfig();
	}
}
