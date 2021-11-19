package fr.simple.teams.claims;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class RegionManager {

	public Location firstLoc, secondLoc;
	public String name;

	public RegionManager(Location firstPoint, Location secondPoint, String teamName) {
		firstLoc = new Location(firstPoint.getWorld(), firstPoint.getX(), firstPoint.getY(), firstPoint.getZ());
		secondLoc = new Location(secondPoint.getWorld(), secondPoint.getX(), secondPoint.getY(), secondPoint.getZ());
		name = teamName;
	}

	public String getName() {
		return name;
	}

	public double firstLoc(double a, double b) {
		return a < b ? a : b;
	}

	public double secondLoc(double a, double b) {
		return a > b ? a : b;
	}

	public boolean isInArea(Location loc) {
		boolean r = false;
		double xMin = Math.min(firstLoc.getX(), secondLoc.getX()), xMax = Math.max(firstLoc.getX(), secondLoc.getX());
		double zMin = Math.min(firstLoc.getZ(), secondLoc.getZ()), zMax = Math.max(firstLoc.getZ(), secondLoc.getZ());
		if (loc.getX() >= xMin && loc.getX() <= xMax && loc.getZ() >= zMin && loc.getZ() <= zMax) {
			r = true;
		}
		return (r);
	}

	public Location getMiddle() {
		double a, b;
		a = (secondLoc.getX() - firstLoc.getX()) / 2D + firstLoc.getX();
		b = (secondLoc.getZ() - firstLoc.getZ()) / 2D + firstLoc.getZ();

		return new Location(firstLoc.getWorld(), a, firstLoc.getY(), b);
	}

	public List<Location> getArea() {
		List<Location> blocksLocation = new ArrayList<>();

		for (int x = firstLoc.getBlockX(); x <= secondLoc.getBlockX(); x++) {
			for (int z = firstLoc.getBlockZ(); z <= secondLoc.getBlockZ(); z++) {
				for (int y = firstLoc.getBlockY(); y <= secondLoc.getBlockY(); y++)
					blocksLocation.add(new Location(firstLoc.getWorld(), x, y, z));
			}
		}
		return blocksLocation;
	}

}
