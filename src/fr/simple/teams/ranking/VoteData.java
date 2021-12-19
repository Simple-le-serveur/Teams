package fr.simple.teams.ranking;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class VoteData {
	
	private Player player = null;
	private Stage stage = null;
	private VoteBuild voteBuild = null;
	private VoteChest voteChest = null;
	private int currentTeam = 0;
	private String currentTeamName = null;
	
	public static Map<Player, VoteData> VoteData = new HashMap<>();

	public VoteData(Player player) {
		this.setPlayer(player);
		VoteData.put(player, this);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public VoteBuild getVoteBuild() {
		return voteBuild;
	}

	public void setVoteBuild(VoteBuild voteBuild) {
		this.voteBuild = voteBuild;
	}

	public VoteChest getVoteChest() {
		return voteChest;
	}

	public void setVoteChest(VoteChest voteChest) {
		this.voteChest = voteChest;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(int currentTeam) {
		this.currentTeam = currentTeam;
	}

	public String getCurrentTeamName() {
		return currentTeamName;
	}

	public void setCurrentTeamName(String currentTeamName) {
		this.currentTeamName = currentTeamName;
	}
	
}
