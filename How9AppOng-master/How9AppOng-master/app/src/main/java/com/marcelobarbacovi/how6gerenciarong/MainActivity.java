package com.marcelobarbacovi.how6gerenciarong;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.marcelobarbacovi.how6gerenciarong.alunos.AlunosActivity;
import com.marcelobarbacovi.how6gerenciarong.databinding.ActivityMainBinding;
import com.marcelobarbacovi.how6gerenciarong.parceiros.ParceirosMainActivity;
import com.marcelobarbacovi.how6gerenciarong.voluntarios.VoluntarioMainActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonJogar;
    Button buttonParceiro;
    Button buttonAluno;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }


// configuração do menu para aparcer na activity principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // configura ao clicar em cada item no meu para chamar a tela de cada item do menu (Voluntario, parceiro e aluno)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_home:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent1);


                return true;

            case R.id.menu_voluntarios:
                Intent intent = new Intent(getApplicationContext(), VoluntarioMainActivity.class);

                startActivity(intent);


                return true;

            case R.id.menu_parceiros:
                Intent intentp = new Intent(getApplicationContext(), ParceirosMainActivity.class);

                startActivity(intentp);
                // Not implemented here
                return true;

            case R.id.menu_alunos:
                Intent intenta = new Intent(getApplicationContext(), AlunosActivity.class);

                startActivity(intenta);

                return true;


            default:
                break;
        }

        return false;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}