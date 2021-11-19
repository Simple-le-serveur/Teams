package fr.simple.teams.claims;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
 
import java.util.HashMap;
import java.util.Map;

public class GamePlayer {
	 
    private Player player;
 
    private Location pos1;
    private Location pos2;
 
    public static Map<String, GamePlayer> gamePlayers = new HashMap<>();
 
    public GamePlayer(String playerName) {
        this.player = Bukkit.getPlayer(playerName);
 
        gamePlayers.put(player.getName(), this);
    }
 
    public Location getPos1() {
        return pos1;
    }
 
    public Location getPos2() {
        return pos2;
    }
 
    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }
 
    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }
}
