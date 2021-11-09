package io.github.ezragoldner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class DragonDeathListener extends AccessiblePlugin implements Listener {
	
	DragonDeathListener(DragonLootTables plugin) {
		super(plugin);
	}

	@EventHandler
	public void onEnderDragonDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof EnderDragon) {
			handleDragonDeath(e);
		}
	}
	
	private void handleDragonDeath(EntityDeathEvent e) {
		World world = e.getEntity().getWorld();
		Location location = new Location(world, 0.0, 66.0, 0.0);
		Block block = world.getBlockAt(location);
		
		block.setType(Material.CHEST);

		Chest chest = (Chest) block.getState();
		ItemStack loot = pickLoot();
		Inventory inventory = chest.getBlockInventory();
		
		setOtherLoot(inventory);
		inventory.setItem(13, loot);
	}
	
	private void setOtherLoot(Inventory inv) {
		int[] slots = {4, 12, 14};
		
		for (int i = 0; i < slots.length; i++) {
			inv.setItem(slots[i], createEnderPearlStack());
		}
		
		inv.setItem(22, randomShulkerStack());
	}
	
	private ItemStack createEnderPearlStack() {
		ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 4);
		return pearls;
	}
	
	private ItemStack pickLoot() {
		if (plugin.booleanChance(47)) {
			ItemStack elytra = createDamagedElytra();
			return elytra;
		}
		else if (plugin.booleanChance(88)) {
			ItemStack egg = new ItemStack(Material.DRAGON_EGG);
			return egg;
		}
		else {
			ItemStack head = new ItemStack(Material.DRAGON_HEAD);
			return head;
		}
	}
	
	private ItemStack randomShulkerStack() {
		int amount = (int) (Math.random() * 10);
		ItemStack shulkers = new ItemStack(Material.SHULKER_SHELL, amount);
		
		return shulkers;
	}
	
	private ItemStack createDamagedElytra() {
		ItemStack item = new ItemStack(Material.ELYTRA);
		ItemMeta meta = item.getItemMeta();
		
		if (meta instanceof Damageable) {
			((Damageable) meta).setDamage(431);
		}
		
		item.setItemMeta(meta);
		
		return item;
	}
}
