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
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
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

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
               + KEY_password + " TEXT," + KEY_email  +")";
       db.execSQL(CREATE_CONTACTS_TABLE);



        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + name + " TEXT,"
                + release_year + " TEXT," + author  + " TEXT," +  genre +
                " TEXT," + rarity + " TEXT," + pages + " TEXT," + cover + ")";
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_password, contact.getPhoneNumber()); // Contact Phone
        values.put(KEY_email,contact.getEmail());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    void addBook(Knyga knyga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(name, knyga.getName()); // Contact Name
        values.put(release_year, knyga.getRelease_year()); // Contact Phone
        values.put(author,knyga.getAuthor());
        values.put(genre,knyga.getGenre());
        values.put(rarity,knyga.getRarity());
        values.put(pages,knyga.getPages());
        values.put(cover,knyga.getCover());
        // Inserting Row
        db.insert(TABLE_BOOKS, null, values);
        db.close(); // Closing database connection
    }



    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_password,KEY_email}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return contact;
    }

    Contact getContactForLogin(String name,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Contact contact = new Contact();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        KEY_NAME, KEY_password,KEY_email}, KEY_NAME + "=?" + " AND " + KEY_password  + "=?" ,
                new String[] { String.valueOf(name),String.valueOf(password)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){


         contact = new Contact(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));
        }
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }



    public List<Knyga> getAllBooks() {
        List<Knyga> bookList = new ArrayList<Knyga>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
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
                // Adding contact to list
                bookList.add(knyga);
            } while (cursor.moveToNext());
        }

        // return contact list
        return bookList;
    }



    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_password, contact.getPhoneNumber());
        values.put(KEY_email,contact.getEmail());
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
