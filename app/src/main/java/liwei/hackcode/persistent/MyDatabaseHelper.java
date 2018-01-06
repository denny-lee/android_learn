package liwei.hackcode.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liwei on 2018/1/2.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE = "create table Book (id integer primary key autoincrement, author text, price real)";

    private static final String TAG = "MyDatabaseHelper";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 只在第一次创建,有了数据库后，就不再执行
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);
    }
}
