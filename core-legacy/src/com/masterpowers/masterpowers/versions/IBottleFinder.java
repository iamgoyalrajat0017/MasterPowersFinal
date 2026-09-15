package com.masterpowers.masterpowers.versions;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface IBottleFinder {

    int findWaterBottle(final PlayerInventory inventory);

    ItemStack createWaterBottle();
}