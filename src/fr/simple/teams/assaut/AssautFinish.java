package fr.simple.teams.assaut;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;
import fr.simple.teams.claims.ClaimData;

public class AssautFinish {

	@SuppressWarnings("unused")
	private Teams teams;

	@SuppressWarnings({ "deprecation", "unused" })
	public AssautFinish(AssautData assaut, Teams teams) {
		
		System.out.println("1 §ePoints attaquants : " + assaut.getPtA() + " ; Points défenceurs : " + assaut.getPtA());

		this.teams = teams;

		List<String> attaquantPlayers = TeamData.getAllPlayerFromTeam(assaut.getAttaquants());
		List<String> défenceursPlayers = TeamData.getAllPlayerFromTeam(assaut.getDéfenceurs());

		Assaut.alivePlayers.clear();
		System.out.println("Taille de la liste des losers : " + Assaut.losers.size());

		// tp des joueurs
		Location locToTp = new Location(Bukkit.getWorld("world"), 55, 103.1, 106);
		for (int p = 0; p < attaquantPlayers.size(); p++) {
			try {
				Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
				Bukkit.getConsoleSender().sendMessage("Joueur actuel : " + current.getName());
				for (int i = 0; i < Assaut.losers.size(); i++) {
					Bukkit.getConsoleSender().sendMessage("Joueur actuel : " + Assaut.losers.get(i).getName());
					if (Assaut.losers.get(i) == current) {
						Assaut.losers.remove(i);
					}
				}
				current.setGameMode(GameMode.SURVIVAL);
				current.teleport(locToTp);
				current.sendTitle("§bScan en cours...", "§9Nous annalysons le champ de bataille", 20 * 1, 20 * 3, 20 * 1);
				current.playSound(current.getLocation(), Sound.ENTITY_TNT_PRIMED, 10, 1);
				current.playSound(current.getLocation(), Sound.BLOCK_PISTON_EXTEND, 10, 1);
			} catch (NullPointerException e) {
				continue;
			}
		}

		for (int p = 0; p < défenceursPlayers.size(); p++) {
			try {
				Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
				for (int i = 0; i < Assaut.losers.size(); i++) {
					if (Assaut.losers.get(i) == current) {
						Assaut.losers.remove(i);
					}
				}
				current.setGameMode(GameMode.SURVIVAL);
				current.teleport(locToTp);
				current.sendTitle("§bScan en cours...", "§9Nous annalysons le champ de bataille", 20 * 1, 20 * 3, 20 * 1);
				current.playSound(current.getLocation(), Sound.ENTITY_TNT_PRIMED, 10, 1);
				current.playSound(current.getLocation(), Sound.BLOCK_PISTON_EXTEND, 10, 1);
			} catch (NullPointerException e) {
				continue;
			}
		}
		
		System.out.println("2 §ePoints attaquants : " + assaut.getPtA() + " ; Points défenceurs : " + assaut.getPtA());

		// scan de la map
		Location claimB1 = ClaimData.getFirstLoc(assaut.getDéfenceurs());
		Location claimB2 = ClaimData.getSecondLoc(assaut.getDéfenceurs());
		double xMin = Math.min(claimB1.getX(), claimB2.getX()) - 50,
				xMax = Math.max(claimB1.getX(), claimB2.getX()) + 50;
		double zMin = Math.min(claimB1.getZ(), claimB2.getZ()) - 50,
				zMax = Math.max(claimB1.getZ(), claimB2.getZ()) + 50;

		int BC = 0;
		int LCR = 0;

		for (int y = 0; y < 256; y++) {
			for (int x = (int) (xMin); x < xMax; x++) {
				for (int z = (int) (zMin); z < (zMax); z++) {
					if (new Location(claimB1.getWorld(), x, y, z).getBlock().getType() != Material.AIR) {
						BC++;
						if (new Location(claimB1.getWorld(), x, y, z).getBlock().getState() instanceof Skull) {
							Skull skull = (Skull) new Location(claimB1.getWorld(), x, y, z).getBlock().getState();
							try {
								if (skull.getOwner().equalsIgnoreCase("Luck")) {
									Block block = new Location(claimB1.getWorld(), x, y, z).getBlock();
									block.setType(Material.AIR);
									LCR++;
									BC--;
								}
							} catch (NullPointerException e) {
							}
						}
					}
				}
			}
		}

		// calcul des résultats
		float pointsAttaquants = 0;
		float pointsDéfenceurs = 0;

		// calcul des kills
		final float totalKills = assaut.getKillsAttaquants() + assaut.getKillsDéfenceurs();
		Bukkit.getConsoleSender().sendMessage("Kills totaux : " + totalKills);
		float ptPerKill = 6 / totalKills;
		Bukkit.getConsoleSender().sendMessage("Point par kill : " + ptPerKill);
		pointsAttaquants = pointsAttaquants + assaut.getKillsAttaquants() * ptPerKill;
		Bukkit.getConsoleSender().sendMessage("Points attaquants : " + pointsAttaquants);
		pointsDéfenceurs = pointsDéfenceurs + assaut.getKillsDéfenceurs() * ptPerKill;
		Bukkit.getConsoleSender().sendMessage("Points défenceurs : " + pointsDéfenceurs);

		// calcul de la destruction
		float pBreak = 100 * BC / assaut.getBlocks();
		pBreak = 100 - pBreak;
		Bukkit.getConsoleSender().sendMessage("Pourcentage de blocks cassés : " + pBreak + "%");
		float ptPerPercent = 6 / pBreak;
		Bukkit.getConsoleSender().sendMessage("Point par pourcent de destruction : " + ptPerPercent);
		pointsAttaquants = pointsAttaquants + pBreak * ptPerPercent;
		Bukkit.getConsoleSender().sendMessage("Points attaquants : " + pBreak * ptPerPercent);
		pointsDéfenceurs = pointsDéfenceurs + (100 - pBreak) * ptPerPercent;
		Bukkit.getConsoleSender().sendMessage("Points défenceurs : " + (100 - pBreak) * ptPerPercent);

		// calcul de la banque cassée
		if (assaut.isBankChestIsBreaked()) {
			pointsAttaquants = pointsAttaquants + 8;
			Bukkit.getConsoleSender().sendMessage("Coffre cassé");
		} else {
			pointsDéfenceurs = pointsDéfenceurs + 8;
			Bukkit.getConsoleSender().sendMessage("Coffre non cassé");
		}

		final float PercentageBreaked = pBreak;
		final float ptsAtt = pointsAttaquants;
		final float ptsDef = pointsDéfenceurs;

		if (ptsAtt > ptsDef) {
			assaut.setWinner("attaquants");
			assaut.setWinnerTeam(assaut.getAttaquants());
			Bukkit.getConsoleSender().sendMessage("Gagnants : attaquants");
		} else if (ptsAtt < ptsDef) {
			assaut.setWinner("défenceurs");
			assaut.setWinnerTeam(assaut.getDéfenceurs());
			Bukkit.getConsoleSender().sendMessage("Gagnants : défenceurs");
		} else {
			assaut.setWinner("égalité !");
			assaut.setWinnerTeam("égalité !");
			Bukkit.getConsoleSender().sendMessage("Gagnant : les deux teams");
		}

		// scan terminé
		for (int p = 0; p < attaquantPlayers.size(); p++) {
			try {
				Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
				current.sendTitle("§bScan terminé !", "§9Maintenant, place aux résultats", 20 * 1, 20 * 3, 20 * 1);
				current.playSound(current.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
			} catch (NullPointerException e) {
				continue;
			}
		}

		for (int p = 0; p < défenceursPlayers.size(); p++) {
			try {
				Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
				current.sendTitle("§bScan terminé !", "§9Maintenant, place aux résultats", 20 * 1, 20 * 3, 20 * 1);
				current.playSound(current.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
			} catch (NullPointerException e) {
				continue;
			}
		}

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
						if (assaut.isBankChestIsBreaked()) {
							current.sendTitle("§bCoffre de banque :", "§9Etat : §bcassé", 20 * 1, 20 * 3, 20 * 1);
						} else {
							current.sendTitle("§bCoffre de banque :", "§9Etat : §bnon cassé", 20 * 1, 20 * 3, 20 * 1);
						}
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						if (assaut.isBankChestIsBreaked()) {
							current.sendTitle("§bCoffre de banque :", "§9Etat : §bcassé", 20 * 1, 20 * 3, 20 * 1);
						} else {
							current.sendTitle("§bCoffre de banque :", "§9Etat : §bnon cassé", 20 * 1, 20 * 3, 20 * 1);
						}
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}, 20 * 5L);

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
						current.sendTitle("§bKills :", "§9Attaquants : §b" + assaut.getKillsAttaquants()
								+ " §9; défenceurs : §b" + assaut.getKillsDéfenceurs(), 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						current.sendTitle("§bKills :", "§9Attaquants : §b" + assaut.getKillsAttaquants()
								+ " §9; défenceurs : §b" + assaut.getKillsDéfenceurs(), 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}, 20 * 10L);

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
						current.sendTitle("§bDestruction : ",
								"§9Pourcentage de destruction : §b" + PercentageBreaked + "%", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						current.sendTitle("§bDestruction : ",
								"§9Pourcentage de destruction : §b" + PercentageBreaked + "%", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}, 20 * 15L);

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
						current.sendTitle("§9Avec un total de : §b" + Math.max(ptsAtt, ptsDef) + "§9,",
								"§9le dagnant est ...", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						current.sendTitle("§9Avec un total de : §b" + Math.max(ptsAtt, ptsDef) + "§9,",
								"§9le dagnant est ...", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}, 20 * 20L);

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
						current.sendTitle("§b" + assaut.getWinnerTeam(), "§9Bravo !", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						current.sendTitle("§b" + assaut.getWinnerTeam(), "§9Bravo !", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}, 20 * 25L);

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					if (assaut.getWinner().equals("attaquants")) {
						try {
							Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
							current.sendTitle(
									"§Les défenceurs ont eu : §b" + Math.min(ptsAtt, ptsDef), "§9Il leur manquait §b"
											+ (assaut.getPtA() - assaut.getPtD() + 1) + " §9pour gagner...",
									20 * 1, 20 * 3, 20 * 1);
							current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
						} catch (NullPointerException e) {
							continue;
						}
					} else {
						try {
							Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
							current.sendTitle(
									"§Les attaquants ont eu : §b" + Math.min(ptsAtt, ptsDef), "§9Il leur manquait §b"
											+ (assaut.getPtD() - assaut.getPtA() + 1) + " §9pour gagner...",
									20 * 1, 20 * 3, 20 * 1);
							current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
						} catch (NullPointerException e) {
							continue;
						}
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					if (assaut.getWinner().equals("attaquants")) {
						try {
							Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
							current.sendTitle(
									"§Les défenceurs ont eu : §b" + Math.min(ptsAtt, ptsDef), "§9Il leur manquait §b"
											+ (assaut.getPtA() - assaut.getPtD() + 1) + " §9pour gagner...",
									20 * 1, 20 * 3, 20 * 1);
							current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
						} catch (NullPointerException e) {
							continue;
						}
					} else {
						try {
							Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
							current.sendTitle(
									"§9Les attaquants ont eu : §b" + Math.min(ptsAtt, ptsDef), "§9Il leur manquait §b"
											+ (assaut.getPtD() - assaut.getPtA() + 1) + " §9pour gagner...",
									20 * 1, 20 * 3, 20 * 1);
							current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
						} catch (NullPointerException e) {
							continue;
						}
					}
				}
			}
		}, 20 * 30L);

		Bukkit.getScheduler().runTaskLater(teams, new Runnable() {
			@Override
			public void run() {
				for (int p = 0; p < attaquantPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
						current.sendTitle("§bSimple, le plugin de teams", "§9Un plugin fait par Simple - pour Simple",
								20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 10);
						Bukkit.dispatchCommand(current, "spawn");
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						current.sendTitle("§bSimple, le plugin de teams", "§9Un plugin fait par Simple - pour Simple",
								20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 10);
						Bukkit.dispatchCommand(current, "spawn");
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
		}, 20 * 35L);

	}
}
