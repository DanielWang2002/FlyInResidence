package mc.danielwang.flyinresidence;

import mc.danielwang.flyinresidence.tasks.timeLimit_Task;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class timePAPI extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "DanielWang";
    }

    @Override
    public String getIdentifier() {
        return "rf";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }




    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("timeFormat")) {
            String uuid = player.getUniqueId().toString();
            int time = timeLimit_Task.getTimeNow(uuid);
            return timeCommand.timeFormat(time);
        }

        return null; // Placeholder is unknown by the Expansion
    }

}
