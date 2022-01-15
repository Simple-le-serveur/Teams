package fr.simple.teams;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.simple.teams.assaut.AssautListener;
import fr.simple.teams.claims.Claims;
import fr.simple.teams.claims.ClaimsManager;
import fr.simple.teams.commands.TeamCommand;
import fr.simple.teams.customsItems.CustomsItemsListeners;
import fr.simple.teams.customsItems.InitCustomsItems;
import fr.simple.teams.luckyBlocks.LuckyBlockBreak;
import fr.simple.teams.ranking.RankingListeners;
import net.milkbowl.vault.economy.Economy;

public class Teams extends JavaPlugin {

	public Economy eco;

	public String prefix = "§eSimplebot §8»";

	public Teams teamsG = this;

	@Override
	public void onEnable() {

		if (!setupEconomy()) {
			System.out.println(
					"Erreur : Vous devez avoir Vault ainsi qu'un plugin d'Economie installé sur votre serveur !");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		getCommand("team").setExecutor(new TeamCommand(null));

		getServer().getPluginManager().registerEvents(new ClaimsManager(this), this);
		getServer().getPluginManager().registerEvents(new TeamListener(this), this);
		getServer().getPluginManager().registerEvents(new LuckyBlockBreak(this), this);
		getServer().getPluginManager().registerEvents(new AssautListener(this), this);
		getServer().getPluginManager().registerEvents(new RankingListeners(this), this);
		getServer().getPluginManager().registerEvents(new CustomsItemsListeners(this), this);

		Claims.initClaimSystem();
		InitCustomsItems.initCustomsItems();

		Date date = new Date();
		SimpleDateFormat jour_semaine = new SimpleDateFormat("E");
		SimpleDateFormat heure = new SimpleDateFormat("k");
		if (heure.format(date).toString().equals("2")) {
			Bukkit.getConsoleSender().sendMessage("===== REDÉMARAGE JOURNALIER =====");
			if (jour_semaine.format(date).toString().equals("lun.")) {
				RankingListeners.isVoted = false;
			}
		}

		Bukkit.getConsoleSender().sendMessage("===================================================");
		Bukkit.getConsoleSender().sendMessage("===== Simple, le plugin de teams est allumé ! =====");
		Bukkit.getConsoleSender().sendMessage("===================================================");

	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("===================================================");
		Bukkit.getConsoleSender().sendMessage("===== Simple, le plugin de teams est éteint ! =====");
		Bukkit.getConsoleSender().sendMessage("===================================================");
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economy != null) {
			eco = economy.getProvider();
		}
		return (eco != null);
	}
	
	public static void helpPage(Player player) {
		player.sendMessage("§9=§b+§9= §bPage d'aide §9=§b+§9=");
		player.sendMessage("§b/team create §9Créer une team");
		player.sendMessage("§b/team info <team> §9Voir les informations sur une team");
		player.sendMessage("§b/team attack <team> §9Attaquer une team");
		player.sendMessage("§b/team control §9Contrôler sa team");
		player.sendMessage("§b/team join <team> §9Rejoindre une team");
		player.sendMessage("§b/team invite <joueur> §9Inviter un joueur dans sa team");
		player.sendMessage("§b/team leave §9Quitter sa team");
		player.sendMessage("§b/team cancel §9Ne fait rien");
		player.sendMessage("§b/team accept §9Accepter une invitation");
		player.sendMessage("§b/team deny §9Refuser une invitation");
		player.sendMessage("§b/team top §9Voir le classement des teams");
		player.sendMessage("§b/team acceptAttack §9Accepter une attaque");
		player.sendMessage("§b/team denyAttack §9Refuser une attaque");
		player.sendMessage("§b/team tpAttack §9Se téléporter a l'attaque");
		player.sendMessage("§b/team tpClaim §9Se téléporter a son claim");
	}

}
