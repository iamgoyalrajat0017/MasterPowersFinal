package com.masterpowers.masterpowers.ability;

import java.util.ArrayList;

import com.masterpowers.masterpowers.ability.util.MultiAbilityManager.MultiAbilityInfoSub;

public interface MultiAbility {

	/**
	 * Returns the sub abilities of a MultiAbility. For example:
	 * <p>
	 * {@code new
	 * MultiAbilitySub("SubAbility", Element.LIGHTNING);}
	 *
	 * @return a list of sub MultiAbilities
	 */
	public abstract ArrayList<MultiAbilityInfoSub> getMultiAbilities();

}
