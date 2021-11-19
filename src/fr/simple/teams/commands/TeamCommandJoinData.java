package fr.simple.teams.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeamCommandJoinData {

	private Player player;

	private Player applicant;
	private String team;

	public static Map<String, TeamCommandJoinData> TeamCommandJoinData = new HashMap<>();

	public TeamCommandJoinData(String playerName) {
		this.player = Bukkit.getPlayer(playerName);
		TeamCommandJoinData.put(player.getName(), this);
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Player getApplicant() {
		return applicant;
	}

	public void setApplicant(Player applicant) {
		this.applicant = applicant;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
