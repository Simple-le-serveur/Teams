package fr.simple.teams.assaut;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
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

		System.out.println("§e1 Kills attaquants : " + assaut.getKillsAttaquants() + " ; Kills défenceurs : "
				+ assaut.getKillsDéfenceurs());

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
				current.sendTitle("§bScan en cours...", "§9Nous annalysons le champ de bataille", 20 * 1, 20 * 3,
						20 * 1);
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
				current.sendTitle("§bScan en cours...", "§9Nous annalysons le champ de bataille", 20 * 1, 20 * 3,
						20 * 1);
				current.playSound(current.getLocation(), Sound.ENTITY_TNT_PRIMED, 10, 1);
				current.playSound(current.getLocation(), Sound.BLOCK_PISTON_EXTEND, 10, 1);
			} catch (NullPointerException e) {
				continue;
			}
		}

		System.out.println("§e 2 Kills attaquants : " + assaut.getKillsAttaquants() + " ; Kills défenceurs : "
				+ assaut.getKillsDéfenceurs());

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
		// pBreak : pourcentage de blocs cassés
		Bukkit.getConsoleSender()
				.sendMessage("Calcul du pourcentage de blocks cassés : 100 * " + BC + " / " + assaut.getBlocks());
		float pBreak = 100 * BC / assaut.getBlocks();
		pBreak = 100 - pBreak;
		Bukkit.getConsoleSender().sendMessage("Pourcentage de blocks cassés : " + pBreak + "%");
		// ptPerPercent : point par pourcentage cassé
		float ptPerPercent = 0.06f;
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

			Location chest = TeamData.getBankChest(assaut.getDéfenceurs());
			Block block = chest.getBlock();
			if (block.getType() == Material.CHEST) {
				try {
					String chestName = ((Chest) block.getState()).getCustomName();
					Bukkit.getConsoleSender()
							.sendMessage("Nom attendu pour le coffre de banque : banque " + assaut.getDéfenceurs());
					if (chestName.equals("banque " + assaut.getDéfenceurs())) {
						pointsDéfenceurs = pointsDéfenceurs + 8;
						Bukkit.getConsoleSender().sendMessage("Coffre non cassé");
					} else {
						pointsAttaquants = pointsAttaquants + 8;
						Bukkit.getConsoleSender().sendMessage("Coffre cassé");
					}
				} catch (NullPointerException e) {

				}

			} else {
				pointsAttaquants = pointsAttaquants + 8;
				Bukkit.getConsoleSender().sendMessage("Coffre cassé");
			}

		}

		final float PercentageBreaked = pBreak;
		final float ptsAtt = pointsAttaquants;
		final float ptsDef = pointsDéfenceurs;

		assaut.setPtA(ptsAtt);
		assaut.setPtD(ptsDef);

		if (ptsAtt > ptsDef) {
			assaut.setWinner("attaquants");
			assaut.setWinnerTeam(assaut.getAttaquants());
			Bukkit.getConsoleSender().sendMessage("Gagnants : attaquants");
			TeamData.setAttacksVictoryNumber(assaut.getAttaquants(),
					TeamData.getAttacksVictoryNumber(assaut.getAttaquants()) + 1);
			TeamData.setAttacksDefeatNumber(assaut.getDéfenceurs(),
					TeamData.getAttacksDefeatNumber(assaut.getDéfenceurs()) + 1);
			TeamData.setAttacksNumber(assaut.getAttaquants(), TeamData.getAttacksNumber(assaut.getAttaquants()) + 1);
			TeamData.setAttacksNumber(assaut.getDéfenceurs(), TeamData.getAttacksNumber(assaut.getDéfenceurs()) + 1);
		} else if (ptsAtt < ptsDef) {
			assaut.setWinner("défenceurs");
			assaut.setWinnerTeam(assaut.getDéfenceurs());
			Bukkit.getConsoleSender().sendMessage("Gagnants : défenceurs");
			TeamData.setAttacksVictoryNumber(assaut.getDéfenceurs(),
					TeamData.getAttacksVictoryNumber(assaut.getDéfenceurs()) + 1);
			TeamData.setAttacksDefeatNumber(assaut.getAttaquants(),
					TeamData.getAttacksDefeatNumber(assaut.getAttaquants()) + 1);
			TeamData.setAttacksNumber(assaut.getAttaquants(), TeamData.getAttacksNumber(assaut.getAttaquants()) + 1);
			TeamData.setAttacksNumber(assaut.getDéfenceurs(), TeamData.getAttacksNumber(assaut.getDéfenceurs()) + 1);
		} else {
			assaut.setWinner("égalité !");
			assaut.setWinnerTeam("égalité !");
			Bukkit.getConsoleSender().sendMessage("Gagnant : les deux teams");
		}

		Bukkit.getConsoleSender().sendMessage("Points attaquants : " + pointsAttaquants);
		Bukkit.getConsoleSender().sendMessage("Points défenceurs : " + pointsDéfenceurs);
		
		for(int i = 0; i < Assaut.teamsInFight2.size(); i ++) {
			if(Assaut.teamsInFight2.get(i).contains(assaut.getAttaquants())) {
				Assaut.teamsInFight2.remove(i);
			}
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
								"§9le gagnant est ...", 20 * 1, 20 * 3, 20 * 1);
						current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 1);
					} catch (NullPointerException e) {
						continue;
					}
				}

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(défenceursPlayers.get(p)));
						current.sendTitle("§9Avec un total de : §b" + Math.max(ptsAtt, ptsDef) + "§9,",
								"§9le gagnant est ...", 20 * 1, 20 * 3, 20 * 1);
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
									"§9Les défenceurs ont eu : §b" + Math.min(ptsAtt, ptsDef), "§9Il leur manquait §b"
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

				for (int p = 0; p < défenceursPlayers.size(); p++) {
					if (assaut.getWinner().equals("attaquants")) {
						try {
							Player current = Bukkit.getPlayer(UUID.fromString(attaquantPlayers.get(p)));
							current.sendTitle(
									"§9Les défenceurs ont eu : §b" + Math.min(ptsAtt, ptsDef), "§9Il leur manquait §b"
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
				Bukkit.broadcastMessage(teams.prefix + " §9L'assaut qui opposait la team §b" + assaut.getAttaquants()
						+ " §9à la team §b" + assaut.getDéfenceurs() + " §9est §bfini ! §9C'est la team §b"
						+ assaut.getWinnerTeam() + " §9qui a gagné avec §b" + Math.max(ptsAtt, ptsDef) + "§9. §bBravo !");
			}
		}, 20 * 35L);

	}
}
