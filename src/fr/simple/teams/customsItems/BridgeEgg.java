package fr.simple.teams.customsItems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BridgeEgg {
	public static ItemStack BridgeEgg;
	
	public static void createBridgeEgg() {
		ItemStack item = new ItemStack(Material.EGG);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§cOeuf de construction rapide");
		meta.setLore(Arrays.asList("§cLancez cet oeuf et", "§cIl vous créera un", "§cmagnifique pont !"));
		item.setItemMeta(meta);
		BridgeEgg = item;
	}
}
