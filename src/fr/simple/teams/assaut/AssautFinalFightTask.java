package fr.simple.teams.assaut;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;

public class AssautFinalFightTask extends BukkitRunnable {

	private AssautData assaut;
	private int decompte = 6;
	@SuppressWarnings("unused")
	private Teams teams;
	List<String> pA = null;
	List<String> pD = null;
	private BossBar bar = null;
	
	public AssautFinalFightTask(AssautData assaut, Teams teams) {
		this.assaut = assaut;
		this.teams = teams;
		pA = TeamData.getAllPlayerFromTeam(assaut.getAttaquants());
		pD = TeamData.getAllPlayerFromTeam(assaut.getDéfenceurs());
		bar = Assaut.timeBar();
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < pA.size(); i++) {
			try {
				final Player player = Bukkit.getPlayer(UUID.fromString(pA.get(i)));
				if(player == null) {
					continue;
				}
				bar.removePlayer(player);
				switch (decompte) {
				case 5:
					Assaut.alivePlayers.add(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§a5", "", 0, 20, 0);
					break;
				case 4:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§24", "", 0, 20, 0);
					break;
				case 3:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§e3", "", 0, 20, 0);
					break;
				case 2:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§c2", "", 0, 20, 0);
					break;
				case 1:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§41", "", 0, 20, 0);
					break;
				case 0:
					player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
					player.sendTitle("§0GO !", "", 0, 20, 0);
					player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
					player.sendMessage("§bC'est parti !§9 Attaquez le château de la team");
					player.sendMessage("§b" + assaut.getDéfenceurs() + "§9. Déjouez leur pièges, tuez-les");
					player.sendMessage("§9tous et cassez tout ! Cassez les §bLucky-Blocks§9 qui apparaissent");
					player.sendMessage("§9aléatoirement pour obtenir plein de choses cools (ou pas) qui");
					player.sendMessage("§9vous permettrons de gagner. Vous pouvez suivre l'avancement");
					player.sendMessage("§9de l'attaque en haut de votre écran. §bBonne chance !");
					player.sendMessage("§9");
					player.sendMessage("§bPhase de combat final§9 : vous ne pouvez plus réapparaître ! Tuez");
					player.sendMessage("§9les tous pour être le dernier en vie !");
					player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
					break;
				}
			} catch (NullPointerException e) {
				continue;
			}
		}
		for (int i = 0; i < pD.size(); i++) {
			try {
				final Player player = Bukkit.getPlayer(UUID.fromString(pD.get(i)));
				if(player == null) {
					continue;
				}
				bar.removePlayer(player);
				switch (decompte) {
				case 6:
					break;
				case 5:
					Assaut.alivePlayers.add(player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§a5", "", 0, 20, 0);
					break;
				case 4:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§24", "", 0, 20, 0);
					break;
				case 3:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§e3", "", 0, 20, 0);
					break;
				case 2:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§c2", "", 0, 20, 0);
					break;
				case 1:
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					player.sendTitle("§41", "", 0, 20, 0);
					break;
				case 0:
					player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
					player.sendTitle("§0GO !", "", 0, 20, 0);
					player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
					player.sendMessage("§bC'est parti !§9 Défendez votre château contre la team");
					player.sendMessage("§b" + assaut.getAttaquants() + "§9. Déjouez leur attaque");
					player.sendMessage("§9et tuez-les tous ! Cassez les §bLucky-Blocks§9 qui apparaissent");
					player.sendMessage("§9aléatoirement pour obtenir plein de choses cools (ou pas) qui");
					player.sendMessage("§9vous permettrons de gagner. Vous pouvez suivre l'avancement");
					player.sendMessage("§9de l'attaque en haut de votre écran. §bBonne chance !");
					player.sendMessage("§9");
					player.sendMessage("§bPhase de combat final§9 : vous ne pouvez plus réapparaître ! Tuez");
					player.sendMessage("§9les tous pour être le dernier en vie !");
					player.sendMessage("§b=§9--------------------§b===§9-------------------§b=");
					break;
				}
			} catch (NullPointerException e) {
				continue;
			}
		}
		
		if(decompte == 0) {
			cancel();
		}
		
		decompte --;

	}

}
