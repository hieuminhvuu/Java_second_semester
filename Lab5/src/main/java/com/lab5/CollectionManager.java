package com.lab5;

import java.io.*;
import java.util.*;

/**
 * This class will read all command from user and excute them
 */
public class CollectionManager {
    private String userCommand = "";
    private String[] splitedUserCommand = new String[2];
    HashSet<String> loadedScript = new HashSet<>();

    /**
     * Read command from user
     */
    public CollectionManager () {
        Scanner commandReader = new Scanner(System.in);
        String command = "";
        do {
            command = commandReader.nextLine();
            executeCommand(command);
        } while (!command.equals("exit"));
    }

    /**
     * Distribute command to methods
     * @param cm command
     */
    public void executeCommand(String cm) {
        try {
            String command = cm;
            splitedUserCommand = command.trim().split(" ", 2);
            switch (splitedUserCommand[0]) {
                case "": break;
                case "help": help(); break;
                case "update": update(splitedUserCommand[1]); break;
                case "show": show(); break;
                case "save": save(); break;
                case "exit": System.exit(1); break;
                case "clear" : clear(); break;
                case "remove_lower" : remove_lower(); break;
                case "remove_key" : remove_key(splitedUserCommand[1]); break;
                case "insert" : insert(splitedUserCommand[1]); break;
                case "replace_if_greater" : replace_if_greater(splitedUserCommand[1]); break;
                case "remove_greater_key" : remove_greater_key(splitedUserCommand[1]); break;
                case "remove_all_by_melee_weapon" : remove_all_by_melee_weapon(splitedUserCommand[1]); break;
                case "sum_of_health" : sum_of_health(); break;
                case "print_field_descending_weapon_type" : print_field_descending_weapon_type(); break;
                case "execute_script" : execute_script(splitedUserCommand[1]); break;
                case "info": info(); break;
                default:
                    System.out.println("Unidentified command. Type \'help\' for help. :(");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Argument missing.");
        } catch (NoSuchElementException ex) {
            System.exit(0);
        }
    }

    /**
     * Show to user what commands the program can execute
     */
    private void help() {
        System.out.println(
                "help                                   : вывести справку по доступным командам\n" +
                        "info                                   : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                        "show                                   : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "insert null {element}                  : добавить новый элемент с заданным ключом\n" +
                        "update id {element}                    : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_key null                        : удалить элемент из коллекции по его ключу\n" +
                        "clear                                  : очистить коллекцию\n" +
                        "save                                   : сохранить коллекцию в файл\n" +
                        "execute_script file_name               : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                        "exit                                   : завершить программу (без сохранения в файл)\n" +
                        "remove_lower {element}                 : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                        "replace_if_greater null {element}      : заменить значение по ключу, если новое значение больше старого\n" +
                        "remove_greater_key null                : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                        "remove_all_by_melee_weapon meleeWeapon : удалить из коллекции все элементы, значение поля meleeWeapon которого эквивалентно заданному\n" +
                        "sum_of_health                          : вывести сумму значений поля health для всех элементов коллекции\n" +
                        "print_field_descending_weapon_type     : вывести значения поля weaponType всех элементов в порядке убывания");
    }

    /**
     * To update data of element
     * @param id element
     */
    private void update(String id) {
        long i = (long) Long.parseLong(id);
        for (String key: FileParserCSV.keySet) {
            if (FileParserCSV.collection.get(key).getId() == i) {
                FileParserCSV.collection.put(key, setData(Long.parseLong(id))); return;
            }
        }
        System.out.println("can't find ");
    }

    /**
     * Show to screen each data of element
     */
    private void show() {
        //tro tung cai values bang id (key)
        for (String key : FileParserCSV.collection.keySet()) {
            FileParserCSV.collection.get(key).toString();
            System.out.println("Key : " + key);
            System.out.println("--------------------------------");
        }
    }

    /**
     * Save data into file
     */
    private void save() {
        try {
            FileWriter writer = new FileWriter(Main.file);
            String data = "";
            for (String key: FileParserCSV.collection.keySet()) {
                data += FileParserCSV.collection.get(key).getName() + "," + FileParserCSV.collection.get(key).getCoordinates().getX() + "," + FileParserCSV.collection.get(key).getCoordinates().getX() + "," + FileParserCSV.collection.get(key).getHealth() + "," + FileParserCSV.collection.get(key).getAstartesCategory() + "," + FileParserCSV.collection.get(key).getWeaponType() + "," + FileParserCSV.collection.get(key).getMeleeWeapon() + "," + FileParserCSV.collection.get(key).getChapter().getName() + "," + FileParserCSV.collection.get(key).getChapter().getWorld() + "\n";
            }
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(data);
            buffer.close();
            System.out.println("Success...");
        } catch (IOException ex) {

        }
    }

    /**
     * Enter data of a new element.
     * @param id
     * @return
     */
    //neu id la -1 thi id cua spacemarine se tu dong tao, neu khong se giu lai id cu
    private SpaceMarine setData(long id) {
        Scanner commandReader = new Scanner(System.in);
        String name = "";
        Coordinates coordinates = null;
        Double health = null;
        AstartesCategory astartesCategory = null;
        Weapon weaponType = null;
        MeleeWeapon meleeWeapon = null;
        Chapter chapter = null;


        while (name == "") {
            System.out.print("name (can't be empty): ");
            name = commandReader.nextLine();
        }
        while (coordinates == null) {
            System.out.print("coordinates (can't be empty, format \"x y\", x integer, y long: ");
            String[] c = commandReader.nextLine().trim().split(" ",2);
            try {
                coordinates = new Coordinates(Integer.parseInt(c[0]), Long.parseLong(c[1]));
            } catch (NumberFormatException ex) {
                System.out.println("Wrong number format");
                coordinates = null;
            }
        }
        while (health == null) {
            System.out.print("health (can't be empty, larger than 0): ");
            try{
                health = (Double.parseDouble(commandReader.nextLine().trim()));
                if (health <= 0) throw new LessThanZeroException();
            } catch (NumberFormatException ex) {
                System.out.println("Wrong number format");
                health = null;
            } catch (LessThanZeroException ex) {
                System.out.println("Value should be larger than 0");
                health = null;
            }
        }
        while (astartesCategory == null) {
            System.out.print("Astartes category (should be DREADNOUGHT, AGGRESSOR, ASSAULT, TACTICAL): ");
            try{
                astartesCategory = AstartesCategory.valueOf(commandReader.nextLine().trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
                System.out.println("Wrong astartes category format");
                astartesCategory = null;
            }
        }
        while (weaponType == null) {
            System.out.print("Weapon type (should be MELTAGUN, COMBI_FLAMER, PLASMA_GUN, FLAMER): ");
            try{
                weaponType = weaponType.valueOf(commandReader.nextLine().trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
                System.out.println("Wrong weapon type format");
                weaponType = null;
            }
        }
        while (meleeWeapon == null) {
            System.out.print("Melee weapon type (should be POWER_SWORD, MANREAPER, LIGHTING_CLAW, POWER_BLADE, POWER_FIST): ");
            try{
                meleeWeapon = meleeWeapon.valueOf(commandReader.nextLine().trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
                System.out.println("Wrong melee weapon type format");
                meleeWeapon = null;
            }
        }
        while (chapter == null) {
            System.out.print("Chapter (can't be empty, format \\\"name world\\\", name : string, world : string) : ");
            String[] c = commandReader.nextLine().trim().split(" ",2);
            chapter = new Chapter(c[0], c[1]);
        }
        SpaceMarine sp = new SpaceMarine(name,coordinates,(double) health,astartesCategory,weaponType,meleeWeapon,chapter);
        if (id != -1) sp.setId(id);
        return sp;
    }

    /**
     * Clear all data.
     */
    private void clear() {
        FileParserCSV.collection.clear();
        FileParserCSV.keySet.clear();
        System.out.println("Success...");
    }

    /**
     * Remove all elements from the collection that are less than the specified one (health).
     */
    private void remove_lower() {
        SpaceMarine temp = setData(-1);
        Enumeration<String> enumKey = FileParserCSV.collection.keys();
        while(enumKey.hasMoreElements()) {
            String key = enumKey.nextElement();
            SpaceMarine s = FileParserCSV.collection.get(key);
            if(s.compareTo(temp) ==-1) {
                FileParserCSV.collection.remove(key);
                FileParserCSV.keySet.remove(key);
            }
        }
        System.out.println("Successful...");
    }

    /**
     * Remove a element with a key.
     * @param key
     */
    private void remove_key(String key) {
        if (FileParserCSV.keySet.contains(key)) {
            FileParserCSV.collection.remove(key);
            FileParserCSV.keySet.remove(key);
            System.out.println("Success...");
        } else {
            System.out.println("Key not existed.");
        }
    }

    /**
     * Creat a new element with a new key.
     * @param key
     */
    private void insert(String key) {
        if (!FileParserCSV.keySet.contains(key)) {
            FileParserCSV.collection.put(key,setData(-1));
            FileParserCSV.keySet.add(key);
            System.out.println("Success...");
        } else {
            System.out.println("Key existed.");
        }
    }

    /**
     * Replace the value by key if the new value is greater than the old one.
     * @param key
     */
    private void replace_if_greater(String key){
        if (FileParserCSV.collection.containsKey(key)) {
            SpaceMarine temp = setData(-1);
            if (temp.compareTo(FileParserCSV.collection.get(key))==1) {
                FileParserCSV.collection.put(key,temp);
                System.out.println("Greater.Replace success...");
            } else {
                System.out.println("Not greater.");
            }
        } else {
            System.out.println("Key not existed");
        }
    }

    /**
     * Remove from the collection all elements whose key exceeds the given one.
     * @param key
     */
    private void remove_greater_key(String key) {
        try {
            long keylong = Long.parseLong(key);
            Enumeration<String> enumKey = FileParserCSV.collection.keys();
            while (enumKey.hasMoreElements()) {
                String k = enumKey.nextElement();
                if (keylong < (long) Long.parseLong(k)) {
                    FileParserCSV.collection.remove(k);
                    FileParserCSV.keySet.remove(k);
                }
            }
            System.out.println("Success...");
        } catch (NumberFormatException ex) {
            System.out.println("Wrong number format");
        }
    }

    /**
     * Remove from the collection all elements whose meleeWeapon field value is equivalent to the given one.
     * @param m
     */
    private void remove_all_by_melee_weapon(String m) {
        try {
            MeleeWeapon ml = MeleeWeapon.valueOf(m.toUpperCase());
            Enumeration<String> enumKey = FileParserCSV.collection.keys();
            while (enumKey.hasMoreElements()) {
                String k = enumKey.nextElement();
                if (ml == FileParserCSV.collection.get(k).getMeleeWeapon()) {
                    FileParserCSV.collection.remove(k);
                    FileParserCSV.keySet.remove(k);
                }
            }
            System.out.println("Success...");
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Wrong kind of melee weapon.");
        }
    }

    /**
     * Print the sum of the values of the health field for all elements of the collection.
     */
    private void sum_of_health(){
        double result = 0;
        for(String key : FileParserCSV.keySet) {
            result += FileParserCSV.collection.get(key).getHealth();
        }
        System.out.println("Sum of health = " + result);
    }

    /**
     * Display the values of the weaponType field of all elements in descending order.
     */
    private void print_field_descending_weapon_type() {
        ArrayList<SpaceMarine> arr = new ArrayList<>(FileParserCSV.collection.values());
        Collections.sort(arr,new WeaponComparator());
        for (SpaceMarine s: arr) {
            s.toString();
            System.out.println("--------------------------------");
        }
    }

    /**
     * Read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.
     * @param s
     */
    public void execute_script(String s)  {
        if (loadedScript.contains(s)) return;
        loadedScript.add(s);
        try {
            Scanner reader = new Scanner(new File(s));
            while(reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                executeCommand(line);
            }
            System.out.println("Script executed");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) { }
        loadedScript.remove(s);
    }

    /**
     * Print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream.
     */
    public void info() {
        System.out.println("Type of Collection element: Key-" + String.class.getName() + " Value-"+SpaceMarine.class.getName());
        System.out.println("Size: " + FileParserCSV.collection.size());
        System.out.println("Initial time: " + FileParserCSV.timeStamp);
    }
}