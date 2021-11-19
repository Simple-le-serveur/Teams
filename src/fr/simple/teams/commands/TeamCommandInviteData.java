package fr.simple.teams.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeamCommandInviteData {

	private Player player;
	
	private Player invitor;
	private String team;
	
	public static Map<String, TeamCommandInviteData> TeamCommandInviteData = new HashMap<>();
	
	public TeamCommandInviteData(String playerName) {
		this.player = Bukkit.getPlayer(playerName);
		TeamCommandInviteData.put(player.getName(), this);
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Player getInvitor() {
		return invitor;
	}

	public void setInvitor(Player invitor) {
		this.invitor = invitor;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
