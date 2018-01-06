package liwei.hackcode.persistent;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import liwei.hackcode.R;
import liwei.hackcode.persistent.entity.Book;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "LW");
                editor.putInt("age", 18);
                editor.apply();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn_4);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                Log.d(TAG, "n:" + name + ",age:" + age);
                /*SQLiteDatabase db = dbHelper.getWritableDatabase(); // 第一次会创建数据库
                ContentValues values = new ContentValues();
                values.put("name", "book_name");
                values.put("price", 11.09);
                db.insert("Book", null, values);

                ContentValues values1 = new ContentValues();
                values1.put("price", 10.01);
                db.update("Book", values, "name = ?", new String[]{"book_name"});*/

                /*SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));

                    } while (cursor.moveToNext());
                }
                cursor.close();*/

                // 使用litepal框架
//                Connector.getDatabase();
                /*Book b = new Book();
                b.setName("aa");
                b.save(); // 新增和更新
                b.updateAll("name = ? and age > ?", "aa", "1");

                DataSupport.deleteAll(Book.class, "age < ?", "10");

                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    //...
                }*/

            }
        });


    }

}
