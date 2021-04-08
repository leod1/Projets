package fr.leod1.jump;

import com.sun.media.sound.InvalidFormatException;
import fr.leod1.jump.DB.CreationDB;
import fr.leod1.jump.Jumps.JumpObj;
import fr.leod1.jump.Jumps.JumpsManager;
import fr.leod1.jump.Utils.Title;
import fr.leod1.jump.events.*;
import fr.leod1.jump.playerjump.PlayerJumpManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;


public class Jump extends JavaPlugin {

    public static JumpsManager jumpsManagers = new JumpsManager();
    public static PlayerJumpManager playerJumpManager = new PlayerJumpManager();

    public static File path = new File("plugins//Jump");

    public static CreationDB SQLite;
    public static Title title = new Title();
    public static Jump jump;

    @Override
    public void onEnable(){
        jump = this;
        getCommand("jump").setExecutor(new ComandesEx());
        Bukkit.getPluginManager().registerEvents(new EventMove(), this);
        Bukkit.getPluginManager().registerEvents(new EventDmg(), this);
        Bukkit.getPluginManager().registerEvents(new EventObjForJump(), this);
        Bukkit.getPluginManager().registerEvents(new EventQuit(), this);
        Bukkit.getPluginManager().registerEvents(new EventDie(), this);

        saveDefaultConfig();

        if (!path.exists()) path.mkdir();

        SQLite = new CreationDB(this);
        if (getConfig().getBoolean("GenerateNewDataBase")) {
            new File(this.getDataFolder(),"Jump.db");
            getConfig().set("GenerateNewDataBase", false);
            saveConfig();
            try {
                CreationDB.initializeStatement();
                SQLite.statement.executeUpdate("CREATE TABLE Courses (\n" +
                        "\t`name`\tTEXT,\n" +
                        "\t`world_Start`\tTEXT,\n" +
                        "\t`x_Start`\tINTEGER,\n" +
                        "\t`y_Start`\tINTEGER,\n" +
                        "\t`z_Start`\tINTEGER,\n" +
                        "\t`world_Finish`\tTEXT,\n" +
                        "\t`x_Finish`\tINTEGER,\n" +
                        "\t`y_Finish`\tINTEGER,\n" +
                        "\t`z_Finish`\tINTEGER,\n" +
                        "\t`worldHolo`\tTEXT,\n" +
                        "\t`x_Holo`\tINTEGER,\n" +
                        "\t`y_Holo`\tINTEGER,\n" +
                        "\t`z_Holo`\tINTEGER" +
                        ");");
                SQLite.statement.executeUpdate("CREATE TABLE PlayerStats (\n" +
                        "\tname\tTEXT,\n" +
                        "\tMilisec\tINTEGER,\n" +
                        "\tplayerName\tINTEGER" +
                        ");");
                SQLite.statement.executeUpdate("CREATE TABLE Checkpoint (\n" +
                        "\tname\tTEXT,\n" +
                        "\ttolerance\tFLOAT,\n" +
                        "\tid\tINTEGER,\n" +
                        "\t`world_Cp`\tTEXT,\n" +
                        "\tx_Checkpoint\tINTEGER,\n" +
                        "\ty_Checkpoint\tINTEGER,\n" +
                        "\tz_Checkpoint\tINTEGER" +
                        ");");
                getServer().getConsoleSender().sendMessage("§3[§b" + this.getDescription().getName() + " " + this.getDescription().getVersion() + "§3] §aSuccessfully generated data base !" );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {

            putCoursesInObj();
            putAllCheckpointincourse();
            putALLbcincourse();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {

        playerJumpManager.cancelAlljumps();

        try {
            SQLite.updateDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        SQLite.close();
    }

    public void putCoursesInObj() throws SQLException {

        for (String name : SQLite.getAllCourse()) {

            JumpObj jumpObj = new JumpObj(name);
            jumpObj.setStart(SQLite.getStart(name));
            jumpObj.setFinish(SQLite.getFinish(name));
            jumpObj.setHolo(SQLite.getHolo(name));

            jumpsManagers.addJumpObj(jumpObj);

        }

    }
    public void putAllCheckpointincourse() throws SQLException {

        for (JumpObj jumpObj : jumpsManagers.getJumpObjs()) {
            jumpObj.setCheckPoints(SQLite.getAllCheckpointFromJump(jumpObj));

        }
    }

    public void putALLbcincourse() throws SQLException {

        for (JumpObj jumpObj  : jumpsManagers.getJumpObjs()) {
            jumpObj.setBestScores(SQLite.getALLBcFromJump(jumpObj));
            if(!(jumpObj.getHolo() == null) && !(jumpObj.getHolo().getWorldName().equalsIgnoreCase("null"))){
                jumpObj.SetHolo();
            }
        }
    }

    public static Jump getInstance(){
        return jump;
    }
}
