package com.masterpowers.masterpowers.chiblocking.passive;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.ChiAbility;
import com.masterpowers.masterpowers.ability.PassiveAbility;
import com.masterpowers.masterpowers.configuration.ConfigManager;

public class ChiSaturation extends ChiAbility implements PassiveAbility {
	public ChiSaturation(final Player player) {
		super(player);
	}

	public static double getExhaustionFactor() {
		return ConfigManager.getConfig().getDouble("Abilities.Chi.Passive.ChiSaturation.ExhaustionFactor");
	}

	@Override
	public void progress() {}

	@Override
	public boolean isSneakAbility() {
		return false;
	}

	@Override
	public boolean isHarmlessAbility() {
		return true;
	}

	@Override
	public long getCooldown() {
		return 0;
	}

	@Override
	public String getName() {
		return "ChiSaturation";
	}

	@Override
	public Location getLocation() {
		return this.player.getLocation();
	}

	@Override
	public boolean isInstantiable() {
		return false;
	}

	@Override
	public boolean isProgressable() {
		return false;
	}
}
