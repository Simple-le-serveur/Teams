package fr.simple.teams.ranking;

import java.util.ArrayList;
import java.util.List;

import fr.simple.teams.TeamData;

public class TeamsSort {
	public static List<String> SortTeams() {
		
		List<String> teamsNoClassed = TeamData.getAllTeams();
		List<String> teams = new ArrayList<String>();
		
		for(int i = 0; i < teamsNoClassed.size(); i ++) {
			if(i == 0) {
				teams.add(teamsNoClassed.get(i));
			} else if (i == 1) {
				if(TeamData.getTeamNotation(teams.get(0)) >= TeamData.getTeamNotation(teamsNoClassed.get(i))) {
					teams.add(teamsNoClassed.get(i));
				} else {
					teams.add(0, teamsNoClassed.get(i));
				}
			} else {
				if(TeamData.getTeamNotation(teams.get(0)) <= TeamData.getTeamNotation(teamsNoClassed.get(i))) {
					teams.add(0, teamsNoClassed.get(i));
				} else if(TeamData.getTeamNotation(teams.get(i - 1)) >= TeamData.getTeamNotation(teamsNoClassed.get(i))) {
					teams.add(teamsNoClassed.get(i));
				} else {
					for(int j = 0; j < teams.size(); j ++) {
						if(TeamData.getTeamNotation(teams.get(j)) >= TeamData.getTeamNotation(teamsNoClassed.get(i)) || TeamData.getTeamNotation(teams.get(j + 1)) <= TeamData.getTeamNotation(teamsNoClassed.get(i))) {
							teams.add(j + 1, teamsNoClassed.get(i));
						}
					}
				}
			}
		}
		
		return teams;
		
	}
}
