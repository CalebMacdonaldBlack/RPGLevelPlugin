package com.gigabytedx.rpgleveling.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffect;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.item.Item;
import com.gigabytedx.rpgleveling.modifiers.Modifier;

public class Interact implements Listener{
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){

			Player damager = (Player) event.getDamager();
			try{
			Item itemUsed = Main.itemMap.get(damager.getItemInHand().getItemMeta().getDisplayName());
			for(Modifier buff: itemUsed.getBuffs()){
				System.out.println("appbuff: " + buff.toString());
				buff.applyBuff(damager, event.getEntity());
			}
			}catch(NullPointerException e){
				damager.sendMessage("not using a custom item");
			}
			
		}else if(event.getDamager() instanceof Arrow){
			Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() instanceof Player){
				Player damager = (Player) arrow.getShooter();
				try{
				Item itemUsed = Main.itemMap.get(damager.getItemInHand().getItemMeta().getDisplayName());
				for(Modifier buff: itemUsed.getBuffs()){
					buff.applyBuff(damager, event.getEntity());
				}
				}catch(NullPointerException e){
					damager.sendMessage("not using a custom item");
				}
				
			}
		}
	}
	
	@EventHandler
	public void onHoldItemInHand(PlayerItemHeldEvent event){
		
		//remove all potion effects from player
		for(PotionEffect effect: event.getPlayer().getActivePotionEffects()){
			event.getPlayer().removePotionEffect(effect.getType());
		}
		try{
		Item itemUsed = Main.itemMap.get(event.getPlayer().getInventory().getItem(event.getNewSlot()).getItemMeta().getDisplayName());
		System.out.println("Well this ran fine..." + itemUsed.getDebuffs().toString());
		for(Modifier buff: itemUsed.getBuffs()){
			if(buff.getTrigger().equals("hold"))
			buff.applyBuff(event.getPlayer(), null);
		}
		for(Modifier buff: itemUsed.getDebuffs()){
			if(buff.getTrigger().equals("hold"))
			buff.applyBuff(event.getPlayer(), null);
		}
		}catch(NullPointerException e){
			//event.getPlayer().sendMessage("not using a custom item");
		}
	}
}
