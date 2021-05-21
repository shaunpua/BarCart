package ph.edu.dlsu.s12.barcart;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MainAdapter extends RecyclerView.Adapter<ViewHolder> {
    /*
    public static final String PRODUCT_NAME_KEY = "PRODUCT_NAME_KEY";

    public static final String BARCODE_KEY = "BARCODE_KEY";
     public static final String PRODUCT_DESC_KEY = "PRODUCT_DESC_KEY";
*/
    private ArrayList<Item> data;

    private int view_holder_count = 0;

    public MainAdapter(ArrayList<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);

        ViewHolder myViewHolder = new ViewHolder(view);

        view_holder_count++;
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setProductName(data.get(position).getProductName());

        holder.setBarcode(data.get(position).getBarcode());
        holder.setProductDesc(data.get(position).getProductDesc());
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), OrderActivity.class);
                i.putExtra(VENDOR_KEY, data.get(position).getVendor());
                i.putExtra(DATE_KEY, data.get(position).getDate());
                i.putExtra(ORDER_NUM_KEY, data.get(position).getOrderNum());
                i.putExtra(COURIER_KEY, data.get(position).getCour());
                i.putExtra("order_id", data.get(position).getitempos());

                view.getContext().startActivity(i);
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        //return data.size();
        return view_holder_count;
    }

}