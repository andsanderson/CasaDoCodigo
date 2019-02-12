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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.Dagger.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Inject
    Carrinho carrinho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro,container,false);
        ButterKnife.bind(this,view);
        Bundle arguments = getArguments();
        livro = (Livro) arguments.getSerializable("livro");
        populaCamposCom(livro);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        app.getComponent().inject(this);
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

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void comprarFisico(){
        Toast.makeText(getActivity(),"Livro adicionado ao carrinho!",Toast.LENGTH_SHORT).show();
        carrinho.adiciona(new Item(livro,TipoDeCompra.FISICO));
    }


    @OnClick(R.id.detalhes_livro_comprar_ebook)
    public void comprarEbook(){
        Toast.makeText(getActivity(),"Livro adicionado ao carrinho!",Toast.LENGTH_SHORT).show();
        carrinho.adiciona(new Item(livro,TipoDeCompra.VIRTUAL));
    }

    @OnClick(R.id.detalhes_livro_comprar_ambos)
    public void comprarAmbos(){
        Toast.makeText(getActivity(),"Livro adicionado ao carrinho!",Toast.LENGTH_SHORT).show();
        carrinho.adiciona(new Item(livro,TipoDeCompra.JUNTOS));
    }
}
