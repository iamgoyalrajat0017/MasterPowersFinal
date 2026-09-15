package com.masterpowers.masterpowers.waterbending.combo;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.masterpowers.masterpowers.ability.util.ComboUtil;
import com.masterpowers.masterpowers.attribute.markers.DayNightFactor;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.ComboAbility;
import com.masterpowers.masterpowers.ability.IceAbility;
import com.masterpowers.masterpowers.ability.util.ComboManager.AbilityInformation;
import com.masterpowers.masterpowers.attribute.Attribute;
import com.masterpowers.masterpowers.util.ClickType;
import com.masterpowers.masterpowers.util.TempBlock;
import com.masterpowers.masterpowers.waterbending.WaterSpoutWave;

public class IceWave extends IceAbility implements ComboAbility {

	private static final Map<Block, TempBlock> FROZEN_BLOCKS = new ConcurrentHashMap<>();

	@Attribute(Attribute.COOLDOWN) @DayNightFactor(invert = true)
	private long cooldown;
	private Location origin;

	public IceWave(final Player player) {
		super(player);

		if (!hasAbility(player, WaterSpoutWave.class)) {
			return;
		}

		if (!this.bPlayer.canBendIgnoreBindsCooldowns(this)) {
			return;
		}

		if (this.bPlayer.isOnCooldown("IceWave") && !this.bPlayer.isAvatarState()) {
			this.remove();
			return;
		}

		this.cooldown = getConfig().getLong("Abilities.Water.IceWave.Cooldown");

		this.start();
	}

	@Override
	public String getName() {
		return "IceWave";
	}

	@Override
	public void progress() {
		if (this.player.isDead() || !this.player.isOnline()) {
			this.remove();
			return;
		}

		if (this.origin == null && WaterSpoutWave.containsType(this.player, WaterSpoutWave.AbilityType.RELEASE)) {
			this.bPlayer.addCooldown("IceWave", this.cooldown);
			this.origin = this.player.getLocation();

			final WaterSpoutWave wave = WaterSpoutWave.getType(this.player, WaterSpoutWave.AbilityType.RELEASE).get(0);
			wave.setIceWave(true);
		} else if (!WaterSpoutWave.containsType(this.player, WaterSpoutWave.AbilityType.RELEASE)) {
			this.remove();
			return;
		}
	}

	public static boolean canThaw(final Block block) {
		return FROZEN_BLOCKS.containsKey(block);
	}

	public static void thaw(final Block block) {
		if (FROZEN_BLOCKS.containsKey(block)) {
			FROZEN_BLOCKS.get(block).revertBlock();
			FROZEN_BLOCKS.remove(block);
		}
	}

	@Override
	public void remove() {
		super.remove();
		this.bPlayer.addCooldown("WaterWave", this.cooldown);
	}

	@Override
	public boolean isSneakAbility() {
		return true;
	}

	@Override
	public boolean isHarmlessAbility() {
		return false;
	}

	@Override
	public long getCooldown() {
		return this.cooldown;
	}

	public void setCooldown(final long cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public Location getLocation() {
		return this.origin;
	}

	public void setLocation(final Location location) {
		this.origin = location;
	}

	@Override
	public Object createNewComboInstance(final Player player) {
		return new IceWave(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		return ComboUtil.generateCombinationFromList(this, ConfigManager.defaultConfig.get().getStringList("Abilities.Water.IceWave.Combination"));
	}
}
