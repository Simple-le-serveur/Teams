package fr.simple.teams.commands;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.TeamData;
import fr.simple.teams.functionsGUI.GUIInterface;
import net.md_5.bungee.api.ChatColor;

public class TeamCommandControlGUI {

	public static void controlTeamGUIMain(Player player) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Contrôle de la team");
		Inventory inv = GUIInterface.startInv54(invB);

		inv.setItem(20, GUIInterface.newItemC(Material.PLAYER_HEAD, "§bGérer les utilisateurs", 1,
				Arrays.asList("§bde la team", "§9", "§9Cliquez pour ouvrir", "§9le menu"), false));
		inv.setItem(23, GUIInterface.newItemC(Material.DIAMOND_SWORD, "§bVoir le classement", 1,
				Arrays.asList("§bde la team", "§9", "§9Cliquez pour ouvrir", "§9le classement"), false));
		inv.setItem(30, GUIInterface.newItemC(Material.REPEATER, "§bReconfigurer la", 1,
				Arrays.asList("§bteam", "§9", "§9Cliquez pour ouvrir", "§9le menu"), false));
		inv.setItem(33, GUIInterface.newItemC(Material.GOLDEN_HOE, "§bDéfinir le claim", 1,
				Arrays.asList("§bde la team", "§9", "§9Cliquez pour commencer", "§9à claim"), false));

		inv.setItem(49, GUIInterface.newItemC(Material.REDSTONE, "§cQuitter", 1, Arrays.asList(), false));

		player.openInventory(inv);

	}

	public static void controlTeamGUIPlayersMain(Player player) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Contrôle des joueurs");
		Inventory inv = GUIInterface.startInv54(invB);

		List<String> playersTeam = TeamData.getAllPlayerFromTeam(TeamData.getPlayerTeam(player.getUniqueId()));
		for (int i = 0; i < playersTeam.size(); i++) {
		}

		int j = 10;
		for (int i = 0; i < playersTeam.size(); i++) {
			if (j == 17 || j == 26 || j == 35) {
				j = j + 2;
			}
			OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(playersTeam.get(i)));
			inv.setItem(j, GUIInterface.getOfflinePlayerSkull(p));
			j++;
		}

		inv.setItem(49,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour au menu principal", 1, Arrays.asList(), false));

		player.openInventory(inv);
	}

	public static void controlTeamGUIPlayersPlayer(Player player, String targetName) {
		Inventory invB = Bukkit.createInventory(null, 45, "§9Contrôle du joueur");
		Inventory inv = GUIInterface.startInv45(invB);

		inv.setItem(20, GUIInterface.newItemC(Material.BARRIER, "§cExpulser§c§l " + targetName, 1,
				Arrays.asList("§c", "§c§lAttention§c, cette action est", "§cirréversible !"), false));
		inv.setItem(24, GUIInterface.newItemC(Material.COMMAND_BLOCK, "§bGérer les permissions de§b§l " + targetName, 1,
				Arrays.asList(""), false));

		inv.setItem(40, GUIInterface.newItemC(Material.REDSTONE, "§cRetour au menu des Joueurs", 1,
				Arrays.asList("§cde la team"), false));

		player.openInventory(inv);
	}

	public static void controlTeamGUIPlayersConfirm(Player player, String targetName) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Confirmer l'expulsion");
		Inventory inv = GUIInterface.startInv27(invB);

		inv.setItem(12, GUIInterface.newItemC(Material.RED_CONCRETE, "§cAnnuler", 1,
				Arrays.asList("§9", "§9Retourner au menu", "§9principal"), false));
		inv.setItem(14, GUIInterface.newItemC(Material.GREEN_CONCRETE, "§aExpulser§a§l " + targetName, 1,
				Arrays.asList("§c", "§c§lAttention§c, cette action est", "§cirréversible !"), false));

		player.openInventory(inv);
	}

	public static void controlTeamGUIPlayersPremission(Player player, String targetName) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Permissions du joueur");
		Inventory inv = GUIInterface.startInv54(invB);

		for (int i = 10; i < 17; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.RED_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 19; i < 26; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.ORANGE_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 28; i < 35; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§9"));
		}
		for (int i = 37; i < 44; i++) {
			inv.setItem(i, GUIInterface.newItemS(Material.BLUE_STAINED_GLASS_PANE, "§9"));
		}

		inv.setItem(13, GUIInterface.newItemC(Material.RED_CONCRETE, "§cDéfinir §c§l" + targetName + " §cchef", 1,
				Arrays.asList("§9", "§9Cliquez pour définir", "§9le rang du membre", "§9sur §bchef"), false));
		inv.setItem(22, GUIInterface.newItemC(Material.ORANGE_CONCRETE, "§6Définir §6§l" + targetName + " §6sous chef",
				1, Arrays.asList("§9", "§9Cliquez pour définir", "§9le rang du membre", "§9sur §bsous chef"), false));
		inv.setItem(31,
				GUIInterface.newItemC(Material.LIGHT_BLUE_CONCRETE,
						"§9Définir §9§l" + targetName + " §9membre de confiance", 1, Arrays.asList("§9",
								"§9Cliquez pour définir", "§9le rang du membre", "§9sur §bmembre de confiance"),
						false));
		inv.setItem(40, GUIInterface.newItemC(Material.BLUE_CONCRETE, "§1Définir §1§l" + targetName + " §1membre", 1,
				Arrays.asList("§9", "§9Cliquez pour définir", "§9le rang du membre", "§9sur §bmembre"), false));

		inv.setItem(49,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour au menu précédent", 1, Arrays.asList(""), false));

		player.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public static void controlTeamGUIResetMain(Player player) {

		Inventory invB = Bukkit.createInventory(null, 54, "§9Modifier la team");
		Inventory inv = GUIInterface.startInv54(invB);

		inv.setItem(20,
				GUIInterface.newItemC(Material.NAME_TAG, "§bNom", 1,
						Arrays.asList("§9Actuel : §b" + TeamData.getPlayerTeam(player.getUniqueId()), "§cInchangeable"),
						false));
		inv.setItem(24,
				GUIInterface.newItemC(Material.PAPER, "§bSlogan", 1,
						Arrays.asList(
								"§9Actuel : §b" + TeamData.getSlogan(TeamData.getPlayerTeam(player.getUniqueId())),
								"§9Cliquez pour changer"),
						false));
		inv.setItem(30,
				GUIInterface.newItemC(Material.OAK_DOOR, "§bAccès", 1,
						Arrays.asList(
								"§9Actuel : §b" + TeamData.getAccess(TeamData.getPlayerTeam(player.getUniqueId())),
								"§9Cliquez pour changer"),
						false));
		inv.setItem(32,
				GUIInterface.newItemC(Material.WHITE_WOOL, "§bCouleur", 1,
						Arrays.asList(
								"§9Actuel : "
										+ ChatColor.valueOf(
												TeamData.getColor(TeamData.getPlayerTeam(player.getUniqueId())))
										+ TeamData.getColor(TeamData.getPlayerTeam(player.getUniqueId())),
								"§9Cliquez pour changer"),
						false));

		inv.setItem(49,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour", 1, Arrays.asList("§cau menu principal"), false));

		player.openInventory(inv);

	}

	public static void controlTeamGUIResetColor(Player player) {
		Inventory invB = Bukkit.createInventory(null, 36, "§9Changer la couleur de la team");
		Inventory inv = GUIInterface.startInv36(invB);

		inv.setItem(31,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour", 1, Arrays.asList("§cau menu précédent"), false));

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
	
	public static void ControlTeamGUIResetAccess(Player player) {
		Inventory invB = Bukkit.createInventory(null, 27, "§9Changer l'accès de la team");
		Inventory inv = GUIInterface.startInv27(invB);

		inv.setItem(22,
				GUIInterface.newItemC(Material.REDSTONE, "§cRetour", 1, Arrays.asList("§cau menu précédent"), false));

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

}
