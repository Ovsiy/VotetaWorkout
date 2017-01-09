package com.example.eugene.votetaworkout.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
@DatabaseTable(tableName = "workout_exercise_instance")
public class WorkoutExerciseInstance {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(foreign = true, columnName = "workout_id")
    private Workout workout;

    @DatabaseField(foreign = true, columnName = "exercise_instance_id")
    private ExerciseInstance exerciseInstance;

    public WorkoutExerciseInstance() {
    }

    public WorkoutExerciseInstance(Workout workout, ExerciseInstance exerciseInstance) {
        this.workout = workout;
        this.exerciseInstance = exerciseInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public ExerciseInstance getExerciseInstance() {
        return exerciseInstance;
    }

    public void setExerciseInstance(ExerciseInstance exerciseInstance) {
        this.exerciseInstance = exerciseInstance;
    }
}
