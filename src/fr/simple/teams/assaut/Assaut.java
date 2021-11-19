package fr.simple.teams.assaut;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;
import fr.simple.teams.assautRequest.AssautRequestSendData;
import fr.simple.teams.claims.ClaimData;

public class Assaut {

	/*private Teams teams;

	public Assaut(Teams teams) {
		this.teams = teams;
	}*/

	public Assaut() {}
	
	public List<String> teamsInFight1 = new ArrayList<String>();
	public static List<String> teamsInFight2 = new ArrayList<String>();
	
	public static BossBar bar = null;
	
	public static List<Player> spectators = new ArrayList<Player>();
	public static List<Player> losers = new ArrayList<Player>();
	
	public static List<Player> alivePlayers = new ArrayList<Player>();

	public void startAssaut(AssautRequestSendData data, Teams teams) {
		
		AssautRequestSendData.AssautRequestSendData.remove(data.getDéfenceurs());

		AssautData assaut = new AssautData(data.getAttaquants());
		assaut.setAccès(data.getAccès());
		assaut.setAttaquants(data.getAttaquants());
		assaut.setDéfenceurs(data.getDéfenceurs());
		assaut.setTpsPréparation(data.getTpsPréparation());
		assaut.setTpsAttaque(data.getTpsAttaque());

		List<String> pA = TeamData.getAllPlayerFromTeam(assaut.getAttaquants());
		List<String> pD = TeamData.getAllPlayerFromTeam(assaut.getDéfenceurs());

		Location claimB1 = ClaimData.getFirstLoc(assaut.getDéfenceurs());
		Location claimB2 = ClaimData.getSecondLoc(assaut.getDéfenceurs());
		
		teamsInFight1.add(" " + assaut.getAttaquants() + " " + assaut.getDéfenceurs() + " ");
		teamsInFight2.add(" " + assaut.getAttaquants() + " " + assaut.getDéfenceurs() + " ");

		double xMin = Math.min(claimB1.getX(), claimB2.getX()), xMax = Math.max(claimB1.getX(), claimB2.getX());
		double zMin = Math.min(claimB1.getZ(), claimB2.getZ()), zMax = Math.max(claimB1.getZ(), claimB2.getZ());

		int BC = 0;

		for (int y = 0; y < 256; y++) {
			for (int x = (int) (xMin - 50); x < (xMax + 50); x++) {
				for (int z = (int) (zMin - 50); z < (zMax + 50); z++) {
					if (new Location(claimB1.getWorld(), x, y, z).getBlock().getType() != Material.AIR) {
						BC++;
					}
				}
			}
		}
		assaut.setBlocks(BC);

		for (int i = 0; i < pA.size(); i++) {
			try {
				final Player player = Bukkit.getPlayer(UUID.fromString(pA.get(i)));
				alivePlayers.add(player);
				player.sendMessage(teams.prefix + " §bC'est parti !§9 Vous et votre team allez attaquer la team §b"
						+ assaut.getDéfenceurs() + " §9dans quelques secondes !");
				Location locToTp = AssautFunctions.tpZoneAlentoure(claimB1, claimB2);
				player.teleport(locToTp);
			} catch (NullPointerException e) {
				continue;
			}
		}

		for (int i = 0; i < pD.size(); i++) {
			try {
				final Player player = Bukkit.getPlayer(UUID.fromString(pD.get(i)));
				alivePlayers.add(player);
				player.sendMessage(teams.prefix + " §bC'est parti !§9 Vous et votre team allez être attaqués par la team §b"
						+ assaut.getAttaquants() + " §9dans quelques secondes !");
				Location locToTp = AssautFunctions.tpZoneClaim(claimB1, claimB2);
				player.teleport(locToTp);
			} catch (NullPointerException e) {
				continue;
			}
		}

		Bukkit.broadcastMessage(teams.prefix + "§9 Début de l'assaut qui oppose la team §b" + assaut.getDéfenceurs()
				+ " §9contre la team §b" + assaut.getAttaquants() + "§9 !");
		
		bar = Bukkit.createBossBar("§b§lTemps restant", BarColor.WHITE, BarStyle.SOLID);
		bar.setVisible(true);

		// ASSAUT PUBLIQUE / PRIVE
		new AssautStartTask(assaut, teams).runTaskTimer(teams, 0L, 20L);
	}
	
	public static BossBar timeBar() {
		return bar;
	}
	
}
