package ph.edu.dlsu.s12.barcart;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Cart> cartList;

    public recyclerAdapter(ArrayList<Cart> cartList){
        this.cartList = cartList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView cartName;

        public MyViewHolder(final View view){
            super(view);
            cartName = view.findViewById(R.id.cartName);
        }

    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);

        return new recyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String cText = cartList.get(position).getCartName();
        holder.cartName.setText(cText);
        Gson gson = new Gson();

        String jsonProducts = gson.toJson(cartList.get(position).getItems());
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PurchaseActivity.class);
                i.putExtra("store_name", orderList.get(position).getStoreName());
                i.putExtra("date_name", orderList.get(position).getDate());
                i.putExtra("order_name", orderList.get(position).getOrderID());
                i.putExtra("cour_name", orderList.get(position).getCourID());
                i.putExtra("product_list", jsonProducts);


                view.getContext().startActivity(i);
            }
        });

         */

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("test", "Logcat->Verbose tagname(test)");
                Intent i = new Intent(view.getContext(), CartItemActivity.class);
                Log.i("test", "Logcat->Verbose tagname(test)");
                i.putExtra("cart_name", cartList.get(position).getCartName());
                i.putExtra("cart_desc", cartList.get(position).getCartDesc());
                i.putExtra("item_list", jsonProducts);
                Log.i("test", "Logcat->Verbose tagname(test)");
                view.getContext().startActivity(i);
                Log.i("test", "Logcat->Verbose tagname(test)");
            }
        });



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
