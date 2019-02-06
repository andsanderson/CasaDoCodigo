package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivrosDelegate;
import br.com.caelum.casadocodigo.fragment.DetalheLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LivrosDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_principal,new ListaLivosFragment());
        transaction.commit();
    }

    @Override
    public void lidaComLivoSelecunado(Livro livro) {
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


}
