package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.FragmentActionsListener;
import br.com.digitalhouse.bclip.modules.CadastroEmpresa.view.CadastroEmpresaActivity;
import br.com.digitalhouse.bclip.modules.Login.view.LoginActivity;
import br.com.digitalhouse.bclip.modules.Noticias.view.NoticiasFragment;
import br.com.digitalhouse.bclip.modules.NoticiasPreferencias.view.NoticiasPreferenciaFragment;
import br.com.digitalhouse.bclip.modules.Preferencias.view.PreferenciasActivity;

public class HomeActivity extends AppCompatActivity implements FragmentActionsListener, BottomNavigationView.OnNavigationItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;




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

        } else if (id == R.id.prefrencias_bottom_id){
            substituirFragment(new NoticiasPreferenciaFragment());
        }


        return true;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_preferencias:
                Intent intent = new Intent(this, PreferenciasActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_concorrentes:
                Intent intent1 = new Intent(this, CadastroEmpresaActivity.class);
                startActivity(intent1);
                return true;
            case R.id.item_sair:
                signOut();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.item_concorrentes:
                Intent intent = new Intent(this, CadastroEmpresaActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_preferencias:
                Intent intent2 = new Intent(this, PreferenciasActivity.class);
                startActivity(intent2);
                return true;
            case R.id.item_sair:
                signOut();
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
            default:
                return false;
        }
    }

    private void signOut() {
        firebaseAuth.getInstance()
                .signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
