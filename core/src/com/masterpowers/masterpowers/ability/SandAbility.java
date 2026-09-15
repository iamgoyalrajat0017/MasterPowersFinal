package com.masterpowers.masterpowers.ability;

import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.Element;

public abstract class SandAbility extends EarthAbility implements SubAbility {

	public SandAbility(final Player player) {
		super(player);
	}

	@Override
	public Class<? extends Ability> getParentAbility() {
		return EarthAbility.class;
	}

	@Override
	public Element getElement() {
		return Element.SAND;
	}

}
