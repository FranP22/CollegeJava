package hr.prizmic.projectfp.Connection;

import hr.prizmic.projectfp.Class.Item;
import hr.prizmic.projectfp.Class.Phone.*;
import hr.prizmic.projectfp.Class.User.UserClass;
import hr.prizmic.projectfp.Exceptions.DatabaseException;
import hr.prizmic.projectfp.Exceptions.FileReadException;
import hr.prizmic.projectfp.File.Log;
import hr.prizmic.projectfp.File.PropertiesLoad;
import hr.prizmic.projectfp.Main;
import javafx.scene.chart.PieChart;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    private static String excMsg = "There was a problem using the database";
    private static Connection connectToDatabase() throws DatabaseException,FileReadException{
        try{
            Properties svojstva = new PropertiesLoad().getProperties();
            String database = svojstva.getProperty("databaseUrl");
            String username = svojstva.getProperty("username");
            String password = svojstva.getProperty("password");
            Connection veza = DriverManager.getConnection(database, username, password);
            return veza;
        }catch (SQLException e){
            Log.dataBaseException();
            throw new DatabaseException(excMsg, e);
        }
    }

    public static List<UserClass> getAllUsers() throws DatabaseException,FileReadException {
        try(Connection connection = connectToDatabase()){
            List<UserClass> users = new ArrayList<>();

            Statement sqlSt = connection.createStatement();
            ResultSet set = sqlSt.executeQuery("SELECT * FROM USER_CLASS");

            while(set.next()){
                UserClass in = new UserClass(set.getLong("ID"),
                        set.getString("FIRSTNAME"),
                        set.getString("LASTNAME"),
                        set.getString("USERNAME"),
                        set.getString("PASSWORD"),
                        set.getDate("DATEOFBIRTH").toLocalDate());
                users.add(in);
            }
            connection.close();
            return users;
        }catch (SQLException e){
            throw new DatabaseException(excMsg, e);
        }
    }
    public static void addUser(UserClass user) throws DatabaseException,FileReadException{
        try(Connection connection = connectToDatabase()){
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO USER_CLASS (firstname, lastname, username, password, dateofbirth) VALUES(?, ?, ?, ?, ?)");

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setDate(5, Date.valueOf(user.getDOB()));

            stmt.executeUpdate();
            connection.close();
        }catch (SQLException e){
            throw new DatabaseException(excMsg, e);
        }
    }

    private static Item getItemFromSet(ResultSet set, List<UserClass> users) throws SQLException{
        Storage st = new Storage(
                set.getInt("INTERNALSTORAGE"),
                set.getString("EXTERNALSTORAGE"),
                set.getInt("RAM")
        );
        Characteristics c = new Characteristics(
                set.getInt("BATTERY"),
                set.getString("OS"),
                set.getString("CPU"),
                set.getString("GPU"),
                st
        );
        MobileDevice m;
        if(set.getString("DEVICETYPE").equals("Tablet")){
            m = new Tablet(
                    set.getString("MANUFACTURER"),
                    set.getString("MODEL"),
                    BigDecimal.valueOf(set.getLong("PRICE")),
                    c
            );
        }else{
            m = new Phone(
                    set.getString("MANUFACTURER"),
                    set.getString("MODEL"),
                    BigDecimal.valueOf(set.getLong("PRICE")),
                    c
            );
        }

        UserClass u = null;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getID()==set.getLong("USERID")){
                u=users.get(i);
                break;
            }
        }
        Item in = new Item(
                set.getLong("ID"),
                m,
                m.getType(),
                u,
                set.getTimestamp("DATEANDTIME").toLocalDateTime()
        );
        return in;
    }
    public static List<Item> getAllItems()throws DatabaseException,FileReadException{
        try(Connection connection = connectToDatabase()){
            List<Item> items = new ArrayList<>();

            Statement sqlSt = connection.createStatement();
            ResultSet set = sqlSt.executeQuery("SELECT * FROM ITEM_CLASS");

            List<UserClass> users = getAll(UserClass.class);

            while(set.next()){
                Item in = getItemFromSet(set,users);
                items.add(in);
            }
            connection.close();
            return items;
        }catch (SQLException e){
            Log.dataBaseException();
            throw new DatabaseException(excMsg, e);
        }
    }
    public static void removeItem(long id) throws DatabaseException,FileReadException{
        try(Connection connection = connectToDatabase()){
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM ITEM_CLASS WHERE ID = ?");

            stmt.setLong(1, id);

            stmt.executeUpdate();
            connection.close();
        }catch (SQLException e){
            Log.dataBaseException();
            throw new DatabaseException(excMsg, e);
        }
    }
    public static void addItem(Item item) throws DatabaseException,FileReadException{
        try(Connection connection = connectToDatabase()){
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO ITEM_CLASS " +
                            "(userid,dateandtime,devicetype,model,manufacturer,price,battery,os,cpu,gpu,internalstorage,externalstorage,ram) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setLong(1, Main.activeUser.getID());
            stmt.setTimestamp(2, Timestamp.valueOf(item.getTimeAdded()));
            stmt.setString(3, item.getType().toString());
            stmt.setString(4,item.getDevice().getModel());
            stmt.setString(5,item.getDevice().getManufacturer());
            stmt.setLong(6,item.getDevice().getPrice().longValue());
            stmt.setInt(7,item.getDevice().getCharacteristics().getBattery());
            stmt.setString(8,item.getDevice().getCharacteristics().getOS());
            stmt.setString(9,item.getDevice().getCharacteristics().getCPU());
            stmt.setString(10,item.getDevice().getCharacteristics().getGPU());
            stmt.setInt(11,item.getDevice().getCharacteristics().getStorage().getStorageInternal());
            stmt.setString(12,item.getDevice().getCharacteristics().getStorage().getStorageExternal());
            stmt.setInt(13,item.getDevice().getCharacteristics().getStorage().getRAM());

            stmt.executeUpdate();
            connection.close();
        }catch (SQLException e){
            Log.dataBaseException();
            throw new DatabaseException(excMsg, e);
        }
    }
    public static List<Item> sortItem(FilterClass filter)
            throws DatabaseException,FileReadException {
        List<Item> l = new ArrayList<>();

        try(Connection connection = connectToDatabase()){
            StringBuilder sqlUpit = new StringBuilder("SELECT * FROM ITEM_CLASS WHERE 1=1");

            if(filter.getModel().isEmpty() == false) {
                sqlUpit.append(" AND MODEL='" + filter.getModel() + "'");
            }
            if(filter.getManufacturer().isEmpty() == false) {
                sqlUpit.append(" AND MANUFACTURER='" + filter.getManufacturer() + "'");
            }
            if(filter.getPrice().isEmpty() == false) {
                sqlUpit.append(" AND PRICE='" + filter.getPrice() + "'");
            }
            if(filter.getBattery().isEmpty() == false) {
                sqlUpit.append(" AND BATTERY='" + filter.getBattery() + "'");
            }
            if(filter.getOs().isEmpty() == false) {
                sqlUpit.append(" AND OS='" + filter.getOs() + "'");
            }
            if(filter.getCpu().isEmpty() == false) {
                sqlUpit.append(" AND CPU='" + filter.getCpu() + "'");
            }
            if(filter.getGpu().isEmpty() == false) {
                sqlUpit.append(" AND GPU='" + filter.getGpu() + "'");
            }
            if(filter.getIn().isEmpty() == false) {
                sqlUpit.append(" AND INTERNALSTORAGE='" + filter.getIn() + "'");
            }
            if(filter.getEx().isEmpty() == false) {
                sqlUpit.append(" AND EXTERNALSTORAGE='" + filter.getEx() + "'");
            }
            if(filter.getRam().isEmpty() == false) {
                sqlUpit.append(" AND RAM='" + filter.getRam() + "'");
            }
            if(filter.getUser().isEmpty() == false) {
                sqlUpit.append(" AND USERID='" + filter.getUser() + "'");
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sqlUpit.toString());
            List<UserClass> users = getAll(UserClass.class);

            while(rs.next()) {
                Item newItem = getItemFromSet(rs,users);
                l.add(newItem);
            }

            connection.close();
            return l;

        }catch (SQLException e){
            Log.dataBaseException();
            throw new DatabaseException(excMsg, e);
        }
    }

    public static <T extends Class> List getAll(T cl) throws DatabaseException,FileReadException{
        if(cl == UserClass.class){
            return getAllUsers();
        }else if(cl == Item.class){
            return getAllItems();
        }else{
            List list = new ArrayList<>();
            return list;
        }
    }
}
