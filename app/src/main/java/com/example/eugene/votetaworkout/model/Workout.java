package com.example.eugene.votetaworkout.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Workout model class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
@DatabaseTable(tableName = "workout")
public class Workout implements Model {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
