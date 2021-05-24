package ph.edu.dlsu.s12.barcart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private ArrayList<Item> itemList;

    public ItemAdapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nameTxt,  codeTxt, descTxt;

        public MyViewHolder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.itemName);
            codeTxt = view.findViewById(R.id.itemCode);
            descTxt = view.findViewById(R.id.itemDesc);
        }

    }


    @NonNull
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);

        return new ItemAdapter.MyViewHolder(itemView2);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String iName = itemList.get(position).getProductName();
        String iCode = itemList.get(position).getBarcode();
        String iDesc= itemList.get(position).getProductDesc();

        holder.nameTxt.setText(iName);
        holder.codeTxt.setText(iCode);
        holder.descTxt.setText(iDesc);


    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
