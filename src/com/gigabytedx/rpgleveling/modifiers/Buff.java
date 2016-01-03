package com.gigabytedx.rpgleveling.modifiers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Buff {
	double rate;
	Long duration;
	Long interval;
	double intensity;
	String type;
	String target;
	String trigger;
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	List<Entity> currentBuffs = new ArrayList<>();
	
	public double getRate() {
		return rate;
	}

	public List<Entity> getCurrentBuffs() {
		return currentBuffs;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public double getIntensity() {
		return intensity;
	}

	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public Buff(String name, double rate, Long duration, Long interval, double intensity, String type, String target,
			String trigger) {
		super();
		this.name = name;
		this.rate = rate;
		this.duration = duration;
		this.interval = interval;
		this.intensity = intensity;
		this.type = type;
		this.target = target;
		this.trigger = trigger;
	}

	public void applyBuff(Player damager, Entity entity) {
	}
	
	
}
