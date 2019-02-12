package br.com.caelum.casadocodigo.Dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.fragment.DetalheLivroFragment;
import dagger.Component;

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {
    void inject(DetalheLivroFragment fragment);
    void inject(CarrinhoActivity activity);
}
