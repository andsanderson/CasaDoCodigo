package br.com.caelum.casadocodigo.eventbus;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;

public class LivroEvent {

    private final List<Livro> livros;

    public LivroEvent(List<Livro> livros) {
        this.livros =livros;
    }

    public List<Livro> getLivros(){
        return livros;
    }
}
