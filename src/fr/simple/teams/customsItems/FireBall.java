package fr.simple.teams.customsItems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FireBall {

	public static ItemStack FireBall1;
	public static ItemStack FireBall2;
	public static ItemStack FireBall3;

	public static void createCustomFireBall1() {
		ItemStack item = new ItemStack(Material.FIRE_CHARGE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Boule de feu");
		meta.setLore(Arrays.asList("§6Lançable : oui", "§6Puissance : 1", "§6Feu : non"));
		item.setItemMeta(meta);
		FireBall1 = item;
	}

	public static void createCustomFireBall2() {
		ItemStack item = new ItemStack(Material.FIRE_CHARGE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Boule de feu");
		meta.setLore(Arrays.asList("§6Lançable : oui", "§6Puissance : 2", "§6Feu : non"));
		item.setItemMeta(meta);
		FireBall2 = item;
	}

	public static void createCustomFireBall3() {
		ItemStack item = new ItemStack(Material.FIRE_CHARGE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Boule de feu");
		meta.setLore(Arrays.asList("§6Lançable : oui", "§6Puissance : 3", "§6Feu : oui"));
		item.setItemMeta(meta);
		FireBall3 = item;
	}
}
