package com.marcelobarbacovi.how6gerenciarong.voluntarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.marcelobarbacovi.how6gerenciarong.MainActivity;
import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.alunos.AlunosActivity;
import com.marcelobarbacovi.how6gerenciarong.parceiros.ParceirosFragmentAdicionar;
import com.marcelobarbacovi.how6gerenciarong.parceiros.ParceirosMainActivity;

public class VoluntarioMainActivity extends AppCompatActivity {
    VoluntariosFragmentAdicionar adcionarvoluntario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntario_main);


        // configurar o button adcionar para chamar o fragment Voluntarios adcionar dentro da activity dos voluntarios
        Button btnAdicionar = findViewById(R.id.buttton_Adiconar_voluntario);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adcionarvoluntario = new VoluntariosFragmentAdicionar();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_voluntario, adcionarvoluntario);
                transaction.commit();

            }
        });
            // button para vhamar o fragment listarVoluntarios dentro da activity voluntarios
                Button btnListarVoluntario = findViewById(R.id.button_listar_voluntario);
                btnListarVoluntario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_voluntario, new VoluntariosFragmentListar()).commit();
                    }
                });


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
        }