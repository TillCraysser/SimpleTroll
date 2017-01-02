package de.headblaster.simpletroll;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleTroll extends JavaPlugin{

	public static String prefix = "§4SimpleTroll §c>> §4";
	
	@Override
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage("[" + SimpleTroll.getPluginName() + "] aktiviert.");
	    new TrollEvents(this);
		
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
			
				 for(Player p : TrollEvents.blockedplayers) {
				    	
				    	p.closeInventory();
				    	
				    }
								 
			}
		}, 0, 5);
	    
	}
	
	public static String getPluginName() {
		
		return SimpleTroll.getInstance().getDescription().getName();
		
	}
	
	public static SimpleTroll getInstance() {
		
		return SimpleTroll.getPlugin(SimpleTroll.class);
		
	}
	
}
