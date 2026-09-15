package com.masterpowers.masterpowers.region;

import com.griefcraft.model.Protection;
import com.masterpowers.masterpowers.ability.CoreAbility;
import org.bukkit.Location;
import org.bukkit.entity.Player;

class LWC extends RegionProtectionBase {

    protected LWC() {
        super("LWC");
    }

    @Override
    public boolean isRegionProtectedReal(Player player, Location location, CoreAbility ability, boolean igniteAbility, boolean explosiveAbility) {
        final com.griefcraft.lwc.LWC lwc2 = com.griefcraft.lwc.LWC.getInstance();
        final Protection protection = lwc2.getProtectionCache().getProtection(location.getBlock());
        if (protection != null) {
            if (!lwc2.canAccessProtection(player, protection)) {
                return true;
            }
        }
        return false;
    }
}
