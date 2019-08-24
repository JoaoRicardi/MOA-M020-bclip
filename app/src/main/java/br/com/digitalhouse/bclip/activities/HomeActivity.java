package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.FragmentActionsListener;
import br.com.digitalhouse.bclip.modules.CadastroEmpresa.view.CadastroEmpresaActivity;
import br.com.digitalhouse.bclip.modules.Noticias.view.NoticiasFragment;
import br.com.digitalhouse.bclip.modules.NoticiasPreferencias.view.NoticiasPreferenciaFragment;

public class HomeActivity extends AppCompatActivity implements FragmentActionsListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        substituirFragment(new NoticiasFragment());

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

        if(id == R.id.home_bottom_id){
            substituirFragment(new NoticiasFragment());
        } else if (id == R.id.favoritos_bottom_id) {
            substituirFragment(new FavoritasFragment());

        } else if (id == R.id.buscar_bottom_id){
            substituirFragment(new NoticiasPreferenciaFragment());
        }


        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem menuItem = menu.add(0, 0, 0, "Preferências");
        menuItem.setShowAsAction(menuItem.SHOW_AS_ACTION_NEVER);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0: Intent intent = new Intent(this, CadastroEmpresaActivity.class);
                startActivity(intent);
                break;
        } return true;
    }
}
