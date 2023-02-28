package com.example.primerproyecto;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Universidad> listaUniversidades;
    Context context;

    public Adapter(List<Universidad> listaUniversidades, Context context){
        this.listaUniversidades = listaUniversidades;
        this.context = context;
    }


    // El ViewHolder controla las vistas, osea cada one_line
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_presPic;
        TextView tv_presName, tv_date;
        ConstraintLayout parentLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            // Obtengo los valores
            iv_presPic = view.findViewById(R.id.iv_urlUniversidad);
            tv_presName = view.findViewById(R.id.tv_nombreUniversidad);
            tv_date = view.findViewById(R.id.tv_valoracionUniversidad);
            parentLayout = view.findViewById(R.id.oneLInePresidentLayout);
        }

    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.one_line, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        viewHolder.tv_presName.setText(""+ listaUniversidades.get(position).getNombre());
        viewHolder.tv_date.setText(""+ listaUniversidades.get(position).getValoracion());

        // Utilizo la libreria Glide para coger imagenes de internet en una sola linea
        Glide.with(context).load(listaUniversidades.get(position).getUrl()).into(viewHolder.iv_presPic);
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnadirOtro.class);

                intent.putExtra("id", listaUniversidades.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listaUniversidades.size();
    }
}


