package com.example.eugene.votetaworkout.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Category model class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
@DatabaseTable(tableName = "category")
public class Category implements Model {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    @ForeignCollectionField(columnName =  "emails", eager = true)
    private ForeignCollection<Exercise> exercises;

    public List<ExerciseInstance> getInstances() {
        List<ExerciseInstance> instances = new ArrayList<>();
        for (Exercise exercise : exercises) {
            instances.addAll(exercise.getExerciseInstances());
        }

        return instances;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public ForeignCollection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ForeignCollection<Exercise> exercises) {
        this.exercises = exercises;
    }
}
