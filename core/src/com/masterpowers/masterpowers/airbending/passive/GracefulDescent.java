package com.masterpowers.masterpowers.airbending.passive;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.AirAbility;
import com.masterpowers.masterpowers.ability.PassiveAbility;

public class GracefulDescent extends AirAbility implements PassiveAbility {

	public GracefulDescent(final Player player) {
		super(player);
	}

	@Override
	public void progress() {

	}

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
		return "GracefulDescent";
	}

	@Override
	public Location getLocation() {
		return null;
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
