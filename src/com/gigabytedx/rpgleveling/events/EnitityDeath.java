package com.gigabytedx.rpgleveling.events;

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
		if (event.getEntity().getKiller() instanceof Player) {
			Player damager = (Player) event.getEntity().getKiller();
			addXPToPlayer(damager, event.getEntity());
		}

	}

	private void addXPToPlayer(Player damager, Entity entity) {
		System.out.println(damager.getName());
		if (damager.getItemInHand() != null
				&& damager.getItemInHand().getType().toString().toLowerCase().contains("sword")) {
			plugin.playerExperience.set(damager.getUniqueId().toString() + ".swordsmanXP",
					plugin.playerExperience.getInt(damager.getUniqueId().toString() + ".swordsmanXP")
							+ plugin.playerExperience.getInt("XP from Generic kill"));
			plugin.playerExperience.set(damager.getUniqueId().toString() + ".totalXP",
					plugin.playerExperience.getInt(damager.getUniqueId().toString() + ".totalXP")
							+ plugin.playerExperience.getInt("XP from Generic kill"));
			damager.sendMessage(ChatColor.GOLD + "You Gained " + ChatColor.RED
					+ plugin.playerExperience.getInt("XP from Generic kill") + "xp " + ChatColor.GOLD + "for killing a "
					+ entity.getType());
		} else if (damager.getItemInHand() != null
				&& damager.getItemInHand().getType().toString().toLowerCase().contains("bow")) {
			plugin.playerExperience.set(damager.getUniqueId().toString() + ".archerXP",
					plugin.playerExperience.getInt(damager.getUniqueId().toString() + ".archerXP")
							+ plugin.playerExperience.getInt("XP from Generic kill"));
			plugin.playerExperience.set(damager.getUniqueId().toString() + ".totalXP",
					plugin.playerExperience.getInt(damager.getUniqueId().toString() + ".totalXP")
							+ plugin.playerExperience.getInt("XP from Generic kill"));
			damager.sendMessage(ChatColor.GOLD + "You Gained " + ChatColor.RED
					+ plugin.playerExperience.getInt("XP from Generic kill") + "xp " + ChatColor.GOLD + "for killing a "
					+ entity.getType());
		} else if (damager.getItemInHand() == null) {
			// code goes here
		}
		plugin.savePlayerExperienceConfig();
	}
}
