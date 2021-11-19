package fr.simple.teams.claims;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.simple.teams.TeamData;

public class Claims {
	
	final static File file = new File("plugins/Teams", "/teams.yml");
	final static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
	
	static List<String> list = TeamData.getAllTeams();
	
	public static List<RegionManager> claimsList;
	
	public static void initClaimSystem() {
		Claims.claimsList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i ++) {
			final ConfigurationSection configurationSection = configuration.getConfigurationSection("teams." + list.get(i) + ".claim");
			try {
				Location firstPoint = configurationSection.getLocation("firstLoc");
				Location secondPoint = configurationSection.getLocation("secondLoc");
				RegionManager region = new RegionManager(firstPoint, secondPoint, list.get(i));
				claimsList.add(region);
			} catch(NullPointerException e) {}
		}
		
	}
		
	
	
	
	
}
