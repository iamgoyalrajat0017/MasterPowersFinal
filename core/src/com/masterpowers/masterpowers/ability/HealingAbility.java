package com.masterpowers.masterpowers.ability;

import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.Element;

public abstract class HealingAbility extends WaterAbility implements SubAbility {

	public HealingAbility(final Player player) {
		super(player);
	}

	@Override
	public Class<? extends Ability> getParentAbility() {
		return WaterAbility.class;
	}

	@Override
	public Element getElement() {
		return Element.HEALING;
	}

}
