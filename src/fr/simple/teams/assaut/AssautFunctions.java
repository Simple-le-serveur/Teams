package fr.simple.teams.assaut;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;

public class AssautFunctions {

	public static Location tpZoneAlentoure(Location claimB1, Location claimB2) {
		
		double xMin = Math.min(claimB1.getX(), claimB2.getX()), xMax = Math.max(claimB1.getX(), claimB2.getX());
		double zMin = Math.min(claimB1.getZ(), claimB2.getZ()), zMax = Math.max(claimB1.getZ(), claimB2.getZ());
		
		Random r = new Random();
		int face = r.nextInt(3);
		int ecart1 = r.nextInt(50);
		int ecart2 = r.nextInt(50);
		
		Location locToTp = new Location(claimB1.getWorld(), 0, 0, 0);
		
		switch(face) {
		case 0:
			locToTp.setZ(zMax + ecart1);
			locToTp.setX(xMin + ecart2);
			break;
		case 1:
			locToTp.setZ(zMax - ecart1);
			locToTp.setX(xMin - ecart2);
			break;
		case 2:
			locToTp.setZ(zMin - ecart1);
			locToTp.setX(xMin - ecart2);
			break;
		case 3:
			locToTp.setZ(zMin + ecart1);
			locToTp.setX(xMax + ecart2);
			break;
		}
		
		for (int i1 = 255; i1 > 0; i1--) {
			locToTp.setY(i1);
			if(locToTp.getBlock().getType() == Material.AIR) {
				continue;
			} else {
				locToTp.setY(i1 + 1);
				break; 
			}
		}
		
		return(locToTp);
		
	}
	
	public static Location tpZoneClaim(Location claimB1, Location claimB2) {
		
		double xMin = Math.min(claimB1.getX(), claimB2.getX()), xMax = Math.max(claimB1.getX(), claimB2.getX());
		double zMin = Math.min(claimB1.getZ(), claimB2.getZ()), zMax = Math.max(claimB1.getZ(), claimB2.getZ());
		
		Random r = new Random();
		int ecartX = r.nextInt((int) (xMax - xMin));
		int ecartZ = r.nextInt((int) (zMax - zMin));
		
		Location locToTp = new Location(claimB1.getWorld(), ecartX + xMin, 0, ecartZ + zMin);
		
		for (int i1 = 255; i1 > 0; i1--) {
			locToTp.setY(i1);
			if(locToTp.getBlock().getType() == Material.AIR) {
				continue;
			} else {
				locToTp.setY(i1 + 1);
				break; 
			}
		}
		
		return(locToTp);
		
	}
	
}
