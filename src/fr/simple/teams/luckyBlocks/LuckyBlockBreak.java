package fr.simple.teams.luckyBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.simple.teams.Teams;
import fr.simple.teams.customsItems.BridgeEgg;
import fr.simple.teams.customsItems.FireBall;

public class LuckyBlockBreak implements Listener {
	
	private Teams teams;

	public LuckyBlockBreak(Teams teams) {
		this.teams = teams;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getState() instanceof Skull) {
			Skull skull = (Skull) event.getBlock().getState();
			try {
				if (skull.getOwner().equalsIgnoreCase("Luck")) {

					Player player = event.getPlayer();
					Block block = event.getBlock();
					BlockState bs = block.getState();

					event.setCancelled(true);
					block.setType(Material.AIR);

					Random random = new Random();
					int alea = random.nextInt(100);

					switch (alea) {

					case 0:

						break;
					case 1:
						// customcompass.setItemMeta(customM);
						// player.getInventory().setItem(4, customcompass);
						// player.updateInventory();
						ItemStack bow = new ItemStack(Material.BOW);
						ItemMeta customM = bow.getItemMeta();
						customM.setDisplayName("§aArc magique");
						customM.setLore(Arrays.asList("Cet arc est magique,", "il vous donne des pouvoirs"));
						bow.setItemMeta(customM);
						player.getInventory().setItem(4, bow);
						player.updateInventory();
						break;
					case 2:
						// Material material = Material.
						ItemStack wool1 = new ItemStack(Material.GREEN_WOOL);
						wool1.setAmount(64);
						ItemMeta meta1 = wool1.getItemMeta();
						meta1.setDisplayName("§aBlocs de construction");
						wool1.setItemMeta(meta1);
						block.getWorld().dropItem(block.getLocation(), wool1);
						break;
					case 3:
						ItemStack wool2 = new ItemStack(Material.ORANGE_WOOL);
						wool2.setAmount(32);
						ItemMeta meta2 = wool2.getItemMeta();
						meta2.setDisplayName("§6Blocs de construction");
						wool2.setItemMeta(meta2);
						block.getWorld().dropItem(block.getLocation(), wool2);
						break;
					case 4:
						ItemStack wool3 = new ItemStack(Material.RED_WOOL);
						wool3.setAmount(16);
						ItemMeta meta3 = wool3.getItemMeta();
						meta3.setDisplayName("§cBlocs de construction");
						wool3.setItemMeta(meta3);
						block.getWorld().dropItem(block.getLocation(), wool3);
						break;
					case 5:
						ItemStack sword1 = new ItemStack(Material.STONE_SWORD);
						sword1.setAmount(1);
						ItemMeta meta4 = sword1.getItemMeta();
						meta4.setDisplayName("§7Épée bas de gamme");
						sword1.setItemMeta(meta4);
						block.getWorld().dropItem(block.getLocation(), sword1);
						break;
					case 6:
						ItemStack sword2 = new ItemStack(Material.GOLDEN_SWORD);
						sword2.setAmount(1);
						ItemMeta meta5 = sword2.getItemMeta();
						meta5.setDisplayName("§6Épée haut de gamme");
						sword2.setItemMeta(meta5);
						block.getWorld().dropItem(block.getLocation(), sword2);
						break;
					case 7:
						ItemStack sword3 = new ItemStack(Material.IRON_SWORD);
						sword3.setAmount(1);
						ItemMeta meta6 = sword3.getItemMeta();
						meta6.setDisplayName("§fÉpée milieu de gamme");
						sword3.setItemMeta(meta6);
						block.getWorld().dropItem(block.getLocation(), sword3);
						break;
					case 8:
						ItemStack sword4 = new ItemStack(Material.WOODEN_SWORD);
						sword4.setAmount(1);
						ItemMeta meta7 = sword4.getItemMeta();
						meta7.setDisplayName("§8Épée claquée au sol");
						sword4.setItemMeta(meta7);
						block.getWorld().dropItem(block.getLocation(), sword4);
						break;
					case 9:
						block.getWorld().createExplosion(block.getLocation(), 1, false);
						break;
					case 10:
						block.getWorld().createExplosion(block.getLocation(), 2, false);
						break;
					case 11:
						block.getWorld().createExplosion(block.getLocation(), 3, true);
						break;
					case 12:
						block.getWorld().createExplosion(block.getLocation(), 4, true);
						break;
					case 13:
						block.getWorld().createExplosion(block.getLocation(), 5, true);
						break;
					case 14:
						block.getWorld().createExplosion(block.getLocation(), 6, true);
						break;
					case 15:
						ItemStack TNT1 = new ItemStack(Material.TNT);
						TNT1.setAmount(4);
						block.getWorld().dropItem(block.getLocation(), TNT1);
						break;
					case 16:
						ItemStack TNT2 = new ItemStack(Material.TNT);
						TNT2.setAmount(8);
						block.getWorld().dropItem(block.getLocation(), TNT2);
						break;
					case 17:
						ItemStack TNT3 = new ItemStack(Material.TNT);
						TNT3.setAmount(12);
						block.getWorld().dropItem(block.getLocation(), TNT3);
						break;
					case 18:
						ItemStack TNT4 = new ItemStack(Material.TNT);
						TNT4.setAmount(16);
						block.getWorld().dropItem(block.getLocation(), TNT4);
						break;
					case 19:
						ItemStack TNT5 = new ItemStack(Material.TNT);
						TNT5.setAmount(20);
						block.getWorld().dropItem(block.getLocation(), TNT5);
						break;
					case 20:
						block.getWorld().dropItem(block.getLocation(), FireBall.FireBall1);
						break;
					case 21:
						block.getWorld().dropItem(block.getLocation(), FireBall.FireBall2);
						break;
					case 22:
						block.getWorld().dropItem(block.getLocation(), FireBall.FireBall3);
						break;
					case 23:
						ItemStack fireBall1 = FireBall.FireBall1;
						fireBall1.setAmount(4);
						block.getWorld().dropItem(block.getLocation(), fireBall1);
						break;
					case 24:
						ItemStack fireBall2 = FireBall.FireBall1;
						fireBall2.setAmount(3);
						block.getWorld().dropItem(block.getLocation(), fireBall2);
						break;
					case 25:
						ItemStack fireBall3 = FireBall.FireBall1;
						fireBall3.setAmount(2);
						block.getWorld().dropItem(block.getLocation(), fireBall3);
						break;
					case 26:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 1));
						break;
					case 27:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 2));
						break;
					case 28:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 3));
						break;
					case 29:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 4));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.FLINT_AND_STEEL, 1));
						break;
					case 30:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 5));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.FLINT_AND_STEEL, 1));
						break;
					case 31:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.ENDER_PEARL, 1));
						break;
					case 32:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.ENDER_PEARL, 2));
						break;
					case 33:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.BOW, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.ARROW, 16));
						break;
					case 34:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.BOW, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.ARROW, 32));
						break;
					case 35:
						ItemStack stick1 = new ItemStack(Material.WOODEN_SWORD);
						stick1.addEnchantment(Enchantment.KNOCKBACK, 2);
						block.getWorld().dropItem(block.getLocation(), stick1);
						break;
					case 36:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 1));
						break;
					case 37:
						player.sendMessage("§aPas de chance (quoi que, c'est mieu que cetains autres events ;)).");
						break;
					case 38:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.IRON_AXE, 1));
						break;
					case 39:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.IRON_CHESTPLATE, 1));
						break;
					case 40:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.IRON_HELMET, 1));
						break;
					case 41:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.IRON_LEGGINGS, 1));
						break;
					case 42:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.IRON_BOOTS, 1));
						break;
					case 43:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.DIAMOND_HOE, 1));
						break;
					case 44:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 1));
						break;
					case 45:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.GOLDEN_APPLE, 3));
						break;
					case 46:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 1));
						break;
					case 47:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.BOW, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.SPECTRAL_ARROW, 4));
						break;
					case 48:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.POPPY, 1));
						break;
					case 49:
						ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
						BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
						bookMeta.setTitle("Journal de bord");
						bookMeta.setAuthor("zetiti10");
						writtenBook.setItemMeta(bookMeta);
						List<String> pages = new ArrayList<String>();
						pages.add("Mercredi 22 décembre 2021 : Le temps passe tellement vite, "
								+ "il est déjà presque Noël et je dois finir touts les events de lucky blocks."
								+ " Je suis actuellement au 49ème sur l'objectif d'en faire 100. C'est long, très long. "
								+ "J'en ai mare mais je sais que des joueurs attendent et ont impatience que le plugin soit terminé. "
								+ "Nous avons déjà fixé la date du test au samedi 8 janvier 2022. Nous allons l'annoncer "
								+ "le 25 décembre, comme un cadeau de Noël. J'y suis presque.");
						bookMeta.setPages(pages);
						writtenBook.setItemMeta(bookMeta);
						break;
					case 50:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.APPLE, 10));
						break;
					case 51:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.POISONOUS_POTATO, 1));
						break;
					case 52:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.COOKED_BEEF, 5));
						break;
					case 53:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.IRON_GOLEM);
						break;
					case 54:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.ZOMBIE);
						break;
					case 55:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.GHAST);
						break;
					case 56:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.LIGHTNING);
						break;
					case 57:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.CREEPER);
						break;
					case 58:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TOTEM_OF_UNDYING, 5));
						break;
					case 59:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WHITE_WOOL, 32));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.LADDER, 32));
						break;
					case 60:
						player.sendMessage("§aVous avez gagné un kit de canon ! Mode d'emploi ici : https://fr.wikihow.com/construire-un-canon-dans-Minecraft#Construire-un-grand-canon");
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.SANDSTONE_SLAB, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.DISPENSER, 8));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.SAND, 20));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.OAK_PLANKS, 7));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.STONE, 13));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.REPEATER, 4));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.REDSTONE, 15));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WATER_BUCKET, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.OAK_BUTTON, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.TNT, 40));
						break;
					case 61:
						player.sendMessage("§aVous venez de gagner 34,81€. Bravo.");
						teams.eco.depositPlayer(player, 35.81);
						break;
					case 62:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.EXPERIENCE_BOTTLE, 20));
						break;
					case 63:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WOODEN_SWORD, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WOODEN_AXE, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WOODEN_PICKAXE, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WOODEN_HOE, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.WOODEN_SHOVEL, 1));
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.SHEARS, 32));
						break;
					case 64:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.SNOWBALL, 16));
						break;
					case 65:
						block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(Material.SNOWBALL, 32));
						break;
					case 66:
						block.getWorld().generateTree(block.getLocation(), TreeType.BIG_TREE);
						break;
					case 67:
						block.getWorld().generateTree(block.getLocation(), TreeType.RED_MUSHROOM);
						break;
					case 68:
						block.getWorld().createExplosion(player.getLocation(), 2, false);
						break;
					case 69:
						block.getWorld().createExplosion(player.getLocation(), 3, false);
						break;
					case 70:
						block.getWorld().createExplosion(player.getLocation(), 4, false);
						break;
					case 71:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.SKELETON);
						break;
					case 72:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.SKELETON_HORSE);
						break;
					case 73:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.WITHER_SKELETON);
						break;
					case 74:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.BLAZE);
						break;
					case 75:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.SPIDER);
						break;
					case 76:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.ZOMBIE_VILLAGER);
						break;
					case 77:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.PIGLIN_BRUTE);
						break;
					case 78:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.CAVE_SPIDER);
						break;
					case 79:
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60 * 20, 1));
						player.sendMessage("§aVous devenez invisible pendant 1 minute !");
						break;
					case 80:
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 1));
						player.sendMessage("§6Vous ne voyez plus rien.");
						break;
					case 81:
						player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60 * 20, 1));
						player.sendMessage("§6Vous êtes fire-proof.");
						break;
					case 82:
						player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 20, 1));
						player.sendMessage("§6Vous brillez, quelle idée limineuse !");
						break;
					case 83:
						player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 10 * 20, 1));
						break;
					case 84:
						player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 10 * 20, 1));
						break;
					case 85:
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 60 * 20, 1));
						player.sendMessage("§6Manger.");
						break;
					case 86:
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60 * 20, 3));
						player.sendMessage("§6Attention a ne pas vous taper le plafond");
						break;
					case 87:
						player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10 * 20, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 20, 1));
						player.sendMessage("§6Fly Fly Fly...");
						break;
					case 88:
						player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60 * 20, 1));
						player.sendMessage("§6Vous avez des yeux de chat");
						break;
					case 89:
						player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 1));
						player.sendMessage("§6Vous êtes empoisonés");
						break;
					case 90:
						player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 1));
						player.sendMessage("§6Vous : OK Google, met moi un effet de regénération");
						player.sendMessage("§6Google : Ok, je vous met REGENERATION pendant 10 secondes.");
						break;
					case 91:
						player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 10 * 20, 1));
						break;
					case 92:
						player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5 * 20, 1));
						break;
					case 93:
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 15 * 20, 1));
						player.sendMessage("§6vous êtes lent.");
						break;
					case 94:
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 15 * 20, 1));
						player.sendMessage("§6Vous êtes inneficasse.");
						break;
					case 95:
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25 * 20, 1));
						player.sendMessage("§6Plus vite !");
						break;
					case 96:
						player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 60 * 20, 1));
						player.sendMessage("§6L'eau est maintenant votre ami (enfin pour une minute seulement;().");
						break;
					case 97:
						player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * 20, 1));
						break;
					case 98:
						block.getWorld().dropItem(block.getLocation(), BridgeEgg.BridgeEgg);
						break;
					case 99:
						block.getWorld().dropItem(block.getLocation(), BridgeEgg.BridgeEgg);
						block.getWorld().dropItem(block.getLocation(), BridgeEgg.BridgeEgg);
						break;
					case 100:
						block.getWorld().dropItem(block.getLocation(), BridgeEgg.BridgeEgg);
						block.getWorld().dropItem(block.getLocation(), BridgeEgg.BridgeEgg);
						block.getWorld().dropItem(block.getLocation(), BridgeEgg.BridgeEgg);
						break;
					default:

						break;
					}

				}
			} catch (NullPointerException e) {

			}
		}
	}

}

//block.getWorld().dropItem(block.getLocation(), LuckyBlock.item(, ));
