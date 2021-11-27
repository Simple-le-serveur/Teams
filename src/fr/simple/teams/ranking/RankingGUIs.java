package fr.simple.teams.ranking;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.TeamData;
import fr.simple.teams.functionsGUI.GUIInterface;

public class RankingGUIs {

	public static void rankingGUIMain(Player player) {

		Inventory invB = Bukkit.createInventory(null, 54, "§9Classement");
		Inventory inv = GUIInterface.startInv54(invB);

		inv.setItem(47, GUIInterface.newItemS(Material.ARROW, "§bPage précédente"));
		inv.setItem(49, GUIInterface.newItemS(Material.REDSTONE, "§cQuitter"));
		inv.setItem(51, GUIInterface.newItemS(Material.ARROW, "§bPage suivante"));

		List<String> teams = TeamsSort.SortTeams();//
		
		int j = 10;
		for (int i = 0; i < teams.size(); i++) {
			if (j == 17 || j == 26 || j == 35) {
				j = j + 2;
			}
			if (j == 10) {
				inv.setItem(j, GUIInterface.newItemC(Material.YELLOW_CONCRETE, "§b" + teams.get(i), (i + 1),
						Arrays.asList("§9Place : §b" + (i + 1) + "ère", "§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9", "§bClique gauche §9pour voir", "§9cette team"), false));
			} else if (j == 11) {
				inv.setItem(j, GUIInterface.newItemC(Material.LIGHT_GRAY_CONCRETE, "§b" + teams.get(i), (i + 1),
						Arrays.asList("§9Place : §b" + (i + 1) + "ème", "§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9", "§bClique gauche §9pour voir", "§9cette team"), false));
			} else if (j == 12) {
				inv.setItem(j, GUIInterface.newItemC(Material.BROWN_CONCRETE, "§b" + teams.get(i), (i + 1),
						Arrays.asList("§9Place : §b" + (i + 1) + "ème", "§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9", "§bClique gauche §9pour voir", "§9cette team"), false));
			} else {
				inv.setItem(j, GUIInterface.newItemC(Material.WHITE_CONCRETE, "§b" + teams.get(i), (i + 1),
						Arrays.asList("§9Place : §b" + (i + 1) + "ème", "§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9", "§bClique gauche §9pour voir", "§9cette team"), false));
			}
			j++;
		}
		
		player.openInventory(inv);

	}

}
