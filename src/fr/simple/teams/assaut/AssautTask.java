package fr.simple.teams.assaut;

import java.util.List;

import org.bukkit.Location;
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
	
	public AssautTask(AssautData assaut, Teams teams, BossBar bar) {
		this.assaut = assaut;
		this.teams = teams;
		claimB1 = ClaimData.getFirstLoc(assaut.getDéfenceurs());
		claimB2 = ClaimData.getSecondLoc(assaut.getDéfenceurs());
		pA = TeamData.getAllPlayerFromTeam(assaut.getAttaquants());
		pD = TeamData.getAllPlayerFromTeam(assaut.getDéfenceurs());
		this.bar = bar;
		time = assaut.getTpsPréparation();
	}

	@Override
	public void run() {
		
		System.out.println("§ePoints attaquants : " + assaut.getPtA() + " ; Points défenceurs : " + assaut.getPtA());
		
		if(lCounter == 10) {
			LuckyBlock.spawn(AssautFunctions.tpZoneClaim(claimB1, claimB2));
			LuckyBlock.spawn(AssautFunctions.tpZoneAlentoure(claimB1, claimB2));
			lCounter = 0;
		}
		lCounter ++;
		
		if(assaut.getPhase().equals("préparation")) {
			
			if(sCounter == 60) {
				sCounter = 0;
				time --;
			}
			
			bar.setProgress((float)time / assaut.getTpsPréparation());
			sCounter ++;
			
			if(bar.getProgress() == 0) {
				assaut.setPhase("attaque");
				time = assaut.getTpsAttaque();
				new AssautAttaqueTask(assaut, teams).runTaskTimer(teams, 0L, 20L);
			}
		
		} else if(assaut.getPhase().equals("attaque")) {
			
			
			if(sCounter == 60) {
				sCounter = 0;
				time --;
			}
			
			bar.setProgress((float)time / assaut.getTpsAttaque());
			sCounter ++;
			
			if(bar.getProgress() == 0) {
				assaut.setPhase("combat_final");
				time = assaut.getTpsAttaque();
				new AssautFinalFightTask(assaut, teams).runTaskTimer(teams, 0L, 20L);
			}
			
			
		} else if(assaut.getPhase().equals("combat_final")) {
			
			if(lCounter == 10) {
				LuckyBlock.spawn(AssautFunctions.tpZoneClaim(claimB1, claimB2));
				LuckyBlock.spawn(AssautFunctions.tpZoneAlentoure(claimB1, claimB2));
				lCounter = 0;
			}
			lCounter ++;
			
			List<Player> list = Assaut.alivePlayers;
			if(list.size() == 1 || list.size() == 0) {
				new AssautFinish(assaut, teams);
				cancel();
			}
			
		}
		
	}
	
	
	
}
