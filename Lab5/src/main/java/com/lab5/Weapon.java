package com.lab5;

/**
 * Enumeration with marine weapon constants.
 */

public enum Weapon {
    MELTAGUN,
    COMBI_FLAMER,
    PLASMA_GUN,
    FLAMER;

    /**
     * Generates a list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (Weapon weaponType : values()) {
            nameList += weaponType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
