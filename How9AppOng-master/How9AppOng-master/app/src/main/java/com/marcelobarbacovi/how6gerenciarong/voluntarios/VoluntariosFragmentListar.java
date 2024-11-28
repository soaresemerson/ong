package com.marcelobarbacovi.how6gerenciarong.voluntarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.database.DataBaseHelper;


public class VoluntariosFragmentListar extends Fragment {



    public VoluntariosFragmentListar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.voluntarios_fragment_listar, container, false);
        // configura o listView
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_View_listar_voluntario);
        dataBaseHelper.getAllVoluntario(getActivity(),lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListarVoluntario);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                VoluntariosFragmentEditar editar = new VoluntariosFragmentEditar();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.frame_voluntario, editar).commit();
            }
        });



        return v;
    }
}