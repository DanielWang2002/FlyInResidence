package mc.danielwang.flyinresidence;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.event.ResidencePlayerEvent;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import mc.danielwang.flyinresidence.tasks.timeLimit_Task;

public class REvents implements Listener {

    private ResidenceManager rmanager = (ResidenceManager) ResidenceApi.getResidenceManager();

    @EventHandler
    public void MoveEnterEvent(PlayerMoveEvent e){

        Player player = e.getPlayer();
        if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ()) return; //The player hasn't moved
        Location loc = e.getTo();
        ClaimedResidence res = rmanager.getByLoc(loc);
        if (!player.isOp()){
            if (res != null) {
                if ((FlyCommand.CanFly(player))
                        &&(timeLimit_Task.getTimeNow(player.getUniqueId().toString()) > 0)
                        &&(res.getOwner() == player.getName())){ //開啟rf + 有飛行時間 + 是領地擁有者
                    player.setAllowFlight(true);
                }else{
                    player.setAllowFlight(false);
                }
            }else{
                player.setAllowFlight(false);
            }
        }else{
            player.setAllowFlight(true);
        }

    }
}
