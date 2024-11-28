package com.marcelobarbacovi.how6gerenciarong.parceiros;

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


public class ParceirosFragmentAdicionar extends Fragment {
    // variaveis dos campos do fragment ParceirosAdicionar
    TextInputEditText textAdcionarNomeParceiro, textAdicionarobservacaoParceiro;
    EditText editTextAdcionarTelefoneParceiro, editTextAdiconarEmailParceiro;


    public ParceirosFragmentAdicionar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parceiros_fragment_adicionar, container, false);

        // identifica os campos do fragmet Parceirosadcionar e grava dentro das variáveis
        textAdcionarNomeParceiro = v.findViewById(R.id.textAdicionar_nome_Parceiro);
        editTextAdcionarTelefoneParceiro = v.findViewById(R.id.editTextAdcionar_Phone_Parceiro);
        editTextAdiconarEmailParceiro = v.findViewById(R.id.editTextAdcionar_Email_Parceiro);
        textAdicionarobservacaoParceiro = v.findViewById(R.id.TextAdcionar_Observacao_parceiro_Parceiro);


        // configura o button adiconar parceiros com metodo adiconar
        Button btnAdcionarParceiro = v.findViewById(R.id.button_salvar_parceiro);
        btnAdcionarParceiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();


            }
        });


        return  v;
    }
    // metodo para validar se os campos do adcionar parceiros estão vazios
    private void adicionar(){
        if (textAdcionarNomeParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do Parceiro", Toast.LENGTH_LONG).show();
        } else if (editTextAdcionarTelefoneParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o telefone do parceiro", Toast.LENGTH_LONG).show();
        } else if (editTextAdiconarEmailParceiro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número email do parceiro", Toast.LENGTH_LONG).show();

            // configura dos dados para dentro dos atributos da classe parceiro
        } else {
            DataBaseHelper databaseHelper = new DataBaseHelper(
                    getActivity());
            Parceiros p = new Parceiros();
            p.setNome(textAdcionarNomeParceiro.getText().toString());
            p.setTelefone(editTextAdcionarTelefoneParceiro .getText().toString());
            p.setEmail(editTextAdiconarEmailParceiro.getText().toString());
            p.setObservacao(textAdicionarobservacaoParceiro.getText().toString());

            // chamaa classe do banco de dados e insere os dados na tabela parceiros
            databaseHelper.createParceiro(p);
            Toast.makeText(getActivity(), "Voluntario salvo", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_parceiro, new ParceirosFragmentListar()).commit();
        }
    }
}