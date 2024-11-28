package com.marcelobarbacovi.how6gerenciarong.alunos;

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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelobarbacovi.how6gerenciarong.MainActivity;
import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.parceiros.ParceirosFragmentAdicionar;
import com.marcelobarbacovi.how6gerenciarong.parceiros.ParceirosFragmentListar;
import com.marcelobarbacovi.how6gerenciarong.parceiros.ParceirosMainActivity;
import com.marcelobarbacovi.how6gerenciarong.voluntarios.VoluntarioMainActivity;

public class AlunosActivity extends AppCompatActivity {
    AlunosFragmentAdcionar adcionaralunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alunos_activity_main);

       // click button adicionar alunos para ir para o fragment alunos
       Button btnAdicionar = findViewById(R.id.buttton_Adiconar_alunos);
       btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // metodo para acessar um fragment alunos a partir da activity alunos quando clicado no button adicionar alunos
                adcionaralunos     = new AlunosFragmentAdcionar();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_alunos, adcionaralunos);
                transaction.commit();
            }
        });

        // voltar a tela principal da lista de alunos com botão listar

        Button btnListar = findViewById(R.id.button_listar_alunos);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_alunos, new AlunosFragmentListar()).commit();
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
    }}