package fr.simple.teams.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamData;

public class CommandRemovep implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("removep")) {
			
			if(args.length == 0) {
				
				if(sender instanceof Player) {
					
					
					//Player player = (Player)sender;
					//String uuid = args[0];
					
					//UUID uuidFinal = UUID.fromString(uuid);
					
					UUID uuidFinal = ((Player) sender).getUniqueId();
					
					String s = TeamData.getPlayerTeam(uuidFinal);
					sender.sendMessage(s);
					/*String t = TeamData.getPlayerRank(uuidFinal);
					
					sender.sendMessage("L'UUID " + uuid + " fait partie de la team : " + s + ".");
					sender.sendMessage("L'UUID " + uuid + " possède le rang : " + t + ".");
					
					//TeamData.removePlayer(team, uuidFinal);*/
					
					
					
					return true;
					
				} else {
					
					sender.sendMessage("§cSeul un joueur peut exécuter cette commande !");
					
				}
				
				
			} else {
				
				sender.sendMessage("§cUsage : /team create <nom> <slogan> <accès> <couleur>");
				
			}
			
		}
		
		return false;
	}

}