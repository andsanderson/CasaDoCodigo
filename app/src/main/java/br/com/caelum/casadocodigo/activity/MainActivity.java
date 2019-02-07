package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivrosDelegate;
import br.com.caelum.casadocodigo.eventbus.LivroEvent;
import br.com.caelum.casadocodigo.fragment.DetalheLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LivrosDelegate {

    private ListaLivosFragment listaLivrosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        listaLivrosFragment = new ListaLivosFragment();
        transaction.replace(R.id.frame_principal,listaLivrosFragment);
        transaction.commit();
        new WebClient().getLivros();
        EventBus.getDefault().register(this);
        Log.i("Man", "onCreate: ");

    }

    @Override
    public void lidaComLivoSelecionado(Livro livro) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalheLivroFragment detalheLivroFragment = criaDetalhesCom(livro);
        transaction.replace(R.id.frame_principal,detalheLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        //Toast.makeText(this,"Livro selecionado: " + livro.getNome(), Toast.LENGTH_LONG).show();
    }



    private DetalheLivroFragment criaDetalhesCom(Livro livro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("livro", livro);
        DetalheLivroFragment detalhesLivroFragment = new DetalheLivroFragment();
        detalhesLivroFragment.setArguments(bundle);
        return detalhesLivroFragment;
    }

    @Subscribe
    public void lidaComSucesso(LivroEvent livroEvent){
        listaLivrosFragment.populaListaCom(livroEvent.getLivros());
    }

    @Subscribe
    public void lidaComErro(Throwable erro)
    {
        Toast.makeText(this,"Não foi possível carregar os livros...",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
