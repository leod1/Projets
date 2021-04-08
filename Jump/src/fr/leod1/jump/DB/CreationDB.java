package fr.leod1.jump.DB;

import com.sun.media.sound.InvalidFormatException;
import fr.leod1.jump.Jump;
import fr.leod1.jump.Jumps.JumpObj;
import fr.leod1.jump.Utils.JumpLoc;
import fr.leod1.jump.score.BestTimeScore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import static fr.leod1.jump.Jump.SQLite;
import static fr.leod1.jump.Jump.jumpsManagers;

public class CreationDB {

    public static Statement statement;
    protected static Connection connection;
    private static JavaPlugin javaPlugin = null;


    public CreationDB(JavaPlugin plugin) {
        javaPlugin = plugin;
        connection = getSqlConnection();
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Connection getSqlConnection() {

        synchronized (this) {
            File dataFolder = new File(javaPlugin.getDataFolder(), "plugin.db");
            if (!dataFolder.exists()) try {
                dataFolder.createNewFile();
            } catch (IOException ignored) {}

            try {
                if ((connection != null) && (!connection.isClosed())) {
                    return connection;
                }

                try {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
                    return connection;

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean addFromQuery(String query) throws InvalidFormatException {

        initializeStatement();
        if (!query.contains("INSERT") && !query.contains("UPDATE")) {
            throw new InvalidFormatException();
        }
        try {
            statement.executeUpdate(query);
        } catch (SQLException ignored) { return false;
        }
        return true;
    }

    public static void initializeStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<HashMap<String, String>> getFromQuery(String sqlQuery) throws InvalidFormatException {

        initializeStatement();
        ResultSet resultSet = null;
        if (!sqlQuery.contains("SELECT")) throw new InvalidFormatException();

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashMap<String, String> result = null;

        try {
            assert result != null;
            ResultSetMetaData rm = resultSet.getMetaData();
            AtomicReference<Integer> i = new AtomicReference<>(0);
            Integer j = 0;
            while (resultSet.next()) {
                result = new HashMap<>();
                j = rm.getColumnCount();
                while (j != 0) {
                    result.put(rm.getColumnName(j), resultSet.getString(j));
                    j--;
                }
                list.add(result);
                i.getAndSet(i.get() + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Object getFromQuerys(String sqlQuery) throws InvalidFormatException {

        initializeStatement();
        ResultSet result = null;
        if (!sqlQuery.contains("SELECT")){
            throw new InvalidFormatException();
        }

        ArrayList<HashMap<String,String>> list = new ArrayList<>();

        try {
            result = this.statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashMap<String,String> Results;

        try {

            assert result != null;
            ResultSetMetaData rm = result.getMetaData();
            Integer i = 0;
            Integer j = 0;
            while (result.next()){
                Results = new HashMap<>();
                //on ajoute l'id pour differencier les iles
                j = rm.getColumnCount();
                while ( j!=0){
                    Results.put(rm.getColumnName(j),result.getString(j));
                    j--;
                }
                list.add(Results);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isCoursecreate(String NameCourse) throws SQLException {
        initializeStatement();
        java.sql.Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM Courses");
        ArrayList test = new ArrayList<String>();
        while (rs.next()) {
            String s = rs.getString("name");
            test.add(s);
        }
        if (test.contains(NameCourse)){
            return true;
        }else {
            return false;
        }
    }

    public void setStart(Location loc,String Namecourse) throws InvalidFormatException {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        SQLite.addFromQuery("UPDATE Courses SET x_Start = "+x+", y_Start="+y+", z_Start="+z+" WHERE name='"+Namecourse+"'");
    }

    public void setFinish(Location loc,String Namecourse) throws InvalidFormatException {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        SQLite.addFromQuery("UPDATE Courses SET x_Finish = "+x+", y_Finish="+y+", z_Finish="+z+" WHERE name='"+Namecourse+"'");
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList LoadStartList(String Axe) throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT x_Start, y_Start, z_Start FROM Courses");
        ArrayList<Integer> Listx = new ArrayList<>();
        ArrayList<Integer> Listy = new ArrayList<>();
        ArrayList<Integer> Listz = new ArrayList<>();

        while (rs.next()){
            Listx.add(rs.getInt("x_Start"));
            Listy.add(rs.getInt("y_Start"));
            Listz.add(rs.getInt("z_Start"));
        }
        if (Axe == "x"){
            return Listx;
        } else if(Axe == "y"){
            return Listy;
        } else if(Axe == "z"){
            return Listz;
        }
        return null;
    }

    public ArrayList LoadFinishList(String Axe) throws SQLException {
        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT x_Finish, y_Finish, z_Finish FROM Courses");
        ArrayList<Integer> Listx = new ArrayList<>();
        ArrayList<Integer> Listy = new ArrayList<>();
        ArrayList<Integer> Listz = new ArrayList<>();
        while (rs.next()){
            Listx.add(rs.getInt("x_Finish"));
            Listy.add(rs.getInt("y_Finish"));
            Listz.add(rs.getInt("z_Finish"));
        }
        if (Axe == "x"){
            return Listx;
        } else if(Axe == "y"){
            return Listy;
        } else if(Axe == "z"){
            return Listz;
        }
        return null;
    }

    public String getNameCourse(int x,int y,int z) throws SQLException {
        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM Courses WHERE x_Start ="+x+" AND y_Start ="+y+" AND z_Start ="+z);
        String wola = rs.getString("name");
        return wola;
    }

    public String getNameCourseFinish(int x,int y,int z) throws SQLException {
        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM Courses WHERE x_Finish ="+x+" AND y_Finish ="+y+" AND z_Finish ="+z);
        String NIQUETAMER = rs.getString("name");
        return NIQUETAMER;
    }

    public ArrayList<String> getAllCourse() throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM Courses");

        ArrayList<String> AllCourses = new ArrayList<>();
        while (rs.next()){
            AllCourses.add(rs.getString("name"));
        }
        return AllCourses;

    }

    public JumpObj getJumpFromStartBlock(Player pl) throws SQLException {

        for (JumpObj jumpObj : Jump.jumpsManagers.getJumpObjs()) {
            if (jumpObj.getStart() != null) {
                if(pl.getLocation().getWorld().getName().equals(jumpObj.getStart().getWorldName())
                        && pl.getLocation().getBlockX() == jumpObj.getStart().getX()
                        && pl.getLocation().getBlockY() == jumpObj.getStart().getY()
                        && pl.getLocation().getBlockZ() == jumpObj.getStart().getZ()){
                    return jumpObj;
                }
            }
        }
        return null;
    }

    public JumpLoc getStart(String name) throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT x_Start, y_Start, z_Start, world_Start FROM Courses WHERE name = '" + name+"'");
        if (rs.getString("world_Start") == null) {
            return new JumpLoc(rs.getInt("x_Start"), rs.getInt("y_Start"), rs.getInt("z_Start"), "world");
        } else {
            return new JumpLoc(rs.getInt("x_Start"), rs.getInt("y_Start"), rs.getInt("z_Start"), rs.getString("world_Start"));
        }

    }

    public JumpLoc getFinish(String name) throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT x_Finish, y_Finish, z_Finish, world_Finish FROM Courses WHERE name = '" + name+"'");
        if (rs.getString("world_Finish") == null) {
            return new JumpLoc(rs.getInt("x_Finish"), rs.getInt("y_Finish"), rs.getInt("z_Finish"), "world");
        } else {
            return new JumpLoc(rs.getInt("x_Finish"), rs.getInt("y_Finish"), rs.getInt("z_Finish"), rs.getString("world_Finish"));
        }

    }
    public JumpLoc getHolo(String name) throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT x_Holo, y_Holo, z_Holo, worldHolo FROM Courses WHERE name = '" + name+"'");
        if (rs.getString("worldHolo") == null) {
            return null;
        } else {
            return new JumpLoc(rs.getInt("x_Holo"), rs.getInt("y_Holo"), rs.getInt("z_Holo"), rs.getString("worldHolo"));
        }

    }
    public ArrayList<JumpLoc> getAllCheckpointFromJump(JumpObj jumpObj ) throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, world_Cp, x_Checkpoint, y_Checkpoint, z_Checkpoint FROM Checkpoint WHERE name = '" + jumpObj.getName()+"'");
        ArrayList<JumpLoc> cp = new ArrayList<>();
        while (rs.next()){
            cp.add(rs.getInt("id"),new JumpLoc(rs.getInt("x_Checkpoint"),rs.getInt("y_Checkpoint"),rs.getInt("z_Checkpoint") , rs.getString("world_Cp")));
        }
        return cp;
    }
    public ArrayList<BestTimeScore> getALLBcFromJump(JumpObj jumpObj ) throws SQLException {

        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Milisec, playerName FROM playerStats WHERE name = '" + jumpObj.getName()+"'");
        ArrayList<BestTimeScore> Bc = new ArrayList<>();
        while (rs.next()){
            Bc.add(new BestTimeScore(rs.getString("playerName"), rs.getInt("Milisec"), jumpObj.getName()));
        }
        return Bc;
    }

    public void updateDB() throws SQLException, InvalidFormatException {


        /**
         *
         * mettre a jour ici
         *
         */


        initializeStatement();
        Statement stmt = connection.createStatement();
        ResultSet courses = stmt.executeQuery("SELECT name FROM Courses");
        ArrayList<String> CoursesArrayDB = new ArrayList<>();
        while (courses.next()){
            CoursesArrayDB.add(courses.getString("name"));
        }

        for (JumpObj jumpObj : jumpsManagers.getJumpObjs()) {

            String name = jumpObj.getName();

            if (CoursesArrayDB.contains(name)) {
                SQLite.statement.executeUpdate("UPDATE Courses SET name = '" + jumpObj.getName() +
                        "', world_Start = '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getWorldName()) +
                        "', x_Start = '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getX()) +
                        "', y_Start = '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getY()) +
                        "', z_Start = '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getZ()) +
                        "', world_Finish = '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getWorldName()) +
                        "', x_Finish = '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getX()) +
                        "', y_Finish = '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getY()) +
                        "', z_Finish = '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getZ()) +
                        "', worldHolo = '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getWorldName()) +
                        "', x_Holo = '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getX()) +
                        "', y_Holo = '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getY()) +
                        "', z_Holo = '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getZ()) +
                        "' WHERE name = '" + name + "'");
            } else {
                SQLite.addFromQuery("INSERT INTO Courses(name, world_Start, x_Start, y_Start, z_Start, world_Finish, x_Finish, y_Finish, z_Finish, worldHolo, x_Holo, y_Holo, z_Holo) values ('" + name +
                        "', '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getWorldName()) +
                        "', '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getX()) +
                        "', '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getY()) +
                        "', '" + (jumpObj.getStart() == null ? null : jumpObj.getStart().getZ()) +
                        "', '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getWorldName()) +
                        "', '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getX()) +
                        "', '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getY()) +
                        "', '" + (jumpObj.getFinish() == null ? null : jumpObj.getFinish().getZ()) +
                        "', '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getWorldName()) +
                        "', '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getX()) +
                        "', '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getY()) +
                        "', '" + (jumpObj.getHolo() == null ? null : jumpObj.getHolo().getZ()) +
                        "');");
            }

            ResultSet cp = stmt.executeQuery("SELECT id FROM Checkpoint WHERE name = '" + name + "'");
            ArrayList<Integer> cpsId = new ArrayList<>();
            while (cp.next()){
                cpsId.add(courses.getInt("id"));
            }

            if (jumpObj.getCheckPoints().size() == cpsId.size()) {
                int i = 0;
                for (JumpLoc jumpLoc : jumpObj.getCheckPoints()) {
                    SQLite.statement.executeUpdate("UPDATE Checkpoint SET x_CheckPoint = '" + jumpLoc.getX() +
                            "', y_CheckPoint = '" + jumpLoc.getY() +
                            "', z_CheckPoint = '" + jumpLoc.getZ() +
                            "', world_Cp = '" + jumpLoc.getWorldName() +
                            "' WHERE name = '" + name + "' AND id = '" + i + "'");
                    i++;
                }

            } else {
                statement.executeUpdate("DELETE FROM Checkpoint WHERE name = '" + name + "'");
                int i = 0;
                for (JumpLoc jumpLoc : jumpObj.getCheckPoints()) {
                    SQLite.addFromQuery("INSERT INTO Checkpoint(name, id, world_Cp, x_Checkpoint, y_Checkpoint, z_Checkpoint) values ('" + name +
                            "', '" + i +
                            "', '" + jumpLoc.getWorldName() +
                            "', '" + jumpLoc.getX() +
                            "', '" + jumpLoc.getY() +
                            "', '" + jumpLoc.getZ() + "')");
                    i++;
                }
            }

            // PLayerSTATS
                statement.executeUpdate("DELETE FROM PlayerStats WHERE name = '" + name + "'");
            if(jumpObj.getBestScores().size() < 10){
                for (int i = 0; i <= jumpObj.getBestScores().size()-1;i++) {//BestTimeScore Bc : jumpObj.getBestScores()
                    BestTimeScore Bc = jumpObj.getBestScores().get(i);
                    SQLite.addFromQuery("INSERT INTO PlayerStats(name,  Milisec, playerName) values ('" + name +
                            "', '" + Bc.getTime() +
                            "', '" + Bc.getPlayer() +
                            "')");
                }
            } else {
                for (int i = 0; i < 10 ;i++) {//BestTimeScore Bc : jumpObj.getBestScores()
                    BestTimeScore Bc = jumpObj.getBestScores().get(i);
                    SQLite.addFromQuery("INSERT INTO PlayerStats(name,  Milisec, playerName) values ('" + name +
                            "', '" + Bc.getTime() +
                            "', '" + Bc.getPlayer() +
                            "')");
                }
            }
            //playerSTAT

        } //qzd

        ArrayList<String> CoursesArrayName = new ArrayList<>();
        for (JumpObj jumpObj : jumpsManagers.getJumpObjs()) {
            CoursesArrayName.add(jumpObj.getName());
        }

        for (String name : CoursesArrayDB) {
            if (!CoursesArrayName.contains(name)) {
                statement.executeUpdate("DELETE FROM Courses WHERE name = '" + name + "'");
                statement.executeUpdate("DELETE FROM Checkpoint WHERE name = '" + name + "'");
                statement.executeUpdate("DELETE FROM PlayerStats WHERE name = '" + name + "'");
            }
        }

    }

}
//"SELECT CASE WHEN name = '"+NameCourse+"' FROM course"