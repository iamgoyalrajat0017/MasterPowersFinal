package com.masterpowers.masterpowers.ability;

import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.Element;

public abstract class MetalAbility extends EarthAbility implements SubAbility {

	public MetalAbility(final Player player) {
		super(player);
	}

	@Override
	public Class<? extends Ability> getParentAbility() {
		return EarthAbility.class;
	}

	@Override
	public Element getElement() {
		return Element.METAL;
	}

}
