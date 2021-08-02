package com.lab5;

/**
 * Data.com.lab5.Chapter with marines.
 */

public class Chapter {
    private final String name;
    private final String world;

    /**
     * Constructor of chapter
     * @param name name of Chapter
     * @param world world of Chapter
     */
    public Chapter(String name, String world){
        this.name = name;
        this.world = world;
    }

    /**
     * @return Name of the chapter.
     */
    public String getName() {
        return name;
    }

    /**
     * @return World of the chapter.
     */
    public String getWorld() {
        return world;
    }

    /**
     *
     * @return Name and world
     */
    @Override
    public String toString() {
        return "Name:" + name + " -" + " World" + ":" + world ;
    }
}

