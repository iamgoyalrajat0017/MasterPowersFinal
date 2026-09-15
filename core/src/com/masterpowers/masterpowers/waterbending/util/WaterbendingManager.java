package com.masterpowers.masterpowers.waterbending.util;

import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.waterbending.Torrent;
import com.masterpowers.masterpowers.waterbending.WaterSpoutWave;
import com.masterpowers.masterpowers.waterbending.multiabilities.WaterArms;

public class WaterbendingManager implements Runnable {

	public MasterPowers plugin;

	public WaterbendingManager(final MasterPowers plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		// WaterPassive.handlePassive(); # Fast Swim is now managed in FastSwim.java.
		Torrent.progressAllCleanup();
		WaterArms.progressAllCleanup();
		WaterSpoutWave.progressAllCleanup();
	}

}
