package com.marcelobarbacovi.how6gerenciarong.parceiros;

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


public class ParceirosFragmentEditar extends Fragment {

   // cria as variaveis dos campos do fragment Parceiros Editar
    TextInputEditText editarNomeParceiro, editarObservacaoParceiro;
    EditText editarTelefoneParceiro, editarEmailParceiro;
    private DataBaseHelper databaseHelper;
    private Parceiros p;

    public ParceirosFragmentEditar() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parceiros_fragment_editar, container, false);

        //identifica os campos do fragment dentro das variaves por meio do ID do campo
        editarNomeParceiro = v.findViewById(R.id.textEditar_Nome_Parceiro);
        editarTelefoneParceiro = v.findViewById(R.id.editTextEditar_Email_Parceiro);
        editarEmailParceiro = v.findViewById(R.id.editTextEditar_Email_Parceiro);
        editarObservacaoParceiro = v.findViewById(R.id.TextEditar_Observacao_parceiro_Parceiro);

        Bundle b = getArguments();
        int id_parceiro = b.getInt("id");
        databaseHelper = new DataBaseHelper(getActivity());
        Parceiros parceiro = databaseHelper.getByIdParceiro(id_parceiro);

        // recuoera os valores que estão dentro dos atributose joga nos campos do fragmet Editar parceiros
        editarNomeParceiro.setText(parceiro.getNome());
        editarTelefoneParceiro.setText(parceiro.getTelefone());
        editarEmailParceiro.setText(parceiro.getEmail());
        editarObservacaoParceiro.setText(parceiro.getObservacao());


        //  configura o button editar parceiros
        Button btnEditar = v.findViewById(R.id.button_editar_aluno);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_parceiro);
            }
        });

        // configura o button excluir parceiro
        Button btnExcluir = v.findViewById(R.id.button_excluir_aluno);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
           //metodo de confirmar exclusão pormeio do alterdialog
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("excluir Parceiro");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_parceiro);
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
    // valida os campos do Parceirosfragmenteditar para saber se não estão vazios
    private void editar(int id) {
        if (editarNomeParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do médico", Toast.LENGTH_LONG).show();
        } else if (editarTelefoneParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade do médico", Toast.LENGTH_LONG).show();
        } else if (editarEmailParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular do médico", Toast.LENGTH_LONG).show();
        } else if (editarObservacaoParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe as funções pertinentes ao voluntario ", Toast.LENGTH_LONG).show();
        } else {

            // instnacia objeto parceiros e grava os novos dados no atributo da classe parceiros
            p = new Parceiros();
            p.setId(id);
            p.setNome(editarNomeParceiro.getText().toString());
            p.setTelefone(editarTelefoneParceiro.getText().toString());
            p.setEmail(editarEmailParceiro.getText().toString());
            p.setObservacao(editarObservacaoParceiro.getText().toString());

            //chama o metodo update da classe DatabaseHelper para alterar os dados da tabela parceiros
            databaseHelper.updateParceiro(p);
            Toast.makeText(getActivity(), "PArceiro atualizado", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_parceiro, new ParceirosFragmentListar()).commit();
        }
    }
//metodo para excluir os dados da classe parceiros
    private void excluir (int id) {
        p = new Parceiros();
        p.setId(id);
        databaseHelper.deleteParceiro(p);
        Toast.makeText(getActivity(), "Parceiro excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_parceiro, new ParceirosFragmentListar()).commit();
    }
}