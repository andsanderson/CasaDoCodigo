package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivrosDelegate;
import br.com.caelum.casadocodigo.eventbus.LivroEvent;
import br.com.caelum.casadocodigo.fragment.DetalheLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;


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
        new WebClient().getLivros(0,10);
        EventBus.getDefault().register(this);
        Log.i("Man", "onCreate: ");

        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        remoteConfig.setDefaults(R.xml.remote_config_defaults);
        remoteConfig.fetch(15).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    remoteConfig.activateFetched();


                }
            }
        });


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.vai_para_carrinho){
            Intent vaiParaCarrinho = new Intent(this,CarrinhoActivity.class);
            startActivity(vaiParaCarrinho);
        }else if(item.getItemId()==R.id.logOut){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent vaiParaMain = new Intent(this,LoginActivity.class);
            startActivity(vaiParaMain);
            finish();
        }
        return true;
    }
}
