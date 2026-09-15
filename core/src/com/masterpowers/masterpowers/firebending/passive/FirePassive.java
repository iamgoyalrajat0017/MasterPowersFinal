package com.masterpowers.masterpowers.firebending.passive;

import org.bukkit.entity.Player;

import com.masterpowers.masterpowers.BendingPlayer;
import com.masterpowers.masterpowers.ability.CoreAbility;
import com.masterpowers.masterpowers.command.Commands;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import com.masterpowers.masterpowers.earthbending.Tremorsense;
import com.masterpowers.masterpowers.firebending.Illumination;

public class FirePassive {

	public static void handle(final Player player) {
		if (Commands.isToggledForAll && ConfigManager.defaultConfig.get().getBoolean("Properties.TogglePassivesWithAllBending")) {
			return;
		}
		final BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (bPlayer != null && bPlayer.canBendPassive(CoreAbility.getAbility(Illumination.class)) && bPlayer.canUsePassive(CoreAbility.getAbility(Illumination.class))) {
			if (!CoreAbility.hasAbility(player, Illumination.class) && (!CoreAbility.hasAbility(player, Tremorsense.class)
					|| (CoreAbility.getAbility(player, Tremorsense.class) != null && !CoreAbility.getAbility(player, Tremorsense.class).isGlowing()))
					&& bPlayer.canBendIgnoreBinds(CoreAbility.getAbility("Illumination")) && ConfigManager.defaultConfig.get().getBoolean("Abilities.Fire.Illumination.Passive")) {
				if (bPlayer.isIlluminating()) {
					new Illumination(player);
				}
			}
		}
	}
}
