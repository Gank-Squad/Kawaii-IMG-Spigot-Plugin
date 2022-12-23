package com.jasonmzx.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinHandle implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
    {
		Player user = event.getPlayer();
		Bukkit.broadcastMessage(user.getName() + " SUP ");
		JsonHandle.createUser(user.getName());
    }
	
}
