package com.gigabytedx.rpgleveling.modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.modifiers.buffs.DamageOverTime;

public class GetBuffs {
	private List<Buff> buffs;
	Main main;

	public GetBuffs(Main main) {
		super();
		buffs = new ArrayList<>();
		this.main = main;
		getBuffsFromConfig(main);
	}

	private void getBuffsFromConfig(Main main) {
		// this gets all the Buff configuration section key names and adds them
		// to a list
		Set<String> buffConfigSectionNames = main.getConfig().getConfigurationSection("Buffs").getKeys(false);

		// iterate through skill names
		for (String buffName : buffConfigSectionNames) {
			ConfigurationSection buffConfSection = main.getConfig().getConfigurationSection("Buffs")
					.getConfigurationSection(buffName);
			Buff buff;
			switch (buffConfSection.getString("Type")) {
			case "damageOverTime":
				buff = new DamageOverTime(buffName, buffConfSection.getDouble("Rate"), buffConfSection.getLong("Duration"),
						buffConfSection.getLong("Interval"), buffConfSection.getDouble("Intensity"),
						buffConfSection.getString("type"), buffConfSection.getString("Target"),
						buffConfSection.getString("Trigger"));
						buffs.add(buff);
						Main.buffsMap.put(buffName, buff);
				break;
			}
		}

	}
}
