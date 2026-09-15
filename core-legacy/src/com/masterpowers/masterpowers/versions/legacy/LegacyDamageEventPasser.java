package com.masterpowers.masterpowers.versions.legacy;

import com.masterpowers.masterpowers.versions.IDamageEventPasser;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LegacyDamageEventPasser implements IDamageEventPasser {
    @Override
    public EntityDamageByEntityEvent createEvent(Player player, Entity source, double damage) {
        return new EntityDamageByEntityEvent(source, player, EntityDamageByEntityEvent.DamageCause.CUSTOM, damage);
    }
}