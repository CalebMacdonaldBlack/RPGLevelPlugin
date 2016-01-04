package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gigabytedx.rpgleveling.Main;

public class GetXP implements CommandExecutor {

	private Main plugin;

	public GetXP(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GOLD + "Your current XP is: " + ChatColor.BLUE + plugin.playerExperience.getInt(player.getUniqueId().toString() + ".totalXP"));
			player.sendMessage(ChatColor.GOLD + "Your current Swordsman XP is: " + ChatColor.BLUE + plugin.playerExperience.getInt(player.getUniqueId().toString() + ".swordsmanXP"));
			player.sendMessage(ChatColor.GOLD + "Your current Archer XP is: " + ChatColor.BLUE + plugin.playerExperience.getInt(player.getUniqueId().toString() + ".archerXP"));

		}
		return false;
	}

}
