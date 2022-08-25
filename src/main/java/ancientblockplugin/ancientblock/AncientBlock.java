package ancientblockplugin.ancientblock;

import ancientblockplugin.ancientblock.commands.Login;
import ancientblockplugin.ancientblock.commands.Register;
import ancientblockplugin.ancientblock.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.sql.SQLException;


public final class AncientBlock extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            Database db = new Database();
            db.initializeDatabase();
        }catch (SQLException e){
            Bukkit.getLogger().info("Unable to connect make tables.");
            e.printStackTrace();
        }
        getCommand("login").setExecutor(new Login());
        getCommand("register").setExecutor(new Register());
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
