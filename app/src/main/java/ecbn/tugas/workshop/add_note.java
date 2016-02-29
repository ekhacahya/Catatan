package ecbn.tugas.workshop;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class add_note extends ActionBarActivity {

    EditText judul,isi;
    Spinner hari;
    DBHelper dbHelper;
    String[] Hari = {"Senin", "Selasa", "Rabu","Kamis","Jumat","Sabtu","Minggu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        dbHelper = new DBHelper(this);
        judul = (EditText)findViewById(R.id.e_judul);
        isi = (EditText)findViewById(R.id.e_isi);
        hari = (Spinner)findViewById(R.id.spnDay);
        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Hari);
        hari.setAdapter(stringArrayAdapter);

        Button save = (Button)findViewById(R.id.btnsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String sJudul = judul.getText().toString();
                    String sIsi = isi.getText().toString();
                    String sHari = hari.getSelectedItem().toString();
                    addData(sJudul, sIsi,sHari);
                    Toast.makeText(getBaseContext(), sHari+sIsi+sJudul,Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addData(String jud,String isi, String hari) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            db.execSQL("insert into "+ dbHelper.TABLE +" values(null,'"+jud+"','"+isi+"','"+hari+"')");
        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "kesalahan "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
