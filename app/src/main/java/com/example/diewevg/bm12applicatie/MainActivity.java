package com.example.diewevg.bm12applicatie;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.diewevg.bm12applicatie.Leeractiviteit.DetailLeeractiviteit;
import com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent;
import com.example.diewevg.bm12applicatie.LeeractiviteitDocent.Feedback;
import com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent;
import com.example.diewevg.bm12applicatie.Resultaten.Resultaat;
import com.example.diewevg.bm12applicatie.Rooster.LesRooster;
import com.example.diewevg.bm12applicatie.Leeractiviteit.Leeractiviteit;

public class MainActivity extends AppCompatActivity
    implements
        Resultaat.OnFragmentInteractionListener,
        Leeractiviteit.OnFragmentInteractionListener,
        DetailLeeractiviteit.OnFragmentInteractionListener,
        LeeractiviteitDocent.OnFragmentInteractionListener,
        DetailLeeractiviteitDocent.OnFragmentInteractionListener,
        Feedback.OnFragmentInteractionListener,
        LesRooster.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        if (id == R.id.leeractiviteit) {
            fragment = new Leeractiviteit();
        } else if (id == R.id.resultaat) {
            fragment = new Resultaat();
        } else if (id == R.id.lesrooster) {
            fragment = new LesRooster();
        } else if (id == R.id.leeractiviteitDocent) {
            fragment = new LeeractiviteitDocent();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
