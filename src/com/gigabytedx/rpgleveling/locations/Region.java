package com.gigabytedx.rpgleveling.locations;

import java.util.ArrayList;
import java.util.List;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.Mobs.MobData;

public class Region {
	String name;
	Main plugin;
	List<MobData> spawnableMobs = new ArrayList<>();

	public Region(String name, List<MobData> spawnableMobs, Main plugin) {
		super();
		this.name = name;
		this.plugin = plugin;
		this.spawnableMobs = spawnableMobs;
	}

	public String getName() {
		return name;
	}

	public List<MobData> getSpawnableMobs() {
		return spawnableMobs;
	}

}
