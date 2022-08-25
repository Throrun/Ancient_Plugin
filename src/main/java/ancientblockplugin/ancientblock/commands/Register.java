package ancientblockplugin.ancientblock.commands;

import ancientblockplugin.ancientblock.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;

public class Register implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 2){
            String password = args[0];
            String repeatpassword = args[1];
            if(Objects.equals(password, repeatpassword)){

                try{
                    Database db = new Database();
                    db.register(password, player);
                    sender.sendMessage("Register SUCCESS");
                }catch (SQLException e){
                    e.getStackTrace();
                    sender.sendMessage("Something went wrong");
                }

            } else {
                sender.sendMessage("Passwords are diffrent");
            }
        } else {
            sender.sendMessage("usage: /register <password> <repeat password>");
        }
        return true;
    }
}
