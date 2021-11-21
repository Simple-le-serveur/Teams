package fr.simple.teams.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.simple.teams.TeamData;
import fr.simple.teams.Teams;
import fr.simple.teams.assaut.Assaut;
import fr.simple.teams.assaut.AssautData;
import fr.simple.teams.assaut.AssautFunctions;
import fr.simple.teams.assautRequest.AssautRequestData;
import fr.simple.teams.assautRequest.AssautRequestGUI;
import fr.simple.teams.assautRequest.AssautRequestSendData;
import fr.simple.teams.claims.ClaimData;
import fr.simple.teams.ranking.TeamsSort;

public class TeamCommand implements TabExecutor {

	@SuppressWarnings("unused")
	private Teams teams;

	public TeamCommand(Teams teams) {
		this.teams = teams;
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		List<String> listToReturn = new ArrayList<String>();
		listToReturn.add("create");
		listToReturn.add("info");
		listToReturn.add("attack");
		listToReturn.add("control");
		listToReturn.add("join");
		listToReturn.add("invite");
		listToReturn.add("leave");
		listToReturn.add("cancel");
		listToReturn.add("accept");
		listToReturn.add("deny");
		listToReturn.add("top");
		listToReturn.add("test");
		listToReturn.add("acceptAttack");
		listToReturn.add("denyAttack");
		listToReturn.add("tpAttack");
		listToReturn.add("tpClaim");
		return listToReturn;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (args[0].isEmpty()) {
				sender.sendMessage("§cPAGE D'AIDE");
				return true;
			}

			switch (args[0]) {
			case "test2":
				TeamsSort.SortTeams();
				break;
			case "create":
				if (TeamData.getPlayerTeam(player.getUniqueId()) != "null") {
					player.sendMessage(
							"§eSimplebot §8» §cVous ne pouvez pas créer une team alors que vous êtes déjà dans une autre !");
					player.sendMessage("§eSimplebot §8» §9Si vous désirez la quitter, tapez §b/team leave §9.");
					return true;
				}
				try {
					TeamCommandCreateData data = TeamCommandCreateData.TeamCommandCreateData.get(player.getName());
					TeamCommandCreateGUI.createTeamGUIMain(player, data);
				} catch (NullPointerException e) {
					TeamCommandCreateData data = new TeamCommandCreateData(player.getName());
					TeamCommandCreateGUI.createTeamGUIMain(player, data);
				}
				break;

			case "test":
				Assaut.alivePlayers.clear();
				/*Block block = loc.getBlock();
		block.setType(Material.PLAYER_HEAD);
		
		Skull skull = (Skull) block.getState();
		skull.setOwner("Luck");
		skull.update();*/
				//ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
				/*Location playerLoc = player.getLocation();
				Block block = playerLoc.getBlock();
				block.setType(Material.PLAYER_HEAD);
				Skull skull = (Skull) block.getState();
				skull.setOwner("Luck");
				skull.update();*/
				
				break;

			case "control":
				if (!TeamData.getPlayerTeam(((Player) sender).getPlayer().getUniqueId()).equals("null")) {
					TeamCommandControlGUI.controlTeamGUIMain(player);
				} else {
					sender.sendMessage("§eSimplebot §8» §cVous devez faire partie d'une team pour accéder à ce menu !");
				}
				break;

			case "join":
				if (!args[1].isEmpty()) {
					List<String> teams = TeamData.getAllTeams();
					for (int i = 0; i < teams.size(); i++) {
						if (args[1].equals(teams.get(i))) {
							String team = args[1];
							if (TeamData.getAccess(team).equals("OPEN")) {
								TeamData.addPlayer(args[1], ((Player) sender).getUniqueId(), "membres");
								sender.sendMessage("§eSimplebot §8» §9Vous venez de rejoindre la team §b" + args[1]
										+ " §9 ! Bon jeu sur Simple.");
								return true;
							} else if (TeamData.getAccess(team).equals("ON_INVITATION")) {
								int counter = 0;
								@SuppressWarnings("unchecked")
								List<Player> connectedP = (List<Player>) Bukkit.getOnlinePlayers();
								for (int j = 0; j < connectedP.size(); j++) {
									if (TeamData.getPlayerTeam(connectedP.get(j).getUniqueId()).equals(team)) {
										Player target = connectedP.get(j);
										target.sendMessage("§9          [ §b§lDemande pour rejoindre §9]          §9");
										target.sendMessage("§b " + sender.getName() + " §9souhaite rejoindre la team §b"
												+ team + "§9.");
										target.sendMessage("§9Tapez §b/tm accept §9pour accepter sa demande,");
										target.sendMessage("§9ou tapez §b/tm deny §9pour refuser");
										TeamCommandJoinData data = new TeamCommandJoinData(target.getName());
										data.setPlayer(target);
										data.setApplicant(player);
										data.setTeam(team);
										counter++;
									}
								}
								if (counter == 0) {
									sender.sendMessage(
											"§eSimplebot §8» §cAucun joueur de cette team n'est connecté. Réessayer plus tard.");
								} else {
									sender.sendMessage("§eSimplebot §8» §9Une demande pour rejoindre la team §b" + team
											+ " §9a bien été envoyée. Attendez une réponse.");
								}
								return true;
							} else if (TeamData.getAccess(team).equals("PRIVATE")) {
								sender.sendMessage(
										"§eSimplebot §8» §cCette team n'autorise pas les demandes pour rejoindre. Seul une personne agrée peut vous inviter a rejoindre sa team.");
								return true;
							}

						}

					}
					sender.sendMessage(
							"§eSimplebot §8» §cLe nom de la team que vous avez indiqué n'existe pas. Réessayez avec un nom correct.");
				} else {
					sender.sendMessage(
							"§eSimplebot §8» §cVous devez spécifier le nom de la team que vous voulez rejoindre. Réessayez.");
				}
				break;

			case "invite":

				if (args[1] != null) {
					@SuppressWarnings("unchecked")
					List<Player> playersC = (List<Player>) Bukkit.getOnlinePlayers();
					for (int i = 0; i < playersC.size(); i++) {
						if (args[1].equalsIgnoreCase(playersC.get(i).getName())) {
							String team = TeamData.getPlayerTeam(player.getUniqueId());
							Player cible = Bukkit.getPlayer(args[1]);
							if (TeamData.getAccess(team).equals("OPEN")) {
								TeamCommandInviteData invite = new TeamCommandInviteData(cible.getName());
								// TeamCommandCreateData tccd111 =
								// TeamCommandCreateData.TeamCommandCreateData.get(player.getName());
								invite.setInvitor(player.getPlayer());
								invite.setTeam(team);
								cible.sendMessage("§9          [ §b§lDemande pour rejoindre §9]          §9");
								cible.sendMessage("§b " + sender.getName() + " §9vous propose de rejoindre la team §b"
										+ team + "§9.");
								cible.sendMessage("§9Tapez §b/tm accept §9pour rejoindre la team,");
								cible.sendMessage("§9ou tapez §b/tm deny §9pour refuser");
								sender.sendMessage(
										"§eSimplebot §8» §9La demande a été envoyée à §b" + cible.getName() + " §9!");
								return true;
							} else if (TeamData.getAccess(team).equals("ON_INVITATION")
									|| TeamData.getAccess(team).equals("PRIVATE")) {
								if (TeamData.getPlayerRank(((Player) sender).getUniqueId()) == "membres-de-confiance"
										|| TeamData.getPlayerRank(((Player) sender).getUniqueId()) == "sous-chefs"
										|| TeamData.getPlayerRank(((Player) sender).getUniqueId()) == "chefs") {
									cible.sendMessage("§9          [ §b§lDemande pour rejoindre §9]          §9");
									cible.sendMessage("§b " + sender.getName()
											+ " §9vous propose de rejoindre la team §b" + team + "§9.");
									cible.sendMessage("§9Tapez §b/tm accept §9pour rejoindre la team,");
									cible.sendMessage("§9ou tapez §b/tm deny §9pour refuser");
									sender.sendMessage("§eSimplebot §8» §9La demande a été envoyée à §b"
											+ cible.getName() + " §9!");
									return true;

								} else {
									sender.sendMessage(
											"§eSimplebot §8» §cVous n'avez pas la permission d'inviter des joueurs dans la team.");
									return true;
								}
							}

							break;
						}
					}

					sender.sendMessage(
							"§eSimplebot §8» §cCe joueur n'existe pas, ou il n'est pas connecté. Réessayez plus tard.");
					return true;

				}

				sender.sendMessage("§eSimplebot §8» §cVous devez spécifier un nom de joueur connecté. Réessayez.");
				break;

			case "accept":
				try {
					TeamCommandInviteData invite = TeamCommandInviteData.TeamCommandInviteData
							.get(((Player) sender).getPlayer().getName());
					if (TeamData.getPlayerTeam(player.getUniqueId()) == "null") {
						TeamData.addPlayer(invite.getTeam(), player.getUniqueId(), "membre");
						invite.getInvitor().sendMessage("§eSimplebot §8» §b " + invite.getPlayer().getName()
								+ " §9a accepté votre demande. Il rejoin donc la team §b" + invite.getTeam() + " §9!");
						player.sendMessage("§eSimplebot §8» §9Vous venez de rejoindre la team §b" + invite.getTeam()
								+ " §9! Bon jeu sur Simple!");
					} else {
						player.sendMessage("§eSimplebot §8» §cVous êtes déjà dans une team !");
						invite.getInvitor().sendMessage("§eSimplebot §8» §cVotre demande pour rejoindre a été annulée.");
					}
					invite.setInvitor(null);
					invite.setPlayer(null);
					invite.setTeam(null);
					break;

				} catch (NullPointerException e) {
					sender.sendMessage("§eSimplebot §8» §cVous n'avez aucune demande pour rejoindre un team en cours.");
				}

				try {
					TeamCommandJoinData invite = TeamCommandJoinData.TeamCommandJoinData
							.get(((Player) sender).getPlayer().getName());
					
					if (TeamData.getPlayerTeam(player.getUniqueId()) == "null") {
						TeamData.addPlayer(invite.getTeam(), player.getUniqueId(), "membre");
						invite.getApplicant().sendMessage("§eSimplebot §8» §b " + invite.getPlayer().getName()
								+ " §9rejoin la team §b" + invite.getTeam() + " §9!");
						player.sendMessage("§eSimplebot §8» §b" + invite.getApplicant() + " §9a accepté votre demande. Vous venez de rejoindre la team §b" + invite.getTeam()
								+ " §9! Bon jeu sur Simple!");
					} else {
						player.sendMessage("§eSimplebot §8» §cVous êtes déjà dans une team !");
						invite.getApplicant().sendMessage("§eSimplebot §8» §cLa demande pour rejoindre a été annulée.");
					}
					
					invite.setApplicant(null);
					invite.setPlayer(null);
					invite.setTeam(null);
					
				} catch (NullPointerException e) {
					sender.sendMessage("§eSimplebot §8» §cVous n'avez aucune demande pour rejoindre un team en cours.");
				}
				break;

			case "deny":
				
				try {
					TeamCommandInviteData invite = TeamCommandInviteData.TeamCommandInviteData
							.get(((Player) sender).getPlayer().getName());
					if (TeamData.getPlayerTeam(player.getUniqueId()) == "null") {
						invite.getInvitor().sendMessage("§eSimplebot §8» §b " + invite.getPlayer().getName()
								+ " §9a refusé votre demande pour rejoindre la team.");
						sender.sendMessage("§eSimplebot §8» §9Vous venez de refuser la demande pour rejoindre la team §b"
								+ invite.getTeam() + "§9.");
					} else {
						player.sendMessage("§eSimplebot §8» §cVous êtes déjà dans une team !");
						invite.getInvitor().sendMessage("§eSimplebot §8» §cVotre demande pour rejoindre a été annulée.");
					}
					invite.setInvitor(null);
					invite.setPlayer(null);
					invite.setTeam(null);
					break;

				} catch (NullPointerException e) {
					// sender.sendMessage("§eSimplebot §8» §cVous n'avez aucune demande pour
					// rejoindre un team en cours.");
				}

				try {
					TeamCommandJoinData invite = TeamCommandJoinData.TeamCommandJoinData
							.get(((Player) sender).getPlayer().getName());
					
					if (TeamData.getPlayerTeam(player.getUniqueId()) == "null") {
						invite.getApplicant().sendMessage("§eSimplebot §8» §b " + invite.getPlayer().getName()
								+ " §9a refusé votre demande pour rejoindre la team.");
						sender.sendMessage("§eSimplebot §8» §9Vous venez de refuser la demande pour rejoindre la team §b"
								+ invite.getTeam() + "§9.");
					} else {
						player.sendMessage("§eSimplebot §8» §cVous êtes déjà dans une team !");
						invite.getApplicant().sendMessage("§eSimplebot §8» §cLa demande pour rejoindre a été annulée.");
					}
					
					invite.setApplicant(null);
					invite.setPlayer(null);
					invite.setTeam(null);
					
				} catch (NullPointerException e) {
					sender.sendMessage("§eSimplebot §8» §cVous n'avez aucune demande pour rejoindre un team en cours.");
				}
				break;
				
			case "info":
				if (args[1] != null) {
					List<String> teams = TeamData.getAllTeams();
					for(int i = 0; i < teams.size(); i ++) {
						if(teams.get(i).equals(args[1])) {
							TeamCommandInfoGUI.teamInfoGUIMain(player, args[1]);
							return true;
						}
					}
					player.sendMessage("§eSimplebot §8» §cVous devez spécifier un nom de team correct !");
				} else { 
					player.sendMessage("§eSimplebot §8» §cVous devez spécifier un nom de team comme ceci : §c§l/team info <non de team>§c.");
				}
				break;
				
			case "acceptAttack":
				try { /////////// BON GRADE ////////////////
					@SuppressWarnings("unused")
					AssautRequestSendData data = AssautRequestSendData.AssautRequestSendData.get(TeamData.getPlayerTeam(player.getUniqueId()));
					AssautRequestGUI.assautRequestGUIConfirm(player);
				} catch (NullPointerException e) {
					player.sendMessage("§eSimplebot §8» §cVous n'avez aucune demande d'assaut en cours, ou vous avez indiqué un nom de team erroné.");
				}
				break;
			case "denyAttack":
				try {
					AssautRequestSendData data = AssautRequestSendData.AssautRequestSendData.get(TeamData.getPlayerTeam(player.getUniqueId()));
					data.getDemandeur().sendMessage("§eSimplebot §8» §cVotre demande d'assaut a été refusée.");
					player.sendMessage("§eSimplebot §8» §9Vous avez refusé la demande d'assaut.");
					List<String> members = TeamData.getAllPlayerFromTeam(data.getDéfenceurs());
					for(int i = 0; i < members.size(); i ++) {
						UUID id = UUID.fromString(members.get(i));
						String rank = TeamData.getPlayerRank(id);
						if(rank.equals("membres-de-confiance") || rank.equals("sous-chefs") || rank.equals("chefs")) {
							try {
							Bukkit.getPlayer(id).sendMessage("§eSimplebot §8» §b" + player.getName() + " §9a refusé la demande d'assaut.");
							} catch(NullPointerException e) {continue;}
						}
					}
					AssautRequestSendData.AssautRequestSendData.remove(TeamData.getPlayerTeam(player.getUniqueId()));
				} catch (NullPointerException e) {
					player.sendMessage("§eSimplebot §8» §cVous n'avez aucune demande d'assaut en cours, ou vous avez indiqué un nom de team erroné.");
				}
				break;
				
			case "attack":
				try {
					String team = args[1];
					
					List<String> allTeams= TeamData.getAllTeams();
					for(int i = 0; i < allTeams.size(); i ++) {
						if(allTeams.get(i).equals(team)) {
							String playerTeam = TeamData.getPlayerTeam(player.getUniqueId());
							if(playerTeam.equals("null")) {
								player.sendMessage("§eSimplebot §8» §cVous devez être dans une team pour utiliser cette commande !");
								return true;
							}
							String playerRank = TeamData.getPlayerRank(player.getUniqueId());
							if(playerRank.equals("chefs") || playerRank.equals("sous-chefs") ||playerRank.equals("membres-de-confiance")) {
								try {
									AssautRequestData data = AssautRequestData.AssautRequestData.get(player.getName());
									data.setAttaquants(playerTeam);
									data.setDemandeur(player);
									data.setDéfenceurs(team);
									AssautRequestGUI.assautRequestGUIMain(player);
								} catch (NullPointerException e) {
									AssautRequestData data = new AssautRequestData(player.getName());
									data.setAttaquants(playerTeam);
									data.setDemandeur(player);
									data.setDéfenceurs(team);
									AssautRequestGUI.assautRequestGUIMain(player);
								}
								return true;
							}
							player.sendMessage("§eSimplebot §8» §cVous n'avez pas la permission d'envoyer des demandes d'attaque.");
							return true;
						}
					}
					player.sendMessage("§eSimplebot §8» §cLe nom de la team que vous voulez attaquer n'existe pas, donc ce serait trop facile de ne rien combarte ;)");
					
				}catch (ArrayIndexOutOfBoundsException e) {
					player.sendMessage("§eSimplebot §8» §cVeuillez indiquer le nom de la team que vous voulez assiéger.");
				}
				break;
			
			case "tpAttack":
				try {
					String playerTeam = TeamData.getPlayerTeam(player.getUniqueId());
					if (!playerTeam.equals("null")) {

						List<String> teamsInFight = Assaut.teamsInFight2;
						for (int i = 0; i < teamsInFight.size(); i++) {

							String current = teamsInFight.get(i);
							String[] words = current.split("\\s+");
							String attaquants = words[1];
							String défenceurs = words[2];

							if (playerTeam.equals(attaquants)) {
								try {
									AssautData assaut = AssautData.AssautData.get(attaquants);
									player.teleport(AssautFunctions.tpZoneAlentoure(ClaimData.getFirstLoc(assaut.getDéfenceurs()), ClaimData.getSecondLoc(assaut.getDéfenceurs())));
									player.sendMessage("§eSimplebot §8» §9Vous voilà sur le champ de bataille !");
								} catch (NullPointerException e) {
									continue;
								}
							} else if(playerTeam.equals(défenceurs)) {
								player.sendMessage("§eSimplebot §8» §cVous pouvez utiliser cette commande uniquement si vous êtes attaquants. Utilisez plûtot §c§l/team tpClaim§c.");
							}
						}
					}
					
				} catch (NullPointerException e) {
					player.sendMessage("§eSimplebot §8» §cVous ne pouvez utiliser cette commande uniquement si vous êtes dans un assaut !");
				}
				break;
				
			case "tpClaim":
				try {
					String playerTeam = TeamData.getPlayerTeam(player.getUniqueId());
					Location chestLoc = TeamData.getBankChest(playerTeam);
					if(chestLoc == null) {
						Location loc1 = ClaimData.getFirstLoc(playerTeam);
						Location loc2 = ClaimData.getSecondLoc(playerTeam);
						AssautFunctions.tpZoneClaim(loc1, loc2);
						player.sendMessage("§eSimplebot §8» §9Vous voilà chez vous !");
						return true;
					}
					chestLoc.setY(chestLoc.getY() + 1);
					player.teleport(chestLoc);
					player.sendMessage("§eSimplebot §8» §9Vous voilà chez vous !");
				} catch (NullPointerException e) {
					player.sendMessage("§eSimplebot §8» §cVeuillez définir la zone de claim de votre team.");
				}
				break;
				
			default:
				break;

			}

			return true;

		}

		return false;

	}

}
