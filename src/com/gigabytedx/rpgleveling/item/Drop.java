package com.gigabytedx.rpgleveling.item;

public class Drop {
	private String type;
	private int qty;
	private int spawnRate;
	private String name;

	public Drop(String type, int qty, int spawnRate, String name) {
		this.type = type;
		this.qty = qty;
		this.spawnRate = spawnRate;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getQty() {
		return qty;
	}

	public int getSpawnRate() {
		return spawnRate;
	}

}
