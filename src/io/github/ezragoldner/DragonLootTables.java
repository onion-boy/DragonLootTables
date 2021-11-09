package io.github.ezragoldner;

import org.bukkit.plugin.java.JavaPlugin;

public class DragonLootTables extends JavaPlugin {
		
	@Override
	public void onEnable() {
		registerEventListeners();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerEventListeners() {
		getServer().getPluginManager().registerEvents(new DragonDeathListener(this), this);
	}
	
	protected boolean booleanChance(int chance) {
		int rand = (int) (Math.random() * 100);
		
		if (rand <= chance) {
			return true;
		}
		else {
			return false;
		}
	}
}
