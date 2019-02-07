package br.com.caelum.casadocodigo.server;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LivrosServices {

    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<List<Livro>> listaLivros();
}
