package fr.simple.teams.luckyBlocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;


public class LuckyBlock {
	@SuppressWarnings("deprecation")
	public static void spawn(Location loc) {
		
		Block block = loc.getBlock();
		block.setType(Material.PLAYER_HEAD);
		
		Skull skull = (Skull) block.getState();
		skull.setOwner("Luck");
		skull.update();
		
	}
	
}
