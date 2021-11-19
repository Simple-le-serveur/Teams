package fr.simple.teams.luckyBlocks;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.simple.teams.Teams;

public class LuckyBlockBreak implements Listener {

	@SuppressWarnings("unused")
	private Teams teams;

	public LuckyBlockBreak(Teams teams) {
		this.teams = teams;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getState() instanceof Skull) {
			Skull skull = (Skull) event.getBlock().getState();
			try {
				if (skull.getOwner().equalsIgnoreCase("Luck")) {

					Player player = event.getPlayer();
					Block block = event.getBlock();
					BlockState bs = block.getState();

					event.setCancelled(true);
					block.setType(Material.AIR);

					Random random = new Random();
					int alea = random.nextInt(2);

					switch (alea) {
					
					case 0:
						
						break;
					case 1:
						//customcompass.setItemMeta(customM);
						//player.getInventory().setItem(4, customcompass);
						//player.updateInventory();
						ItemStack bow = new ItemStack(Material.BOW);
						ItemMeta customM = bow.getItemMeta();
						customM.setDisplayName("§aArc magique");
						customM.setLore(Arrays.asList("Cet arc est magique,", "il vous donne des pouvoirs"));
						bow.setItemMeta(customM);
						player.getInventory().setItem(4, bow);
						player.updateInventory();
						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

						break;
					case 5:

						break;
					case 6:

						break;
					case 7:

						break;
					case 8:

						break;
					case 9:

						break;
					case 10:

						break;
					case 11:

						break;
					case 12:

						break;
					case 13:

						break;
					case 14:

						break;
					case 15:

						break;
					case 16:

						break;
					case 17:

						break;
					case 18:

						break;
					case 19:

						break;
					case 20:

						break;

					default:

						break;
					}

				}
			} catch (NullPointerException e) {

			}
		}
	}

}
