package fr.simple.teams;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.simple.teams.assaut.AssautListener;
import fr.simple.teams.claims.Claims;
import fr.simple.teams.claims.ClaimsManager;
import fr.simple.teams.commands.CommandRemovep;
import fr.simple.teams.commands.TeamCommand;
import fr.simple.teams.luckyBlocks.LuckyBlockBreak;
import net.milkbowl.vault.economy.Economy;

public class Teams extends JavaPlugin {
	
	public Economy eco;
	
	public String prefix = "§eSimplebot §8»";
	
	public Teams teamsG = this;
	
	@Override
	public void onEnable() {
		
		if (!setupEconomy()) {
			System.out.println("Erreur : Vous devez avoir Vault ainsi qu'un plugin d'Economie installé sur votre serveur !");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		getCommand("team").setExecutor(new TeamCommand(null));
		getCommand("removep").setExecutor(new CommandRemovep());
		
		getServer().getPluginManager().registerEvents(new ClaimsManager(this), this);
		getServer().getPluginManager().registerEvents(new TeamListener(this), this);
		getServer().getPluginManager().registerEvents(new LuckyBlockBreak(this), this);
		getServer().getPluginManager().registerEvents(new AssautListener(this), this);
		
		Claims.initClaimSystem();
		
		System.out.println("===================================================");
		System.out.println("===== Simple, le plugin de teams est allumé ! =====");
		System.out.println("===================================================");
		
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("===================================================");
		System.out.println("===== Simple, le plugin de teams est éteint ! =====");
		System.out.println("===================================================");
	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if(economy != null) {
			eco = economy.getProvider();
		}
		return (eco != null);
	}
	
}
