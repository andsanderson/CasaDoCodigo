package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivosFragment extends Fragment {


    @BindView(R.id.lista_livors)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragmaent_lista_livros,container,false);

        List<Livro> livros = new ArrayList<>();
        for (int i=0;i<6;i++)
        {
            Autor autor = new Autor();
            autor.setNome("Autor"+1);
            Livro livro = new Livro("Livro"+1, "Descricao " +1, Arrays.asList(autor));
            livros.add(livro);
        }

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.lista_livors);
        recyclerView.setAdapter(new LivroAdapter(livros));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ButterKnife.bind(this, view);
        return view;
    }
}
