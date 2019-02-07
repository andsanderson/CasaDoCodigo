package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheLivroFragment extends Fragment {

    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;

    @BindView(R.id.detalhes_livro_autores)
    TextView autores;


    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;

    @BindView(R.id.detalhes_livro_num_paginas)
    TextView paginas;

    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;

    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    private Livro livro;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro,container,false);
        ButterKnife.bind(this,view);
        Bundle arguments = getArguments();
        livro = (Livro) arguments.getSerializable("livro");
        populaCamposCom(livro);
        return view;
    }

    private void populaCamposCom(Livro livro){

         nome.setText(livro.getNome());
         descricao.setText(livro.getDescricao());
         isbn.setText(livro.getISBN());
         paginas.setText( String.valueOf(livro.getNumPaginas()));
         dataPublicacao.setText(livro.getDataPublicacao());
         
         String autoresstr = "";

        for (Autor autor:livro.getAutores()) {
            autoresstr +=  "; " + autor.getNome();
        }
        autores.setText(autoresstr);

        Picasso.with(getContext()).load(livro.getUrlFoto()).placeholder(R.drawable.livro).into(foto);


    }
}
