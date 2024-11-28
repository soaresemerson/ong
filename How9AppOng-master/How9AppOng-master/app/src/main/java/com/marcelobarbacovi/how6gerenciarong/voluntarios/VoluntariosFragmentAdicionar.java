package com.marcelobarbacovi.how6gerenciarong.voluntarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.database.DataBaseHelper;


public class VoluntariosFragmentAdicionar extends Fragment {

    private TextInputEditText textInputNome, textInputEspecializacao, textInputFuncao;
    private EditText editTextTelefone;

    public VoluntariosFragmentAdicionar() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.voluntarios_fragment_adicionar, container, false);

        // configura os campos dofragment adcionarVoluntarios dentro das variaveis por meio do seus Id
        textInputNome = v.findViewById(R.id.textNome_Voltuntario);
        textInputEspecializacao = v.findViewById(R.id.text_adionar_especializacao);
        editTextTelefone = v.findViewById(R.id.Adcionar_Telefone_Voluntario);
        textInputFuncao = v.findViewById(R.id.text_adcionar_funcao);


            // configura o button adcionar voluntarios chamando o metodo adcionar
        Button btnAdcionarVoluntario = v.findViewById(R.id.button_salvar_voluntario);
        btnAdcionarVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();


            }
        });

        return  v;
    }
// valida dos campos se estão vazios do adcionar medicos
    private void adicionar(){
        if (textInputNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do Voluntario", Toast.LENGTH_LONG).show();
        } else if (textInputEspecializacao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade do voluntario", Toast.LENGTH_LONG).show();
        } else if (editTextTelefone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do telefone do voluntario", Toast.LENGTH_LONG).show();
        } else if (textInputFuncao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe as funções pertinentes ao voluntario ", Toast.LENGTH_LONG).show();
        } else {

            // se não vazios configura os dados dentro da variavel da classe voluntarios os dados docampos do fragment adcionarVoluntarios
            DataBaseHelper databaseHelper = new DataBaseHelper(getActivity());
            Voluntario v = new Voluntario();
            v.setName(textInputNome.getText().toString());
            v.setEspecializacao(textInputEspecializacao.getText().toString());
            v.setTelefone(editTextTelefone.getText().toString());
            v.setFuncao(textInputFuncao.getText().toString());

            //chama o metodo de inserção no bando de dados da classe DataBaseHelper
            databaseHelper.createVoluntario(v);
            Toast.makeText(getActivity(), "Voluntario salvo", Toast.LENGTH_LONG).show();
            //substitui o fragment adcionarVoluntarios pelo listar na acivity principal do Voluntarios
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_voluntario, new ListFragment()).commit();
        }
    }
}