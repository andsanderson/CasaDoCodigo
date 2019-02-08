package br.com.caelum.casadocodigo.server;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LivrosServices {

    @GET("listarLivros")
    Call<List<Livro>> listaLivros(@Query("indicePrimeiroLivro") int indicePrimeiroLivro, @Query("qtdLivros") int qtdLivros);
}
