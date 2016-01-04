package com.gigabytedx.rpgleveling.modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.modifiers.modifier.DamageOverTime;
import com.gigabytedx.rpgleveling.modifiers.modifier.Slowness;

public class GetBuffs {
	private List<Modifier> buffs;
	private List<Modifier> debuffs;
	Main main;

	public GetBuffs(Main main) {
		super();
		buffs = new ArrayList<>();
		debuffs = new ArrayList<>();
		this.main = main;
		getBuffsFromConfig(main);
		getDebuffsFromConfig(main);
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
			switch (buffConfSection.getString("Type")) {
			case "damageOverTime":
				buff = new DamageOverTime(buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			case "slowness":
				buff = new Slowness(buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
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
				buff = new Slowness(buffName, debuffConfSection.getDouble("Rate"), debuffConfSection.getLong("Duration"),
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
