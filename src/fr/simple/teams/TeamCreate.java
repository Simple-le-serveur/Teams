package fr.simple.teams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TeamCreate {
	
	public static void CreateATeam(Player player, UUID creator, String name, String slogan, String access, String color) {
		
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		
		List<String> uuids = new ArrayList<>();
		uuids.add(creator.toString());
		
		configuration.set("teams." + name + ".name", name);
		configuration.set("teams." + name + ".slogan", slogan.toString());
		configuration.set("teams." + name + ".access", access);
		configuration.set("teams." + name + ".color", color);
		
		configuration.set("teams." + name + ".fights.attacks", 0);
		configuration.set("teams." + name + ".fights.defeat", 0);
		configuration.set("teams." + name + ".fights.victory", 0);
		configuration.set("teams." + name + ".fights.notation", 0);
		
		configuration.set("teams." + name + ".players.createur", creator.toString());
		configuration.set("teams." + name + ".players.chefs", uuids);
		//configuration.set("teams." + name + ".players.sous-chefs", "none");
		//configuration.set("teams." + name + ".players.membres-de-confiance", "none");
		//configuration.set("teams." + name + ".players.membres", "none");
		
		try {
			configuration.save(file);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("Une erreur fatale est survenue lors de la création du fichier \"teams.yml\".");
			player.sendMessage("Une erreur fatale est survenue lors de la création de la team. Contactez le staff.");
			e.printStackTrace();
		}
		
	}
	
}
