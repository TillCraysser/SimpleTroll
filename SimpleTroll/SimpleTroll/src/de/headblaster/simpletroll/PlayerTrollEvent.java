package de.headblaster.simpletroll;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTrollEvent extends Event{

	public static HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		
		return handlers;
		
	}
	
	public Player player;
	public String[] arguments;
	
	public PlayerTrollEvent(Player p,String[] args) {
		
		this.player = p;
		this.arguments = args;
		
	}
	
	public String[] getArguments() {
		return arguments;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
