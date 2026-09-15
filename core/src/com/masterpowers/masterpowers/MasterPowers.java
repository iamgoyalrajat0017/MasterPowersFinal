package com.masterpowers.masterpowers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import com.djrapitops.plan.extension.ExtensionService;
import com.masterpowers.masterpowers.hooks.PlanExtension;
import com.masterpowers.masterpowers.region.RegionProtection;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.masterpowers.masterpowers.ability.CoreAbility;
import com.masterpowers.masterpowers.ability.util.CollisionInitializer;
import com.masterpowers.masterpowers.ability.util.CollisionManager;
import com.masterpowers.masterpowers.ability.util.ComboManager;
import com.masterpowers.masterpowers.ability.util.MultiAbilityManager;
import com.masterpowers.masterpowers.airbending.util.AirbendingManager;
import com.masterpowers.masterpowers.board.BendingBoardManager;
import com.masterpowers.masterpowers.chiblocking.util.ChiblockingManager;
import com.masterpowers.masterpowers.command.Commands;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import com.masterpowers.masterpowers.earthbending.util.EarthbendingManager;
import com.masterpowers.masterpowers.firebending.util.FirebendingManager;
import com.masterpowers.masterpowers.hooks.PlaceholderAPIHook;
import com.masterpowers.masterpowers.hooks.WorldGuardFlag;
import com.masterpowers.masterpowers.object.Preset;
import com.masterpowers.masterpowers.storage.DBConnection;
import com.masterpowers.masterpowers.util.ChatUtil;
import com.masterpowers.masterpowers.util.Metrics;
import com.masterpowers.masterpowers.util.RevertChecker;
import com.masterpowers.masterpowers.util.StatisticsManager;
import com.masterpowers.masterpowers.util.Updater;
import com.masterpowers.masterpowers.waterbending.util.WaterbendingManager;

public class MasterPowers extends JavaPlugin {

	public static MasterPowers plugin;
	public static Logger log;
	public static CollisionManager collisionManager;
	public static CollisionInitializer collisionInitializer;
	public static long time_step = 1;
	public Updater updater;
	public static BukkitTask creatorBroadcastTask;
	BukkitTask revertChecker;
	private static PlaceholderAPIHook papiHook;

	@Override
	public void onEnable() {
		plugin = this;
		MasterPowers.log = this.getLogger();

		MasterPowers.log.info("  __  __           _            ____                            ");
		MasterPowers.log.info(" |  \\/  | __ _ ___| |_ ___ _ __|  _ \\ _____      _____ _ __ ___ ");
		MasterPowers.log.info(" | |\\/| |/ _` / __| __/ _ \\ '__| |_) / _ \\ \\ /\\ / / _ \\ '__/ __|");
		MasterPowers.log.info(" | |  | | (_| \\__ \\ ||  __/ |  |  __/ (_) \\ V  V /  __/ |  \\__ \\");
		MasterPowers.log.info(" |_|  |_|\\__,_|___/\\__\\___|_|  |_|   \\___/ \\_/\\_/ \\___|_|  |___/");
		MasterPowers.log.info("               Official Bending Plugin \u2014 Night SMP Edition       ");
		MasterPowers.log.info("                        Created by Master!                         ");
		MasterPowers.log.info("");

		new ConfigManager();
		final boolean checkUpdateOnStartup = ConfigManager.getConfig().getBoolean("Properties.UpdateChecker");
		this.updater = new Updater(this, "https://api.github.com/repos/iamgoyalrajat0017/MasterPowers-v2/releases/latest", checkUpdateOnStartup);
		new Commands(this);
		new MultiAbilityManager();
		new ComboManager();
		new RegionProtection();
		collisionManager = new CollisionManager();
		collisionInitializer = new CollisionInitializer(collisionManager);
		CoreAbility.registerAbilities();
		collisionInitializer.initializeDefaultCollisions();
		collisionManager.startCollisionDetection();

		Preset.loadExternalPresets();

		DBConnection.init();
		if (!DBConnection.isOpen()) {
			return;
		}

		Manager.startup();
		BendingBoardManager.setup();
		BendingPlayer.DISABLED_WORLDS = new HashSet<>(ConfigManager.defaultConfig.get().getStringList("Properties.DisabledWorlds"));

		this.getServer().getPluginManager().registerEvents(new PKListener(), this);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BendingManager(), 0, 1);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new AirbendingManager(this), 0, 1);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new WaterbendingManager(this), 0, 1);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new EarthbendingManager(this), 0, 1);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new FirebendingManager(this), 0, 1);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ChiblockingManager(this), 0, 1);
		this.getServer().getScheduler().runTaskTimerAsynchronously(this, new BendingManager.TempElementsRunnable(), 20, 20);
		this.revertChecker = this.getServer().getScheduler().runTaskTimerAsynchronously(this, new RevertChecker(this), 0, 200);

		startCreatorBroadcast();

		for (final Player player : Bukkit.getOnlinePlayers()) {
			PKListener.getJumpStatistics().put(player, player.getStatistic(Statistic.JUMP));

			OfflineBendingPlayer.loadAsync(player.getUniqueId(), true);
			Manager.getManager(StatisticsManager.class).load(player.getUniqueId());
		}

		final Metrics metrics = new Metrics(this, 909);
		metrics.addCustomChart(new Metrics.AdvancedPie("Elements", () -> {

			final HashMap<String, Integer> valueMap = new HashMap<>();
			for (final Element element : Element.getMainElements()) {
				int counter = 0;
				for (final Player player : Bukkit.getOnlinePlayers()) {
					final BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
					if (bPlayer != null && bPlayer.hasElement(element)) {
						counter++;
					}
				}
				valueMap.put(element.getName(), counter);
			}

			return valueMap;
		}));

		final double cacheTime = ConfigManager.getConfig().getDouble("Properties.RegionProtection.CacheBlockTime");

		RegionProtection.startCleanCacheTask(cacheTime);

		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			papiHook = new PlaceholderAPIHook(this);
			papiHook.register();
		}

		if (Bukkit.getPluginManager().isPluginEnabled("Plan")) {
			new PlanExtension();
		}
	}

	@Override
	public void onDisable() {
		this.revertChecker.cancel();
		GeneralMethods.stopBending();
		for (final Player player : this.getServer().getOnlinePlayers()) {
			if (isStatisticsEnabled()) {
				Manager.getManager(StatisticsManager.class).save(player.getUniqueId(), false);
			}
			final BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
			if (bPlayer != null && isDatabaseCooldownsEnabled()) {
				bPlayer.saveCooldowns(false);
			}
		}
		Manager.shutdown();
		if (DBConnection.isOpen()) {
			DBConnection.sql.close();
		}

		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			papiHook.unregister();
		}
	}

	@Override
	public void onLoad() {
		if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
			WorldGuardFlag.registerBendingWorldGuardFlag();
		}
	}

	public static CollisionManager getCollisionManager() {
		return collisionManager;
	}

	public static void setCollisionManager(final CollisionManager collisionManager) {
		MasterPowers.collisionManager = collisionManager;
	}

	public static CollisionInitializer getCollisionInitializer() {
		return collisionInitializer;
	}

	public static void setCollisionInitializer(final CollisionInitializer collisionInitializer) {
		MasterPowers.collisionInitializer = collisionInitializer;
	}

	public static boolean isStatisticsEnabled() {
		return ConfigManager.getConfig().getBoolean("Properties.Statistics");
	}

	public static boolean isDatabaseCooldownsEnabled() {
		return ConfigManager.getConfig().getBoolean("Properties.DatabaseCooldowns");
	}

	/**
	 * (Re)starts the periodic "created by Master" chat broadcast based on the current
	 * Properties.CreatorBroadcast.* config values. Cancels any previously running task first,
	 * so this is safe to call again after a config change (toggle, interval, reload) without
	 * needing a server restart. If disabled in config, this just cancels the task and returns.
	 */
	public static void startCreatorBroadcast() {
		if (creatorBroadcastTask != null) {
			creatorBroadcastTask.cancel();
			creatorBroadcastTask = null;
		}

		if (!ConfigManager.getConfig().getBoolean("Properties.CreatorBroadcast.Enabled")) {
			return;
		}

		final int intervalMinutes = Math.max(1, ConfigManager.getConfig().getInt("Properties.CreatorBroadcast.IntervalMinutes"));
		final long intervalTicks = intervalMinutes * 60L * 20L;

		creatorBroadcastTask = plugin.getServer().getScheduler().runTaskTimer(plugin, MasterPowers::sendCreatorBroadcastNow, intervalTicks, intervalTicks);
	}

	/**
	 * Immediately sends the "created by Master" premium chat banner to all online players,
	 * regardless of whether the periodic broadcast is enabled. Used by /mp broadcast now.
	 */
	public static void sendCreatorBroadcastNow() {
		final String tagline = ConfigManager.getConfig().getString("Properties.CreatorBroadcast.Message");
		final String banner = ChatUtil.color(
				"&8&m                                                        \n" +
				"   &6&l\u2605 &e&lMasterPowers &6&l\u2605\n" +
				"      &7" + tagline + "\n" +
				"&8&m                                                        ");
		for (final Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(banner);
		}
	}
}
