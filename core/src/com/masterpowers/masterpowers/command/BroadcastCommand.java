package com.masterpowers.masterpowers.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.configuration.ConfigManager;

/**
 * Executor for /mp broadcast. Controls the periodic "Created by Master!" chat banner
 * without needing a server restart: toggle it on/off, send it immediately, change how
 * often it repeats, or reload the settings from config.yml.
 * <p>
 * Usage:
 * <ul>
 * <li>{@code /mp broadcast toggle} - turns the periodic broadcast on or off</li>
 * <li>{@code /mp broadcast now} - sends the banner once, immediately</li>
 * <li>{@code /mp broadcast interval <minutes>} - sets how often it repeats</li>
 * <li>{@code /mp broadcast reload} - re-reads Properties.CreatorBroadcast.* from config.yml</li>
 * </ul>
 */
public class BroadcastCommand extends PKCommand {

	public BroadcastCommand() {
		super("broadcast", "/mp broadcast <toggle|now|interval|reload>", "Controls the periodic \"Created by Master!\" chat broadcast.", new String[] { "broadcast", "bc" });
	}

	@Override
	public void execute(final CommandSender sender, final List<String> args) {
		if (!this.hasPermission(sender) || !this.correctLength(sender, args.size(), 1, 2)) {
			return;
		}

		final String sub = args.get(0).toLowerCase();

		switch (sub) {
			case "toggle": {
				final boolean newState = !ConfigManager.getConfig().getBoolean("Properties.CreatorBroadcast.Enabled");
				ConfigManager.getConfig().set("Properties.CreatorBroadcast.Enabled", newState);
				ConfigManager.defaultConfig.save();
				MasterPowers.startCreatorBroadcast();
				sender.sendMessage(ChatColor.GOLD + "Creator broadcast is now " + (newState ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED") + ChatColor.GOLD + ".");
				break;
			}
			case "now": {
				MasterPowers.sendCreatorBroadcastNow();
				sender.sendMessage(ChatColor.GOLD + "Broadcast sent.");
				break;
			}
			case "interval": {
				if (args.size() < 2 || !this.isNumeric(args.get(1))) {
					sender.sendMessage(ChatColor.RED + "Usage: /mp broadcast interval <minutes>");
					return;
				}
				final int minutes = Integer.parseInt(args.get(1));
				if (minutes < 1) {
					sender.sendMessage(ChatColor.RED + "Interval must be at least 1 minute.");
					return;
				}
				ConfigManager.getConfig().set("Properties.CreatorBroadcast.IntervalMinutes", minutes);
				ConfigManager.defaultConfig.save();
				MasterPowers.startCreatorBroadcast();
				sender.sendMessage(ChatColor.GOLD + "Broadcast interval set to " + ChatColor.YELLOW + minutes + ChatColor.GOLD + " minute(s).");
				break;
			}
			case "reload": {
				MasterPowers.startCreatorBroadcast();
				sender.sendMessage(ChatColor.GOLD + "Broadcast settings reloaded from config.");
				break;
			}
			default:
				this.help(sender, true);
		}
	}

}
