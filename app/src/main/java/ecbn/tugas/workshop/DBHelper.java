package ecbn.tugas.workshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 24/06/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "catatanworkshop.db";
    private static final int DATABASE_VERSION = 1;
    // Table name
    public static final String TABLE = "catatan";
    // Columns
    public static final String judul = "judul";
    public static final String isi = "note";
    public static final String day = "hari";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE + "( _id"
                + " integer primary key autoincrement, " + judul + " text not null, "
                + isi + " text not null, "
                + day + " text not null);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
