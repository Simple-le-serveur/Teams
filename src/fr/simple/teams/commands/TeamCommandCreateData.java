package fr.simple.teams.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamAcces;

public class TeamCommandCreateData {
	
	private Player player;
	
	private String color = "WHITE";
	private String teamName = "null";
	private String slogan = "null";
	private TeamAcces acces = TeamAcces.OPEN;
	
	public static Map<String, TeamCommandCreateData> TeamCommandCreateData = new HashMap<>();
	
	public TeamCommandCreateData(String playerName) {
		this.player = Bukkit.getPlayer(playerName);
		
		TeamCommandCreateData.put(player.getName(), this);
	}
	
	public void TeamCommandCreateDataSetColor(String color) {
		this.color = color;
	}
	
	public void TeamCommandCreateDataSetName(String name) {
		this.teamName = name;
	}
	
	public void TeamCommandCreateDataSetSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	public void TeamCommandCreateDataSetAccess(TeamAcces acces) {
		this.acces = acces;
	}
	
	
	public String TeamCommandCreateDataGetColor() {
		return color;
	}
	
	public String TeamCommandCreateDataGetName() {
		return teamName;
	}
	
	public String TeamCommandCreateDataGetSlogan() {
		return slogan;
	}
	
	public TeamAcces TeamCommandCreateDataGetAccess() {
		return acces;
	}
}
