package com.mysvac.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class SQLiteManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;
    private static SQLiteManager manager = null;
    private SQLiteDatabase mDB = null;

    public static final String TABLE_NAME = "test_info";


    public SQLiteManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public SQLiteManager(Context context, int version){
        super(context, DB_NAME, null, version);
    }

    public static SQLiteManager getInstance(Context context, int version){
        if(version>0 && manager==null){
            manager = new SQLiteManager(context.getApplicationContext(),version);
        }else if(manager==null){
            manager = new SQLiteManager(context.getApplicationContext());
        }
        return manager;
    }

    public SQLiteDatabase openReadLink(){
        if(mDB == null || !mDB.isOpen()){
            mDB = manager.getReadableDatabase();
        }
        return mDB;
    }

    public SQLiteDatabase openWriteLink(){
        if(mDB == null || !mDB.isOpen()){
            mDB = manager.getWritableDatabase();
        }
        return mDB;
    }

    public void closeLink(){
        if(mDB != null && mDB.isOpen()){
            mDB.close();
            mDB=null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String drop_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        String create_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "content VARCHAR NOT NULL "+
                ");";
        db.execSQL(drop_SQL);
        db.execSQL(create_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }


    public int delete(String condition){
        return mDB.delete(TABLE_NAME,condition,null);
    }

    public long insert(ArrayList<TestEntity> entityList){
        long result = -1;
        for(int i=0; i<entityList.size(); ++i){
            TestEntity item = entityList.get(i);

            ContentValues cv = new ContentValues();
            cv.put("content",item.getContent());

            result = mDB.insert(TABLE_NAME,"content", cv);
            if(result==-1){
                return result;
            }
        }
        return result;
    }

    public int update(TestEntity item, String condition){
        ContentValues cv = new ContentValues();
        cv.put("_id",item.get_id());
        cv.put("content",item.getContent());

        return mDB.update(TABLE_NAME,cv,condition,null);
    }

    public ArrayList<TestEntity> query(String condition){
        String sql = "SELECT _id,content FROM " + TABLE_NAME + " WHERE " + condition +";";
        ArrayList<TestEntity> entityList = new ArrayList<>();

        Cursor cursor = mDB.rawQuery(sql, null);
        while(cursor.moveToNext()){
            TestEntity item = new TestEntity();
            item.set_id(cursor.getInt(0));
            item.setContent(cursor.getString(1));
            entityList.add(item);
        }
        cursor.close();
        return entityList;
    }

        public TestEntity queryLast(){
            TestEntity item = null;
            ArrayList<TestEntity> list = query("_id>=0");
            if(list.size()>0){
                item = list.get(list.size()-1);
            }
            return item;
        }

}
