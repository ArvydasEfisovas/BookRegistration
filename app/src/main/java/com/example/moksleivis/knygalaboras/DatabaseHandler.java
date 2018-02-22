package com.example.moksleivis.knygalaboras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvyd on 1/20/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "db";

    // Contacts table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_BOOKS = "books";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_password = "password";
    private static final String KEY_email = "email";

    private static final String name = "name";
    private static final String release_year  = "release_year";
    private static final String author ="author";
    private static final String genre= "genre"; //check box
    private static final String rarity= "rarity";//choice box
    private static final String pages = "pages";
    private  static final String cover = "cover"; // radio button hard/soft cover
    private  static final String check1 = "check1";
    private  static final String check2 = "check2";
    private  static final String check3 = "check3";
    private  static final String check4 = "check4";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
               + KEY_password + " TEXT," + KEY_email  +")";
       db.execSQL(CREATE_CONTACTS_TABLE);



        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + name + " TEXT,"
                + release_year + " TEXT," + author  + " TEXT," +  genre +
                " TEXT," + rarity + " TEXT," + pages + " TEXT," + cover + " TEXT,"  +
                check1 + " INTEGER," + check2 + " INTEGER," + check3 + " INTEGER," + check4 +    ")";
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new user
    void addUsers(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_password, user.getPhoneNumber());
        values.put(KEY_email, user.getEmail());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    void addBook(Knyga knyga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(name, knyga.getName());
        values.put(release_year, knyga.getRelease_year());
        values.put(author,knyga.getAuthor());
        values.put(genre,knyga.getGenre());
        values.put(rarity,knyga.getRarity());
        values.put(pages,knyga.getPages());
        values.put(cover,knyga.getCover());
        values.put(check1,knyga.getCheck1());
        values.put(check2,knyga.getCheck2());
        values.put(check3,knyga.getCheck3());
        values.put(check4,knyga.getCheck4());
        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }

    User getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_NAME, KEY_password,KEY_email}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return user
        return user;
    }

    User getUserForLogin(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();
        Cursor cursor = db.query(TABLE_USERS, new String[] {
                        KEY_NAME, KEY_password,KEY_email}, KEY_NAME + "=?" + " AND " + KEY_password  + "=?" ,
                new String[] { String.valueOf(name),String.valueOf(password)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){


         user = new User(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));
        }
        return user;
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setPhoneNumber(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }



    public List<Knyga> getAllBooks() {
        List<Knyga> bookList = new ArrayList<Knyga>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Knyga knyga = new Knyga();
                knyga.setId(Integer.parseInt(cursor.getString(0)));
                knyga.setName(cursor.getString(1));
                knyga.setRelease_year(cursor.getString(2));
                knyga.setAuthor(cursor.getString(3));
                knyga.setGenre(cursor.getString(4));
                knyga.setRarity(cursor.getString(5));
                knyga.setPages(Integer.parseInt((cursor.getString(6))));
                knyga.setCover(cursor.getString(7));
               knyga.setCheck1(Integer.parseInt((cursor.getString(8))));
               knyga.setCheck2(Integer.parseInt((cursor.getString(9))));
             knyga.setCheck3(Integer.parseInt((cursor.getString(10))));
              knyga.setCheck4(Integer.parseInt((cursor.getString(11))));
                bookList.add(knyga);
            } while (cursor.moveToNext());
        }

        return bookList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_password, user.getPhoneNumber());
        values.put(KEY_email, user.getEmail());
        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getID()) });
    }

    public void updateBook(Knyga knyga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(name, knyga.getName()); // User Name
        values.put(release_year, knyga.getRelease_year()); // User Phone
        values.put(author,knyga.getAuthor());
        values.put(genre,knyga.getGenre());
        values.put(rarity,knyga.getRarity());
        values.put(pages,knyga.getPages());
        values.put(cover,knyga.getCover());
        values.put(check1,knyga.getCheck1());
        values.put(check2,knyga.getCheck2());
        values.put(check3,knyga.getCheck3());
        values.put(check4,knyga.getCheck4());
         db.update(TABLE_BOOKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(knyga.getId()+1)});
         db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getID()) });
        db.close();
    }

    public void deleteBooks(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
