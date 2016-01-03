package com.gigabytedx.rpgleveling.modifiers.buffs;

import com.gigabytedx.rpgleveling.modifiers.Buff;

public class DamageOverTime extends Buff{

	public DamageOverTime(double rate, double duration, Long interval, double intensity, String type, String target,
			String trigger) {
		super(rate, duration, interval, intensity, type, target, trigger);
	}
	
}
