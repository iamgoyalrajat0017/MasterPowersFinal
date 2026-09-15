package com.masterpowers.masterpowers.earthbending.combo;

import java.util.ArrayList;

import com.masterpowers.masterpowers.ability.util.ComboUtil;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.ability.ComboAbility;
import com.masterpowers.masterpowers.ability.EarthAbility;
import com.masterpowers.masterpowers.ability.util.ComboManager.AbilityInformation;
import com.masterpowers.masterpowers.earthbending.EarthDome;
import com.masterpowers.masterpowers.util.ClickType;

public class EarthDomeSelf extends EarthAbility implements ComboAbility {

	public EarthDomeSelf(final Player player) {
		super(player);
		if (!this.bPlayer.canBendIgnoreBinds(this)) {
			return;
		}
		new EarthDome(player);
	}

	@Override
	public void progress() {}

	@Override
	public boolean isSneakAbility() {
		return false;
	}

	@Override
	public boolean isHarmlessAbility() {
		return false;
	}

	@Override
	public long getCooldown() {
		return 0;
	}

	@Override
	public String getName() {
		return "EarthDome";
	}

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public Object createNewComboInstance(final Player player) {
		return new EarthDomeSelf(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		return ComboUtil.generateCombinationFromList(this, ConfigManager.defaultConfig.get().getStringList("Abilities.Earth.EarthDome.Combination.Self"));
	}
}
