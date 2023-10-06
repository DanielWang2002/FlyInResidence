package mc.danielwang.flyinresidence;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements CommandExecutor {

    public static List<Player> CanFly_List = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (CanFly_List.contains(player)){
                CanFly_List.remove(player);
                player.sendMessage("§f[§7領地飛行§f] §6已關閉領地飛行");
            }else{
                CanFly_List.add(player);
                player.sendMessage("§f[§7領地飛行§f] §6已開啟領地飛行");
            }

        }









        return false;
    }

    public static boolean CanFly(Player player){
        if (CanFly_List.contains(player)){
            return true;
        }else{
            return false;
        }

    }
}
