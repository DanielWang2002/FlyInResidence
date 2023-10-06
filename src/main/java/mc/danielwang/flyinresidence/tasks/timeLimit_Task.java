package mc.danielwang.flyinresidence.tasks;

import mc.danielwang.flyinresidence.CustomConfig;
import mc.danielwang.flyinresidence.FlyInResidence;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class timeLimit_Task extends BukkitRunnable {

    private static FlyInResidence fr = FlyInResidence.getPlugin();
    private static timeLimit_Task instance;
    FlyInResidence plugin;

    public timeLimit_Task(FlyInResidence plugin) {
        this.plugin = plugin;
    }

    private static Map<String, Integer> Player_FlyTime = FlyInResidence.get_Player_FlyTime();
    private static List<String> nowString = new ArrayList<>();

    @Override
    public void run() {
        try {
            Player_FlyTime = FlyInResidence.get_Player_FlyTime();
            if ((Player_FlyTime.size() > 0)){
                for(Map.Entry<String,Integer> s : Player_FlyTime.entrySet()){
                    if (s.getValue() > 0) {
                        s.setValue(s.getValue() - 1);
                        nowString.add(s.getKey() + " " + s.getValue());
                    }
                }
                CustomConfig.get().set("FlyTime",nowString);
                try {
                    CustomConfig.save();
                    nowString.clear();
                }catch (Exception e){

                }
            }
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public static int getTimeNow(String uuid){
        if (!Player_FlyTime.containsKey(uuid)) return 0;
        return Player_FlyTime.get(uuid);
    }

    public static timeLimit_Task getPlugin(){
        return instance;
    }

    public static List<String> getNowString(){
        return nowString;
    }
}
