package fr.simple.teams.functionsGUI;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIInterface {

	public static ItemStack newItemS(Material material, String customName) {

		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(customName);
		it.setItemMeta(itM);
		return it;

	}

	@SuppressWarnings("deprecation")
	public static ItemStack getOnlinePlayerSkull(Player player) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName("§b" + player.getName());
		meta.setOwner(player.getName());
		skull.setItemMeta(meta);
		return skull;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack getOfflinePlayerSkull(OfflinePlayer p) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName("§b" + p.getName());
		meta.setOwner(p.getName());
		skull.setItemMeta(meta);
		return skull;
	}

	public static ItemStack newItemC(Material material, String customName, int number, List<String> lore,
			boolean colored) {
		ItemStack item = new ItemStack(material, number);
		ItemMeta customM = item.getItemMeta();
		customM.setDisplayName(customName);
		customM.setLore(lore);
		if (colored) {
			customM.addEnchant(Enchantment.DURABILITY, 1, true);
			customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(customM);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack newHead(String headOwner, String customName, int number, List<String> lore, boolean colored) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, number);
		SkullMeta customM = (SkullMeta) item.getItemMeta();
		
		customM.setDisplayName(customName);
		customM.setLore(lore);
		if (colored) {
			customM.addEnchant(Enchantment.DURABILITY, 1, true);
			customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		customM.setOwner(headOwner);
		
		item.setItemMeta(customM);
		return item;
		
	}

	public static Inventory startInv54(Inventory inv) {

		for (int i = 1; i < 8; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 9; i < 45; i = i + 9) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 17; i < 53; i = i + 9) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 46; i < 53; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}

		inv.setItem(0, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(8, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(45, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(53, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));

		return inv;

	}

	public static Inventory startInv45(Inventory inv) {

		for (int i = 1; i < 8; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 9; i < 36; i = i + 9) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 17; i < 44; i = i + 9) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 37; i < 44; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}

		inv.setItem(0, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(8, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(36, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(44, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));

		return inv;

	}

	public static Inventory startInv36(Inventory inv) {

		for (int i = 1; i < 8; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 28; i < 35; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}

		inv.setItem(0, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(8, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(27, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(35, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));

		inv.setItem(9, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		inv.setItem(17, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		inv.setItem(18, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		inv.setItem(26, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));

		return inv;
	}

	public static Inventory startInv27(Inventory inv) {

		for (int i = 1; i < 8; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 19; i < 26; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		}

		inv.setItem(0, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(8, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(18, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		inv.setItem(26, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));

		inv.setItem(9, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));
		inv.setItem(17, GUIInterface.newItemS(Material.BLACK_STAINED_GLASS_PANE, "§9"));

		return inv;

	}

}
