package com.example.ccunsa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccunsa.R;
import com.example.ccunsa.model.Pintura;

import java.util.List;

public class PinturaAdapter extends RecyclerView.Adapter<PinturaAdapter.PinturaViewHolder> {
    private List<Pintura> pinturas;
    private Context context;

    public PinturaAdapter(Context context, List<Pintura> pinturas) {
        this.context = context;
        this.pinturas = pinturas;
    }

    @NonNull
    @Override
    public PinturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("PinturaAdapter", "onCreateViewHolder called");
        View view = LayoutInflater.from(context).inflate(R.layout.item_pintura, parent, false);
        return new PinturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PinturaViewHolder holder, int position) {
        Pintura pintura = pinturas.get(position);
        Log.d("PinturaAdapter", "onBindViewHolder called for position: " + position + ", pintura: " + pintura.getPaintingName());
        holder.pinturaName.setText(pintura.getPaintingName());
        holder.pinturaAuthor.setText(pintura.getAuthorName());
        holder.pinturaDescription.setText(pintura.getDescription());

        // Cargar la imagen del icono con la extensión .jpg
        String iconPath = pintura.getIconPath() + ".jpg"; // Agregar la extensión .jpg
        int resId = context.getResources().getIdentifier(iconPath, "drawable", context.getPackageName());
        Log.d("PinturaAdapter", "IconPath: " + iconPath + ", ResId: " + resId);
        if (resId != 0) {
            holder.pinturaIcon.setImageResource(resId);
        } else {
            // Manejar el caso en que el recurso no se encuentra
            //holder.pinturaIcon.setImageResource(R.drawable.placeholder_image); // Asegúrate de tener un recurso placeholder
        }
    }

    @Override
    public int getItemCount() {
        Log.d("PinturaAdapter", "getItemCount called, size: " + pinturas.size());
        return pinturas.size();
    }

    public void updatePinturas(List<Pintura> nuevasPinturas) {
        this.pinturas = nuevasPinturas;
        notifyDataSetChanged();
        Log.d("PinturaAdapter", "updatePinturas called, new size: " + pinturas.size());
    }

    public static class PinturaViewHolder extends RecyclerView.ViewHolder {
        TextView pinturaName;
        TextView pinturaAuthor;
        TextView pinturaDescription;
        ImageView pinturaIcon;

        public PinturaViewHolder(@NonNull View itemView) {
            super(itemView);
            pinturaName = itemView.findViewById(R.id.pintura_name);
            pinturaAuthor = itemView.findViewById(R.id.pintura_author);
            pinturaDescription = itemView.findViewById(R.id.pintura_description);
            pinturaIcon = itemView.findViewById(R.id.pintura_icon);
        }
    }
}
