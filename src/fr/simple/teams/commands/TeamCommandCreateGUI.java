package fr.simple.teams.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.functionsGUI.GUIInterface;
import net.md_5.bungee.api.ChatColor;

public class TeamCommandCreateGUI {

	@SuppressWarnings("deprecation")
	public static void createTeamGUIMain(Player player, TeamCommandCreateData data) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Créer une team");
		Inventory inv = GUIInterface.startInv54(invB);

		inv.setItem(20, GUIInterface.newItemC(Material.NAME_TAG, "§bNom", 1,
				Arrays.asList("§9Actuel : §b" + data.TeamCommandCreateDataGetName(), "§9Cliquez pour changer"), false));
		inv.setItem(24, GUIInterface.newItemC(Material.PAPER, "§bSlogan", 1,
				Arrays.asList("§9Actuel : §b" + data.TeamCommandCreateDataGetSlogan(), "§9Cliquez pour changer"),
				false));
		inv.setItem(30, GUIInterface.newItemC(Material.OAK_DOOR, "§bAccès", 1,
				Arrays.asList("§9Actuel : §b" + data.TeamCommandCreateDataGetAccess(), "§9Cliquez pour changer"),
				false));
		inv.setItem(32,
				GUIInterface.newItemC(Material.WHITE_WOOL, "§bCouleur", 1,
						Arrays.asList("§9Actuel : " + ChatColor.valueOf(data.TeamCommandCreateDataGetColor())
								+ data.TeamCommandCreateDataGetColor(), "§9Cliquez pour changer"),
						false));

		inv.setItem(48, GUIInterface.newItemC(Material.RED_CONCRETE, "§cQuitter et abandonner", 1,
				Arrays.asList("§cATTENTION, Cette action", "§cest irréversible !"), false));

		if (data.TeamCommandCreateDataGetName() != "null" && data.TeamCommandCreateDataGetSlogan() != "null") {
			inv.setItem(50,
					GUIInterface.newItemC(Material.GREEN_CONCRETE, "§aQuitter et créer la team", 1,
							Arrays.asList("§9Avec le nom §b" + data.TeamCommandCreateDataGetName() + "§9,",
									"§9le slogan §b" + data.TeamCommandCreateDataGetSlogan() + "§9,",
									"§9l'accès §b" + data.TeamCommandCreateDataGetAccess() + "§9,",
									"§9et la couleur §b" + data.TeamCommandCreateDataGetColor() + "§9.", "§9",
									"§c§lATTENTION, CELA COUTE", "§4§l500 000 §ceuros !"),
							true));
		} else {
			inv.setItem(50, GUIInterface.newItemC(Material.GREEN_CONCRETE, "§aQuitter et créer la team", 1,
					Arrays.asList("§cImpossible : vous devez", "§cchoisir un nom et un slogan", "§cde team."),
					false));
		}

		player.openInventory(inv);
	}

	public static void createTeamGUIColor(Player player, TeamCommandCreateData data) {
		Inventory invB = Bukkit.createInventory(null, 36, "§9Choix de la couleur");
		Inventory inv = GUIInterface.startInv36(invB);

		inv.setItem(31,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour", 1, Arrays.asList("§cau menu principal"), false));

		inv.setItem(11, GUIInterface.newItemC(Material.YELLOW_DYE, "§eJaune", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(12, GUIInterface.newItemC(Material.ORANGE_DYE, "§6Or", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(13, GUIInterface.newItemC(Material.RED_DYE, "§cRouge", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(14, GUIInterface.newItemC(Material.PINK_DYE, "§dRose", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(15, GUIInterface.newItemC(Material.PURPLE_DYE, "§5Violet", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(20, GUIInterface.newItemC(Material.GREEN_DYE, "§2Vert foncé", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(21, GUIInterface.newItemC(Material.LIME_DYE, "§aVert clair", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(22, GUIInterface.newItemC(Material.BLUE_DYE, "§1Bleu foncé", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(23, GUIInterface.newItemC(Material.LIGHT_BLUE_DYE, "§9Bleu clair", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));
		inv.setItem(24, GUIInterface.newItemC(Material.GRAY_DYE, "§7Gris", 1,
				Arrays.asList("§9Cliquez pour sélectionner"), false));

		player.openInventory(inv);
	}

	public static void createTeamGUIAccess(Player player, TeamCommandCreateData data) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Choix de l'accès");
		Inventory inv = GUIInterface.startInv27(invB);

		inv.setItem(22,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour", 1, Arrays.asList("§cau menu principal"), false));

		inv.setItem(11,
				GUIInterface.newItemC(Material.IRON_DOOR, "§cPrivé", 1, Arrays.asList("§9Personne ne pourra rejoindre",
						"§9la team, seul un membre peut", "§9inviter un joueur", "§9", "§9Cliquez pour sélectionner"),
						false));
		inv.setItem(13,
				GUIInterface.newItemC(Material.SPRUCE_DOOR, "§eSur invitation", 1,
						Arrays.asList("§9Les joueurs devront postuler", "§9et un membre devra accepter",
								"§9pour qu'il rejoigne la team", "§9", "§9Cliquez pour sélectionner"),
						false));
		inv.setItem(15, GUIInterface.newItemC(Material.ACACIA_DOOR, "§aOuvert", 1,
				Arrays.asList("§9Tout le monde peut venir", "§9comme il veut", "§9", "§9Cliquez pour sélectionner"),
				false));

		((Player) player).openInventory(inv);
	}
	
	public static void createTeamGUIConfirm(Player player, TeamCommandCreateData data) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Confirmation");
		Inventory inv = GUIInterface.startInv27(invB);
		
		inv.setItem(12, GUIInterface.newItemC(Material.RED_CONCRETE, "§cRetour", 1, Arrays.asList("§cau menu principal"), false));
		inv.setItem(14,
				GUIInterface.newItemC(Material.GREEN_CONCRETE, "§aCréer la team", 1,
						Arrays.asList("§9Avec le nom §b" + data.TeamCommandCreateDataGetName() + "§9,",
								"§9le slogan §b" + data.TeamCommandCreateDataGetSlogan() + "§9,",
								"§9l'accès §b" + data.TeamCommandCreateDataGetAccess() + "§9,",
								"§9et la couleur §b" + data.TeamCommandCreateDataGetColor() + "§9.", "§9",
								"§c§lATTENTION, CELA COUTE", "§4§l10 000 §ceuros !"),
						true));
		
		player.openInventory(inv);
	}

}
