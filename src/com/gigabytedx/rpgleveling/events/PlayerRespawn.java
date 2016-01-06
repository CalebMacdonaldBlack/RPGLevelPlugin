package com.gigabytedx.rpgleveling.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.modifiers.GetBuffs;

public class PlayerRespawn implements Listener {

	private Main plugin;

	public PlayerRespawn(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				GetBuffs.applyUnlockedModifiers(player, plugin);

			}
		}, 20);

	}
}
