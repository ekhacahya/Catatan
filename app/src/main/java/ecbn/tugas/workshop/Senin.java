package ecbn.tugas.workshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by root on 24/06/15.
 */
public class Senin extends android.app.Fragment {
    DBHelper dbHelper;
    View rootView;
    private int id=-1;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ListView noteList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.senin, container, false);
        this.noteList = (ListView) rootView.findViewById(R.id.listRabu);
        dbHelper = new DBHelper(getActivity());

        ((ImageButton)rootView.findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(), add_note.class);
                startActivity(a);
            }
        });
        view();
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent a = new Intent(getActivity(), Detail.class);
                a.putExtra("id", i);
                //Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
                startActivity(a);
            }
        });
        return rootView;
    }
    private void view() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            cursor = db.rawQuery("SELECT * FROM catatan where hari='Senin'",null);
            adapter = new SimpleCursorAdapter(
                    getActivity(),
                    R.layout.lihat,
                    cursor,
                    new String[] {"judul","note","hari"},
                    new int[] {R.id.t_judul,R.id.t_note,R.id.t_hari});
            noteList.setAdapter(adapter);
        }

        catch(Exception e)
        {
            //ed_num.setText(e.toString());
        }
    }
}
