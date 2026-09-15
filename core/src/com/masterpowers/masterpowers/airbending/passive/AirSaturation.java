package com.masterpowers.masterpowers.airbending.passive;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.AirAbility;
import com.masterpowers.masterpowers.ability.PassiveAbility;
import com.masterpowers.masterpowers.configuration.ConfigManager;

public class AirSaturation extends AirAbility implements PassiveAbility {
	public AirSaturation(final Player player) {
		super(player);
	}

	public static double getExhaustionFactor() {
		return ConfigManager.getConfig().getDouble("Abilities.Air.Passive.Factor");
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
		return "AirSaturation";
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
