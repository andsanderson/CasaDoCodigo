package br.com.caelum.casadocodigo.Dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import dagger.Module;
import dagger.Provides;

@Module
public class CasaDoCodigoModule {
    @Provides
    @Singleton
    public Carrinho gerCarrinho(){
        return new Carrinho();
    }
}
