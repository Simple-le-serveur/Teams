package fr.simple.teams.ranking;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.functionsGUI.GUIInterface;

public class RankingGUIs {

	public static void rankingGUIMain(Player player) {

		Inventory invB = Bukkit.createInventory(null, 54, "§9Classement");
		Inventory inv = GUIInterface.startInv54(invB);

		inv.setItem(47, GUIInterface.newItemS(Material.ARROW, "§bPage précédente"));
		inv.setItem(49, GUIInterface.newItemS(Material.REDSTONE, "§cQuitter"));
		inv.setItem(51, GUIInterface.newItemS(Material.ARROW, "§bPage suivante"));

		List<String> teams = TeamsSort.SortTeams();
		
		int j = 10;
		for (int i = 0; i < teams.size(); i++) {
			if (j == 17 || j == 26 || j == 35) {
				j = j + 2;
			}
			if (j == 10) {
				inv.setItem(j, GUIInterface.newItemC(Material.YELLOW_CONCRETE, teams.get(i), i,
						Arrays.asList("§b" + i + "ème", "§bClique gauche §9pour voir", "§9cette team"), true));
			} else if (j == 11) {
				inv.setItem(j, GUIInterface.newItemC(Material.LIGHT_GRAY_CONCRETE, teams.get(i), i,
						Arrays.asList("§b" + i + "ème", "§bClique gauche §9pour voir", "§9cette team"), true));
			} else if (j == 12) {
				inv.setItem(j, GUIInterface.newItemC(Material.BROWN_CONCRETE, teams.get(i), i,
						Arrays.asList("§b" + i + "ème", "§bClique gauche §9pour voir", "§9cette team"), true));
			} else {
				inv.setItem(j, GUIInterface.newItemC(Material.WHITE_CONCRETE, teams.get(i), i,
						Arrays.asList("§b" + i + "ème", "§bClique gauche §9pour voir", "§9cette team"), false));
			}
			j++;
		}

	}

}
