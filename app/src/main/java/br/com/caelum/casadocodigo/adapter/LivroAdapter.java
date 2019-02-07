package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivrosDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroAdapter extends RecyclerView.Adapter {

    private List<Livro> livros;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        int tipoDeLayout = R.layout.item_livro_par;
        if(viewType%2 != 0 ) {
            tipoDeLayout = R.layout.item_livro_impar;
        }
        View view =LayoutInflater.from(parent.getContext()).inflate(tipoDeLayout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position%2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        ViewHolder viewHolder = (ViewHolder) holder;
        Livro livro = livros.get(position);
        viewHolder.nome.setText(livro.getNome());

        Picasso.with(viewHolder.foto.getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.foto);
    }

    public LivroAdapter (List<Livro> livros)
    {
        this.livros =livros;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_livro_nome)
        TextView nome;
        @BindView(R.id.item_livro_foto)
        ImageView foto;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.item_livro)
        public void clickItem(){
            Livro livro = livros.get(getAdapterPosition());
            LivrosDelegate delegate = (LivrosDelegate) itemView.getContext();
            delegate.lidaComLivoSelecionado(livro);
        }

    }


}
