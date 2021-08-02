package com.lab5;

/**
 * Enumeration with marine melee weapon constants.
 */

public enum MeleeWeapon {
    POWER_SWORD,
    MANREAPER,
    LIGHTING_CLAW,
    POWER_BLADE,
    POWER_FIST;

    /**
     * Generates a list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (MeleeWeapon meleeWeapon : values()) {
            nameList += meleeWeapon.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
