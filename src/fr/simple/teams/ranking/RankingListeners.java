package fr.simple.teams.ranking;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;
import fr.simple.teams.functionsGUI.GUIInterface;

public class RankingListeners implements Listener {

	public static boolean isVoted = false;
	private Teams teams;

	public RankingListeners(Teams teams) {
		this.teams = teams;
	}

	@EventHandler
	public void onConnect(PlayerJoinEvent event) {
		if (isVoted == false && event.getPlayer().hasPermission("teams.vote")) {
			Player player = event.getPlayer();
			player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
			player.sendMessage("§9Vous pouvez §bvoter §9pour le classement des teams.");
			player.sendMessage("§9Tapez §b/team vote §9pour commencer.");
			player.sendMessage("§c§lATTENTION ! Si vous commencer, veillez a avoir le temps de terminer !");
		} else if (Vote.inVoting == event.getPlayer()) {
			Player player = event.getPlayer();
			player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
			player.sendMessage("§9Vous avez commencé a §bvoter §9pour le classement des teams.");
			player.sendMessage("§9Tapez §b/team vote §9pour continuer.");
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {

		if (event.getCurrentItem() == null) {
			return;
		}

		ItemStack current = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();

		if (event.getView().getTitle().equals("§9Classement")) {
			event.setCancelled(true);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 10, 10);
			if (current.getType() == Material.REDSTONE) {
				player.closeInventory();
			}
			if (current.getType() == Material.YELLOW_CONCRETE || current.getType() == Material.LIGHT_GRAY_CONCRETE
					|| current.getType() == Material.BROWN_CONCRETE || current.getType() == Material.WHITE_CONCRETE) {
				List<String> teams = TeamsSort.SortTeams();
				Bukkit.dispatchCommand(player, "team info " + teams.get(current.getAmount() - 1));
			}
		} else if (event.getView().getTitle().equals("§9Vote construction")) {
			if (player != Vote.inVoting) {
				player.closeInventory();
				player.sendMessage(teams.prefix + " §cVous n'avez rien a faire ici !!!");
			}
			VoteData data = VoteData.VoteData.get(player);
			event.setCancelled(true);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 10, 10);
			switch (current.getType()) {
			case BLACK_CONCRETE:
				data.setVoteBuild(VoteBuild.HORRIBLE);
				break;
			case RED_CONCRETE:
				data.setVoteBuild(VoteBuild.PAS_BIEN);
				break;
			case ORANGE_CONCRETE:
				data.setVoteBuild(VoteBuild.PASSABLE);
				break;
			case YELLOW_CONCRETE:
				data.setVoteBuild(VoteBuild.BIEN);
				break;
			case LIME_CONCRETE:
				data.setVoteBuild(VoteBuild.TRES_BIEN);
				break;
			case GREEN_CONCRETE:
				data.setVoteBuild(VoteBuild.TRES_BIEN);

				break;
			default:
				break;
			}

			data.setStage(Stage.VISIT_CHEST);
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

		} else if (event.getView().getTitle().contains("§9Vote - Richesses de la team §b")) {
			if (current.getType() == Material.GREEN_CONCRETE) {
				RankingGUIs.RankingVoteRessources(player);
				VoteData data = VoteData.VoteData.get(player);
				data.setStage(Stage.VOTE_CHEST);
			}
		} else if (event.getView().getTitle().equals("§9Vote ressources")) {
			if (player != Vote.inVoting) {
				player.closeInventory();
				player.sendMessage(teams.prefix + " §cVous n'avez rien a faire ici !!!");
			}
			VoteData data = VoteData.VoteData.get(player);
			event.setCancelled(true);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 10, 10);
			switch (current.getType()) {
			case BLACK_CONCRETE:
				data.setVoteChest(VoteChest.RIEN);
				break;
			case RED_CONCRETE:
				data.setVoteChest(VoteChest.PRESQUE_RIEN);
				break;
			case ORANGE_CONCRETE:
				data.setVoteChest(VoteChest.PASSABLE);
				break;
			case YELLOW_CONCRETE:
				data.setVoteChest(VoteChest.BIEN);
				break;
			case LIME_CONCRETE:
				data.setVoteChest(VoteChest.BEAUCOUP_DE_CHOSES);
				break;
			case GREEN_CONCRETE:
				data.setVoteChest(VoteChest.ENORMEMENT_DE_CHOSES);
				break;
			default:
				break;
			}
			data.setStage(Stage.VISIT_BUILD);
			Vote.next();
		}
	}
}
