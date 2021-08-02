package com.lab5;

import java.io.*;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import org.apache.commons.csv.*;

/**
 * This class to read and parse file csv.
 */

public class FileParserCSV {
    static HashSet<String> keySet = new HashSet<String>();
    static Hashtable<String, SpaceMarine> collection = new Hashtable<String, SpaceMarine>();
    static final String timeStamp = new SimpleDateFormat("HH:mm:ss:SS dd/MM/yy").format(Calendar.getInstance().getTime());

    /**
     * Constructor of com.lab5.FileParserCSV
     * @param s
     * @throws IOException
     */
    public FileParserCSV(String s) throws IOException {
        FileReader in = new FileReader(s);
        String input = "";
        int i;
        while((i=in.read())!=-1)
            input += String.valueOf((char)i);
        CSVParser p = CSVParser.parse(input,CSVFormat.DEFAULT);
        List<CSVRecord> re = p.getRecords();
        for (CSVRecord a: re) {
            Coordinates coordinates = new Coordinates(Integer.parseInt(a.get(1)), Long.parseLong(a.get(2)));
            Chapter chapter = new Chapter(a.get(7), a.get(8));
            SpaceMarine space = new SpaceMarine( a.get(0), coordinates, Double.parseDouble(a.get(3)), AstartesCategory.valueOf(a.get(4).toUpperCase()), Weapon.valueOf(a.get(5).toUpperCase()), MeleeWeapon.valueOf(a.get(6).toUpperCase()), chapter);
            //kiem tra co key chua
            Long key=Math.round(Math.random()*10000);
            while (!keySet.contains(key.toString()))
            {key = Math.round(Math.random()*10000);keySet.add(key.toString());}
            collection.put(key.toString(), space);
        }
        CollectionManager manager = new CollectionManager();

    }
}