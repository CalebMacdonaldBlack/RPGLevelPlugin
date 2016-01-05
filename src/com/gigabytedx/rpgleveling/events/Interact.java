package com.gigabytedx.rpgleveling.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.item.Item;
import com.gigabytedx.rpgleveling.modifiers.Modifier;
import com.gigabytedx.rpgleveling.shop.Shop;

public class Interact implements Listener {

	Main plugin;

	public Interact(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {

			Player damager = (Player) event.getDamager();
			try {
				Item itemUsed = Main.itemMap.get(damager.getItemInHand().getItemMeta().getDisplayName());
				for (Modifier buff : itemUsed.getBuffs()) {
					System.out.println("appbuff: " + buff.toString());
					buff.applyBuff(damager, event.getEntity());
				}
				applyDamage(damager, (LivingEntity) event.getEntity(), itemUsed, itemUsed.getDamage());
			} catch (NullPointerException e) {
				damager.sendMessage("not using a custom item");
			}

		} else if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if (arrow.getShooter() instanceof Player) {
				Player damager = (Player) arrow.getShooter();
				if (damager.getItemInHand().getType().equals(Material.BOW)) {
					System.out.println("Confim?");
					try {
						Item itemUsed = Main.itemMap.get(damager.getItemInHand().getItemMeta().getDisplayName());
						for (Modifier buff : itemUsed.getBuffs()) {
							buff.applyBuff(damager, (LivingEntity) event.getEntity());
						}
						if (arrow.isCritical()) {
							applyDamage(damager, (LivingEntity) event.getEntity(), itemUsed,
									Math.floor(itemUsed.getDamage() * 1.5));
							damager.sendMessage(ChatColor.GOLD + "1.5x Damage for critical hit!");
						} else {
							applyDamage(damager, (LivingEntity) event.getEntity(), itemUsed, itemUsed.getDamage());
						}
					} catch (NullPointerException e) {
						damager.sendMessage("not using a custom item");
					}
				}
			}
		}

	}

	private void applyDamage(Player damager, LivingEntity entity, Item itemUsed, double damage) {
		double armorProtectionValue = 0;

		for (ItemStack itemStack : entity.getEquipment().getArmorContents()) {
			try {
				armorProtectionValue += Main.itemMap.get(itemStack.getItemMeta().getDisplayName()).getProtection();
			} catch (NullPointerException e) {

			}
		}
		armorProtectionValue = armorProtectionValue * 4;
		double dmgToSubtract = armorProtectionValue/100 * damage;
		damage -=dmgToSubtract;
		entity.damage(damage);

	}

	@EventHandler
	public void onHoldItemInHand(PlayerItemHeldEvent event) {

		// remove all potion effects from player
		for (PotionEffect effect : event.getPlayer().getActivePotionEffects()) {
			event.getPlayer().removePotionEffect(effect.getType());
		}
		try {
			Item itemUsed = Main.itemMap
					.get(event.getPlayer().getInventory().getItem(event.getNewSlot()).getItemMeta().getDisplayName());
			System.out.println("Well this ran fine..." + itemUsed.getDebuffs().toString());
			for (Modifier buff : itemUsed.getBuffs()) {
				if (buff.getTrigger().equals("hold"))
					buff.applyBuff(event.getPlayer(), null);
			}
			for (Modifier buff : itemUsed.getDebuffs()) {
				if (buff.getTrigger().equals("hold"))
					buff.applyBuff(event.getPlayer(), null);
			}
		} catch (NullPointerException e) {
			// event.getPlayer().sendMessage("not using a custom item");
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent event) {
		if (event.getRightClicked() instanceof Player) {
			Player getPersonClicked = (Player) event.getRightClicked();
			if (plugin.locations.getLocationNames().contains(getPersonClicked.getName().toLowerCase())) {
				Shop.openShop(plugin, event.getPlayer(), getPersonClicked.getName());
			}

		}
	}
}
