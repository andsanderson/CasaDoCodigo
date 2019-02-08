package br.com.caelum.casadocodigo.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {


    int quantidadeTotalItens ;
    int primeitoItemVisivel ;
    int quantidadeItensVisiveis;
    boolean carregando = true;
    int totalAnterior =0;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int qtdScrollHorizontal, int qtdScrollVertical) {
        super.onScrolled(recyclerView, qtdScrollHorizontal, qtdScrollVertical);

        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        quantidadeTotalItens = layoutManager.getItemCount();
        primeitoItemVisivel = layoutManager.findFirstCompletelyVisibleItemPosition();
        quantidadeItensVisiveis = layoutManager.getChildCount();

        int indiceLimiteParaCarregar = quantidadeTotalItens -quantidadeItensVisiveis -5;
        if (carregando){
            if(quantidadeTotalItens>totalAnterior){
                totalAnterior = quantidadeTotalItens;
                carregando=false;
            }
        }


        if(!carregando && primeitoItemVisivel>= indiceLimiteParaCarregar){
            carregaMaisItens();
            carregando = true;
        }

    }


    public abstract void carregaMaisItens();



}
