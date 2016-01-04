package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gigabytedx.rpgleveling.Main;
import com.gigabytedx.rpgleveling.skills.Skill;

public class SetSkillExperience implements CommandExecutor {

	private Main plugin;

	public SetSkillExperience(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player && args.length == 2){
			Player player = (Player) sender;
			boolean wasSkillFound = false;
			try{
			for(Skill skill: plugin.skills.getSkills()){
				if(args[0].toLowerCase().equals(skill.getName().toLowerCase())){
					plugin.playerExperience.set(player.getUniqueId().toString() + "." + skill.getName(), Integer.parseInt(args[1]));
					plugin.savePlayerExperienceConfig();
					player.sendMessage(ChatColor.GREEN + "XP for " + args[0] + " is now set to " + ChatColor.RED + args[1]);
				wasSkillFound = true;
				}
			}
			if(!wasSkillFound){
				player.sendMessage(ChatColor.RED + "Could not find skill with name: " + args[0]);
			}
			
			
			for(String xpTypeName: plugin.playerExperience.getConfigurationSection(player.getUniqueId().toString()).getKeys(false)){
				player.sendMessage(ChatColor.GOLD + "XP for "+ xpTypeName +" is: " + ChatColor.BLUE + plugin.playerExperience.getInt(player.getUniqueId().toString() + "." + xpTypeName));
			}
			}catch(NumberFormatException e){
				player.sendMessage(ChatColor.RED + args[1] + " is not a valid number");
			}
		}else{
			sender.sendMessage(ChatColor.RED + "Syntax /setskillexperience <skill name> <xp as number> . You also must be a player to use this command");
		}
		return false;
	}

}
