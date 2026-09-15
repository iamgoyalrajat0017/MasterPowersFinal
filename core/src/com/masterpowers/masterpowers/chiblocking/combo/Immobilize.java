package com.masterpowers.masterpowers.chiblocking.combo;

import java.util.ArrayList;

import com.masterpowers.masterpowers.ability.util.ComboUtil;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import com.masterpowers.masterpowers.region.RegionProtection;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.Element;
import com.masterpowers.masterpowers.GeneralMethods;
import com.masterpowers.masterpowers.ability.ChiAbility;
import com.masterpowers.masterpowers.ability.ComboAbility;
import com.masterpowers.masterpowers.ability.CoreAbility;
import com.masterpowers.masterpowers.ability.util.ComboManager.AbilityInformation;
import com.masterpowers.masterpowers.attribute.Attribute;
import com.masterpowers.masterpowers.command.Commands;
import com.masterpowers.masterpowers.util.ClickType;
import com.masterpowers.masterpowers.util.MovementHandler;

public class Immobilize extends ChiAbility implements ComboAbility {

	@Attribute(Attribute.DURATION)
	private long duration;
	@Attribute(Attribute.COOLDOWN)
	private long cooldown;
	private Entity target;

	public Immobilize(final Player player) {
		super(player);

		this.cooldown = getConfig().getLong("Abilities.Chi.Immobilize.Cooldown");
		this.duration = getConfig().getLong("Abilities.Chi.Immobilize.ParalyzeDuration");
		this.target = GeneralMethods.getTargetedEntity(player, 5);
		if (!this.bPlayer.canBendIgnoreBinds(this)) {
			return;
		}
		if (this.target == null) {
			this.remove();
			return;
		} else {
			if (RegionProtection.isRegionProtected(this, this.target.getLocation()) ||
					((this.target instanceof Player) && Commands.invincible.contains(((Player) this.target).getName())) ||
					!this.bPlayer.canBeChiblocked()) {
				return;
			}
			paralyze(this.target, this.duration);
			this.bPlayer.addCooldown(this);
		}
	}

	/**
	 * Paralyzes the target for the given duration. The player will be unable to
	 * move or interact for the duration.
	 *
	 * @param target The Entity to be paralyzed
	 * @param duration The time in milliseconds the target will be paralyzed
	 */
	private static void paralyze(final Entity target, final Long duration) {
		final MovementHandler mh = new MovementHandler((LivingEntity) target, CoreAbility.getAbility(Immobilize.class));
		mh.stopWithDuration(duration / 1000 * 20, Element.CHI.getColor() + "* Immobilized *");
	}

	@Override
	public String getName() {
		return "Immobilize";
	}

	@Override
	public void progress() {}

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

	@Override
	public Location getLocation() {
		return this.target != null ? this.target.getLocation() : null;
	}

	@Override
	public Object createNewComboInstance(final Player player) {
		return new Immobilize(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		return ComboUtil.generateCombinationFromList(this, ConfigManager.defaultConfig.get().getStringList("Abilities.Chi.Immobilize.Combination"));
	}

	public long getDuration() {
		return this.duration;
	}

	public void setDuration(final long duration) {
		this.duration = duration;
	}

	public Entity getTarget() {
		return this.target;
	}

	public void setTarget(final Entity target) {
		this.target = target;
	}

	public void setCooldown(final long cooldown) {
		this.cooldown = cooldown;
	}
}
