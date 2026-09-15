package com.masterpowers.masterpowers.ability.util;

import java.util.ArrayList;

import com.masterpowers.masterpowers.ability.CoreAbility;
import com.masterpowers.masterpowers.airbending.AirBlast;
import com.masterpowers.masterpowers.airbending.AirBurst;
import com.masterpowers.masterpowers.airbending.AirScooter;
import com.masterpowers.masterpowers.airbending.AirShield;
import com.masterpowers.masterpowers.airbending.AirSpout;
import com.masterpowers.masterpowers.airbending.AirSuction;
import com.masterpowers.masterpowers.airbending.AirSwipe;
import com.masterpowers.masterpowers.airbending.Suffocate;
import com.masterpowers.masterpowers.airbending.Tornado;
import com.masterpowers.masterpowers.airbending.combo.AirStream;
import com.masterpowers.masterpowers.airbending.combo.AirSweep;
import com.masterpowers.masterpowers.airbending.flight.FlightMultiAbility;
import com.masterpowers.masterpowers.earthbending.Catapult;
import com.masterpowers.masterpowers.earthbending.Collapse;
import com.masterpowers.masterpowers.earthbending.EarthArmor;
import com.masterpowers.masterpowers.earthbending.EarthBlast;
import com.masterpowers.masterpowers.earthbending.EarthSmash;
import com.masterpowers.masterpowers.earthbending.EarthTunnel;
import com.masterpowers.masterpowers.earthbending.RaiseEarth;
import com.masterpowers.masterpowers.earthbending.Ripple;
import com.masterpowers.masterpowers.earthbending.lava.LavaFlow;
import com.masterpowers.masterpowers.firebending.BlazeArc;
import com.masterpowers.masterpowers.firebending.FireBlast;
import com.masterpowers.masterpowers.firebending.FireBlastCharged;
import com.masterpowers.masterpowers.firebending.FireBurst;
import com.masterpowers.masterpowers.firebending.FireJet;
import com.masterpowers.masterpowers.firebending.FireManipulation;
import com.masterpowers.masterpowers.firebending.FireShield;
import com.masterpowers.masterpowers.firebending.WallOfFire;
import com.masterpowers.masterpowers.firebending.combo.FireKick;
import com.masterpowers.masterpowers.firebending.combo.FireSpin;
import com.masterpowers.masterpowers.firebending.combo.FireWheel;
import com.masterpowers.masterpowers.firebending.combustion.Combustion;
import com.masterpowers.masterpowers.firebending.lightning.Lightning;
import com.masterpowers.masterpowers.waterbending.OctopusForm;
import com.masterpowers.masterpowers.waterbending.SurgeWall;
import com.masterpowers.masterpowers.waterbending.SurgeWave;
import com.masterpowers.masterpowers.waterbending.Torrent;
import com.masterpowers.masterpowers.waterbending.TorrentWave;
import com.masterpowers.masterpowers.waterbending.WaterBubble;
import com.masterpowers.masterpowers.waterbending.WaterManipulation;
import com.masterpowers.masterpowers.waterbending.WaterSpout;
import com.masterpowers.masterpowers.waterbending.WaterSpoutWave;
import com.masterpowers.masterpowers.waterbending.blood.Bloodbending;
import com.masterpowers.masterpowers.waterbending.combo.IceBullet;
import com.masterpowers.masterpowers.waterbending.combo.IceWave;
import com.masterpowers.masterpowers.waterbending.healing.HealingWaters;
import com.masterpowers.masterpowers.waterbending.ice.IceBlast;
import com.masterpowers.masterpowers.waterbending.ice.IceSpikeBlast;

/**
 * CollisionInitializer is used to create the default Collisions for a given
 * CollisionManager.
 *
 * @see Collision
 * @see CollisionManager
 */
public class CollisionInitializer {

	private final CollisionManager collisionManager;
	private ArrayList<CoreAbility> smallAbilities;
	private ArrayList<CoreAbility> largeAbilities;
	private ArrayList<CoreAbility> comboAbilities;
	private ArrayList<CoreAbility> removeSpoutAbilities;
	private final ArrayList<CoreAbility> ignoreAbilities;

	public CollisionInitializer(final CollisionManager collisionManager) {
		this.collisionManager = collisionManager;
		this.smallAbilities = new ArrayList<>();
		this.comboAbilities = new ArrayList<>();
		this.largeAbilities = new ArrayList<>();
		this.removeSpoutAbilities = new ArrayList<>();
		this.ignoreAbilities = new ArrayList<>();
	}

	public void initializeDefaultCollisions() {
		final CoreAbility airBlast = CoreAbility.getAbility(AirBlast.class);
		CoreAbility.getAbility(AirBurst.class);
		CoreAbility.getAbility(AirScooter.class);
		final CoreAbility airShield = CoreAbility.getAbility(AirShield.class);
		CoreAbility.getAbility(AirSpout.class);
		final CoreAbility airStream = CoreAbility.getAbility(AirStream.class);
		final CoreAbility airSuction = CoreAbility.getAbility(AirSuction.class);
		final CoreAbility airSweep = CoreAbility.getAbility(AirSweep.class);
		final CoreAbility airSwipe = CoreAbility.getAbility(AirSwipe.class);
		CoreAbility.getAbility(FlightMultiAbility.class);
		CoreAbility.getAbility(Suffocate.class);
		CoreAbility.getAbility(Tornado.class);

		CoreAbility.getAbility(Catapult.class);
		CoreAbility.getAbility(Collapse.class);
		CoreAbility.getAbility(EarthArmor.class);
		final CoreAbility earthBlast = CoreAbility.getAbility(EarthBlast.class);
		final CoreAbility earthSmash = CoreAbility.getAbility(EarthSmash.class);
		CoreAbility.getAbility(EarthTunnel.class);
		CoreAbility.getAbility(LavaFlow.class);
		CoreAbility.getAbility(RaiseEarth.class);
		CoreAbility.getAbility(Ripple.class);

		final CoreAbility blazeArc = CoreAbility.getAbility(BlazeArc.class);
		final CoreAbility combustion = CoreAbility.getAbility(Combustion.class);
		final CoreAbility fireBlast = CoreAbility.getAbility(FireBlast.class);
		final CoreAbility fireBlastCharged = CoreAbility.getAbility(FireBlastCharged.class);
		CoreAbility.getAbility(FireBurst.class);
		CoreAbility.getAbility(FireJet.class);
		final CoreAbility fireKick = CoreAbility.getAbility(FireKick.class);
		final CoreAbility fireSpin = CoreAbility.getAbility(FireSpin.class);
		final CoreAbility fireWheel = CoreAbility.getAbility(FireWheel.class);
		final CoreAbility fireShield = CoreAbility.getAbility(FireShield.class);
		final CoreAbility fireManipulation = CoreAbility.getAbility(FireManipulation.class);
		CoreAbility.getAbility(Lightning.class);
		CoreAbility.getAbility(WallOfFire.class);

		CoreAbility.getAbility(Bloodbending.class);
		CoreAbility.getAbility(HealingWaters.class);
		final CoreAbility iceBlast = CoreAbility.getAbility(IceBlast.class);
		final CoreAbility iceBullet = CoreAbility.getAbility(IceBullet.class);
		CoreAbility.getAbility(IceWave.class);
		final CoreAbility iceSpikeBlast = CoreAbility.getAbility(IceSpikeBlast.class);
		CoreAbility.getAbility(OctopusForm.class);
		CoreAbility.getAbility(SurgeWall.class);
		CoreAbility.getAbility(SurgeWave.class);
		CoreAbility.getAbility(Torrent.class);
		CoreAbility.getAbility(TorrentWave.class);
		CoreAbility.getAbility(WaterBubble.class);
		final CoreAbility waterManipulation = CoreAbility.getAbility(WaterManipulation.class);
		CoreAbility.getAbility(WaterSpout.class);
		CoreAbility.getAbility(WaterSpoutWave.class);

		final CoreAbility[] smallAbils = { airSwipe, earthBlast, waterManipulation, iceBlast, iceSpikeBlast, fireBlast };
		final CoreAbility[] largeAbils = { earthSmash, airShield, fireBlastCharged, fireKick, fireSpin, fireWheel, airSweep, iceBullet };
		final CoreAbility[] comboAbils = { fireKick, fireSpin, fireWheel, airSweep, iceBullet };
		final CoreAbility[] removeSpoutAbils = { airSwipe, earthBlast, waterManipulation, iceBlast, iceSpikeBlast, fireBlast, fireBlastCharged, earthSmash, fireKick, fireSpin, fireWheel, airSweep, iceBullet };
		final CoreAbility[] ignoreAbils = { airBlast, airSuction, blazeArc, combustion };

		for (final CoreAbility smallAbil : smallAbils) {
			this.addSmallAbility(smallAbil);
		}
		for (final CoreAbility largeAbil : largeAbils) {
			this.addLargeAbility(largeAbil);
		}
		for (final CoreAbility comboAbil : comboAbils) {
			this.addComboAbility(comboAbil);
		}
		for (final CoreAbility removeSpoutAbil : removeSpoutAbils) {
			this.addRemoveSpoutAbility(removeSpoutAbil);
		}
		for (final CoreAbility ignoreAbil : ignoreAbils) {
			this.addIgnoreAbility(ignoreAbil);
		}
		for (final CoreAbility comboAbil : comboAbils) {
			this.collisionManager.addCollision(new Collision(airShield, comboAbil, false, true));
		}

		this.collisionManager.addCollision(new Collision(airSwipe, airSwipe, false, false));

		this.collisionManager.addCollision(new Collision(airShield, airSwipe, false, false));
		this.collisionManager.addCollision(new Collision(airShield, airSweep, false, false));
		this.collisionManager.addCollision(new Collision(airShield, fireBlastCharged, false, false));
		this.collisionManager.addCollision(new Collision(airShield, airStream, false, true));

		this.collisionManager.addCollision(new Collision(airSweep, airSweep, false, false));

		this.collisionManager.addCollision(new Collision(fireShield, fireBlastCharged, false, false));
		this.collisionManager.addCollision(new Collision(fireShield, fireBlast, false, true));
		this.collisionManager.addCollision(new Collision(fireShield, waterManipulation, false, true));
		this.collisionManager.addCollision(new Collision(fireShield, earthBlast, false, true));
		this.collisionManager.addCollision(new Collision(fireShield, airSweep, false, true));

		this.collisionManager.addCollision(new Collision(fireManipulation, airBlast, false, true));
		this.collisionManager.addCollision(new Collision(fireManipulation, airSuction, false, true));
		this.collisionManager.addCollision(new Collision(fireManipulation, fireBlast, false, true));
		this.collisionManager.addCollision(new Collision(fireManipulation, fireBlastCharged, false, true));
		this.collisionManager.addCollision(new Collision(fireManipulation, waterManipulation, false, true));
		this.collisionManager.addCollision(new Collision(fireManipulation, earthBlast, false, true));
		this.collisionManager.addCollision(new Collision(fireManipulation, airSweep, false, true));
	}

	/**
	 * An ability that collides with other small abilities. (EarthBlast,
	 * FireBlast). Two colliding small abilities will remove each other. A small
	 * ability is removed when it collides with a large ability.
	 *
	 * @param smallAbility the small CoreAbility
	 */
	public void addSmallAbility(final CoreAbility smallAbility) {
		if (smallAbility == null) {
			return;
		}
		this.smallAbilities.add(smallAbility);
		for (final CoreAbility otherSmallAbility : this.smallAbilities) {
			this.collisionManager.addCollision(new Collision(smallAbility, otherSmallAbility, true, true));
		}

		for (final CoreAbility largeAbility : this.largeAbilities) {
			this.collisionManager.addCollision(new Collision(largeAbility, smallAbility, false, true));
		}

	}

	/**
	 * A large ability that removes small abilities (EarthSmash, Charged
	 * FireBlast). The large ability remains while the small ability is
	 * destroyed.
	 *
	 * @param largeAbility the large CoreAbility
	 */
	public void addLargeAbility(final CoreAbility largeAbility) {
		if (largeAbility == null) {
			return;
		}
		this.largeAbilities.add(largeAbility);
		for (final CoreAbility otherLargeAbility : this.largeAbilities) {
			this.collisionManager.addCollision(new Collision(largeAbility, otherLargeAbility, true, true));
		}

		for (final CoreAbility smallAbility : this.smallAbilities) {
			this.collisionManager.addCollision(new Collision(largeAbility, smallAbility, false, true));
		}

	}

	/**
	 * A combo ability that collides with all other combo abilities. Both
	 * colliding combo abilities are destroyed.
	 *
	 * @param comboAbility the combo CoreAbility
	 */
	public void addComboAbility(final CoreAbility comboAbility) {
		if (comboAbility == null) {
			return;
		}
		this.comboAbilities.add(comboAbility);
		for (final CoreAbility otherComboAbility : this.smallAbilities) {
			this.collisionManager.addCollision(new Collision(comboAbility, otherComboAbility, true, true));
		}
	}

	/**
	 * An ability that destroys WaterSpout, AirSpout, and SandSpout upon
	 * contact. The spout is destroyed and this ability remains.
	 *
	 * @param ability the ability that removes spouts
	 */
	public void addRemoveSpoutAbility(final CoreAbility ability) {
		if (ability == null) {
			return;
		}
		this.removeSpoutAbilities.add(ability);
		this.collisionManager.addCollision(new Collision(ability, CoreAbility.getAbility(AirSpout.class), false, true));
		this.collisionManager.addCollision(new Collision(ability, CoreAbility.getAbility(WaterSpout.class), false, true));
	}

	/**
	 * Cancel interaction between two abilities
	 *
	 * @param ignoreAbility the small CoreAbility
	 */
	public void addIgnoreAbility(final CoreAbility ignoreAbility) {
		if (ignoreAbility == null) {
			return;
		}
		this.ignoreAbilities.add(ignoreAbility);
		for (final CoreAbility smallAbility : this.smallAbilities) {
			this.collisionManager.addCollision(new Collision(ignoreAbility, smallAbility, false, false));
		}

		for (final CoreAbility largeAbility : this.largeAbilities) {
			this.collisionManager.addCollision(new Collision(largeAbility, ignoreAbility, false, false));
		}

		this.collisionManager.addCollision(new Collision(ignoreAbility, CoreAbility.getAbility(AirSpout.class), false, false));
		this.collisionManager.addCollision(new Collision(ignoreAbility, CoreAbility.getAbility(WaterSpout.class), false, false));
	}

	public CollisionManager getCollisionManager() {
		return this.collisionManager;
	}

	public ArrayList<CoreAbility> getSmallAbilities() {
		return this.smallAbilities;
	}

	public void setSmallAbilities(final ArrayList<CoreAbility> smallAbilities) {
		this.smallAbilities = smallAbilities;
	}

	public ArrayList<CoreAbility> getLargeAbilities() {
		return this.largeAbilities;
	}

	public void setLargeAbilities(final ArrayList<CoreAbility> largeAbilities) {
		this.largeAbilities = largeAbilities;
	}

	public ArrayList<CoreAbility> getComboAbilities() {
		return this.comboAbilities;
	}

	public void setComboAbilities(final ArrayList<CoreAbility> comboAbilities) {
		this.comboAbilities = comboAbilities;
	}

	public ArrayList<CoreAbility> getRemoveSpoutAbilities() {
		return this.removeSpoutAbilities;
	}

	public void setRemoveSpoutAbilities(final ArrayList<CoreAbility> removeSpoutAbilities) {
		this.removeSpoutAbilities = removeSpoutAbilities;
	}

}
