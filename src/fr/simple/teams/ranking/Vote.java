package fr.simple.teams.ranking;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamData;
import fr.simple.teams.assaut.AssautFunctions;
import fr.simple.teams.claims.ClaimData;

public class Vote {

	public static Player inVoting = null;
	private static List<String> Teams = null;

	public static void playerVote(Player player) {

		RankingListeners.isVoted = true;
		inVoting = player;
		player.setGameMode(GameMode.SPECTATOR);
		Teams = TeamData.getAllTeams();
		Location locToTp = AssautFunctions.tpZoneClaim(ClaimData.getFirstLoc(Teams.get(0)),
				ClaimData.getSecondLoc(Teams.get(0)));
		locToTp.setZ(locToTp.getZ() + 10);
		player.teleport(locToTp);
		player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
		player.sendMessage("§9Une fois que vous avez vu le repère, vous devez voter. pour cela, tapez §b/team vote§9.");

	}

	public static void next() {
		Player player = inVoting;
		VoteData data = VoteData.VoteData.get(inVoting);
		//on passe à la team suivante
		data.setCurrentTeam(data.getCurrentTeam() + 1);
		try {
			data.setCurrentTeamName(Teams.get(data.getCurrentTeam()));
			player.setGameMode(GameMode.SPECTATOR);
			Location locToTp = AssautFunctions.tpZoneClaim(ClaimData.getFirstLoc(Teams.get(0)), ClaimData.getSecondLoc(Teams.get(0)));
			locToTp.setZ(locToTp.getZ() + 10);
			player.teleport(locToTp);
			player.sendMessage("§9        [ §b§lVote de teams §9]        §9");
			player.sendMessage("§9Une fois que vous avez vu le repère, vous devez voter. pour cela, tapez §b/team vote§9.");
		} catch (IndexOutOfBoundsException e) {
			player.sendMessage("§eSimplebot §8» §9Vous avez §bterminé §9de voter. Merci !");
			inVoting = null;
			Bukkit.getConsoleSender().sendMessage("Vote effectué par " + inVoting.getName() + ".");
			Bukkit.broadcastMessage("§eSimplebot §8» §9Le vote de la semaine a été fait. Le classement des teams a été mis a jour.");
		}
		
	}

}
