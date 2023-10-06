package mc.danielwang.flyinresidence;

import mc.danielwang.flyinresidence.tasks.timeLimit_Task;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class timeCommand implements CommandExecutor {

    private static timeLimit_Task tlt = timeLimit_Task.getPlugin();
    private static Map<String,Integer> Player_FlyTime = FlyInResidence.get_Player_FlyTime();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //fradmin add/set {id} {time}
        if (sender.isOp()){
            if (args.length == 3) {
                String method = args[0];
                String player_name = args[1];
                int time = Integer.parseInt(args[2]);
                OfflinePlayer player = null;
                List<String> nowString = timeLimit_Task.getNowString();

                switch (method){
                    case "add":
                        for (OfflinePlayer p : Bukkit.getOfflinePlayers()){
                            if (p.getName().equalsIgnoreCase(args[1])) {
                                player = p;
                                break;
                            }
                        }
                        if (player == null) {
                            sender.sendMessage("§f[§7領地飛行§f] §c查無此玩家，請重新輸入玩家ID");
                            return false;
                        }
                        sender.sendMessage("§f[§7領地飛行§f] " + "§6" + player_name + " §e當前可飛行時間： " + timeFormat(timeLimit_Task.getTimeNow(player.getUniqueId().toString()) + time));

                        for(Map.Entry<String,Integer> s : Player_FlyTime.entrySet()){
                            if (s.getKey() == player.getUniqueId().toString()) {
                                s.setValue(timeLimit_Task.getTimeNow(player.getUniqueId().toString()) + time);
                                break;
                            }
                        }

                        Player_FlyTime.put(player.getUniqueId().toString(),timeLimit_Task.getTimeNow(player.getUniqueId().toString()) + time);


                        CustomConfig.save();
                        break;
                    case "set":
                        for (OfflinePlayer p : Bukkit.getOfflinePlayers()){
                            if (p.getName().equalsIgnoreCase(args[1])) {
                                player = p;
                                break;
                            }
                        }
                        if (player == null) {
                            sender.sendMessage("§f[§7領地飛行§f] §c查無此玩家，請重新輸入玩家ID");
                            return false;
                        }

                        Player_FlyTime.remove(player.getUniqueId().toString());
                        Player_FlyTime.put(player.getUniqueId().toString(),time);
                        sender.sendMessage("§f[§7領地飛行§f] " + "§6" + player_name + " §e當前可飛行時間： " + timeFormat(time));
                        CustomConfig.save();
                        break;
                }
            }else if (args.length == 2 && args[0].equalsIgnoreCase("get")){
                //fradmin get {id}
                OfflinePlayer player = null;
                for (OfflinePlayer p : Bukkit.getOfflinePlayers()){
                    if (p.getName().equalsIgnoreCase(args[1])) {
                        player = p;
                        break;
                    }
                }
                if (player == null) {
                    sender.sendMessage("§f[§7領地飛行§f] §c查無此玩家，請重新輸入玩家ID");
                    return false;
                }
                sender.sendMessage("§f[§7領地飛行§f] " + "§6" + player.getName() + " §e當前可飛行時間： " + timeFormat(timeLimit_Task.getTimeNow(player.getUniqueId().toString())));
            }else{
                sender.sendMessage("§f[§7領地飛行§f] §c指令輸入錯誤！");
            }
        }else{
            sender.sendMessage("§f[§7領地飛行§f] §c你沒有權限使用這個指令！");
        }







        return false;
    }


    public static String timeFormat(int time){
        String day = Integer.toString(time / 60 / 60 / 24);
        String hour = Integer.toString((time-(60*60*24*Integer.parseInt(day))) / 3600);
        String minute = Integer.toString((time-60*60*24*Integer.parseInt(day)-3600*Integer.parseInt(hour))/60);
        String second = Integer.toString(time - 60 * 60 * 24 * Integer.parseInt(day) - 3600 * Integer.parseInt(hour) - 60 * Integer.parseInt(minute));


        return "§c" + day + " §6天 §c" + hour + " §6時 §c" + minute + " §6分 §c" + second + " §6秒";
    }
}
