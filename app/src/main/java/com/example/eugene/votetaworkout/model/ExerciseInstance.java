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
public class ExerciseInstance implements Model {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseInstance instance = (ExerciseInstance) o;

        if (id != instance.id) return false;
        if (sets != instance.sets) return false;
        if (weight != instance.weight) return false;
        if (reps != instance.reps) return false;
        return exercise != null ? exercise.equals(instance.exercise) : instance.exercise == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sets;
        result = 31 * result + weight;
        result = 31 * result + reps;
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExerciseInstance{" +
                "id=" + id +
                ", sets=" + sets +
                ", weight=" + weight +
                ", reps=" + reps +
                ", exercise=" + exercise +
                '}';
    }
}
