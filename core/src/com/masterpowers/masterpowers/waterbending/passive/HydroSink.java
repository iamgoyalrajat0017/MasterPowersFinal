package com.masterpowers.masterpowers.waterbending.passive;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.ElementalAbility;
import com.masterpowers.masterpowers.ability.PassiveAbility;
import com.masterpowers.masterpowers.ability.WaterAbility;
import com.masterpowers.masterpowers.command.Commands;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import com.masterpowers.masterpowers.util.TempBlock;

public class HydroSink extends WaterAbility implements PassiveAbility {
	public HydroSink(final Player player) {
		super(player);
	}

	public static boolean applyNoFall(final Player player) {
		if (Commands.isToggledForAll && ConfigManager.defaultConfig.get().getBoolean("Properties.TogglePassivesWithAllBending")) {
			return false;
		}

		final Block block = player.getLocation().getBlock();
		final Block fallBlock = block.getRelative(BlockFace.DOWN);
		if (TempBlock.isTempBlock(fallBlock) && (fallBlock.getType().equals(Material.ICE))) {
			return true;
		} else if (TempBlock.isTempBlock(block) && (block.getType().equals(Material.SNOW))) {
			return true;
		} else if (WaterAbility.isWaterbendable(player, null, block) && !ElementalAbility.isPlant(block)) {
			return true;
		} else if (ElementalAbility.isAir(fallBlock.getType())) {
			return true;
		} else if ((WaterAbility.isWaterbendable(player, null, fallBlock) && !ElementalAbility.isPlant(fallBlock)) || fallBlock.getType() == Material.SNOW_BLOCK) {
			return true;
		}

		return false;
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
		return "HydroSink";
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
