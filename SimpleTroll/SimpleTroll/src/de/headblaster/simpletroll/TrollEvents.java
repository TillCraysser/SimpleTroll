package de.headblaster.simpletroll;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerCommandEvent;

import net.minecraft.server.v1_11_R1.EnumDifficulty;
import net.minecraft.server.v1_11_R1.EnumGamemode;
import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_11_R1.PacketPlayOutRespawn;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_11_R1.WorldType;

@SuppressWarnings("deprecation")
public class TrollEvents implements Listener{

	public String prefix = SimpleTroll.prefix;
	
	public SimpleTroll st;
	
	public static ArrayList<Player> nomove = new ArrayList<>();
	public static ArrayList<Player> muted = new ArrayList<>();
	public static ArrayList<CommandSender> blocked = new ArrayList<>();
    public static ArrayList<Player> capslocker = new ArrayList<>();
    public static ArrayList<Player> blockedplayers = new ArrayList<>();
    public static ArrayList<Player> cobweb = new ArrayList<>();
    
	public TrollEvents(SimpleTroll main) {
		
		this.st = main;
		
		main.getServer().getPluginManager().registerEvents(this, main);
		
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		if(muted.contains(e.getPlayer())) e.setCancelled(true);
		if(capslocker.contains(e.getPlayer())) { e.setMessage(e.getMessage().toUpperCase()); }
		
		if(e.getMessage().startsWith("#")) {
			
			if(e.getPlayer().hasPermission("simpletroll.troll")) {
				
				String msg = e.getMessage().replace("#", "");
				String[] args = msg.split(" ");
				
				Event event = new PlayerTrollEvent(e.getPlayer(), args);
				
				SimpleTroll.getInstance().getServer().getPluginManager().callEvent(event);
				e.setCancelled(true);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onTroll1(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
			
		if(args[0].equalsIgnoreCase("fakeop")) {
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					Player target = Bukkit.getPlayer(args[1]);
					target.sendMessage(ChatColor.ITALIC + "" + ChatColor.GRAY + ChatColor.ITALIC + "[Server: " + target.getName() + " wurde zum Operator ernannt]");
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else e.getPlayer().sendMessage(prefix + "#fakeop <Player>");
			
		} else if(args[0].equalsIgnoreCase("fakedeop")) {
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					Player target = Bukkit.getPlayer(args[1]);
					target.sendMessage(ChatColor.ITALIC + "" + ChatColor.GRAY + ChatColor.ITALIC + "[Server: " + target.getName() + " ist nun kein Operator mehr]");
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else e.getPlayer().sendMessage(prefix + "#fakedeop <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll2(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("rain")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(2, 8.0F);
					((CraftPlayer) Bukkit.getPlayer(args[1])).getHandle().playerConnection.sendPacket(packet);
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#rain <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll3(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("jumpscare")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(10, 1.0F);
					((CraftPlayer) Bukkit.getPlayer(args[1])).getHandle().playerConnection.sendPacket(packet);
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#jumpscare <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll4(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("endscreen")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(4, 1.0F);
					((CraftPlayer)Bukkit.getPlayer(args[1])).getHandle().playerConnection.sendPacket(packet);
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#endscreen <Player>");
			
		}
		
	}
	@EventHandler
	public void onTroll5(PlayerTrollEvent e) {
		
		String[] args = e.getArguments(); 
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("nomove")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					if(nomove.contains(Bukkit.getPlayer(args[1]))) {
						
						nomove.remove(Bukkit.getPlayer(args[1]));
						p.sendMessage(prefix + args[1] + "kann sich nun wieder bewegen.");
						
					} else {
					
					nomove.add(Bukkit.getPlayer(args[1]));
					p.sendMessage(prefix + args[1] + " kann sich nun nicht mehr bewegen.");
					
					}
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#nomove <Player>");
			
		} else if(args[0].equalsIgnoreCase("move")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					if(nomove.contains(Bukkit.getPlayer(args[1]))) {
					
					nomove.remove(Bukkit.getPlayer(args[1]));
					p.sendMessage(prefix + args[1] + "kann sich nun wieder bewegen.");
					
					} else p.sendMessage(prefix + args[1] + " war nicht geblockt.");
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#move <Player>");
		}
		
	}
	
	@EventHandler
	public void onTroll6(PlayerTrollEvent e) {
		
		String[] args = e.getArguments(); 
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("mute")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					muted.add(Bukkit.getPlayer(args[1]));
					p.sendMessage(prefix + "Du hast "+ args[1] + " gemuted.");
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#mute <Player>");
			
		} else if(args[0].equalsIgnoreCase("unmute")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					if(nomove.contains(Bukkit.getPlayer(args[1]))) {
					
					muted.remove(Bukkit.getPlayer(args[1]));
					p.sendMessage(prefix + "Du hast " + args[1] + " entmuted"); 
					
					} else p.sendMessage(prefix + args[1] + " ist nicht gemutet.");
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#unmute <Player>");
		}
		
	}
	
	@EventHandler
	public void onTroll7(PlayerTrollEvent e) {
		
		String[] args = e.getArguments(); 
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("block")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					final Player target = Bukkit.getPlayer(args[1]);
					
				    blockedplayers.add(target);
				    	
					blocked.add(Bukkit.getPlayer(args[1]));
					
					p.sendMessage(prefix + "Du hast " + args[1] + " geblockt. Er kann nun keine Commands ausführen oder sein Inventar/Chat etc. öffnen.");
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#nomove <Player>");
			
		} else if(args[0].equalsIgnoreCase("unblock")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					blocked.remove(Bukkit.getPlayer(args[1]));
					p.sendMessage(prefix + "Du hast " + args[1] + " entblockt. Er kann nun wieder Commands ausführen.");
					
					blockedplayers.remove(Bukkit.getPlayer(args[1]));
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#unblock <Player>");
		} else if(args[0].equalsIgnoreCase("blockconsole")) {
			
			if(args.length == 1) {
				
					blocked.add(Bukkit.getConsoleSender());
					p.sendMessage(prefix + "Du hast die Console geblockt. Sie kann nun keine Commands ausführen.");
									
			} else p.sendMessage(prefix + "#blockconsole");
		} else if(args[0].equalsIgnoreCase("unblockconsole")) {
			
			if(args.length == 1) {
						
					blocked.remove(Bukkit.getConsoleSender());
					p.sendMessage(prefix + "Du hast die Console entblockt. Sie kann nun wieder Commands ausführen.");
									
			} else p.sendMessage(prefix + "#unblockconsole");
		}
		
	}
	
	@EventHandler
	public void onTroll8(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("soundtroll")) {
			
			if(args.length == 5) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
				try {	
				for(int i = 0;i < Integer.parseInt(args[2]);i++) {
					
					for(Sound s: Sound.values()) {
						
						Player target = Bukkit.getPlayer(args[1]);
						
					    target.playSound(target.getLocation(), s, Float.parseFloat(args[3]), Float.parseFloat(args[4]));

					    target.sendMessage(s.name());
					    
					}
				}
				} catch(Exception ex) {
					
					p.sendMessage(prefix + args[2] + "," + args[3] + " oder "+ args[4] + " müssen Nummern sein.");
					ex.printStackTrace();
					
				}
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#soundtroll <Player> <Oftheit> <Lautstärke> <Tonhöhe>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll9(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("crash")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					for(int i = 0;i < 5;i++) {
					
                      Player target = Bukkit.getPlayer(args[1]);
					
                      //target.performCommand("particle flame ~ ~ ~ 10 10 10 0 2147483647 force");
						
                      ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.PORTAL, true, 0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE, new int[] { Integer.MAX_VALUE }));
                      
                      target.spigot().playEffect(target.getLocation(), Effect.FLAME, 0, 10, 10, 10, 10,2147483647 , 2147483647, 6);
                     						
					}
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#endscreen <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll10(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("vector")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					final Player target = Bukkit.getPlayer(args[1]);
					
					target.setAI(false);
						
					Bukkit.getScheduler().scheduleSyncDelayedTask(SimpleTroll.getInstance(), new Runnable() {
						
						@Override
						public void run() {

                            target.setAI(true);
							
						}
					},30*20);
						
						
					
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#endscreen <Player>");
			
		}

	}
	
	@EventHandler
	public void onTroll11(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("einschließen")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					final Player target = Bukkit.getPlayer(args[1]);
						
					 ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutRespawn(1, EnumDifficulty.HARD, WorldType.NORMAL_1_1, EnumGamemode.NOT_SET));
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#einschließen <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll12(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("demoscreen")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					final Player target = Bukkit.getPlayer(args[1]);
						
					((CraftPlayer) target).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0.0F));
					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#demoscreen <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll13(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("capslocker")) {
			
			if(args.length == 2) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
										
					final Player target = Bukkit.getPlayer(args[1]);
						
					if(capslocker.contains(target)) {
						
						capslocker.remove(target);
						p.sendMessage(prefix + args[1] + " kann nun wieder normal schreiben.");
						
					} else {
						
						capslocker.add(target);
						p.sendMessage(prefix + args[1] + " kann nun nur noch in Caps schreiben.");
						
					}

					
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
			} else p.sendMessage(prefix + "#capslockerr <Player>");
			
		}
		
	}
	
	@EventHandler
	public void onTroll14(PlayerTrollEvent e) {
		
		String[] args = e.getArguments();
		Player p = e.getPlayer();
		
		if(args[0].equalsIgnoreCase("cobwebtroll")) {
			
			if(args.length == 2) {
				
				final Player target = Bukkit.getPlayer(args[1]);
				
				if(cobweb.contains(e.getPlayer())) {
				
			      cobweb.remove(e.getPlayer());
				  p.sendMessage(prefix + args[1] + " wird jetzt nicht mehr mit Cobwebs getrollt.");
			    
				} else {
					
				  cobweb.add(target);
				  p.sendMessage(prefix + args[1] + " wird jetzt mit Cobwebs getrollt.");
				  
				}
			    
				} else p.sendMessage(prefix + "Dieser Spieler ist nicht online!");
				
		}  else p.sendMessage(prefix + "#cobwebtroll <Player>");
			
	}	   
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		if(nomove.contains(e.getPlayer())) e.getPlayer().teleport(e.getFrom());
		
		if(cobweb.contains(e.getPlayer())) {
			
			e.getPlayer().sendBlockChange(e.getPlayer().getLocation().add(0, 1, 0), Material.WEB, (byte)1);
			
		}
		
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		
		if(blocked.contains(e.getPlayer())) e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onCmdServer(ServerCommandEvent e) {
		
		if(blocked.contains(e.getSender())) e.setCancelled(true);
		
	}
	
}
