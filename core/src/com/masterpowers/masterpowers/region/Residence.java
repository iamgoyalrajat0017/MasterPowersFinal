package com.masterpowers.masterpowers.region;

import com.bekvon.bukkit.residence.api.ResidenceInterface;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import com.masterpowers.masterpowers.MasterPowers;
import com.masterpowers.masterpowers.ability.CoreAbility;
import com.masterpowers.masterpowers.configuration.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

class Residence extends RegionProtectionBase {

    private String flag;

    protected Residence() {
        super("Residence", "Residence.Respect");

        this.flag = ConfigManager.defaultConfig.get().getString("Properties.RegionProtection.Residence.Flag", "bending");
        if (this.flag.equals("")) this.flag = "bending";
        FlagPermissions.addFlag(this.flag);

        if (Flags.getFlag(this.flag.toLowerCase()) == null) { //If they don't just use an existing flag, like "build"
            MasterPowers.log.info("Registered custom flag for Residence");
        }
    }

    @Override
    public boolean isRegionProtectedReal(Player player, Location location, CoreAbility ability, boolean igniteAbility, boolean explosiveAbility) {
        final ResidenceInterface res = com.bekvon.bukkit.residence.Residence.getInstance().getResidenceManagerAPI();
        final ClaimedResidence claim = res.getByLoc(location);
        if (claim != null) {
            final ResidencePermissions perms = claim.getPermissions();
            //If is their residence
            if (perms.hasResidencePermission(player, false)) return false;
            //If the bending flag is turned off
            if (!perms.playerHas(player, this.flag, false)) {
                return true;
            }
        }

        return false;
    }
}
