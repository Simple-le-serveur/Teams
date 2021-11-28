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
				inv.setItem(j,
						GUIInterface.newItemC(Material.YELLOW_CONCRETE, "§b" + teams.get(i), (i + 1),
								Arrays.asList("§9Place : §b" + (i + 1) + "ère",
										"§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9",
										"§bClique gauche §9pour voir", "§9cette team"),
								false));
			} else if (j == 11) {
				inv.setItem(j,
						GUIInterface.newItemC(Material.LIGHT_GRAY_CONCRETE, "§b" + teams.get(i), (i + 1),
								Arrays.asList("§9Place : §b" + (i + 1) + "ème",
										"§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9",
										"§bClique gauche §9pour voir", "§9cette team"),
								false));
			} else if (j == 12) {
				inv.setItem(j,
						GUIInterface.newItemC(Material.BROWN_CONCRETE, "§b" + teams.get(i), (i + 1),
								Arrays.asList("§9Place : §b" + (i + 1) + "ème",
										"§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9",
										"§bClique gauche §9pour voir", "§9cette team"),
								false));
			} else {
				inv.setItem(j,
						GUIInterface.newItemC(Material.WHITE_CONCRETE, "§b" + teams.get(i), (i + 1),
								Arrays.asList("§9Place : §b" + (i + 1) + "ème",
										"§9Score : §b" + TeamData.getTeamNotation(teams.get(i)), "§9",
										"§bClique gauche §9pour voir", "§9cette team"),
								false));
			}
			j++;
		}

		player.openInventory(inv);

	}

	public static void RankingVoteBuild(Player player) {

		Inventory invB = Bukkit.createInventory(null, 54, "§9Vote construction");
		Inventory inv = GUIInterface.startInv45(invB);

		inv.setItem(40, GUIInterface.newItemS(Material.GREEN_CONCRETE, "§aSuivant"));

		inv.setItem(19, GUIInterface.newItemC(Material.BLACK_CONCRETE, "§0HORRIBLE !", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(20, GUIInterface.newItemC(Material.RED_CONCRETE, "§4Pas bien", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(21, GUIInterface.newItemC(Material.ORANGE_CONCRETE, "§6Passable", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(22, GUIInterface.newItemC(Material.BRICK_STAIRS, "§bVotez pour la", 1,
				Arrays.asList("§bqualité de la", "§bconstruction"), false));
		inv.setItem(23, GUIInterface.newItemC(Material.YELLOW_CONCRETE, "§eBien", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(24, GUIInterface.newItemC(Material.LIME_CONCRETE, "§aTrès bien", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(25, GUIInterface.newItemC(Material.GREEN_CONCRETE, "§2Incroyable !", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));

		player.openInventory(inv);

	}

	public static void RankingVoteRessources(Player player) {

		Inventory invB = Bukkit.createInventory(null, 54, "§9Vote ressources");
		Inventory inv = GUIInterface.startInv45(invB);

		inv.setItem(40, GUIInterface.newItemS(Material.GREEN_CONCRETE, "§aSuivant"));

		inv.setItem(19, GUIInterface.newItemC(Material.BLACK_CONCRETE, "§0RIEN !", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(20, GUIInterface.newItemC(Material.RED_CONCRETE, "§4Presque rien", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(21, GUIInterface.newItemC(Material.ORANGE_CONCRETE, "§6Passable", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(22, GUIInterface.newItemC(Material.DIAMOND, "§bVotez pour les", 1,
				Arrays.asList("§brichesses"), false));
		inv.setItem(23, GUIInterface.newItemC(Material.YELLOW_CONCRETE, "§eBien", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(24, GUIInterface.newItemC(Material.LIME_CONCRETE, "§aBeaucoup de choses", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(25, GUIInterface.newItemC(Material.GREEN_CONCRETE, "§2ENORMEMENT DE CHOSES !", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));

		player.openInventory(inv);

	}

}
