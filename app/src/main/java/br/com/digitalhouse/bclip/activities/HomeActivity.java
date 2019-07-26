package br.com.digitalhouse.bclip.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.FragmentActionsListener;

public class HomeActivity extends AppCompatActivity implements FragmentActionsListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.home_botton_menu_id);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void substituirFragment(Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.home_container_id, fragment);
        transaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(id == R.id.home_bottom_id){
            transaction.replace(R.id.home_container_id, new NoticiasFragment());

        } else if (id == R.id.favoritos_bottom_id) {
            transaction.replace(R.id.home_container_id, new FavoritasFragment());

        } else if (id == R.id.buscar_bottom_id){
            transaction.replace(R.id.home_container_id, new BuscarFragment());
        }

        transaction.commit();

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem menuItem = menu.add(0, 0, 0, "Preferências");
        menuItem.setShowAsAction(menuItem.SHOW_AS_ACTION_NEVER);

//        MenuItem menuItem2 = menu.add(0, 0, 0, "Preferências");
//        menuItem.setShowAsAction(menuItem2.SHOW_AS_ACTION_NEVER);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0: Intent intent = new Intent(this, PreferenciasActivity.class);
                startActivity(intent);
//            case 1: Intent intent1 = new Intent(this, PreferenciasActivity.class);
//            startActivity(intent1);
                break;
        } return true;
    }
}
