package ecbn.tugas.workshop;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mNavItems.add(new NavItem("Semua Hari", "Transaksi dan Histori", R.drawable.eka));
        mNavItems.add(new NavItem("Senin", "Laporan penjualan", R.drawable.eka));
        mNavItems.add(new NavItem("Selasa", "Temukan penjual pulsa", R.drawable.eka));
        mNavItems.add(new NavItem("Rabu", "Atur Saldo, Harga dll", R.drawable.eka));
        mNavItems.add(new NavItem("Kamis", "Panduan menggunakan m-pulsa", R.drawable.eka));
        mNavItems.add(new NavItem("Jumat", "Kenali m-pulsa", R.drawable.eka));
        mNavItems.add(new NavItem("Sabtu", "Kenali m-pulsa", R.drawable.eka));
        mNavItems.add(new NavItem("Minggu", "Kenali m-pulsa", R.drawable.eka));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
    }

    //inner class untuk merepresentasikan menu hari
    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }
    //inner class untuk implementasi adapternya untuk item item yang ada di menu samping juga
    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.daftar_hari, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    //implementasi seleksi menu di samping
    private void selectItemFromDrawer(int position) {
        //Fragment fragment = new PreferencesFragment();

        Fragment fragment = new home();
        Activity obj = null;
        switch (position){
            case 0 :
                fragment = new AllDay();
                break;
            case 1 :
                fragment = new Senin();
                break;
            case 2 :
                fragment = new Selasa();
                break;
            case 3 :
                fragment = new Rabu();
                break;
            case 4 :
                fragment = new Kamis();
                break;
            case 5 :
                fragment = new Jumat();
                break;
            case 6 :
                fragment = new Sabtu();
                break;
            case 7 :
                fragment = new Minggu();
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);

        //tombol diatas
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout , R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            //Log.d(TAG, "action bar clicked");
            //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            if (mDrawerLayout.isDrawerOpen(mDrawerPane)){
                mDrawerLayout.closeDrawer(mDrawerPane);
            }else {
                mDrawerLayout.openDrawer(mDrawerPane);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
