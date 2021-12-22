package fr.simple.teams.customsItems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.simple.teams.Teams;

public class CustomsItemsListeners implements Listener {

	Teams plugin;

	public CustomsItemsListeners(Teams plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null
					&& event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null) {
				if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore()
						.contains("§6Lançable : oui")) {
					Fireball fireBall = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(),
							Fireball.class);
					String lorePuissance = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore()
							.get(1);
					char puissance = lorePuissance.charAt(14);
					fireBall.setYield(0);
					fireBall.setCustomName("fireBallLevel" + puissance);
					fireBall.setVelocity(event.getPlayer().getLocation().getDirection());

					new BukkitRunnable() {

						@Override
						public void run() {
							if (!fireBall.isDead()) {
								if (puissance == '1') {
									fireBall.getWorld().spawnParticle(Particle.FLAME, fireBall.getLocation(), 1);
									fireBall.getWorld().spawnParticle(Particle.LAVA, fireBall.getLocation(), 1);
								} else if (puissance == '2') {
									fireBall.getWorld().spawnParticle(Particle.FLAME, fireBall.getLocation(), 5);
									fireBall.getWorld().spawnParticle(Particle.LAVA, fireBall.getLocation(), 5);
								} else {
									fireBall.getWorld().spawnParticle(Particle.FLAME, fireBall.getLocation(), 10);
									fireBall.getWorld().spawnParticle(Particle.LAVA, fireBall.getLocation(), 10);
								}
							} else {
								cancel();
							}
						}
					}.runTaskTimer(plugin, 0L, 2L);

					event.getPlayer().getInventory().getItemInMainHand()
							.setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
					event.setCancelled(true);
				} else if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore()
						.contains("§cLancez cet oeuf et")) {
					Egg egg = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Egg.class);
					egg.setCustomName("Oeuf de construction rapide");
					Location loc1 = event.getPlayer().getLocation();
					loc1.setZ(loc1.getZ() + 1);
					egg.setVelocity(loc1.getDirection());
					new BukkitRunnable() {
						int counter = 0;

						@Override
						public void run() {
							if (!egg.isDead()) {
								egg.getWorld().spawnParticle(Particle.NOTE, egg.getLocation(), 10);
								if (egg.getLocation().getBlock().getType() == Material.AIR) {
									new BukkitRunnable() {
										Location loc = egg.getLocation();
										@Override
										public void run() {
											loc.getBlock().setType(Material.BLACK_WOOL);
										}
									}.runTaskLater(plugin, 4L);
									counter++;
								}
							} else {
								cancel();
							}
							if (counter == 50) {
								cancel();
							}
						}
					}.runTaskTimer(plugin, 4L, 1L);
					event.setCancelled(true);
					event.getPlayer().getInventory().getItemInMainHand()
							.setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
				}
			}
		}
	}

	@EventHandler
	public void onLand(ProjectileHitEvent event) {
		if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains("fireBallLevel")) {
			char puissance = event.getEntity().getCustomName().charAt(13);
			if (puissance == '1') {
				event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 2, false);
				for (Entity entity : event.getEntity().getNearbyEntities(2, 2, 2)) {
					if (entity instanceof LivingEntity) {
						LivingEntity livingEntity = (LivingEntity) entity;
						double distance = event.getEntity().getLocation().distanceSquared(livingEntity.getLocation());
						if (distance <= 0.5) {
							livingEntity.setVelocity(new Location(livingEntity.getWorld(), 0, 1, 0).toVector());
						} else {
							livingEntity.setVelocity(livingEntity.getLocation()
									.subtract(event.getEntity().getLocation()).toVector().multiply(2 / distance));
						}
					}
				}
			} else if (puissance == '2') {
				event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 4, false);
				for (Entity entity : event.getEntity().getNearbyEntities(4, 4, 4)) {
					if (entity instanceof LivingEntity) {
						LivingEntity livingEntity = (LivingEntity) entity;
						double distance = event.getEntity().getLocation().distanceSquared(livingEntity.getLocation());
						if (distance <= 0.5) {
							livingEntity.setVelocity(new Location(livingEntity.getWorld(), 0, 1, 0).toVector());
						} else {
							livingEntity.setVelocity(livingEntity.getLocation()
									.subtract(event.getEntity().getLocation()).toVector().multiply(4 / distance));
						}
					}
				}
			} else {
				event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 6, true);
				for (Entity entity : event.getEntity().getNearbyEntities(6, 6, 6)) {
					if (entity instanceof LivingEntity) {
						LivingEntity livingEntity = (LivingEntity) entity;
						double distance = event.getEntity().getLocation().distanceSquared(livingEntity.getLocation());
						if (distance <= 0.5) {
							livingEntity.setVelocity(new Location(livingEntity.getWorld(), 0, 1, 0).toVector());
						} else {
							livingEntity.setVelocity(livingEntity.getLocation()
									.subtract(event.getEntity().getLocation()).toVector().multiply(6 / distance));
						}
					}
				}
			}
		}
	}

}
