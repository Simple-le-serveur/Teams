package fr.simple.teams.claims;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ClaimData {
	
	final static File file = new File("plugins/Teams", "/teams.yml");
	final static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
	
	private static void fin() {
		try {
			configuration.save(file);
		} catch (IOException e) {
			System.out.println("Une erreur fatale est survenue lors de la création du fichier \"teams.yml\".");
			e.printStackTrace();
		}
	}
	
	public static void setClaim(String teamName, Location firstLoc, Location secondLoc) {
		configuration.set("teams." + teamName + ".claim.firstLoc", firstLoc);
		configuration.set("teams." + teamName + ".claim.secondLoc", secondLoc);
		fin();
	}
	
	public static Location getFirstLoc(String teamName) {
		final ConfigurationSection configurationSection = configuration.getConfigurationSection("teams." + teamName + ".claim");
		return configurationSection.getLocation("firstLoc");
	}
	
	public static Location getSecondLoc(String teamName) {
		final ConfigurationSection configurationSection = configuration.getConfigurationSection("teams." + teamName + ".claim");
		return configurationSection.getLocation("secondLoc");
	}
	
}
