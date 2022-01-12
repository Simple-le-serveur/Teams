package fr.simple.teams.assaut;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;
import fr.simple.teams.claims.ClaimData;

public class AssautListener implements Listener {

	private Teams teams;

	public AssautListener(Teams teams) {
		this.teams = teams;
	}

	public static List<Player> deathPlayer = new ArrayList<>();
	public static List<String> deathPlayerTeam = new ArrayList<>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {

		String playerTeam = TeamData.getPlayerTeam(event.getEntity().getUniqueId());
		if (!playerTeam.equals("null")) {

			List<String> teamsInFight = Assaut.teamsInFight2;
			for (int i = 0; i < teamsInFight.size(); i++) {

				String current = teamsInFight.get(i);
				String[] words = current.split("\\s+");
				String attaquants = words[1];
				String défenceurs = words[2];

				Player p = event.getEntity();

				try {
					if (playerTeam.equals(attaquants)) {
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (assaut.getPhase().equals("combat_final")) {
							List<Player> list = Assaut.alivePlayers;
							for (int j = 0; j < list.size(); j++) {
								if (list.get(j) == p) {
									Assaut.losers.add(p);
									Assaut.alivePlayers.remove(j);
									p.setGameMode(GameMode.SPECTATOR);
									p.sendMessage(teams.prefix
											+ " §bVous êtes mort !§9 Vous ne pouvez plus réaparaître. En attendant les résultats, vous pouvez assister à la fin du combat.");
									p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_DEATH, 10, 10);
									assaut.setKillsDéfenceurs(assaut.getKillsDéfenceurs() + 1);
									return;
								}
							}
							return;
						}
						assaut.setKillsDéfenceurs(assaut.getKillsDéfenceurs() + 1);
						deathPlayer.add(event.getEntity());
						deathPlayerTeam.add("attaquant");

					}
					if (playerTeam.equals(défenceurs)) {
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (assaut.getPhase().equals("combat_final")) {
							List<Player> list = Assaut.alivePlayers;
							for (int j = 0; j < list.size(); j++) {
								if (list.get(j) == p) {
									Assaut.losers.add(p);
									Assaut.alivePlayers.remove(j);
									p.setGameMode(GameMode.SPECTATOR);
									p.sendMessage(teams.prefix
											+ " §bVous êtes mort !§9 Vous ne pouvez plus réaparaître. En attendant les résultats, vous pouvez assister à la fin du combat.");
									p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_DEATH, 10, 10);
									assaut.setKillsAttaquants(assaut.getKillsAttaquants() + 1);
									return;
								}
							}
							return;
						}
						assaut.setKillsAttaquants(assaut.getKillsAttaquants() + 1);
						deathPlayer.add(event.getEntity());
						deathPlayerTeam.add("défenceur");
					}

				} catch (NullPointerException e) {
				}
			}

		}

	}

	@EventHandler
	public void onDisconect(PlayerQuitEvent event) {
		String playerTeam = TeamData.getPlayerTeam(event.getPlayer().getUniqueId());
		if (!playerTeam.equals("null")) {

			List<String> teamsInFight = Assaut.teamsInFight2;
			for (int i = 0; i < teamsInFight.size(); i++) {

				String current = teamsInFight.get(i);
				String[] words = current.split("\\s+");
				String attaquants = words[1];
				String défenceurs = words[2];

				Player p = event.getPlayer();

				try {
					if (playerTeam.equals(attaquants)) {
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (assaut.getPhase().equals("combat_final")) {
							List<Player> list = Assaut.alivePlayers;
							for (int j = 0; j < list.size(); j++) {
								if (list.get(j) == p) {
									Assaut.losers.add(p);
									Assaut.alivePlayers.remove(j);
									break;
								}
							}
						}
						assaut.setKillsDéfenceurs(assaut.getKillsDéfenceurs() + 1);
						return;

					} else if (playerTeam.equals(défenceurs)) {
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (assaut.getPhase().equals("combat_final")) {
							List<Player> list = Assaut.alivePlayers;
							for (int j = 0; j < list.size(); j++) {
								if (list.get(j) == p) {
									Assaut.losers.add(p);
									Assaut.alivePlayers.remove(j);
									break;
								}
							}
						}
						assaut.setKillsAttaquants(assaut.getKillsAttaquants() + 1);
						return;

					}

				} catch (NullPointerException e) {
				}
			}

		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		for (int i = 0; i < deathPlayer.size(); i++) {
			if (event.getPlayer() == deathPlayer.get(i)) {
				if (deathPlayerTeam.get(i).equals("attaquant")) {
					event.getPlayer().sendMessage(teams.prefix
							+ " §bVous êtes mort !§9 Prenez un nouveau stuff et tout ce dont vous avez besoin puis retournez au combat en tapant §b/team tpAttack§9.");
				} else {
					event.getPlayer().sendMessage(teams.prefix
							+ " §bVous êtes mort !§9 Prenez un nouveau stuff et tout ce dont vous avez besoin puis retournez au combat en tapant §b/team tpClaim§9.");
				}
				deathPlayer.remove(i);
				deathPlayerTeam.remove(i);
			}
		}
	}

	@EventHandler
	public void onPlayerConnection(PlayerJoinEvent event) {
		String playerTeam = TeamData.getPlayerTeam(event.getPlayer().getUniqueId());
		if (!playerTeam.equals("null")) {

			List<String> teamsInFight = Assaut.teamsInFight2;
			for (int i = 0; i < teamsInFight.size(); i++) {

				String current = teamsInFight.get(i);
				String[] words = current.split("\\s+");
				String attaquants = words[1];
				String défenceurs = words[2];

				if (playerTeam.equals(attaquants)) {
					try {
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (assaut.getPhase().equals("combat_final")) {
							event.getPlayer().setGameMode(GameMode.SPECTATOR);
							event.getPlayer().sendMessage(teams.prefix
									+ " §9Votre team est en train d'attaquer la team §b" + assaut.getDéfenceurs()
									+ " §9. Vous ne pouvez participer car c'est le combat final. En attendant les résultats, vous pouvez les regarder !");
							return;
						}
						if (assaut.getPhase().equals("préparation")) {
							Player player = event.getPlayer();
							player.sendMessage(teams.prefix + " §9Votre team est en assaut ! Ils attaquent la team §b"
									+ assaut.getDéfenceurs()
									+ " §9. Préparez votre équipement puis tapez §b/team tpAttack§9 pour aller au combat.");
							player.sendMessage("");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							player.sendMessage("§9 Attaquez le château de la team");
							player.sendMessage("§b" + assaut.getDéfenceurs() + "§9. Déjouez leur pièges, tuez-les");
							player.sendMessage("§9tous et cassez tout ! Cassez les §bLucky-Blocks§9 qui apparaissent");
							player.sendMessage("§9aléatoirement pour obtenir plein de choses cools (ou pas) qui");
							player.sendMessage("§9vous permettrons de gagner. Vous pouvez suivre l'avancement");
							player.sendMessage("§9de l'attaque en haut de votre écran. §bBonne chance !");
							player.sendMessage("§9");
							player.sendMessage("§bPhase de préparation§9 : apportez votre matériel, installez vos");
							player.sendMessage("§9cannons à TNT et cassez des Lucky Blocks !");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							BossBar timeBar = Assaut.timeBar();
							timeBar.addPlayer(player);
							timeBar.setVisible(true);
						} else if (assaut.getPhase().equals("attaque")) {
							Player player = event.getPlayer();
							player.sendMessage(teams.prefix + " §9Votre team est en assaut ! Ils attaquent la team §b"
									+ assaut.getDéfenceurs()
									+ " §9. Préparez votre équipement puis tapez §b/team tpAttack§9 pour aller au combat.");
							player.sendMessage("");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							player.sendMessage("§bC'est parti !§9 Attaquez le château de la team");
							player.sendMessage("§b" + assaut.getDéfenceurs() + "§9. Déjouez leur pièges, tuez-les");
							player.sendMessage("§9tous et cassez tout ! Cassez les §bLucky-Blocks§9 qui apparaissent");
							player.sendMessage("§9aléatoirement pour obtenir plein de choses cools (ou pas) qui");
							player.sendMessage("§9vous permettrons de gagner. Vous pouvez suivre l'avancement");
							player.sendMessage("§9de l'attaque en haut de votre écran. §bBonne chance !");
							player.sendMessage("§9");
							player.sendMessage("§bPhase d'attaque§9 : attaquez les défenceurs du château, cassez");
							player.sendMessage("§9tout, prenez leur coffre banque et usez de la ruse pour gagner !");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							BossBar timeBar = Assaut.timeBar();
							timeBar.addPlayer(player);
							timeBar.setVisible(true);
						} else {
							Player player = event.getPlayer();
							player.sendMessage(teams.prefix + " §9Votre team est en assaut ! Ils attaquent la team §b"
									+ assaut.getDéfenceurs()
									+ " §9. Vous ne pouvez les rejoindre, car ils sont en phase de §bcombat final§9 !");
						}
					} catch (NullPointerException e) {
						continue;
					}

				} else if (playerTeam.equals(défenceurs)) {
					try {
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (assaut.getPhase().equals("préparation")) {
							Player player = event.getPlayer();
							player.sendMessage(teams.prefix
									+ " §9Votre team est en assaut ! Vous êtes attaqués par la team §b"
									+ assaut.getAttaquants()
									+ " §9. Préparez votre équipement puis tapez §b/team tpClaim§9 pour aller au combat.");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							player.sendMessage("§bC'est parti !§9 Défendez votre château contre la team");
							player.sendMessage("§b" + assaut.getAttaquants() + "§9. Déjouez leur attaque");
							player.sendMessage("§9et tuez-les tous ! Cassez les §bLucky-Blocks§9 qui apparaissent");
							player.sendMessage("§9aléatoirement pour obtenir plein de choses cools (ou pas) qui");
							player.sendMessage("§9vous permettrons de gagner. Vous pouvez suivre l'avancement");
							player.sendMessage("§9de l'attaque en haut de votre écran. §bBonne chance !");
							player.sendMessage("§9");
							player.sendMessage("§bPhase de préparation§9 : préparez vos défences, mettez en place");
							player.sendMessage("§9une stratégie de défence et cassez des Lucky Blocks !");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							BossBar timeBar = Assaut.timeBar();
							timeBar.addPlayer(player);
							timeBar.setVisible(true);
						} else if (assaut.getPhase().equals("attaque")) {
							Player player = event.getPlayer();
							player.sendMessage(teams.prefix
									+ " §9Votre team est en assaut ! Vous êtes attaqués par la team §b"
									+ assaut.getAttaquants()
									+ " §9. Préparez votre équipement puis tapez §b/team tpClaim§9 pour aller au combat.");
							player.sendMessage("");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							player.sendMessage("§bC'est parti !§9 Défendez votre château contre la team");
							player.sendMessage("§b" + assaut.getAttaquants() + "§9. Déjouez leur attaque");
							player.sendMessage("§9et tuez-les tous ! Cassez les §bLucky-Blocks§9 qui apparaissent");
							player.sendMessage("§9aléatoirement pour obtenir plein de choses cools (ou pas) qui");
							player.sendMessage("§9vous permettrons de gagner. Vous pouvez suivre l'avancement");
							player.sendMessage("§9de l'attaque en haut de votre écran. §bBonne chance !");
							player.sendMessage("§9");
							player.sendMessage("§bPhase d'attaque§9 : repoussez l'envahiseur, contrez leur attaques");
							player.sendMessage(
									"§9et empêchez-les de casser votre château et de voler votre coffre banque !");
							player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
							BossBar timeBar = Assaut.timeBar();
							timeBar.addPlayer(player);
							timeBar.setVisible(true);
						} else {
							Player player = event.getPlayer();
							player.sendMessage(teams.prefix + " §9Votre team est en assaut ! Ils attaquent la team §b"
									+ assaut.getDéfenceurs()
									+ " §9. Vous ne pouvez les rejoindre, car ils sont en phase de §bcombat final§9 !");
						}
					} catch (NullPointerException e) {
						continue;
					}

				}

			}

		}
	}

	@EventHandler
	public void onPvp(EntityDamageByEntityEvent event) {

		if (event.getEntity() instanceof Player) {
			String playerTeam = TeamData.getPlayerTeam(event.getEntity().getUniqueId());

			List<String> teamsInFight = Assaut.teamsInFight2;
			for (int i = 0; i < teamsInFight.size(); i++) {

				String current = teamsInFight.get(i);
				String[] words = current.split("\\s+");
				String attaquants = words[1];
				String défenceurs = words[2];

				if (playerTeam.equals(attaquants) || playerTeam.equals(défenceurs)) {

					if (event.getDamager().getType() == EntityType.PLAYER) {
						Player damager = (Player) event.getDamager();
						String damagerTeam = TeamData.getPlayerTeam(damager.getUniqueId());
						if (damagerTeam.equals(attaquants) || damagerTeam.equals(défenceurs)) {
							if (playerTeam.equals(damagerTeam)) {
								event.setCancelled(true);
								return;
							} else {
								try {
									AssautData assaut = AssautData.AssautData.get(attaquants);
									if (assaut.getPhase().equals("préparation")) {
										event.setCancelled(true);
										event.getDamager().sendMessage(teams.prefix
												+ " §cVous ne pouvez pas attaquer les joueurs lors de la phase de préparation !");
										return;
									}
								} catch (NullPointerException e) {

								}
							}
						}
					} else if (event.getCause().equals(DamageCause.PROJECTILE)) {
						Projectile proj = (Projectile) event.getDamager();
						if (proj.getShooter() instanceof Player) {
							Player damager = (Player) proj.getShooter();
							String damagerTeam = TeamData.getPlayerTeam(damager.getUniqueId());
							if (damagerTeam.equals(attaquants) || damagerTeam.equals(défenceurs)) {
								if (playerTeam.equals(damagerTeam)) {
									event.setCancelled(true);
									return;
								} else {
									AssautData assaut = AssautData.AssautData.get(attaquants);
									if (assaut.getPhase().equals("préparation")) {
										event.setCancelled(true);
										event.getDamager().sendMessage(teams.prefix
												+ " §cVous ne pouvez pas attaquer les joueurs lors de la phase de préparation !");
										return;
									}
								}
							}
						} else {
							return;
						}

					} else {
						return;
					}

					if (event.getDamager() instanceof Player) {
						String damagerTeam = TeamData.getPlayerTeam(event.getDamager().getUniqueId());
						AssautData assaut = AssautData.AssautData.get(attaquants);
						if (playerTeam.equals(damagerTeam)) {
							event.setCancelled(true);
							return;
						}
						if (assaut.getPhase().equals("préparation")) {
							if (event.getDamager() instanceof Player) {
								if (damagerTeam.equals(attaquants) || damagerTeam.equals(défenceurs)) {
									event.setCancelled(true);
									event.getDamager().sendMessage(teams.prefix
											+ " §cVous ne pouvez pas attaquer un joueur lors de la phase de préparation !");
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		for (int i = 0; i < Assaut.losers.size(); i++) {
			String attaquants = "testtest";
			String défenceurs = "testtest";
			if (event.getPlayer() == Assaut.losers.get(i)) {
				List<String> teamsInFight = Assaut.teamsInFight2;
				for (int j = 0; j < teamsInFight.size(); j++) {
					String current = teamsInFight.get(j);
					String pAttaquants = current.split("\\s+")[0];
					String pDéfenceurs = current.split("\\s+")[1];
					if (TeamData.getPlayerTeam(event.getPlayer().getUniqueId()).equals(pAttaquants)
							|| TeamData.getPlayerTeam(event.getPlayer().getUniqueId()).equals(pDéfenceurs)) {
						attaquants = current.split("\\s+")[1];
						défenceurs = current.split("\\s+")[2];
					}
				}
			}
			if (TeamData.getPlayerTeam(event.getPlayer().getUniqueId()).equals(attaquants)
					|| TeamData.getPlayerTeam(event.getPlayer().getUniqueId()).equals(défenceurs)) {
				Location claimB1 = ClaimData.getFirstLoc(défenceurs);
				Location claimB2 = ClaimData.getSecondLoc(défenceurs);
				double xMin = Math.min(claimB1.getX(), claimB2.getX()), xMax = Math.max(claimB1.getX(), claimB2.getX());
				double zMin = Math.min(claimB1.getZ(), claimB2.getZ()), zMax = Math.max(claimB1.getZ(), claimB2.getZ());
				if (event.getPlayer().getLocation().getX() < xMin - 50) { /// zone alentoure + pas de joueur bloqué
					if (event.getPlayer().getLocation().getX() < xMin - 56) {
						event.getPlayer().teleport(AssautFunctions.tpZoneClaim(claimB1, claimB2));
						event.getPlayer().sendMessage(teams.prefix
								+ " §cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
								10);
						return;
					}
					Location locToTp = event.getPlayer().getLocation();
					locToTp.setX(event.getPlayer().getLocation().getX() + 3);
					event.getPlayer().teleport(locToTp);
					event.getPlayer().sendMessage(teams.prefix
							+ " §cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
							10);
				} else if (event.getPlayer().getLocation().getZ() < zMin - 50) {
					if (event.getPlayer().getLocation().getZ() < zMin - 56) {
						event.getPlayer().teleport(AssautFunctions.tpZoneClaim(claimB1, claimB2));
						event.getPlayer().sendMessage(teams.prefix
								+ " §cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
								10);
						return;
					}
					Location locToTp = event.getPlayer().getLocation();
					locToTp.setZ(event.getPlayer().getLocation().getZ() + 3);
					event.getPlayer().teleport(locToTp);
					event.getPlayer().sendMessage(teams.prefix
							+ "§cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
							10);
				} else if (event.getPlayer().getLocation().getX() > xMax + 50) {
					if (event.getPlayer().getLocation().getX() > xMax + 56) {
						event.getPlayer().teleport(AssautFunctions.tpZoneClaim(claimB1, claimB2));
						event.getPlayer().sendMessage(teams.prefix
								+ " §cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
								10);
						return;
					}
					Location locToTp = event.getPlayer().getLocation();
					locToTp.setX(event.getPlayer().getLocation().getX() - 3);
					event.getPlayer().teleport(locToTp);
					event.getPlayer().sendMessage(teams.prefix
							+ "§cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
							10);
				} else if (event.getPlayer().getLocation().getZ() > zMax + 50) {
					if (event.getPlayer().getLocation().getZ() > zMax + 56) {
						event.getPlayer().teleport(AssautFunctions.tpZoneClaim(claimB1, claimB2));
						event.getPlayer().sendMessage(teams.prefix
								+ " §cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
								10);
						return;
					}
					Location locToTp = event.getPlayer().getLocation();
					locToTp.setZ(event.getPlayer().getLocation().getZ() - 3);
					event.getPlayer().teleport(locToTp);
					event.getPlayer().sendMessage(teams.prefix
							+ "§cVous ne pouvez pas partir ! Attendez que tout le monde soit mort pour voir si vous avez gagné !");
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 10,
							10);
				}
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		if (event.getBlock().getType() == Material.CHEST) {
			Block block = event.getBlock();
			try {
				if (((Chest) block.getState()).getCustomName().startsWith("banque")) {

					String playerTeam = TeamData.getPlayerTeam(event.getPlayer().getUniqueId());
					if (!playerTeam.equals("null")) {
						List<String> teamsInFight = Assaut.teamsInFight2;
						for (int i = 0; i < teamsInFight.size(); i++) {

							String current = teamsInFight.get(i);
							String[] words = current.split("\\s+");
							String attaquants = words[0];
							String défenceurs = words[1];

							if (playerTeam.equals(attaquants)) {
								try {
									AssautData assaut = AssautData.AssautData.get(attaquants);
									if (assaut.getPhase().equals("préparation")) {
										event.getPlayer().sendMessage(teams.prefix
												+ " §cVous ne pouvez pas casser le coffre banque lors de la phase de préparation !");
										return;
									}
									assaut.setBankChestIsBreaked(true);
									TeamData.setBankChest(assaut.getDéfenceurs(), null);
									event.getPlayer().sendMessage(teams.prefix
											+ " §bFélicitations !§9 Vous venez de casser le coffre banque de la team §b"
											+ assaut.getAttaquants()
											+ " §9! Vous avez désormais beaucoup de chances de gagner.");
									List<String> pA = TeamData.getAllPlayerFromTeam(assaut.getAttaquants());
									List<String> pD = TeamData.getAllPlayerFromTeam(assaut.getDéfenceurs());
									for (int j = 0; j < pA.size(); j++) {
										Player currentPlayer = Bukkit.getPlayer(UUID.fromString(pA.get(j)));
										currentPlayer.playSound(currentPlayer.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
										currentPlayer.sendTitle("§bCoffre banque cassé",
												"§9par §b" + event.getPlayer().getName(), 0, 3 * 20, 1 * 20);
										currentPlayer.sendMessage(teams.prefix + " §b" + event.getPlayer().getName()
												+ " §9a cassé le coffre banque des défenceurs !");
									}
									for (int j = 0; j < pD.size(); j++) {
										Player currentPlayer = Bukkit.getPlayer(UUID.fromString(pA.get(j)));
										currentPlayer.playSound(currentPlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 10, 1);
										currentPlayer.sendTitle("§bCoffre banque cassé",
												"§9par §b" + event.getPlayer().getName(), 0, 3 * 20, 1 * 20);
										currentPlayer.sendMessage(teams.prefix + " §b" + event.getPlayer().getName()
												+ " §9a cassé le coffre banque des défenceurs !");
									}
									return;
								} catch (NullPointerException e) {
									continue;
								}

							} else if (playerTeam.equals(défenceurs)) {
								event.getPlayer().sendMessage(
										teams.prefix + " §cNe cassez pas votre coffre de team ! Vous allez perdre !");
								event.setCancelled(true);
								return;
							}

						}

					}

				}

			} catch (NullPointerException e) {
				return;
			}

		}

	}

}
