package com.masterpowers.masterpowers.airbending.util;

import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.airbending.AirBlast;

public class AirbendingManager implements Runnable {

	public MasterPowers plugin;

	public AirbendingManager(final MasterPowers plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		AirBlast.progressOrigins();
	}

}
