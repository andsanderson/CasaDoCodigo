package br.com.caelum.casadocodigo.Dagger;

import android.app.Application;

public class CasaDoCodigoApplication extends Application {
    private CasaDoCodigoComponent component;

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerCasaDoCodigoComponent.builder().build();

    }

    public CasaDoCodigoComponent getComponent(){
        return component;
    }
}
