package com.example.bpdctimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public ArrayList<ViewCourseCard> getCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String getCourses = "SELECT * FROM " + TABLE_COURSES;
        Cursor cursor = db.rawQuery(getCourses, null);

        ArrayList<ViewCourseCard> cardArrayList = new ArrayList<>();
        ArrayList<Integer> courseIdtempList = new ArrayList<>();
        int courseId = 0;
        String courseName;
        String instructorName;


        //fetch course id, course name, instructor name
        if(cursor.moveToFirst()) {
            do {
                    courseId = cursor.getInt(0);
                    courseName = cursor.getString(1);
                    instructorName = cursor.getString(2);

                    cardArrayList.add(new ViewCourseCard(courseId, courseName, instructorName, null));
                    courseIdtempList.add(courseId);

            }while(cursor.moveToNext());
        }
        else {
            //no records of courses in the database. do not add anything
        }

        String getClassHours;
        int classHour = 0;
        int classDay = 0;
        String classHours = "";
        String classType = "";


        for (int i = 0; i < cardArrayList.size(); i++) {
            //fetch class hours
            getClassHours = "SELECT " + COLUMN_CLASS_TYPE + ", " +COLUMN_CLASS_HOUR + ", " + COLUMN_CLASS_DAY + " FROM " +
                    TABLE_TIMETABLE + " WHERE " + COLUMN_COURSE_ID + "=" + String.valueOf(courseIdtempList.get(i));
            cursor = db.rawQuery(getClassHours, null);


            classHours = "";
            if(cursor.moveToFirst()) {
                do {
                    classType = cursor.getString(0);
                    classHour = cursor.getInt(1);
                    classDay = cursor.getInt(2);

                    classHours += classType + " " + classDayNumToString(classDay) +  String.valueOf(classHour) + "\n";
                } while (cursor.moveToNext());
            }
            cardArrayList.get(i).setClassHours(classHours);
        }

        //add attendance later
        cursor.close();
        db.close();
        return cardArrayList;
    }

    public String classDayNumToString(int classDay) {
        switch (classDay) {
            case 0: return "S";
            case 1: return "M";
            case 2: return "T";
            case 3: return "W";
            case 4: return "Th";
            default: return String.valueOf(classDay);
        }
    }

    public int addReminder(ReminderModel reminderModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REMINDER_TITLE, reminderModel.getReminderTitle());
        cv.put(COLUMN_REMINDER_DATE_TIME, reminderModel.getReminderDateTime());

        long insert = 0;
        try {
            insert = db.insert(TABLE_REMINDERS, null, cv);
        }
        catch (Exception e) {
            //error
        }

        int reminderId = 0;

        String fetchReminderId = "SELECT " + COLUMN_REMINDER_ID + " FROM " + TABLE_REMINDERS + " WHERE " + COLUMN_REMINDER_TITLE + " LIKE \"" + reminderModel.getReminderTitle() + "\"";
        Cursor cursor = db.rawQuery(fetchReminderId, null);

        if(cursor.moveToFirst()) {
            reminderId = cursor.getInt(0);
        }

        db.close();
        cursor.close();
        return reminderId;
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

    public ArrayList<ReminderViewCard> getReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        String getReminder = "SELECT * FROM " + TABLE_REMINDERS;
        Cursor cursor = db.rawQuery(getReminder, null);

        ArrayList<ReminderViewCard> reminderViewCards = new ArrayList<>();
        ArrayList<Integer> reminderIdList = new ArrayList<>();
        int reminderId = 0;
        String reminderTitle, reminderDateTime;

        //fetch reminder Id, title and dateTime
        if(cursor.moveToFirst()) {
            do {
                reminderId = cursor.getInt(0);
                reminderTitle = cursor.getString(1);
                reminderDateTime = cursor.getString(2);

                reminderViewCards.add(new ReminderViewCard(reminderTitle, null, reminderDateTime));
                reminderIdList.add(reminderId);
            } while(cursor.moveToNext());
        }

        for(int i = 0; i < reminderViewCards.size(); i++) {
            String getTags = "SELECT " + COLUMN_TAG + " FROM " + TABLE_REMINDER_TAG + " WHERE " + COLUMN_REMINDER_ID + "=" + String.valueOf(reminderIdList.get(i));
            cursor = db.rawQuery(getTags, null);

            String tag = "";
            if(cursor.moveToFirst()) {
                do {
                    tag += cursor.getString(0) + " ";
                } while (cursor.moveToNext());
            }

            reminderViewCards.get(i).setReminderTags(tag);
        }

        cursor.close();
        db.close();
        return reminderViewCards;
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

    //returns class type, hour and day on giving course ID
    public ArrayList<String> getTimetable(int courseId) {

        SQLiteDatabase db = this.getReadableDatabase();
        String getTimetable = "SELECT " + COLUMN_CLASS_TYPE + ", " + COLUMN_CLASS_HOUR +
                ", " + COLUMN_CLASS_DAY + " FROM " + TABLE_TIMETABLE + " WHERE " + COLUMN_COURSE_ID +
                "=" + String.valueOf(courseId);
        Cursor cursor = db.rawQuery(getTimetable, null);

        ArrayList<String> timetableList = new ArrayList<>();
        String classType = "";
        int classHour = 0, classDay = 0;

        if(cursor.moveToFirst()) {
            do {
                    classType = cursor.getString(0);
                    classHour = cursor.getInt(1);
                    classDay = cursor.getInt(2);

                    String temp = classType + " " + classDayNumToString(classDay) + String.valueOf(classHour);
                    timetableList.add(temp);

            } while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return timetableList;
    }

    //returns all class type, hour and day of all courses
    public ArrayList<TimetableModel> getTimetable() {
        SQLiteDatabase db = this.getReadableDatabase();
        String getTimetable = "SELECT " + TABLE_COURSES + "." + COLUMN_COURSE_ID + ", " +
                COLUMN_COURSE_NAME + ", " + COLUMN_CLASS_TYPE + ", " + COLUMN_CLASS_HOUR +
                ", " + COLUMN_CLASS_DAY + " FROM " + TABLE_COURSES + " INNER JOIN " + TABLE_TIMETABLE +
                " ON " + TABLE_COURSES + "." + COLUMN_COURSE_ID + "=" + TABLE_TIMETABLE + "." + COLUMN_COURSE_ID;
        Cursor cursor = db.rawQuery(getTimetable, null);

        ArrayList<TimetableModel>  timetableList = new ArrayList<>();
        int courseId = 0, classHour = 0, classDay = 0;
        String courseName = "", classType = "";

        if(cursor.moveToFirst()) {
            do {
                courseId = cursor.getInt(0);
                courseName = cursor.getString(1);
                classType = cursor.getString(2);
                classHour = cursor.getInt(3);
                classDay = cursor.getInt(4);

                timetableList.add(new TimetableModel(courseId, courseName, classType, classHour, classDay));
            }while (cursor.moveToNext());
        }

        return timetableList;
    }

    //returns course details on giving course ID
    public CourseModel getCourseName(int courseId) {

        SQLiteDatabase db = this.getReadableDatabase();
        String getTimetable = "SELECT " + COLUMN_COURSE_NAME + ", " + COLUMN_INSTRUCTOR_NAME +
                " FROM " + TABLE_COURSES + " WHERE " + COLUMN_COURSE_ID +
                "=" + String.valueOf(courseId);
        Cursor cursor = db.rawQuery(getTimetable, null);

        CourseModel courseModel = null;
        if(cursor.moveToFirst()) {
            String courseName = cursor.getString(0);
            String instructorName = cursor.getString(1);

            courseModel = new CourseModel(courseId, courseName, instructorName);
        }

        db.close();
        cursor.close();
        return courseModel;
    }

    //returns evaluation details given a course ID
    public ArrayList<String> getEvaluationDetails(int courseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String getTimetable = "SELECT " + COLUMN_EVALUATION_TYPE + ", " + COLUMN_EVALUATION_DATE +
                " FROM " + TABLE_EVALUATION + " WHERE " + COLUMN_COURSE_ID +
                "=" + String.valueOf(courseId);
        Cursor cursor = db.rawQuery(getTimetable, null);

        ArrayList<String> evaluation = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                String evaluationType = cursor.getString(0);
                String evaluationDate = cursor.getString(1);

                evaluation.add(evaluationType + " " + evaluationDate);
            } while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return evaluation;
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
