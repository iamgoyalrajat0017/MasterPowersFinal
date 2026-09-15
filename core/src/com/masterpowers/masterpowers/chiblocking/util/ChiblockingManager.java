package com.masterpowers.masterpowers.chiblocking.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.chiblocking.Smokescreen;

public class ChiblockingManager implements Runnable {
	public MasterPowers plugin;

	public ChiblockingManager(final MasterPowers plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			Smokescreen.removeFromHashMap(player);
		}
	}

}
