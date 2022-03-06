package fr.simple.teams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.simple.teams.assautRequest.AssautRequestSendData;

public class TeamData {
	
	private static void fin(File file, YamlConfiguration configuration) {
		try {
			configuration.save(file);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("Une erreur fatale est survenue lors de la création du fichier \"teams.yml\".");
			e.printStackTrace();
		}
	}

	public static String getSlogan(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration.getConfigurationSection("teams." + teamName);
		return configurationSection.getString("slogan");
	}

	public static void setSlogan(String teamName, String newTeamSlogan) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		// configuration.set("teams." + teamName + ".name", newTeamName);
		// <------------ICI-------------------
		configuration.set("teams." + teamName + ".slogan", newTeamSlogan);
		fin(file, configuration);
	}

	public static String getAccess(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration.getConfigurationSection("teams." + teamName);
		return configurationSection.getString("access");
	}

	public static void setAccess(String teamName, String newTeamAccess) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".access", newTeamAccess);
		fin(file, configuration);
	}

	public static String getColor(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration.getConfigurationSection("teams." + teamName);
		System.out.println("section : " + configurationSection);
		System.out.println("color : " + configurationSection.getString("color"));
		return configurationSection.getString("color");
	}

	public static void setColor(String teamName, String newTeamColor) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".color", newTeamColor);
		fin(file, configuration);
	}

	public static int getAttacksNumber(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".fights");
		return configurationSection.getInt("attacks");
	}

	public static void setAttacksNumber(String teamName, int newAttackNumber) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".fights.attacks", newAttackNumber);
		fin(file, configuration);
	}

	public static int getAttacksVictoryNumber(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".fights");
		return configurationSection.getInt("victory");
	}

	public static void setAttacksVictoryNumber(String teamName, int newVictoryNumber) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".fights.victory", newVictoryNumber);
		fin(file, configuration);
	}

	public static int getAttacksDefeatNumber(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".fights");
		return configurationSection.getInt("defeat");
	}

	public static void setAttacksDefeatNumber(String teamName, int newDefeatNumber) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".fights.defeat", newDefeatNumber);
		fin(file, configuration);
	}

	public static int getTeamNotation(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".fights");
		return configurationSection.getInt("notation");
	}

	public static void setTeamNotation(String teamName, int newTeamNotation) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".fights.notation", newTeamNotation);
		fin(file, configuration);
	}

	public static String getCreator(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".players");
		return configurationSection.getString("createur");
	}

	public static void setCreator(String teamName, UUID newTeamCreator) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".players.creator", newTeamCreator);
		fin(file, configuration);
	}
	
	public static void setBankChest(String teamName, Location chestLocation) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("teams." + teamName + ".bankChest", chestLocation);
		fin(file, configuration);
	}
	
	public static Location getBankChest(String teamName) {
		try {
			final File file = new File("plugins/Teams", "/teams.yml");
			final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
			final ConfigurationSection configurationSection = configuration
					.getConfigurationSection("teams." + teamName + "");
			return configurationSection.getLocation("bankChest");
		} catch(NullPointerException e) {
			return null;
		}
	}

	public static void addPlayer(String teamName, UUID playerName, String rank) {
		
		/*System.out.println(teamName);
		System.out.println(playerName);
		System.out.println(rank);*/
		
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		final ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".players");
		
		if (rank == "chefs") {
			
			try {
				@SuppressWarnings("unchecked")
				List<String> players = (List<String>) configurationSection.getList("chefs");
				players.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.chefs", players);
				fin(file, configuration);
			} catch (NullPointerException e) {
				List<String> list = new ArrayList<String>();
				list.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.membres", list);
				fin(file, configuration);
			}
		} else if (rank == "sous-chefs") {
			
			try {
				@SuppressWarnings("unchecked")
				List<String> players1 = (List<String>) configurationSection.getList("sous-chefs");
				players1.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.sous-chefs", players1);
				fin(file, configuration);
			} catch (NullPointerException e) {
				List<String> list11 = new ArrayList<String>();
				list11.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.membres", list11);
				fin(file, configuration);
			}
		} else if (rank == "membres-de-confiance") { 
			try {
				@SuppressWarnings("unchecked")
				List<String> players11 = (List<String>) configurationSection.getList("membres-de-confiance");
				players11.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.membres-de-confiance", players11);
				fin(file, configuration);
			} catch (NullPointerException e) {
				List<String> list11 = new ArrayList<String>();
				list11.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.membres", list11);
				fin(file, configuration);
			}
		} else {
			try {
				@SuppressWarnings("unchecked")
				List<String> players111 = (List<String>) configurationSection.getList("membres");
				players111.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.membres", players111);
				fin(file, configuration);
			} catch (NullPointerException e) {
				List<String> list111 = new ArrayList<String>();
				list111.add(playerName.toString());
				configuration.set("teams." + teamName + ".players.membres", list111);
				fin(file, configuration);
			}
			
		}
			
	}

	@SuppressWarnings("unchecked")
	public static List<String> getAllPlayerFromTeam(String teamName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection configurationSection = configuration
				.getConfigurationSection("teams." + teamName + ".players");

		List<String> players = (List<String>) configurationSection.getList("chefs");

		List<String> souschefs = (List<String>) configurationSection.getList("sous-chefs");
		List<String> membresdeconfiance = (List<String>) configurationSection.getList("membres-de-confiance");
		List<String> membres = (List<String>) configurationSection.getList("membres");
		try {
		for (int i = 0; i < souschefs.size(); i++) {
			players.add(souschefs.get(i));
		}
		} catch (NullPointerException e) {}

		try {
		for (int i = 0; i < membresdeconfiance.size(); i++) {
			players.add(membresdeconfiance.get(i));
		}
		} catch (NullPointerException e) {}

		try {
		for (int i = 0; i < membres.size(); i++) {
			players.add(membres.get(i));
		}
		} catch (NullPointerException e) {}

		return players;

	}

	public static List<String> getAllTeams() {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		// String [] list = {"init"}; //on crée une liste qui contiendra toutes les
		// teams
		List<String> list2 = new ArrayList<String>(); // on crée une liste qui contiendra toutes les teams
		list2.add(0, "init");

		int in = 0;

		for (String key : configuration.getKeys(true)) { // cette boucle récupère toutes les clés

			if (in == 0) {
				in++;
				continue;
			}

			String[] mots = key.split("\\."); // on crée un tableau qui contiendra toutes les parties de la clé
			String fin = mots[1]; // on récupère le nom de la team

			int verif = 0;
			if (list2.contains("init")) {

				// list[0] = fin;
				list2.set(0, fin);

			} else {

				for (int i = 0; i < list2.size(); i++) {
					if (list2.get(i).contains(fin)) {
						verif++;
					}

				}

				if (verif < 1) {
					list2.add(fin);
				}

			}

		}

		return list2;

	}

	public static String getPlayerRank(UUID playerName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		String playerTeam = getPlayerTeam(playerName);
		String[] grades = { "chefs", "sous-chefs", "membres-de-confiance", "membres" };
		String r = "chefs";

		for (int i = 0; i < 4; i++) {
			ConfigurationSection configurationSection = configuration
					.getConfigurationSection("teams." + playerTeam + ".players");
			try {
				@SuppressWarnings("unchecked")
				List<String> uuids = (List<String>) configurationSection.getList(grades[i]);
				for (int k = 0; k < uuids.size(); k++) {
					if (uuids.get(k).equals(playerName.toString())) {
						r = grades[i];
						return r;
					}
				}
			} catch (NullPointerException e) {
				continue;
			}
		}

		return r;

	}

	public static String getPlayerTeam(UUID playerName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		List<String> list = getAllTeams();

		String[] grades = { "chefs", "sous-chefs", "membres-de-confiance", "membres" };

		// String r = list.get(0);
		String r = "null";

		for (int i = 0; i < list.size(); i++) {
			ConfigurationSection configurationSection = configuration
					.getConfigurationSection("teams." + list.get(i) + ".players");
			for (int s = 0; s < 4; s++) {
				try {
					@SuppressWarnings("unchecked")
					List<String> uuids = (List<String>) configurationSection.getList(grades[s]);
					if (!uuids.isEmpty()) {
						for (int k = 0; k < uuids.size(); k++) {
							if (uuids.get(k).equals(playerName.toString())) {
								r = list.get(i).toString();
								return r;
							}
						}
					}
				} catch (NullPointerException e) {
					continue;
				}

			}

		}
		return r;

	}

	public static void removePlayer(String teamName, UUID playerName) {
		final File file = new File("plugins/Teams", "/teams.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		String player = playerName.toString();

		try {
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) configuration.getList("teams." + teamName + ".players.chefs");
			for (int number = 0; number < list.size(); number++) {
				if (player.equals(list.get(number))) {
					list.remove(number);
					configuration.set("teams." + teamName + ".players.chefs", list);
					fin(file, configuration);
					return;
				}
			}

		} catch (NullPointerException e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) configuration.getList("teams." + teamName + ".players.sous-chefs");
			for (int number = 0; number < list1.size(); number++) {
				if (player.equals(list1.get(number))) {
					list1.remove(number);
					configuration.set("teams." + teamName + ".players.sous-chefs", list1);
					fin(file, configuration);
					return;
				}
			}
		} catch (NullPointerException e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			@SuppressWarnings("unchecked")
			List<String> list11 = (List<String>) configuration
					.getList("teams." + teamName + ".players.membres-de-confiance");
			for (int number = 0; number < list11.size(); number++) {
				if (player.equals(list11.get(number))) {
					list11.remove(number);
					configuration.set("teams." + teamName + ".players.membres-de-confiance", list11);
					fin(file, configuration);
					return;
				}
			}
		} catch (NullPointerException e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			@SuppressWarnings("unchecked")
			List<String> list111 = (List<String>) configuration.getList("teams." + teamName + ".players.membres");
			for (int number = 0; number < list111.size(); number++) {
				if (player.equals(list111.get(number))) {
					list111.remove(number);
					configuration.set("teams." + teamName + ".players.membres", list111);
					fin(file, configuration);
					return;
				}
			}

		} catch (NullPointerException e) {
		}

	}
	
	public static void attackRequest(String attaquants, String défenceurs, Player demandeur, String accès, int tpsPréparation, int tpsAttaque) {
		List<String> PFTD = TeamData.getAllPlayerFromTeam(défenceurs); ////////////////////// défenceurs != attaquants //////////////////////////////
		int counter = 0;
		for(int i = 0; i < PFTD.size(); i ++) {
			try {
				UUID uuid = UUID.fromString(PFTD.get(i));
				Player target = Bukkit.getPlayer(uuid);
				String playerRank = TeamData.getPlayerRank(target.getUniqueId());
				if(playerRank.equals("membres-de-confiance") || playerRank.equals("sous-chefs") || playerRank.equals("chefs")) {
					target.sendMessage("§eSimplebot §8» §9La team §b" + attaquants + " §9vous propose de vous assiéger. Tapez §b/team acceptAttack " + attaquants + " §9pour voir les paramètres de l'assaut / l'accepter ou §b/team denyAttack " + attaquants + " §9pour refuser.");
					counter ++;
				}
			} catch (NullPointerException e) {continue;}
		}
		
		if(counter == 0) {
			demandeur.sendMessage("§eSimplebot §8» §cAucun joueur de cette team agrée à accepter des demandes d'assaut n'est connecté. Réessayez plus tard.");
		} else {
			demandeur.sendMessage("§eSimplebot §8» §9La demande a été envoyée !");
			AssautRequestSendData data = new AssautRequestSendData(défenceurs);
			data.setAccès(accès);
			data.setAttaquants(attaquants);
			data.setDemandeur(demandeur);
			data.setDéfenceurs(défenceurs);
			data.setTpsAttaque(tpsAttaque);
			data.setTpsPréparation(tpsPréparation);
		}
		
	}

}
