package ancientblockplugin.ancientblock.commands;

import ancientblockplugin.ancientblock.database.Database;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;

public class Login implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 1){
            String password = args[0];
            try {
                Database db = new Database();
                if(db.login(password,player)){
                    sender.sendMessage("Login Sucessfull");
                } else {
                    sender.sendMessage("Login Failed");
                }
            } catch (SQLException e) {
                e.getStackTrace();
            }
        } else {
            sender.sendMessage("usage: /login <password>");
        }

        return true;
    }
}
