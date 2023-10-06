package mc.danielwang.flyinresidence;

import mc.danielwang.flyinresidence.tasks.timeLimit_Task;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class rfTimeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 && sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("§f[§7領地飛行§f] " + "§6" + player.getName() + " §e當前可飛行時間： " + timeFormat(timeLimit_Task.getTimeNow(player.getUniqueId().toString())));
        }

        return false;
    }

    public String timeFormat(int time){
        String day = Integer.toString(time / 60 / 60 / 24);
        String hour = Integer.toString((time-(60*60*24*Integer.parseInt(day))) / 3600);
        String minute = Integer.toString((time-60*60*24*Integer.parseInt(day)-3600*Integer.parseInt(hour))/60);
        String second = Integer.toString(time - 60 * 60 * 24 * Integer.parseInt(day) - 3600 * Integer.parseInt(hour) - 60 * Integer.parseInt(minute));


        return "§c" + day + " §6天 §c" + hour + " §6時 §c" + minute + " §6分 §c" + second + " §6秒";
    }
}
