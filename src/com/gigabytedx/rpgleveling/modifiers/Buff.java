package com.gigabytedx.rpgleveling.modifiers;

public class Buff {
	double rate;
	double duration;
	Long interval;
	double intensity;
	String type;
	String target;
	String trigger;
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
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

	public Buff(double rate, double duration, Long interval, double intensity, String type, String target,
			String trigger) {
		super();
		this.rate = rate;
		this.duration = duration;
		this.interval = interval;
		this.intensity = intensity;
		this.type = type;
		this.target = target;
		this.trigger = trigger;
	}
	
	
}
