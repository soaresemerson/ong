package com.marcelobarbacovi.how6gerenciarong.parceiros;

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


public class ParceirosFragmentListar extends Fragment {



    public ParceirosFragmentListar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parceiros_fragment_listar, container, false);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_View_listar_parceiros);
        dataBaseHelper.getAllParceiro(getActivity(),lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListarParceiro);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                ParceirosFragmentEditar editar = new ParceirosFragmentEditar();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.frame_parceiro, editar).commit();
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
}