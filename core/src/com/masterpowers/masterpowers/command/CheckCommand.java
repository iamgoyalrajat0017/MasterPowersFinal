package com.masterpowers.masterpowers.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.configuration.ConfigManager;

/**
 * Executor for /bending check. Extends {@link PKCommand}.
 */
public class CheckCommand extends PKCommand {

	private final String newVersionAvailable;
	private final String curVersion;
	private final String newVersion;
	private final String upToDate;

	public CheckCommand() {
		super("check", "/bending check", ConfigManager.languageConfig.get().getString("Commands.Check.Description"), new String[] { "check", "chk" });

		this.newVersionAvailable = ConfigManager.languageConfig.get().getString("Commands.Check.NewVersionAvailable");
		this.curVersion = ConfigManager.languageConfig.get().getString("Commands.Check.CurrentVersion");
		this.newVersion = ConfigManager.languageConfig.get().getString("Commands.Check.LatestVersion");
		this.upToDate = ConfigManager.languageConfig.get().getString("Commands.Check.UpToDate");
	}

	@Override
	public void execute(final CommandSender sender, final List<String> args) {
		if (!this.hasPermission(sender)) {
			return;
		} else if (args.size() > 0) {
			this.help(sender, false);
			return;
		}
		if (!MasterPowers.plugin.updater.isEnabled()) {
			sender.sendMessage(ChatColor.YELLOW + "The update checker has been disabled in the config. Please enable it in order to use this command.");
		} else if (MasterPowers.plugin.updater.updateAvailable()) {
			sender.sendMessage(ChatColor.GREEN + this.newVersionAvailable.replace("MasterPowers", ChatColor.GOLD + "MasterPowers" + ChatColor.GREEN));
			sender.sendMessage(ChatColor.YELLOW + this.curVersion.replace("{version}", ChatColor.RED + MasterPowers.plugin.updater.getCurrentVersion() + ChatColor.YELLOW));
			sender.sendMessage(ChatColor.YELLOW + this.newVersion.replace("{version}", ChatColor.GOLD + MasterPowers.plugin.updater.getUpdateVersion() + ChatColor.YELLOW));
		} else {
			sender.sendMessage(ChatColor.YELLOW + this.upToDate.replace("MasterPowers", ChatColor.GOLD + "MasterPowers" + ChatColor.YELLOW));
		}
	}

}
