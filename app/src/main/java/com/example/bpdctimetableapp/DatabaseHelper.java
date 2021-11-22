package com.example.bpdctimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String COLUMN_COURSE_NAME = "COURSE_NAME";
    public static final String COLUMN_INSTRUCTOR_NAME = "INSTRUCTOR_NAME";
    public static final String TABLE_COURSES = "COURSES";
    public static final String TABLE_TIMETABLE = "TIMETABLE";
    public static final String TABLE_EVALUATION = "EVALUATION";
    public static final String TABLE_REMINDERS = "REMINDERS";
    public static final String COLUMN_TAG = "TAG";
    public static final String TABLE_REMINDER_TAG = "REMINDER_" + COLUMN_TAG;
    public static final String COLUMN_COURSE_ID = "COURSE_ID";
    public static final String COLUMN_CLASS_TYPE = "CLASS_TYPE";
    public static final String COLUMN_CLASS_HOUR = "CLASS_HOUR";
    public static final String COLUMN_CLASS_DAY = "CLASS_DAY";
    public static final String COLUMN_TABLE_EVALUATION_TYPE = "TABLE_EVALUATION_TYPE";
    public static final String COLUMN_TABLE_EVALUATION_DATE = "TABLE_EVALUATION_DATE";
    public static final String COLUMN_REMINDER_ID = "REMINDER_ID";
    public static final String COLUMN_REMINDER_DATE_TIME = "REMINDER_DATE_TIME";
    public static final String COLUMN_REMINDER_TITLE = "REMINDER_TITLE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "courses.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCourseTableStatement = "CREATE TABLE " + TABLE_COURSES + "(" +
                COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COURSE_NAME + " TEXT, " +
                COLUMN_INSTRUCTOR_NAME + " TEXT)";

        String createTimetableTableStatement = "CREATE TABLE " + TABLE_TIMETABLE + "(" +
                COLUMN_COURSE_ID + " INTEGER," +
                COLUMN_CLASS_TYPE + " TEXT, " +
                COLUMN_CLASS_HOUR + " INTEGER, " +
                COLUMN_CLASS_DAY + " INTEGER, " +
                "PRIMARY KEY(" + COLUMN_CLASS_HOUR + ", " + COLUMN_CLASS_DAY + "), " +
                "FOREIGN KEY(" + COLUMN_COURSE_ID + ") REFERENCES COURSES(" + COLUMN_COURSE_ID + "))";

        String createEvaluationTableStatement = "CREATE TABLE " + TABLE_EVALUATION + "(" +
                COLUMN_COURSE_ID + " INTEGER, " +
                COLUMN_TABLE_EVALUATION_TYPE + " TEXT, " +
                COLUMN_TABLE_EVALUATION_DATE + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_COURSE_ID + ") REFERENCES COURSES(" + COLUMN_COURSE_ID + "))";
        //using date in string form for now

        String createReminderTableStatement = "CREATE TABLE " + TABLE_REMINDERS + "(" +
                COLUMN_REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REMINDER_TITLE + " TEXT," +
                COLUMN_REMINDER_DATE_TIME + " TEXT)";

        String createReminderTagTableStatement = "CREATE TABLE " + TABLE_REMINDER_TAG + "(" +
                COLUMN_REMINDER_ID + " INTEGER, " +
                COLUMN_TAG + " TEXT, " +
                "PRIMARY KEY(" + COLUMN_REMINDER_ID + ", " + COLUMN_TAG + "), " +
                "FOREIGN KEY(" + COLUMN_REMINDER_ID + ") REFERENCES " + TABLE_REMINDERS + "(" + COLUMN_REMINDER_ID + "))";

        db.execSQL(createCourseTableStatement);
        db.execSQL(createTimetableTableStatement);
        db.execSQL(createEvaluationTableStatement);
        db.execSQL(createReminderTableStatement);
        db.execSQL(createReminderTagTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addCourse(CourseModel courseModel) {
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

    public boolean addReminder(ReminderModel reminderModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REMINDER_TITLE, reminderModel.getReminderTitle());
        cv.put(COLUMN_REMINDER_DATE_TIME, reminderModel.getReminderDateTime());

        long insert = db.insert(TABLE_REMINDERS, null, cv);

        if(insert==-1)
            return false;
        else
            return true;
    }

    public boolean addReminderTags(ReminderTagModel reminderTagModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REMINDER_ID, reminderTagModel.getReminderId());
        cv.put(COLUMN_TAG, reminderTagModel.getReminderTag());

        long insert = db.insert(TABLE_REMINDER_TAG, null, cv);

        if(insert==-1)
            return false;
        else
            return true;
    }
}
