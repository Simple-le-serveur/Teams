package fr.simple.teams.assautRequest;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.TeamData;
import fr.simple.teams.functionsGUI.GUIInterface;

public class AssautRequestGUI {

	public static void assautRequestGUIMain(Player player) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Demande d'assiègement");
		Inventory inv = GUIInterface.startInv54(invB);

		AssautRequestData data = AssautRequestData.AssautRequestData.get(player.getName());

		inv.setItem(20, GUIInterface.newItemC(Material.DIAMOND_SWORD,
				"§b" + data.getAttaquants() + " §9 VS §b " + data.getDéfenceurs(), 1, Arrays.asList(), true));
		inv.setItem(24, GUIInterface.newItemC(Material.CLOCK, "§bParamétrage des phases", 1,
				Arrays.asList("§9Cliquez pour configurer les", "§9différentes phases d'attaque."), false));
		inv.setItem(30,
				GUIInterface.newItemC(Material.IRON_DOOR, "§bAccès aux invités", 1,
						Arrays.asList("§9Choisissez si vous voulez ou non que",
								"§9touts les joueurs connectés puissent", "§9venir voir le combat en spectateurs."),
						false));
		inv.setItem(32,
				GUIInterface.newItemC(Material.PAPER, "§bMises", 1, Arrays.asList("§9Cliquez pour définir les mises",
						"§9en jeux.", "§9", "§cINDISPONIBLE SUR CETTE VESRION DU PLUGIN"), false));

		inv.setItem(48, GUIInterface.newItemC(Material.RED_CONCRETE, "§cAnnuler", 1,
				Arrays.asList("§9Annule la demande de siège."), false));
		inv.setItem(50,
				GUIInterface.newItemC(Material.GREEN_CONCRETE, "§aEnvoyer", 1,
						Arrays.asList("§9Envoie la demande de siège", "§9à l'équipe adverse.", "§9", "§bParamètres :",
								"§9Teams : §b " + data.getAttaquants() + "§9 VS §b" + data.getDéfenceurs(),
								"§9Temps préparation : §b" + data.getTpsPréparation() + " minutes",
								"§9Temps attaque : §b" + data.getTpsAttaque() + " minutes", "§9Accès : §b" + data.getAccès(),
								"§9Mise : §cINDISPONIBLE SUR CETTE VESRION DU PLUGIN"),
						false));

		player.openInventory(inv);
	}

	public static void assautRequestGUIAccess(Player player) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Configuration de l'accès");
		Inventory inv = GUIInterface.startInv27(invB);

		inv.setItem(12, GUIInterface.newItemC(Material.IRON_DOOR, "§bPrivé", 1,
				Arrays.asList("§9Cliquez pour définir l'attaque", "§9en mode privé."), false));
		inv.setItem(14,
				GUIInterface
						.newItemC(
								Material.ACACIA_DOOR, "§bPublique", 1, Arrays.asList("§9Cliquez pour définir l'attaque",
										"§9en mode publique : touts les", "§9joueurs peuvent venir voir le combat."),
								false));

		inv.setItem(22, GUIInterface.newItemS(Material.REDSTONE, "§cretour au menu précédent"));

		player.openInventory(inv);
	}

	public static void assautRequestGUIPhasesMain(Player player) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Configuration des phases");
		Inventory inv = GUIInterface.startInv27(invB);

		AssautRequestData data = AssautRequestData.AssautRequestData.get(player.getName());

		inv.setItem(11,
				GUIInterface
						.newItemC(Material.REPEATER, "§bPréparation", 1,
								Arrays.asList("§9Cliquez pour configurer la", "§9phase de préparation.", "§9",
										"§bParamètres :", "§9Temps de préparation : §b" + data.getTpsPréparation() + " minutes"),
								false));
		inv.setItem(13,
				GUIInterface.newItemC(Material.TNT, "§bAttaque", 1, Arrays.asList("§9Cliquez pour configurer la",
						"§9phase d'attaque.", "§9", "§bParamètres :", "§9Temps d'attaque : §b" + data.getTpsAttaque() + " minutes"),
						false));
		inv.setItem(15, GUIInterface.newItemC(
				Material.STONE_SWORD, "§bCombat final", 1, Arrays.asList("§9Cliquez pour configurer la",
						"§9phase d'attaque, sauf qu'il", "§9n'y a rien a configurer donc", "§9ne cliquez pas sinon..."),
				false));

		inv.setItem(22, GUIInterface.newItemS(Material.REDSTONE, "§cretour au menu précédent"));

		player.openInventory(inv);
	}

	public static void assautRequestGUIPhasesPréparationTPS(Player player) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Configuration du temps de préparation");
		Inventory inv = GUIInterface.startInv27(invB);

		AssautRequestData data = AssautRequestData.AssautRequestData.get(player.getName());

		inv.setItem(13, GUIInterface.newItemC(Material.CLOCK,
				"§bTemps actuel : §b§l" + data.getTpsPréparation() + "§b minutes", 1, Arrays.asList(), false));
		inv.setItem(11, GUIInterface.newHead("MHF_arrowdown", "§b-10 minutes", 1, Arrays.asList(), false));
		inv.setItem(15, GUIInterface.newHead("MHF_arrowup", "§b+10 minutes", 1, Arrays.asList(), false));

		inv.setItem(22, GUIInterface.newItemS(Material.REDSTONE, "§cretour au menu précédent"));

		player.openInventory(inv);
	}

	public static void assautRequestGUIPhasesAttaqueTPS(Player player) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Configuration du temps d'attaque");
		Inventory inv = GUIInterface.startInv27(invB);

		AssautRequestData data = AssautRequestData.AssautRequestData.get(player.getName());

		inv.setItem(13, GUIInterface.newItemC(Material.CLOCK,
				"§bTemps actuel : §b§l" + data.getTpsAttaque() + "§b minutes", 1, Arrays.asList(), false));
		inv.setItem(11, GUIInterface.newHead("MHF_arrowdown", "§b-10 minutes", 1, Arrays.asList(), false));
		inv.setItem(15, GUIInterface.newHead("MHF_arrowup", "§b+10 minutes", 1, Arrays.asList(), false));

		inv.setItem(22, GUIInterface.newItemS(Material.REDSTONE, "§cretour au menu précédent"));

		player.openInventory(inv);
	}
	
	public static void assautRequestGUIConfirm(Player player) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Conditions de l'assaut");
		Inventory inv = GUIInterface.startInv54(invB);
		
		AssautRequestSendData data = AssautRequestSendData.AssautRequestSendData.get(TeamData.getPlayerTeam(player.getUniqueId()));
		
		inv.setItem(20, GUIInterface.newItemC(Material.DIAMOND_SWORD, "§b§l" + data.getAttaquants() + "§b VS §b§l" + data.getDéfenceurs(), 1, Arrays.asList("§9"), false));
		inv.setItem(22, GUIInterface.newItemC(Material.IRON_DOOR, "§bAccès", 1, Arrays.asList("§9Accès : §b" + data.getAccès()), false));
		inv.setItem(24, GUIInterface.newItemC(Material.PAPER, "§bMises", 1, Arrays.asList("§9Mises : §cINDISPONIBLE SUR CETTE VESRION DU PLUGIN"), false));
		inv.setItem(29, GUIInterface.newItemC(Material.REPEATER, "§bPréparation", 1, Arrays.asList("§9", "§bParamètres :", "§9Temps : §b" + data.getTpsPréparation() + " minutes"), false));
		inv.setItem(31, GUIInterface.newItemC(Material.TNT, "§bAttaque", 1, Arrays.asList("§9", "§bParamètres :", "§9Temps : §b" + data.getTpsAttaque() + " minutes"), false));
		inv.setItem(33, GUIInterface.newItemC(Material.STONE_SWORD, "§bCombat Final", 1, Arrays.asList("§9", "§bParamètres :", "§cAucun paramètres disponibles sur cette phase"), false));
		
		inv.setItem(48, GUIInterface.newItemC(Material.RED_CONCRETE, "§cRefuser la demande d'assaut", 1, Arrays.asList(), false));
		inv.setItem(50, GUIInterface.newItemC(Material.GREEN_CONCRETE, "§aAccepter la demande d'assaut", 1, Arrays.asList(), false));
		
		player.openInventory(inv);
	}

}
