package com.masterpowers.masterpowers.ability;

import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.Element;

public abstract class ChiAbility extends ElementalAbility {

	public ChiAbility(final Player player) {
		super(player);
	}

	@Override
	public boolean isIgniteAbility() {
		return false;
	}

	@Override
	public boolean isExplosiveAbility() {
		return false;
	}

	@Override
	public Element getElement() {
		return Element.CHI;
	}

}
