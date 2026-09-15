package com.masterpowers.masterpowers.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.masterpowers.masterpowers.ability.Ability;

public class AbilityEndEvent extends Event {
	private static final HandlerList HANDLERS = new HandlerList();

	Ability ability;

	public AbilityEndEvent(final Ability ability) {
		this.ability = ability;
	}

	public Ability getAbility() {
		return this.ability;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
