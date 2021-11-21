package com.example.bpdctimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_COURSES = "COURSES";
    public static final String COLUMN_COURSE_ID = "COURSE_ID";
    public static final String COLUMN_COURSE_NAME = "COURSE_NAME";
    public static final String COLUMN_INSTRUCTOR_NAME = "INSTRUCTOR_NAME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "courses.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCourseTableStatement = "CREATE TABLE " + TABLE_COURSES + "(" +
                COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COURSE_NAME + " TEXT, " +
                COLUMN_INSTRUCTOR_NAME + " TEXT)";

        String createTimetableTableStatement = "CREATE TABLE TIMETABLE(" +
                "COURSE_ID INTEGER," +
                "CLASS_TYPE TEXT, " +
                "CLASS_HOUR INTEGER, " +
                "CLASS_DAY INTEGER, " +
                "PRIMARY KEY(COURSE_ID, CLASS_TYPE), " +
                "FOREIGN KEY(COURSE_ID) REFERENCES COURSES(COURSE_ID))";

        String createEvaluationTableStatement = "CREATE TABLE EVALUATION(" +
                "COURSE_ID INTEGER, " +
                "EVALUATION_TYPE TEXT, " +
                "EVALUATION_DATE TEXT, " +
                "FOREIGN KEY(COURSE_ID) REFERENCES COURSES(COURSE_ID))";
        //using date in string form for now

        db.execSQL(createCourseTableStatement);
        db.execSQL(createTimetableTableStatement);
        db.execSQL(createEvaluationTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CourseModel courseModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COURSE_NAME, courseModel.getCourseName());
        cv.put(COLUMN_INSTRUCTOR_NAME, courseModel.getInstructorName());

        long insert = db.insert(TABLE_COURSES, null, cv);

        if(insert==-1)
            return false;
        else
            return true;
    }
}
