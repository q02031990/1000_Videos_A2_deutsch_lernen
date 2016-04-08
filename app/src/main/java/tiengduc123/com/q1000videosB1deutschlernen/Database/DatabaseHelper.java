package tiengduc123.com.q1000videosB1deutschlernen.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import tiengduc123.com.q1000videosB1deutschlernen.Object.CategoryObj;
import tiengduc123.com.q1000videosB1deutschlernen.Object.ListVideoObj;
import tiengduc123.com.q1000videosB1deutschlernen.Object.VideoObj;

/**
 * Created by Dell on 13/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    int SoLuongVideo = 3;
    int SoLuongListVideo = 4;

    boolean ProVersion = true;
    // trinh do A1 thi de Free het
    // tu A2 tro di thi de ProVersion = false de cho ng dung no phai down app tra tien


    public static final String DATABASE_NAME = "Database.db";
    public static final String TABLE_NAME = "VideoTable";

    public ArrayList<CategoryObj> getAllCategory(){
        ArrayList<CategoryObj> array_list = new ArrayList<CategoryObj>();
        Cursor res;
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT * " +
                    "FROM Category ";
        res = db.rawQuery(qr, null);

        if(res.getCount() >1) {
            res.moveToFirst();
            int i=1;
            while (res.isAfterLast() == false) {
                int ID = Integer.parseInt(res.getString(res.getColumnIndex("ID")).toString().trim());
                String categoryName = res.getString(res.getColumnIndex("CategoryName"));
                CategoryObj a = new CategoryObj(ID, categoryName);
                array_list.add(a);
                res.moveToNext();
            }
        }
        return array_list;
    }

    public int countVideoDetail(){
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT * " +
                "FROM VideoDetail";

        Cursor res;

        if(ProVersion){
            res = db.rawQuery(qr, null);
        }else{
            res = db.rawQuery(qr + " limit " + (SoLuongVideo * countListVideoDetail()), null);
        }

        return res.getCount();
    }

    public int countListVideoDetail(){
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT * " +
                "FROM ListDetail";
        Cursor res = db.rawQuery(qr, null);
        //return res.getCount();

        if(res.getCount() > (SoLuongListVideo)){
            if(!ProVersion){
                return (SoLuongListVideo);
            }
        }

        return res.getCount();
    }

    public ArrayList<VideoObj> GetVideoObjByList(String ListID){
        ArrayList<VideoObj> array_list = new ArrayList<VideoObj>();
        Cursor res;

        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT  VideoDetail.videoID, VideoDetail.VideoName, VideoDetail.VideoDuration, ListDetail.ListName " +
                    "FROM VideoDetail " +
                    "inner Join ListDetail " +
                    "on VideoDetail.GetFromList = ListDetail.ID and VideoDetail.GetFromList = '" + ListID + "'";

        if(ProVersion){
             res = db.rawQuery(qr, null);
        }else{
            res = db.rawQuery(qr + " limit " + SoLuongVideo, null);
            if(!ProVersion){
                Toast.makeText(context,"If you want more video Please use Pro Version", Toast.LENGTH_LONG).show();
            }
        }

        if(res.getCount() >1) {
            res.moveToFirst();
            int i=1;
            while (res.isAfterLast() == false) {
                String key = res.getString(res.getColumnIndex("videoID")).toString().trim();
                String timeVideo = res.getString(res.getColumnIndex("VideoDuration"));
                String nameVideo = i++ + ". " + res.getString(res.getColumnIndex("VideoName"));
                String ListName = res.getString(res.getColumnIndex("ListName"));

                VideoObj a = new VideoObj(nameVideo, timeVideo,key, ListName);

                array_list.add(a);
                res.moveToNext();
            }
        }
        return array_list;
    }

    public ArrayList<ListVideoObj> GetAllNameOfListByID(String IDCate){
        ArrayList<ListVideoObj> array_list = new ArrayList<ListVideoObj>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT * " +
                    "FROM ListDetail where CategoryID = " + IDCate + " ORDER BY ID DESC " ;
        Cursor res;
        if(ProVersion){
            res = db.rawQuery(qr, null);
        }else{
            res = db.rawQuery(qr + " limit " + SoLuongListVideo, null);
        }



        if(res.getCount() >1) {
            res.moveToFirst();
            int i=1;
            while (res.isAfterLast() == false) {
                int ID = Integer.parseInt(res.getString(res.getColumnIndex("ID")));
                String ListName = res.getString(res.getColumnIndex("ListName"));
                String ImageKey = res.getString(res.getColumnIndex("ImageKey"));
                /*if(ListName.length()>30){
                    ListName =ListName.substring(0,10)+ "...";
                }*/
                ListName = i++ + ". " + ListName;
                array_list.add(new ListVideoObj(ID,"", ListName,ImageKey, Integer.parseInt(getCountVideOfList(res.getString(res.getColumnIndex("ID"))))));
                res.moveToNext();
            }
        }
        return array_list;
    }

    public ArrayList<ListVideoObj> GetAllNameOfList(){
        ArrayList<ListVideoObj> array_list = new ArrayList<ListVideoObj>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT * " +
                    "FROM ListDetail" ;
        Cursor res;
        if(ProVersion){
            res = db.rawQuery(qr, null);
        }else{
            res = db.rawQuery(qr + " limit " + SoLuongListVideo, null);
        }



        if(res.getCount() >1) {
            res.moveToFirst();
            int i=1;
            while (res.isAfterLast() == false) {
                int ID = Integer.parseInt(res.getString(res.getColumnIndex("ID")));
                String ListName = res.getString(res.getColumnIndex("ListName"));
                String ImageKey = res.getString(res.getColumnIndex("ImageKey"));
                /*if(ListName.length()>30){
                    ListName =ListName.substring(0,10)+ "...";
                }*/
                ListName = i++ + ". " + ListName;
                array_list.add(new ListVideoObj(ID,"", ListName,ImageKey, Integer.parseInt(getCountVideOfList(res.getString(res.getColumnIndex("ID"))))));
                res.moveToNext();
            }
        }
        return array_list;
    }

    public String getCountVideOfList(String str){
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT count(ID) " +
                    "FROM VideoDetail " +
                    "where GetFromList = '" + str + "'" ;
        Cursor res = db.rawQuery(qr, null);

        String count ="" ;
        if(res.getCount() >0) {
            res.moveToFirst();
            int i=1;
            while (res.isAfterLast() == false) {
                count = res.getString(0);
                res.moveToNext();
            }
        }

        if(Integer.parseInt(count) > SoLuongVideo){
            if(!ProVersion){
                count = "" + SoLuongVideo;
            }
        }
        return count;
    }

    public boolean checkDBExit(){

        File file = new File("/data/data/" + context.getPackageName() + "/databases/Database.db");
        //long fileExists = getFolderSize(file);
        // Get length of file in bytes
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;

        if(fileSizeInKB > 50) {
            return true;
        }
        return false;
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        this.context= context;
        AssetDatabaseOpenHelper assetDB =  new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db =  assetDB.StoreDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!checkDBExit()) {
            /*db.execSQL("CREATE TABLE VideoDetail (" +
                    "ID INTEGER " +
                    "videoID TEXT, " +
                    "VideoName TEXT, " +
                    "VideoDuration TEXT, " +
                    "GetFromList INTEGER" +
                    ");");
            db.execSQL("CREATE TABLE ListVideoDetail (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "listID TEXT," +
                    "ListName TEXT" +
                    ");");*/
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
