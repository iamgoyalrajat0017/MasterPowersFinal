package com.masterpowers.masterpowers.firebending.util;

import com.masterpowers.masterpowers.MasterPowers;

public class FirebendingManager implements Runnable {

	public MasterPowers plugin;

	public FirebendingManager(final MasterPowers plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		FireDamageTimer.handleFlames();
	}
}
