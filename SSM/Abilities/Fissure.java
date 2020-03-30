package SSM.Abilities;

import SSM.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Listener;;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Fissure extends Ability {

    int fissureLength = 14;
    int fissureHeight = 3;
    double delay = 0.5;
    int runn = -1;
    int i = 0;


    public Fissure() {
        super();
        this.name = "Fissure";
        this.cooldownTime = 10;
        this.rightClickActivate = true;
    }

    public void activate() {
        if (!owner.isOnGround()){
            return;
        }
        addBlock();
    }
    public void addBlock(){
        Location location = owner.getLocation();
        Vector direction = location.getDirection();
        runn = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                i++;
                direction.multiply(1);
                location.add(direction);
                Location blockHere = new Location(owner.getWorld(), (int)location.getX(), (int)owner.getLocation().getY(), (int)location.getZ());
                Location material = new Location(owner.getWorld(), (int)location.getX(), (int)owner.getLocation().getY()-1, (int)location.getZ());
                if (material.getBlock().isPassable()){
                    stop();
                }
                owner.getWorld().getBlockAt(blockHere).setType(material.getBlock().getType());
                if (i >= fissureLength){
                    stop();
                }
            }
        }, 0, (long) delay * 20);

    }

    private void stop(){
        i = 0;
        Bukkit.getScheduler().cancelTask(runn);
    }

}
