package com.example.eugene.votetaworkout.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ExerciseInstance model class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
@DatabaseTable(tableName = "exercise_instance")
public class ExerciseInstance {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "sets")
    private int sets;

    @DatabaseField(columnName = "weight")
    private int weight;

    @DatabaseField(columnName = "reps")
    private int reps;

    @DatabaseField(columnName = "exercise_id", foreign = true, foreignAutoRefresh = true)
    private Exercise exercise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
