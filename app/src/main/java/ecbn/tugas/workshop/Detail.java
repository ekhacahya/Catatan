package ecbn.tugas.workshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class Detail extends ActionBarActivity {
    DBHelper dbHelper;
    Cursor cursor;
    ListAdapter adapter;
    EditText judul, isi;
    Spinner hari;
    String id;
    Senin a = new Senin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbHelper = new DBHelper(this);
        judul = (EditText)findViewById(R.id.e_judul);
        isi = (EditText)findViewById(R.id.e_isi);
        hari = (Spinner)findViewById(R.id.spnDay);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this, id,Toast.LENGTH_SHORT).show();
        view();

    }
    private void view() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            cursor = db.rawQuery("SELECT * FROM catatan where id="+id,null);
            String jud = cursor.getString( cursor.getColumnIndex("judul") );
            String day = cursor.getString( cursor.getColumnIndex("hari") );
            String is = cursor.getString( cursor.getColumnIndex("note") );
            judul.setText(jud);
            isi.setText(is);

        }
        catch(Exception e)
        {
            //ed_num.setText(e.toString());
        }
    }
}
