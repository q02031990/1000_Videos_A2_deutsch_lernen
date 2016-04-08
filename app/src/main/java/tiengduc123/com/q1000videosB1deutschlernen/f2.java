package tiengduc123.com.q1000videosB1deutschlernen;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import tiengduc123.com.q1000videosB1deutschlernen.Adapter.AdapterListVideoObject;
import tiengduc123.com.q1000videosB1deutschlernen.Database.DatabaseHelper;
import tiengduc123.com.q1000videosB1deutschlernen.Object.ListVideoObj;

public class f2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    ArrayList<ListVideoObj> _Cursor;
    ArrayList<ListVideoObj> mang;
    String CategoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadData();
        showAds();
    }

    public void showAds(){
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    public void loadData() {
        CategoryID = getIntent().getExtras().getString("CategoryID");
        setTitle(getIntent().getExtras().getString("CategoryName"));
        DatabaseHelper dbHelpter = new DatabaseHelper(this);
        //Toast.makeText(getApplicationContext(),dbHelpter.countVideoDetail() + " Videos were loaded", Toast.LENGTH_LONG).show();
        _Cursor = dbHelpter.GetAllNameOfListByID(CategoryID);
        ListView listView = (ListView) findViewById(R.id.listView);

        mang  = new ArrayList<ListVideoObj>();


        for (int i = 0; i < _Cursor.size(); i++) {
            String title = _Cursor.get(i).getListName();
            if(title.length()>30){
                title = title.substring(0,30)+ "...";
            }
            mang.add(new ListVideoObj(
                    _Cursor.get(i).getID(),
                    _Cursor.get(i).getListID(),
                    _Cursor.get(i).getListName(),
                    _Cursor.get(i).getImageKey(),
                    _Cursor.get(i).getCountVideo()
            ));
        }

        AdapterListVideoObject adapter = new AdapterListVideoObject(
                getApplicationContext(),
                R.layout.activity_item_video,
                mang
        );

        listView.setAdapter(adapter);

        //load lai list
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
        listView.refreshDrawableState();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChuyenManHinhPlay(position + "", _Cursor.get(position).getID() + "");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            //ChuyenManHinhCategory("0","8000 Videos Deutsch lernen");
           /* Intent it = new Intent(this, MainActivity.class);
            startActivity(it);*/

        }else if (id == R.id.nav_Grammatik) {
            ChuyenManHinhCategory("1","Grammatik");

        } else if (id == R.id.nav_Wortschaft) {
            ChuyenManHinhCategory("2","Wortschatz");

        } else if (id == R.id.nav_Dialogen) {
            ChuyenManHinhCategory("3", "Dialog");

        } else if (id == R.id.nav_Horen_Text) {
            ChuyenManHinhCategory("4", "Hören Text");

        } else if (id == R.id.nav_prufung) {
            ChuyenManHinhCategory("5", "Prüfung");

        } else if (id == R.id.nav_Dokument) {
            ChuyenManHinhCategory("6", "Dokument film");

        } else if (id == R.id.nav_Leben) {
            ChuyenManHinhCategory("7", "Leben");

        } else if (id == R.id.nav_share) {
            shareforFriend();

        } else if (id == R.id.nav_send) {
            Intent browserAbout = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tiengduc123.com"));
            startActivity(browserAbout);

        }else if(id == R.id.nav_rate_app){
            Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                this.startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                //UtilityClass.showAlertDialog(context, ERROR, "Couldn't launch the market", null, 0);
            }
        }else if(id == R.id.nav_app1){
            Uri uri = Uri.parse("market://details?id=tiengduc123.com.derdiedas");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            this.startActivity(goToMarket);
        }else if(id == R.id.nav_app2){
            Uri uri = Uri.parse("market://details?id=com.tiengduc123.deutschlernen.deutschlernen");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            this.startActivity(goToMarket);

        }else if(id == R.id.nav_app3){
            Uri uri = Uri.parse("market://details?id=tiengduc123.com.DeutschLernenA1");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            this.startActivity(goToMarket);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //share app
    public void shareforFriend(){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Share this App for friend");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + this.getPackageName());
        startActivity(Intent.createChooser(share, "Share link!"));
    }

    /****Tao Menu*************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_app:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=TiengDuc123"));
                startActivity(browserIntent);
                return true;

            case R.id.menu_share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_SUBJECT, "Share this App for friend");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + this.getPackageName());
                startActivity(Intent.createChooser(share, "Share link!"));
                return true;

            case R.id.menu_contact:
                Intent browserContact = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tiengduc123.com/contact.html"));
                startActivity(browserContact);
                return true;

            case R.id.menu_about:
                Intent browserAbout = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tiengduc123.com/about.html"));
                startActivity(browserAbout);
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void ChuyenManHinhCategory(String CategoryID, String CategoryName) {
        Intent it = new Intent(this, f2.class);
        it.putExtra("CategoryID", CategoryID);
        it.putExtra("CategoryName", CategoryName);
        startActivity(it);
    }

    public void ChuyenManHinhPlay(String position, String ListID) {
        Intent it = new Intent(this, activity_play.class);
        it.putExtra("key", position);
        it.putExtra("ListID", ListID);
        startActivity(it);
    }

}
