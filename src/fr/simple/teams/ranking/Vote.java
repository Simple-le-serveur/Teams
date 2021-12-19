package fr.simple.teams.ranking;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.simple.teams.TeamData;
import fr.simple.teams.assaut.AssautFunctions;
import fr.simple.teams.claims.ClaimData;
import fr.simple.teams.functionsGUI.GUIInterface;

public class Vote {

	public static Player inVoting = null;
	private static List<String> Teams = null;
	public static VoteData data1 = null;

	public static void playerVote(Player player) {

		player.sendMessage("§eSimplebot §8» §bC'est parti pour le vote !");

		RankingListeners.isVoted = true;
		inVoting = player;
		//player.setGameMode(GameMode.SPECTATOR);
		Teams = TeamData.getAllTeams();
		Location locToTp = AssautFunctions.tpZoneClaim(ClaimData.getFirstLoc(Teams.get(0)),
				ClaimData.getSecondLoc(Teams.get(0)));
		player.teleport(locToTp);
		player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
		player.sendMessage("§9Une fois que vous avez vu le repère, vous devez voter. pour cela, tapez §b/team vote§9.");
		VoteData data = new VoteData(player);
		data.setPlayer(player);
		data.setStage(Stage.VISIT_BUILD);
		data.setCurrentTeamName(Teams.get(0));
		data1 = data;

	}

	public static void next() {
		Player player = inVoting;
		VoteData data = Vote.data1;

		if (canVote(data.getCurrentTeamName())) {
			float ptPerAssaut = 0;
			float ptsAssauts = 0;
			if (TeamData.getAttacksNumber(data.getCurrentTeamName()) != 0) {
				ptPerAssaut = 8 / TeamData.getAttacksNumber(data.getCurrentTeamName());
				ptsAssauts = TeamData.getAttacksVictoryNumber(data.getCurrentTeamName()) * ptPerAssaut;
			}
			float pts = ptsAssauts;

			switch (data.getVoteBuild()) {
			case HORRIBLE:
				pts = pts + 1;
				break;
			case PAS_BIEN:
				pts = pts + 2;
				break;
			case PASSABLE:
				pts = pts + 3;
				break;
			case BIEN:
				pts = pts + 4;
				break;
			case TRES_BIEN:
				pts = pts + 5;
				break;
			case INCROYABLE:
				pts = pts + 6;
				break;
			default:
				break;
			}

			switch (data.getVoteChest()) {
			case RIEN:
				pts = pts + 1;
				break;
			case PRESQUE_RIEN:
				pts = pts + 2;
				break;
			case PASSABLE:
				pts = pts + 3;
				break;
			case BIEN:
				pts = pts + 4;
				break;
			case BEAUCOUP_DE_CHOSES:
				pts = pts + 5;
				break;
			case ENORMEMENT_DE_CHOSES:
				pts = pts + 6;
				break;
			default:
				break;
			}

			TeamData.setTeamNotation(data.getCurrentTeamName(), Math.round(pts));
		}

		data.setCurrentTeam(data.getCurrentTeam() + 1);
		try {
			data.setCurrentTeamName(Teams.get(data.getCurrentTeam()));
			if (!canVote(data.getCurrentTeamName())) {
				try {
					next();
				} catch (NullPointerException ee) {
				}
				return;
			}
			Location locToTp = AssautFunctions.tpZoneClaim(ClaimData.getFirstLoc(Teams.get(0)),
					ClaimData.getSecondLoc(Teams.get(0)));
			player.teleport(locToTp);
			player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
			player.sendMessage(
					"§9Une fois que vous avez vu le repère, vous devez voter. pour cela, tapez §b/team vote§9.");
		} catch (IndexOutOfBoundsException e) {
			player.setGameMode(GameMode.CREATIVE);
			player.closeInventory();
			player.sendMessage("§eSimplebot §8» §9Vous avez §bterminé §9de voter. Merci !");
			inVoting = null;
			Bukkit.getConsoleSender().sendMessage("Vote effectué par " + inVoting.getName() + ".");
			Bukkit.broadcastMessage(
					"§eSimplebot §8» §9Le vote de la semaine a été fait. Le classement des teams a été mis a jour.");
			data1 = null;
		}

	}

	@SuppressWarnings("unused")
	public static boolean canVote(String team) {
		try {
			Location tryLoc = AssautFunctions.tpZoneClaim(ClaimData.getFirstLoc(team), ClaimData.getSecondLoc(team));
			Location location = TeamData.getBankChest(team);
			Block block = location.getBlock();
			Chest chest = (Chest) block.getState();
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static void reOpen(Player player) {
		try {
			if (Vote.inVoting.getName().equals(player.getName())) {

				VoteData data = data1;
				Vote.inVoting = player;
				
				if (data.getStage() == Stage.VISIT_BUILD) {

					RankingGUIs.RankingVoteBuild(player);
					data.setStage(Stage.VOTE_BUILD);
				} else if (data.getStage() == Stage.VOTE_BUILD) {
					RankingGUIs.RankingVoteBuild(player);
				} else if (data.getStage() == Stage.VISIT_CHEST || data.getStage() == Stage.VOTE_CHEST) {
					Location location = TeamData.getBankChest(data.getCurrentTeamName());
					Block block = location.getBlock();
					Chest chest = (Chest) block.getState();
					Inventory inve = chest.getInventory();
					Inventory inv = Bukkit.createInventory(null, 36,
							"§9Vote - Richesses de la team §b" + data.getCurrentTeamName());
					for (int i = 0; i < 27; i++) {
						inv.setItem(i, inve.getItem(i));
					}
					inv.setItem(31, GUIInterface.newItemS(Material.GREEN_CONCRETE, "§9Voter"));
					player.openInventory(inv);
				}
			} else {
				player.sendMessage(
						"§eSimplebot §8» §cLe vote a déjà été fait. Attendez lundi prochain pour le prochain vote.");
			}
		} catch (NullPointerException e) {
			player.sendMessage(
					"§eSimplebot §8» §cLe vote a déjà été fait. Attendez lundi prochain pour le prochain vote.");
		}
	}

}
