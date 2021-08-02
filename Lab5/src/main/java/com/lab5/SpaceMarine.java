package com.lab5;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.lab5.com.lab5.Main character. Is stored in the collection.
 */

public class SpaceMarine implements Comparable<SpaceMarine>  {
    private long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private double health;
    private AstartesCategory astartesCategory;
    private Weapon weaponType;
    private MeleeWeapon meleeWeapon;
    private Chapter chapter;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Constructor of space marine
     * @param name
     * @param coordinates
     * @param health
     * @param astartesCategory
     * @param weaponType
     * @param meleeWeapon
     * @param chapter
     */
    public SpaceMarine( String name, Coordinates coordinates, double health, AstartesCategory astartesCategory, Weapon weaponType, MeleeWeapon meleeWeapon,Chapter chapter ) {
        this.id = generateID();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.health = health;
        this.astartesCategory = astartesCategory;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }
    public SpaceMarine() {}

    /**
     * @return ID of the marine.
     */
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id =id;
    }
    /**
     * @return Name of the marine.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Data.com.lab5.Coordinates of the marine.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date of the marine.
     */
    public Date getcreationDate() {
        return creationDate;
    }

    /**
     * @return Health of the marine.
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return Category of the marine.
     */
    public AstartesCategory getAstartesCategory() {
        return astartesCategory;
    }

    /**
     * @return Data.com.lab5.Weapon type of the marine.
     */
    public Weapon getWeaponType() {
        return weaponType;
    }

    /**
     * @return Melee weapon of the marine.
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     * @return Data.com.lab5.Chapter of the marine.
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Print out informations of space marine
     * @return info of space marine
     */
    @Override
    public String toString() {
        String info = "";
        info += "Солдат №" + id;
        info += "\n(добавлен " + dateFormat.format(creationDate) + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Здоровье: " + health;
        info += "\n Категория: " + astartesCategory;
        info += "\n Дальнее оружие: " + weaponType;
        info += "\n Ближнее оружие: " + meleeWeapon;
        info += "\n Орден: " + chapter;
        System.out.println(info);
        return info;
    }

    /**
     * Set random ID of space marine
     * @return a random ID
     */
    private long generateID() {
        boolean c = false;
        Long idTemp = null;
        do {
            c = true;
            idTemp =Long.valueOf(Math.round(Math.random()*1000000000));
            for (String key: FileParserCSV.collection.keySet()){
                if (idTemp == FileParserCSV.collection.get(key).getId()) {
                    c = false;
                }
            }
        } while (idTemp < 100000000 && c);
        return idTemp;
    }

    /**
     * To compare health of the space marine
     * @param o
     * @return 1 if greatter, 0 if equal, -1 if smaller
     */
    @Override
    public int compareTo(SpaceMarine o) {
        if (o == null || this.getHealth()>o.getHealth()) return 1;
        else if (this.getHealth()==o.getHealth()) return 0;
        else return -1;
    }
}