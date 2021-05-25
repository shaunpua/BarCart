package ph.edu.dlsu.s12.barcart;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cartAdapter  extends RecyclerView.Adapter<cartAdapter.MyViewHolder>{


    private ArrayList<Item> itemList;

    public cartAdapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView  nameTxt,  codeTxt, descTxt;

        public MyViewHolder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.CartitemName);
            codeTxt = view.findViewById(R.id.CartitemCode);
            descTxt = view.findViewById(R.id.CartitemDesc);
        }

    }


    @NonNull
    @Override
    public cartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new cartAdapter.MyViewHolder(itemView2);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name = itemList.get(position).getProductName();
        String code = itemList.get(position).getBarcode();
        String desc= itemList.get(position).getProductDesc();

        holder.nameTxt.setText(name);
        holder.codeTxt.setText(code);
        holder.descTxt.setText(desc);


    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
