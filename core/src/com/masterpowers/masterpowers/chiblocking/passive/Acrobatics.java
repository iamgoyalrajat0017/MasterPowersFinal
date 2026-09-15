package com.masterpowers.masterpowers.chiblocking.passive;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.ChiAbility;
import com.masterpowers.masterpowers.ability.PassiveAbility;
import com.masterpowers.masterpowers.configuration.ConfigManager;

public class Acrobatics extends ChiAbility implements PassiveAbility {
	public Acrobatics(final Player player) {
		super(player);
	}

	public static double getFallReductionFactor() {
		return ConfigManager.getConfig().getDouble("Abilities.Chi.Passive.Acrobatics.FallReductionFactor");
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
		return "Acrobatics";
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
