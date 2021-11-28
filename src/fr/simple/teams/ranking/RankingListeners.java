package fr.simple.teams.ranking;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.simple.teams.Teams;

public class RankingListeners implements Listener {
	
	public static boolean isVoted = true;

	@SuppressWarnings("unused")
	private Teams teams;

	public RankingListeners(Teams teams) {
		this.teams = teams;
	}
	
	@EventHandler
	public void onConnect(PlayerJoinEvent event) {
		if(isVoted == false && event.getPlayer().hasPermission("teams.vote")) {
			Player player = event.getPlayer();
			player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
			player.sendMessage("§9Vous pouvez §bvoter §9pour le classement des teams.");
			player.sendMessage("§9Tapez §b/team vote §9pour commencer.");
			player.sendMessage("§c§lATTENTION ! Si vous commencer, veillez a avoir le temps de terminer !");
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
		}
	}
}
