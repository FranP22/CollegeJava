package hr.prizmic.projectfp.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

   // private static Logger log = Logger.getLogger(Log.class.getName())
    private static final Logger log = LoggerFactory.getLogger(Log.class);

    public static void dataBaseException(){
        log.error("Problem with database has occured");
    }
    public static void fileReadException(){
        log.error("Problem with reading file has occured");
    }
    public static void IOException(){
        log.error("Problem with IO has occured");
    }
    public static void noAlgorithmExeption(){
        log.error("Problem with IO has occured");
    }
    public static void nullpointerException(){
        log.error("NullPointer has occured");
    }
    public static void classNFException(){
        log.error("Class not found");
    }

    public static void login(boolean success){
        if(success){
            log.info("Successful login");
        }else{
            log.info("Failed to login");
        }
    }
    public static void logout(){
        log.info("User has signed out");
    }
    public static void itemAdded(){
        log.info("New item has been added");
    }
    public static void itemRemoved(long id){
        log.info("Item with the id: " + id + " has been removed");
    }
    public static void itemsBought(long price){
        log.info("Bought items for " + price +"€");
    }

    public static void threadStarted(){
        log.info("Thread started");
    }
    public static void threadEnded(){
        log.info("Thread ended");
    }
}
