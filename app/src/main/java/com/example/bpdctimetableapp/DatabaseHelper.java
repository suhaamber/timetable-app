package com.example.bpdctimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static final String COLUMN_EVALUATION_TYPE = "EVALUATION_TYPE";
    public static final String COLUMN_EVALUATION_DATE = "EVALUATION_DATE";
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
                //"PRIMARY KEY(" + COLUMN_CLASS_HOUR + ", " + COLUMN_CLASS_DAY + "), " +
                "FOREIGN KEY(" + COLUMN_COURSE_ID + ") REFERENCES COURSES(" + COLUMN_COURSE_ID + "))";

        String createEvaluationTableStatement = "CREATE TABLE " + TABLE_EVALUATION + "(" +
                COLUMN_COURSE_ID + " INTEGER, " +
                COLUMN_EVALUATION_TYPE + " TEXT, " +
                COLUMN_EVALUATION_DATE + " TEXT, " +
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

    public int addCourse(CourseModel courseModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COURSE_NAME, courseModel.getCourseName());
        cv.put(COLUMN_INSTRUCTOR_NAME, courseModel.getInstructorName());

        try {
            long insert = db.insert(TABLE_COURSES, null, cv);
        }
        catch (Exception e) {

        }

        //return courseId since courseId is automatically incremented in the table, we need the update courseId for insertion into evaluation and class type tables
        int courseId = 0;

        String fetchCourseId = "SELECT " + COLUMN_COURSE_ID + " FROM " + TABLE_COURSES + " WHERE " + COLUMN_COURSE_NAME + " LIKE \"" + courseModel.getCourseName() + "\"";
        Cursor cursor = db.rawQuery(fetchCourseId, null);

        if(cursor.moveToFirst()) {
            courseId = cursor.getInt(0);
        }

        db.close();
        cursor.close();
        return courseId;
    }

    public boolean addReminder(ReminderModel reminderModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REMINDER_TITLE, reminderModel.getReminderTitle());
        cv.put(COLUMN_REMINDER_DATE_TIME, reminderModel.getReminderDateTime());

        long insert = db.insert(TABLE_REMINDERS, null, cv);
        db.close();

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
        db.close();

        if(insert==-1)
            return false;
        else
            return true;
    }

    public boolean addTimetable(TimetableModel timetableModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(timetableModel.getClassType() == null)
            timetableModel.setClassType("Lecture");

        cv.put(COLUMN_COURSE_ID, timetableModel.getCourseId());
        cv.put(COLUMN_CLASS_TYPE, timetableModel.getClassType());
        cv.put(COLUMN_CLASS_DAY, timetableModel.getClassDay());
        cv.put(COLUMN_CLASS_HOUR, timetableModel.getClassHour());

        long insert = db.insert(TABLE_TIMETABLE, null, cv);
        db.close();

        if(insert==-1)
            return false;
        else
            return true;
    }

    public boolean addEvaluation(EvaluationModel evaluationModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(evaluationModel.getEvaluationType() == null)
            evaluationModel.setEvaluationType("Mid-semester Test");

        cv.put(COLUMN_COURSE_ID, evaluationModel.getCourseId());
        cv.put(COLUMN_EVALUATION_TYPE, evaluationModel.getEvaluationType());
        cv.put(COLUMN_EVALUATION_DATE, evaluationModel.getEvaluationDate());

        long insert = db.insert(TABLE_EVALUATION, null, cv);
        db.close();

        if(insert==-1)
            return false;
        else
            return true;
    }
}
