package fr.simple.teams.assaut;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;
import fr.simple.teams.claims.ClaimData;
import fr.simple.teams.luckyBlocks.LuckyBlock;

public class AssautTask extends BukkitRunnable {

	private AssautData assaut;
	private Teams teams;
	List<String> pA = null;
	List<String> pD = null;
	private int time = 0;
	BossBar bar = null;
	int sCounter = 0;
	int lCounter = 0;
	Location claimB1 = null;
	Location claimB2 = null;

	Location chest = null;
	Block block = null;

	public AssautTask(AssautData assaut, Teams teams, BossBar bar) {
		this.assaut = assaut;
		this.teams = teams;
		claimB1 = ClaimData.getFirstLoc(assaut.getDéfenceurs());
		claimB2 = ClaimData.getSecondLoc(assaut.getDéfenceurs());
		pA = TeamData.getAllPlayerFromTeam(assaut.getAttaquants());
		pD = TeamData.getAllPlayerFromTeam(assaut.getDéfenceurs());
		this.bar = bar;
		time = assaut.getTpsPréparation();
		chest = TeamData.getBankChest(assaut.getDéfenceurs());
		block = chest.getBlock();
	}

	@Override
	public void run() {

		if (assaut.isBankChestIsBreaked() == false) {
			if (block.getType() == Material.CHEST) {
				try {
					String chestName = ((Chest) block.getState()).getCustomName();
					if (chestName.equals("banque " + assaut.getDéfenceurs())) {
					} else {
						assaut.setBankChestIsBreaked(true);
						TeamData.setBankChest(assaut.getDéfenceurs(), null);
						for (int j = 0; j < pA.size(); j++) {
							try {
								Player current = Bukkit.getPlayer(UUID.fromString(pA.get(j)));
								current.sendTitle("§bCoffre banque cassé", "", 0, 3 * 20, 1 * 20);
								current.playSound(current.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
								current.sendMessage(
										teams.prefix + " §9Le §bcoffre de banque §9des défenceurs a été cassé !");
							} catch (NullPointerException e) {
								continue;
							}
						}
						for (int j = 0; j < pD.size(); j++) {
							try {
								Player current = Bukkit.getPlayer(UUID.fromString(pD.get(j)));
								current.sendTitle("§bCoffre banque cassé", "", 0, 3 * 20, 1 * 20);
								current.playSound(current.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 10, 1);
								current.sendMessage(teams.prefix + " §9Votre §bcoffre de banque §9a été cassé !");
							} catch (NullPointerException e) {
								continue;
							}
						}
						Bukkit.getConsoleSender().sendMessage("Coffre cassé");
					}
				} catch (NullPointerException e) {

				}

			} else {
				assaut.setBankChestIsBreaked(true);
				TeamData.setBankChest(assaut.getDéfenceurs(), null);
				for (int j = 0; j < pA.size(); j++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(pA.get(j)));
						current.sendTitle("§bCoffre banque cassé", "", 0, 3 * 20, 1 * 20);
						current.playSound(current.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
						current.sendMessage(teams.prefix + " §9Le §bcoffre de banque §9des défenceurs a été cassé !");
					} catch (NullPointerException e) {
						continue;
					}
				}
				for (int j = 0; j < pD.size(); j++) {
					try {
						Player current = Bukkit.getPlayer(UUID.fromString(pD.get(j)));
						current.sendTitle("§bCoffre banque cassé", "", 0, 3 * 20, 1 * 20);
						current.playSound(current.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 10, 1);
						current.sendMessage(teams.prefix + " §9Votre §bcoffre de banque §9a été cassé !");
					} catch (NullPointerException e) {
						continue;
					}
				}
				Bukkit.getConsoleSender().sendMessage("Coffre cassé");
			}
		}

		if (lCounter == 10) {
			LuckyBlock.spawn(AssautFunctions.tpZoneClaim(claimB1, claimB2));
			LuckyBlock.spawn(AssautFunctions.tpZoneAlentoure(claimB1, claimB2));
			lCounter = 0;
		}
		lCounter++;

		if (assaut.getPhase().equals("préparation")) {

			if (sCounter == 60) {
				sCounter = 0;
				time--;
			}

			bar.setProgress((float) time / assaut.getTpsPréparation());
			sCounter++;

			if (bar.getProgress() == 0) {
				assaut.setPhase("attaque");
				time = assaut.getTpsAttaque();
				new AssautAttaqueTask(assaut, teams).runTaskTimer(teams, 0L, 20L);
			}

		} else if (assaut.getPhase().equals("attaque")) {

			if (sCounter == 60) {
				sCounter = 0;
				time--;
			}

			bar.setProgress((float) time / assaut.getTpsAttaque());
			sCounter++;

			if (bar.getProgress() == 0) {
				assaut.setPhase("combat_final");
				time = assaut.getTpsAttaque();
				new AssautFinalFightTask(assaut, teams).runTaskTimer(teams, 0L, 20L);
			}

		} else if (assaut.getPhase().equals("combat_final")) {

			if (lCounter == 10) {
				LuckyBlock.spawn(AssautFunctions.tpZoneClaim(claimB1, claimB2));
				LuckyBlock.spawn(AssautFunctions.tpZoneAlentoure(claimB1, claimB2));
				lCounter = 0;
			}
			lCounter++;

			List<Player> list = Assaut.alivePlayers;
			if (list.size() == 1 || list.size() == 0) {
				new AssautFinish(assaut, teams);
				cancel();
			} else {
				int a = 0;
				int d = 0;
				for(int i = 0; i < list.size(); i ++) {
					if(TeamData.getPlayerTeam(list.get(i).getUniqueId()).equals(assaut.getAttaquants())) {
						a++;
					} else if(TeamData.getPlayerTeam(list.get(i).getUniqueId()).equals(assaut.getDéfenceurs())) {
						d++;
					}
				}
				if(a == 0 || d == 0) {
					new AssautFinish(assaut, teams);
					cancel();
				}
			}

		}

	}

}
