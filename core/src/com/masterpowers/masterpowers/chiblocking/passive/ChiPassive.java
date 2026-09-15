package com.masterpowers.masterpowers.chiblocking.passive;

import com.masterpowers.masterpowers.ability.StanceAbility;
import com.masterpowers.masterpowers.util.ChatUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.masterpowers.masterpowers.BendingPlayer;
import com.masterpowers.masterpowers.Element;
import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.ability.CoreAbility;
import com.masterpowers.masterpowers.airbending.Suffocate;
import com.masterpowers.masterpowers.chiblocking.AcrobatStance;
import com.masterpowers.masterpowers.chiblocking.QuickStrike;
import com.masterpowers.masterpowers.chiblocking.SwiftKick;
import com.masterpowers.masterpowers.configuration.ConfigManager;

public class ChiPassive {
	public static boolean willChiBlock(final Player attacker, final Player player) {
		final BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (bPlayer == null) {
			return false;
		}

		final StanceAbility stance = bPlayer.getStance();
		final QuickStrike quickStrike = CoreAbility.getAbility(player, QuickStrike.class);
		final SwiftKick swiftKick = CoreAbility.getAbility(player, SwiftKick.class);
		double newChance = getChance();

		if (stance instanceof AcrobatStance) {
			newChance += ((AcrobatStance) stance).getChiBlockBoost();
		}

		if (quickStrike != null) {
			newChance += quickStrike.getBlockChance();
		} else if (swiftKick != null) {
			newChance += swiftKick.getBlockChance();
		}

		if (Math.random() > newChance / 100.0) {
			return false;
		} else if (bPlayer.isChiBlocked()) {
			return false;
		}

		return true;
	}

	public static void blockChi(final Player player) {
		if (Suffocate.isChannelingSphere(player)) {
			Suffocate.remove(player);
		}

		final BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (bPlayer == null) {
			return;
		}

		bPlayer.blockChi();
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 2, 0);

		final long start = System.currentTimeMillis();
		new BukkitRunnable() {
			@Override
			public void run() {
				ChatUtil.sendActionBar(Element.CHI.getColor() + "* Chiblocked *", player);
				if (System.currentTimeMillis() >= start + getDuration()) {
					bPlayer.unblockChi();
					this.cancel();
				}
			}
		}.runTaskTimer(MasterPowers.plugin, 0, 1);
	}

	public static double getChance() {
		return ConfigManager.getConfig().getDouble("Abilities.Chi.Passive.BlockChi.Chance");
	}

	public static int getDuration() {
		return ConfigManager.getConfig().getInt("Abilities.Chi.Passive.BlockChi.Duration");
	}

	public static long getTicks() {
		return (getDuration() / 1000) * 20;
	}
}
