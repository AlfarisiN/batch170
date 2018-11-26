package bootcamp.com.batch170.belajar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.fragment.FragmentDua;
import bootcamp.com.batch170.fragment.FragmentSatu;
import bootcamp.com.batch170.fragment.FragmentTiga;

public class MenuNavigasiActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context = this;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navigasi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.open,
                R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        setNavigationHeader(headerView);

        setTitle("Demo Navigation Drawer");

        //default fragment menu yg tampil pertama kali
        FragmentSatu fragmentSatu = new FragmentSatu();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_all_menu, fragmentSatu, "Menu Kiri 1");
        fragmentTransaction.commit();
    }

    private void setNavigationHeader(View headerView){
        //
    }

    //Left Menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_menu_1){
            setActionBarTitle("Menu Kiri 1");
            FragmentSatu fragmentSatu = new FragmentSatu();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_all_menu, fragmentSatu, "Menu Kiri 1");
            fragmentTransaction.commit();
        }
        else if(id == R.id.nav_menu_2){
            setActionBarTitle("Menu Kiri 2");
            FragmentDua fragmentDua = new FragmentDua();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_all_menu, fragmentDua, "Menu Kiri 2");
            fragmentTransaction.commit();
        }
        else if(id == R.id.nav_menu_3){
            setActionBarTitle("Menu Kiri 3");
            FragmentTiga fragmentTiga = new FragmentTiga();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_all_menu, fragmentTiga, "Menu Kiri 3");
            fragmentTransaction.commit();
        }
        else if(id == R.id.nav_menu_4){
            Toast.makeText(context, "Anda menekan menu 4!!!", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    //Right Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //deteksi klik burger icon (home)
        if(id == android.R.id.home){
            //slide navigation drawer
            drawerLayout.openDrawer(Gravity.LEFT);
        }
        else if(id == R.id.option_1){
            //logic
        }
        else if(id == R.id.option_2){
            //logic
        }
        else if(id == R.id.option_3){
            //logic
        }

        return super.onOptionsItemSelected(item);
    }
}
