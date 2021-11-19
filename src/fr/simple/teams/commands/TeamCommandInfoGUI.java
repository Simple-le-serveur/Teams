package fr.simple.teams.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.TeamData;
import fr.simple.teams.functionsGUI.GUIInterface;

public class TeamCommandInfoGUI {

	public static void teamInfoGUIMain(Player player, String team) {
		Inventory invB = Bukkit.createInventory(null, 54, "§9Info sur la team");
		Inventory inv = GUIInterface.startInv54(invB);
		
		String state = null;
		if(TeamData.getAccess(team).equals("OPEN")) {
			state = "§brejoindre la team " + team;
		} else if(TeamData.getAccess(team).equals("ON_INVITATION")) {
			state = "§bpostuler pour rejoindre la team " + team;
		} else {
			state = "§cLa team est privée";
		}

		inv.setItem(20, GUIInterface.newItemC(Material.DIAMOND_SWORD, "§bClassement de la team " + team, 1,
				Arrays.asList("§9Cliquez pour voir le classement", "§9de la team par rapport aux autres", "§9teams."), false));
		inv.setItem(22, GUIInterface.newItemC(Material.TNT, "§bEnvoyer une demande d'assaut à la team " + team, 1, Arrays.asList("§9Cliquez pour demander un assaut à la", "§9team §b" + team + "§9."), false));
		inv.setItem(24, GUIInterface.newItemC(Material.PAPER, "§bSlogan", 1, Arrays.asList("§9","§b" + TeamData.getSlogan(team)), false));
		inv.setItem(29, GUIInterface.newItemC(Material.CHEST, "§bRichesses de la team " + team, 1, Arrays.asList("§9Cliquez pour voir le coffre", "§9des richesses de la team"), false));
		inv.setItem(31, GUIInterface.newItemC(Material.BOOK, state, 1, Arrays.asList(), false));
		inv.setItem(33, GUIInterface.newItemC(Material.OAK_DOOR, "§bAccès", 1, Arrays.asList("§9","§b" + TeamData.getAccess(team)), false));
		
		inv.setItem(49, GUIInterface.newItemC(Material.REDSTONE, "§cQuitter", 1, Arrays.asList(), false));
		
		player.openInventory(inv);
	}
	
}
