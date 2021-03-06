package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.listener.EndlessListListener;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();


    @BindView(R.id.lista_livors)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragmaent_lista_livros,container,false);

        ButterKnife.bind(this,view);
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        boolean listType = firebaseRemoteConfig.getBoolean("list_type_single_item");

        recyclerView.setAdapter(new LivroAdapter(livros, listType));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            public void carregaMaisItens() {
                new WebClient().getLivros(livros.size(),10);
            }
        });

        return view;
    }

    public void populaListaCom(List<Livro> livroslista){
        this.livros.addAll(livroslista);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.addOnScrollListener(new EndlessListListener(){
            @Override
            public void carregaMaisItens() {
                new WebClient().getLivros(livros.size(),10);
            }
        });
    }
}
