package hr.prizmic.projectfp.File;

import hr.prizmic.projectfp.Exceptions.FileReadException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatFile {
    public static Map<String,String> readFile(String path) throws FileReadException, ClassNotFoundException{
        Map<String, String> list = new HashMap<>();
        LoginInfo obj;

        try {
            File f = new File(path);
            if(f.length()==0){
                return list;
            }

            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);

            while(file.available()>0){
                obj = (LoginInfo) in.readObject();

                list.put(obj.getUsername(), obj.getPassword());
            }

            in.close();
            file.close();
            return list;
        }
        catch(IOException e){
            String msg = "Couldn't load file";
            throw new FileReadException(msg, e);
        }
        catch(ClassNotFoundException e){
            throw new ClassNotFoundException();
        }
    }

    public static void writeFile(String path, LoginInfo info) throws FileReadException,ClassNotFoundException{
        try{
            Map<String, String> list = readFile(path);
            List<LoginInfo> l = new ArrayList<>();
            for(Map.Entry<String, String> en : list.entrySet()){
                l.add(new LoginInfo(en.getKey(),en.getValue()));
            }

            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);

            for(int i=0;i<l.size();i++){
                out.writeObject(l.get(i));
            }

            out.writeObject(info);

            out.close();
            file.close();
        }
        catch(IOException e){
            String msg = "Couldn't load file";
            throw new FileReadException(msg, e);
        }
    }
}
