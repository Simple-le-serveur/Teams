package fr.simple.teams.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamCreate;
import fr.simple.teams.Teams;

public final class CreateTeam implements CommandExecutor {
	
	private Teams teams;
	public CreateTeam(Teams teams) {
		this.teams = teams;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("team")) {
			
			if(args.length == 4) {
				
				if(sender instanceof Player) {
					
					Player player = (Player)sender;
					UUID uuid = player.getUniqueId();
					String TMname = args[0];
					String TMslogan = args[1];
					String TMacces = args[2];
					String TMcolor = args[3];
					
					TeamCreate.CreateATeam(player, uuid, TMname, TMslogan, TMacces, TMcolor);
					
					sender.sendMessage(teams.prefix + "§9La team §b" + TMname + "§9 a été crée !");
					
					return true;
					
				} else {
					
					sender.sendMessage(teams.prefix + "§cSeul un joueur peut exécuter cette commande !");
					
				}
				
				
			} else {
				
				sender.sendMessage(teams.prefix + "§cUsage : /team create <nom> <slogan> <accès> <couleur>");
				
			}
			
		}
		
		return false;
		
	}

}
