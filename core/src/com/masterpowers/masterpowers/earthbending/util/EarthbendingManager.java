package com.masterpowers.masterpowers.earthbending.util;

import org.bukkit.Bukkit;

import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.earthbending.Shockwave;
import com.masterpowers.masterpowers.earthbending.Tremorsense;
import com.masterpowers.masterpowers.util.RevertChecker;

public class EarthbendingManager implements Runnable {
	public MasterPowers plugin;

	public EarthbendingManager(final MasterPowers plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		RevertChecker.revertEarthBlocks();
		Shockwave.progressAll();
		Tremorsense.manage(Bukkit.getServer());
	}
}
