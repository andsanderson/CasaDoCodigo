package br.com.caelum.casadocodigo.server;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;

import br.com.caelum.casadocodigo.eventbus.LivroEvent;
import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient  {



    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";





    public void getLivros(int indicePrimeiroLivro, int qtdLivros ){

        Retrofit client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();
        LivrosServices services = client.create(LivrosServices.class);
        Call<List<Livro>> call = services.listaLivros(indicePrimeiroLivro,qtdLivros);
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                EventBus.getDefault().post(new LivroEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        });
    }
}
