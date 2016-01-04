package com.gigabytedx.rpgleveling.modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.modifiers.modifier.Blind;
import com.gigabytedx.rpgleveling.modifiers.modifier.Chameleon;
import com.gigabytedx.rpgleveling.modifiers.modifier.DamageOverTime;
import com.gigabytedx.rpgleveling.modifiers.modifier.Nausea;
import com.gigabytedx.rpgleveling.modifiers.modifier.Slowness;

public class GetBuffs {
	private List<Modifier> buffs;
	private List<Modifier> debuffs;
	Main plugin;

	public GetBuffs(Main plugin) {
		super();
		buffs = new ArrayList<>();
		debuffs = new ArrayList<>();
		this.plugin = plugin;
		getBuffsFromConfig(plugin);
		getDebuffsFromConfig(plugin);
	}

	private void getBuffsFromConfig(Main main) {
		// this gets all the Buff configuration section key names and adds them
		// to a list
		Set<String> buffConfigSectionNames = main.getConfig().getConfigurationSection("Buffs").getKeys(false);

		// iterate through skill names
		for (String buffName : buffConfigSectionNames) { 
			ConfigurationSection buffConfSection = main.getConfig().getConfigurationSection("Buffs")
					.getConfigurationSection(buffName);
			Modifier buff;
			System.out.println("Type: " + buffConfSection.getString("Type"));
			switch (buffConfSection.getString("Type")) {
			case "damageOverTime":
				buff = new DamageOverTime(plugin, buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			case "slowness":
				buff = new Slowness(plugin, buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			case "nausea":
				buff = new Nausea(plugin, buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			case "blind":
				buff = new Blind(plugin, buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			case "chameleon":
				buff = new Chameleon(plugin, buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			}
		}

	}
	
	private void getDebuffsFromConfig(Main main) {
		// this gets all the Buff configuration section key names and adds them
		// to a list
		Set<String> debuffConfigSectionNames = main.getConfig().getConfigurationSection("Debuffs").getKeys(false);

		// iterate through skill names
		for (String buffName : debuffConfigSectionNames) { 
			ConfigurationSection debuffConfSection = main.getConfig().getConfigurationSection("Debuffs")
					.getConfigurationSection(buffName);
			Modifier buff;
			switch (debuffConfSection.getString("Type")) {
			case "slowness":
				buff = new Slowness(plugin, buffName, debuffConfSection.getDouble("Rate"), debuffConfSection.getLong("Duration"),
						debuffConfSection.getLong("Interval"), debuffConfSection.getDouble("Intensity"),
						debuffConfSection.getString("type"), debuffConfSection.getString("Target"),
						debuffConfSection.getString("Trigger"));
						debuffs.add(buff);
						Main.debuffsMap.put(buffName, buff);
				break;
			}
		}

	}
}
