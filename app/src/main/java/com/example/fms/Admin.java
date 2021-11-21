package com.example.fms;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.fms.databinding.ActivityAdminBinding;
import com.example.fms.ui.Add_Students;
import com.example.fms.ui.Fees;
import com.example.fms.ui.VStudents;
import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String C ="Add Fees Details";
    String B = "Students";
    String D = "Add Students";
    TextView Heading;
    private DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        Heading = findViewById(R.id.heading);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VStudents()).commit();
            navigationView.setCheckedItem(R.id.nav_students);
            Heading.setText(B);
        }
    }



    public void onBackPressed() {
        if (drawer.isDrawerOpen( GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_add_students :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Add_Students()).commit();
                Heading.setText(D);
                break;
            case R.id.nav_logout :
                startActivity(new Intent(getApplicationContext(), ALogin.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                break;
            case R.id.nav_students :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VStudents()).commit();
                Heading.setText(B);
                break;
            case R.id.nav_add_fee:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fees()).commit();
                Heading.setText(C);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
