package com.masterpowers.masterpowers.util;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Registry;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

/**
 * Resolves particles by their stable Minecraft namespaced key (e.g. "poof",
 * "explosion_emitter") instead of the Bukkit {@link Particle} enum constant
 * name. The enum constant names change between game/API versions (e.g.
 * {@code EXPLOSION_NORMAL} became {@code POOF}), but the underlying
 * namespaced key stays the same, so resolving this way keeps this class
 * working across old and new server versions without needing per-version
 * source changes.
 *
 * @deprecated Marked for removal. Use {@link org.bukkit.World#spawnParticle} instead
 */
@Deprecated
public enum ParticleEffect {

	ASH ("ash"),

	/**
	 * Applicable data: {@link BlockData}
	 */
	BLOCK_CRACK ("block"),

	/**
	 * Applicable data: {@link BlockData}
	 */
	BLOCK_DUST ("block"),
	BUBBLE_COLUMN_UP ("bubble_column_up"),
	BUBBLE_POP ("bubble_pop"),
	CAMPFIRE_COSY_SMOKE ("campfire_cosy_smoke"),
	CAMPFIRE_SIGNAL_SMOKE ("campfire_signal_smoke"),
	CLOUD ("cloud"),
	COMPOSTER ("composter"),
	CRIMSON_SPORE ("crimson_spore"),
	CRIT ("crit"),
	CRIT_MAGIC ("enchanted_hit"), @Deprecated MAGIC_CRIT ("enchanted_hit"),
	CURRENT_DOWN ("current_down"),
	DAMAGE_INDICATOR ("damage_indicator"),
	DOLPHIN ("dolphin"),
	DRAGON_BREATH ("dragon_breath"),
	DRIP_LAVA ("dripping_lava"),
	DRIP_WATER ("dripping_water"),
	DRIPPING_HONEY ("dripping_honey"),
	DRIPPING_OBSIDIAN_TEAR ("dripping_obsidian_tear"),
	ENCHANTMENT_TABLE ("enchant"),
	END_ROD ("end_rod"),
	EXPLOSION_HUGE ("explosion_emitter"), @Deprecated HUGE_EXPLOSION ("explosion_emitter"),
	EXPLOSION_LARGE ("explosion"), @Deprecated LARGE_EXPLODE ("explosion"),
	EXPLOSION_NORMAL ("poof"), @Deprecated EXPLODE ("poof"),

	/**
	 * Applicable data: {@link BlockData}
	 */
	FALLING_DUST ("falling_dust"),
	FALLING_HONEY ("falling_honey"),
	FALLING_LAVA ("falling_lava"),
	FALLING_NECTAR ("falling_nectar"),
	FALLING_OBSIDIAN_TEAR ("falling_obsidian_tear"),
	FALLING_WATER ("falling_water"),
	FIREWORKS_SPARK ("firework"),
	FLAME ("flame"),
	FLASH ("flash"),
	HEART ("heart"),

	/**
	 * Applicable data: {@link ItemStack}
	 */
	ITEM_CRACK ("item"),
	LANDING_HONEY ("landing_honey"),
	LANDING_LAVA ("landing_lava"),
	LANDING_OBSIDIAN_TEAR ("landing_obsidian_tear"),
	LAVA ("lava"),
	MOB_APPEARANCE ("elder_guardian"),
	NAUTILUS ("nautilus"),
	NOTE ("note"),
	PORTAL ("portal"),

	/**
	 * Applicable data: {@link DustOptions}
	 */
	REDSTONE ("dust"), @Deprecated RED_DUST ("dust"),
	REVERSE_PORTAL ("reverse_portal"),
	SLIME ("item_slime"),
	SMOKE_NORMAL ("smoke"), @Deprecated SMOKE ("smoke"),
	SMOKE_LARGE ("large_smoke"), @Deprecated LARGE_SMOKE ("large_smoke"),
	SNEEZE ("sneeze"),
	SNOW_SHOVEL ("poof"),
	SNOWBALL ("item_snowball"), @Deprecated SNOWBALL_PROOF ("item_snowball"),
	SOUL ("soul"),
	SOUL_FIRE_FLAME ("soul_fire_flame"),
	SPELL ("effect"),
	SPELL_INSTANT ("instant_effect"), @Deprecated INSTANT_SPELL ("instant_effect"),
	SPELL_MOB ("entity_effect"), @Deprecated MOB_SPELL ("entity_effect"),
	SPELL_MOB_AMBIENT ("entity_effect"),
	@Deprecated MOB_SPELL_AMBIENT ("entity_effect"),
	SPELL_WITCH ("witch"), @Deprecated WITCH_SPELL ("witch"),
	SPIT ("spit"),
	SQUID_INK ("squid_ink"),
	SUSPENDED ("underwater"), @Deprecated SUSPEND ("underwater"),
	SUSPENDED_DEPTH ("underwater"), @Deprecated DEPTH_SUSPEND ("underwater"),
	SWEEP_ATTACK ("sweep_attack"),
	TOTEM ("totem_of_undying"),
	TOWN_AURA ("mycelium"),
	VILLAGER_ANGRY ("angry_villager"), @Deprecated ANGRY_VILLAGER ("angry_villager"),
	VILLAGER_HAPPY ("happy_villager"), @Deprecated HAPPY_VILLAGER ("happy_villager"),
	WARPED_SPORE ("warped_spore"),
	WATER_BUBBLE ("bubble"), @Deprecated BUBBLE ("bubble"),
	WATER_DROP ("rain"),
	WATER_SPLASH ("splash"), @Deprecated SPLASH ("splash"),
	WATER_WAKE ("fishing"), @Deprecated WAKE ("fishing"),
	WHITE_ASH ("white_ash");

	private final Particle particle;
	private final Class<?> dataClass;

	private ParticleEffect(String key) {
		Particle resolved = Registry.PARTICLE_TYPE.get(NamespacedKey.minecraft(key));
		if (resolved == null) {
			// Fall back to a harmless, universally-present particle rather than
			// crashing if a key ever goes missing on some server version.
			resolved = Registry.PARTICLE_TYPE.get(NamespacedKey.minecraft("poof"));
		}
		this.particle = resolved;
		this.dataClass = this.particle.getDataType();
	}

	public Particle getParticle() {
		return particle;
	}

	/**
	 * Displays the particle at the specified location without offsets
	 * @param loc Location to display the particle at
	 * @param amount how many of the particle to display
	 */
	public void display(Location loc, int amount) {
		display(loc, amount, 0, 0, 0);
	}

	/**
	 * Displays the particle at the specified location with no extra data
	 * @param loc Location to spawn the particle
	 * @param amount how many of the particle to spawn
	 * @param offsetX random offset on the x axis
	 * @param offsetY random offset on the y axis
	 * @param offsetZ random offset on the z axis
	 */
	public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ) {
		display(loc, amount, offsetX, offsetY, offsetZ, 0);
	}

	/**
	 * Displays the particle at the specified location with extra data
	 * @param loc Location to spawn the particle
	 * @param amount how many of the particle to spawn
	 * @param offsetX random offset on the x axis
	 * @param offsetY random offset on the y axis
	 * @param offsetZ random offset on the z axis
	 * @param extra extra data to affect the particle, usually affects speed or does nothing
	 */
	public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ, double extra) {
		loc.getWorld().spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ, extra, null, true);
	}

	/**
	 * Displays the particle at the specified location with data
	 * @param loc Location to spawn the particle
	 * @param amount how many of the particle to spawn
	 * @param offsetX random offset on the x axis
	 * @param offsetY random offset on the y axis
	 * @param offsetZ random offset on the z axis
	 * @param data data to display the particle with, only applicable on several particle types (check the enum)
	 */
	public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ, Object data) {
		display(loc, amount, offsetX, offsetY, offsetZ, 0, data);
	}

	/**
	 * Displays the particle at the specified location with regular and extra data
	 * @param loc Location to spawn the particle
	 * @param amount how many of the particle to spawn
	 * @param offsetX random offset on the x axis
	 * @param offsetY random offset on the y axis
	 * @param offsetZ random offset on the z axis
	 * @param extra extra data to affect the particle, usually affects speed or does nothing
	 * @param data data to display the particle with, only applicable on several particle types (check the enum)
	 */
	public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ, double extra, Object data) {
		if (dataClass.isAssignableFrom(Void.class) || data == null || !dataClass.isAssignableFrom(data.getClass())) {
			display(loc, amount, offsetX, offsetY, offsetZ, extra);
		} else {
			loc.getWorld().spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ, extra, data, true);
		}
	}
}
