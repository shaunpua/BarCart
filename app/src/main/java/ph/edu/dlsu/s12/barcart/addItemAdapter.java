package ph.edu.dlsu.s12.barcart;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class addItemAdapter  extends RecyclerView.Adapter<addItemAdapter.MyViewHolder>{


    private ArrayList<Item> itemList;
    boolean isSelectedMode = false;

    ArrayList<Item> selectedItems = new ArrayList<>();


    public addItemAdapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView  nameTxt,  codeTxt, descTxt;
        private ImageView selectCheck;

        public MyViewHolder(final View view){
            super(view);


            selectCheck = view.findViewById(R.id.CartitemCheck);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isSelectedMode = true;
                    if (selectedItems.contains(itemList.get(getAdapterPosition()))){

                        selectCheck.setVisibility(View.INVISIBLE);

                        selectedItems.remove(itemList.get(getAdapterPosition()));
                    } else {

                        selectCheck.setVisibility(View.VISIBLE);
                        selectedItems.add(itemList.get(getAdapterPosition()));
                    }


                    if (selectedItems.size() == 0) {
                        isSelectedMode = false;
                    }
                    return true;
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelectedMode){
                        if (selectedItems.contains(itemList.get(getAdapterPosition()))){

                            selectCheck.setVisibility(View.INVISIBLE);
                            selectedItems.remove(itemList.get(getAdapterPosition()));
                        } else {

                            selectCheck.setVisibility(View.VISIBLE);
                            selectedItems.add(itemList.get(getAdapterPosition()));
                        }

                        if (selectedItems.size() == 0) {
                            isSelectedMode = false;
                        }
                    } else {

                    }

                }
            });
            nameTxt = view.findViewById(R.id.CartitemName);
            codeTxt = view.findViewById(R.id.CartitemCode);
            descTxt = view.findViewById(R.id.CartitemDesc);

        }



    }

    @NonNull
    @Override
    public addItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new addItemAdapter.MyViewHolder(itemView3);

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

    public ArrayList<Item> getSelectedItems(){
        return selectedItems;
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
