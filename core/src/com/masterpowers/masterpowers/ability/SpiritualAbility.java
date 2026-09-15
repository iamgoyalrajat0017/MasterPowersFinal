package com.masterpowers.masterpowers.ability;

import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.Element;

public abstract class SpiritualAbility extends AirAbility implements SubAbility {

	public SpiritualAbility(final Player player) {
		super(player);
	}

	@Override
	public Class<? extends Ability> getParentAbility() {
		return AirAbility.class;
	}

	@Override
	public Element getElement() {
		return Element.SPIRITUAL;
	}

}
