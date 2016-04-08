package tiengduc123.com.q1000videosB1deutschlernen.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetDatabaseOpenHelper {
    // tên file datebase trong thư mục assets
    private static final String DB_NAME = "Database.db";
    private Context context;
    public static String PACKAGE_NAME;

    public AssetDatabaseOpenHelper(Context context) {
        this.context = context;
        PACKAGE_NAME = context.getPackageName();
    }

    public SQLiteDatabase StoreDatabase() {
        ///data/data/{package của bạn}/databases
        String path = "/data/data/"+PACKAGE_NAME+"/databases";
        File pathDb = new File(path);
        try {
            // kiểm tra nếu có thư mục databases thì sẽ tạo
            if (!pathDb.exists()){
                pathDb.mkdir();
            }
            // kiểm tra file đó chưa tồn tại thì copy vào
            // điều kiện này tránh trường hợp mỗi lần mở ứng dụng sẽ copy vào.
            if (!checkDBExit()) {
                copy(path);
            }
        } catch (IOException e) {
            Log.d("IOException", e.getMessage());
        }
        Log.i("DB",context.getDatabasePath(DB_NAME).getPath());
        return SQLiteDatabase.openDatabase(context.getDatabasePath(DB_NAME).getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    public boolean checkDBExit(){

        File file = new File("/data/data/" + PACKAGE_NAME + "/databases/Database.db");
        //long fileExists = getFolderSize(file);
        // Get length of file in bytes
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;

        if(fileSizeInKB > 5) {
            return true;
        }
        return false;
    }
    /**
     * Thực hiện copy file
     * @param path Đường đãn thư mục đến
     * @throws IOException
     */
    private void copy(String path) throws IOException {

        // mở file trong thư mục assets
        InputStream is = context.getAssets().open(DB_NAME);
        FileOutputStream fos = new FileOutputStream(path + "/Database.db" );
        byte buffer[] = new byte[1024];
        int length;

        // đọc và ghi vào thư mục databases
        while ((length = is.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }
        is.close();
        fos.flush();
        fos.close();
    }

}