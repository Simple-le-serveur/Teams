package fr.simple.teams.claims;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;

public class ClaimsManager implements Listener {

	private Teams teams;

	public ClaimsManager(Teams teams) {
		this.teams = teams;
	}

	public static List<Player> claimersList = new ArrayList<Player>();
	
	private static void end(GamePlayer gp, Player player) {
		gp.setPos1(null);
		gp.setPos2(null);
		claimersList.remove(player);
		if(player.getInventory().contains(Material.GOLDEN_HOE)) {
			player.getInventory().remove(Material.GOLDEN_HOE);
		}
		return;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		new GamePlayer(event.getPlayer().getName());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		for (int x = 0; x < Claims.claimsList.size(); x++) {
			if (Claims.claimsList.get(x).isInArea(event.getBlock().getLocation())) {
				if (Claims.claimsList.get(x).getName().equals(TeamData.getPlayerTeam(player.getUniqueId()))) {
					return;
				}
				event.setCancelled(true);
				player.sendMessage(teams.prefix + " §cCette zone est protégée par la Team "
						+ Claims.claimsList.get(x).getName() + ".");
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		for (int x = 0; x < Claims.claimsList.size(); x++) {
			if (Claims.claimsList.get(x).isInArea(event.getBlock().getLocation())) {
				if (Claims.claimsList.get(x).getName().equals(TeamData.getPlayerTeam(player.getUniqueId()))) {
					return;
				}
				event.setCancelled(true);
				player.sendMessage(teams.prefix + " §cCette zone est protégée par la Team "
						+ Claims.claimsList.get(x).getName() + ".");
			}
		}
	}

	@EventHandler
	public void onPVP(EntityDamageByEntityEvent event) {
		if (event.getDamager().getType() == EntityType.PLAYER) {
			for (int x = 0; x < Claims.claimsList.size(); x++) {
				if (Claims.claimsList.get(x).isInArea(event.getEntity().getLocation())) {
					if (Claims.claimsList.get(x).getName()
							.equals(TeamData.getPlayerTeam(event.getDamager().getUniqueId()))) {
						return;
					}
					event.setCancelled(true);
					event.getDamager().sendMessage(teams.prefix + " §cCette animal est protégé par la Team "
							+ Claims.claimsList.get(x).getName() + ".");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

			for (int x = 0; x < Claims.claimsList.size(); x++) {
				if (Claims.claimsList.get(x).isInArea(event.getClickedBlock().getLocation())) {
					if (Claims.claimsList.get(x).getName().equals(TeamData.getPlayerTeam(player.getUniqueId()))) {
						return;
					}
					event.setCancelled(true);
					player.sendMessage(teams.prefix + " §cCette zone est protégée par la Team "
							+ Claims.claimsList.get(x).getName() + ".");
					if (player.getItemInHand().getType() == Material.WATER_BUCKET
							|| player.getItemInHand().getType() == Material.LAVA_BUCKET) {

						player.sendMessage(teams.prefix + " §cCette zone est protégée par la Team "
								+ Claims.claimsList.get(x).getName() + ".");
					}
				}
			}

		}

		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

			if (event.getMaterial() == Material.GOLDEN_HOE) {
				for (int lol = 0; lol < claimersList.size(); lol++) {
					if (event.getPlayer().equals(claimersList.get(lol))) {
						event.setCancelled(true);
						GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());

						if (gp.getPos1() == null) {
							gp.setPos1(event.getClickedBlock().getLocation());
							player.sendMessage(teams.prefix + " §9Première position définie.");

							Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
								@Override
								public void run() {
									player.sendMessage(teams.prefix
											+ " §cVous avez attendu trop longtemps, votre premier point a été supprimé.");
									end(gp, player);
								}
							}, 20 * 60 * 5);
							return;

						}

						if (gp.getPos1() != null || gp.getPos2() == null) {
							gp.setPos2(event.getClickedBlock().getLocation());

							if (gp.getPos1().distance(gp.getPos2()) > 250) {
								player.sendMessage(
										teams.prefix + " §cRégion trop grande, veuilliez en définir une plus petite.");
								end(gp, player);
								return;
							}
							if (gp.getPos1().getWorld() != gp.getPos2().getWorld()) {
								player.sendMessage(
										teams.prefix + " §cVous ne pouvez pas créer de claims entre deux mondes ! ");
								end(gp, player);
								return;
							}

							RegionManager region = new RegionManager(gp.getPos1(), gp.getPos2(),
									TeamData.getPlayerTeam(player.getUniqueId()));

							for (int i = 0; i < Claims.claimsList.size(); i++) {
								RegionManager regionget = Claims.claimsList.get(i);

								if (regionget.isInArea(region.secondLoc) || regionget.isInArea(region.firstLoc)
										|| regionget.isInArea(region.getMiddle())) {
									if (regionget.getName().equals(TeamData.getPlayerTeam(player.getUniqueId()))) {
										player.sendMessage(teams.prefix + " §9Seconde position définie.");

										ClaimData.setClaim(TeamData.getPlayerTeam(player.getUniqueId()), gp.getPos1(),
												gp.getPos2());

										Claims.claimsList.add(region);

										player.sendMessage(teams.prefix + " §9Claim de la team §b"
												+ TeamData.getPlayerTeam(player.getUniqueId()) + " §9a été crée !");
										
										end(gp, player);
										return;
									}
									player.sendMessage(teams.prefix + " §cVous ne pouvez pas claim une zone appartenant déjà à la Team " + regionget.getName() + " !");
									end(gp, player);
									return;
								}

							}

							/*
							 * String[] loc = new String[] { "" + gp.getPos1().getX(), "" +
							 * gp.getPos1().getY(), "" + gp.getPos1().getZ(), "" + gp.getPos2().getX(), "" +
							 * gp.getPos2().getY(), "" + gp.getPos2().getZ(),
							 * player.getLocation().getWorld().getName(), player.getName() };
							 */

							// enregistrer le claim, et voir si il y a une api world Guard !!!

							player.sendMessage(teams.prefix + " §9Seconde position définie.");

							ClaimData.setClaim(TeamData.getPlayerTeam(player.getUniqueId()), gp.getPos1(),
									gp.getPos2());

							Claims.claimsList.add(region);

							player.sendMessage(teams.prefix + " §9Claim de la team §b"
									+ TeamData.getPlayerTeam(player.getUniqueId()) + " §9a été crée !");
							
							end(gp, player);
							return;

						}

					}

				}

			}

		}

	}

}
