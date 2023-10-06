package mc.danielwang.flyinresidence;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import mc.danielwang.flyinresidence.tasks.timeLimit_Task;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public final class FlyInResidence extends JavaPlugin {

    private static FlyInResidence instance;
    private static ResidenceApi resAPI;

    private static Map<String, Integer> Player_FlyTime = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new REvents(),this);

        getCommand("rf").setExecutor(new FlyCommand());
        getCommand("rfadmin").setExecutor(new timeCommand());
        getCommand("rft").setExecutor(new rfTimeCommand());


        CustomConfig.setup();
        CustomConfig.save();
        CustomConfig.get().options().copyDefaults(true);

        Plugin resPlug = getServer().getPluginManager().getPlugin("Residence");
        if (resPlug != null) {
            ResidenceApi resAPI = Residence.getInstance().getAPI();
        }
        CustomConfig.get().getStringList("FlyTime").forEach(t ->{
            String[] s = t.split(" ");
            String uuid = s[0];
            int time = Integer.parseInt(s[1]);
            Player_FlyTime.put(uuid,time);
        });
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new timePAPI().register();
        }

        BukkitTask tlT = new timeLimit_Task(this).runTaskTimer(this,0L,20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static FlyInResidence getPlugin(){
        return instance;
    }

    public static Map<String,Integer> get_Player_FlyTime(){
        return Player_FlyTime;
    }
}
