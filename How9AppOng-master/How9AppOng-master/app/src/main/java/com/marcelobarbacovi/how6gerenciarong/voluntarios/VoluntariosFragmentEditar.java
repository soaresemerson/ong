package com.marcelobarbacovi.how6gerenciarong.voluntarios;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.database.DataBaseHelper;

public class VoluntariosFragmentEditar extends Fragment {

    private TextInputEditText textInputNome, textInputEspecializacao, textInputFuncao;
    private EditText editTextTelefone;
    private DataBaseHelper databaseHelper;
    private Voluntario v;
    public VoluntariosFragmentEditar() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.voluntarios_fragment_editar, container, false);

        // configura os campos do EditarVoluntarios dentro das variveis por meio do seu ID
        textInputNome = v.findViewById(R.id.textEditar_Nome_Voltuntario);
        textInputEspecializacao = v.findViewById(R.id.textEditar_Especializacao_Voltuntario);
        editTextTelefone = v.findViewById(R.id.textEditar_Telefone_Voltuntario);
        textInputFuncao = v.findViewById(R.id.textEditar_Funcao_Voltuntario);

        Bundle b = getArguments();
        int id_voluntario = b.getInt("id");
        databaseHelper = new DataBaseHelper(getActivity());
        Voluntario volunario = databaseHelper.getByIdVoluntario(id_voluntario);

        // recupera para dentro dos campos do editarVoluntarios o que esta dentro dos atributo da classe voluntarios
        textInputNome.setText(volunario.getName());
        textInputEspecializacao.setText(volunario.getEspecializacao());
        editTextTelefone.setText(volunario.getTelefone());
        textInputFuncao.setText(volunario.getFuncao());

        // configura o button editar voluntarios
        Button btnEditar = v.findViewById(R.id.button_editar_voluntario);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_voluntario);
            }
        });

        //configura o button excluir voluntario
        Button btnExcluir = v.findViewById(R.id.button_excluir_voluntario);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("excluir Voluntário");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_voluntario);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }
// valida os campos vazios
    private void editar(int id) {
        if (textInputNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do médico", Toast.LENGTH_LONG).show();
        } else if (textInputEspecializacao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade do médico", Toast.LENGTH_LONG).show();
        } else if (editTextTelefone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular do médico", Toast.LENGTH_LONG).show();
        } else if (textInputFuncao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe as funções pertinentes ao voluntario ", Toast.LENGTH_LONG).show();
        } else {

            // se não vazios grave os dados dentro das variaveis da classe Voluntarios
            v = new Voluntario();
            v.setId(id);
            v.setName(textInputNome.getText().toString());
            v.setEspecializacao(textInputEspecializacao.getText().toString());
            v.setTelefone(editTextTelefone.getText().toString());
            v.setFuncao(textInputFuncao.getText().toString());

            // chama o metodo update da classe dataBaseHelper para Voluntarios
            databaseHelper.updateVoluntario(v);
            Toast.makeText(getActivity(), "Voluntario atualizado", Toast.LENGTH_LONG).show();

            //substitui o fragment editar pelo listar Voluntario
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_voluntario, new VoluntariosFragmentListar()).commit();
        }
    }

    private void excluir (int id) {
        v = new Voluntario();
        v.setId(id);
        databaseHelper.deleteVoluntario(v);
        Toast.makeText(getActivity(), "Voluntario excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_voluntario, new VoluntariosFragmentListar()).commit();
    }
}