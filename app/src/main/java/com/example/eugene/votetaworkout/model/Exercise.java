package com.example.eugene.votetaworkout.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Exercise model class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
@DatabaseTable(tableName = "exercise")
public class Exercise {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "description")
    private String description;

    @DatabaseField(columnName = "category_id",foreign = true, foreignAutoRefresh = true)
    private Category category;

    public Exercise() {
    }

    public Exercise(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
