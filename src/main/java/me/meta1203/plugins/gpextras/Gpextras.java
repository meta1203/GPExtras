package me.meta1203.plugins.gpextras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Gpextras extends JavaPlugin {
	public static Map<Integer, Short> itemBan = new HashMap<Integer, Short>();
	public static WorldEditPlugin wep = null;
	public static GriefPrevention gp = null;
	
    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    public void onEnable() {
    	this.saveDefaultConfig();
    	loadItems();
    	gp = (GriefPrevention)Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention");
    	getWorldEdit();
        getServer().getPluginManager().registerEvents(new ExtrasListener(), this);
    }
    
    private void loadItems() {
    	Configuration cfg = this.getConfig();
    	List<String> init = cfg.getStringList("claimItems");
    	for (String x : init) {
    		String[] split = x.split(":");
    		itemBan.put(Integer.parseInt(split[0]), Short.parseShort(split[1]));
    	}
    }
    
    private void getWorldEdit() {
    	Plugin basic = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    	if (basic != null) {
    		wep = (WorldEditPlugin)basic;
    	}
    }
}

