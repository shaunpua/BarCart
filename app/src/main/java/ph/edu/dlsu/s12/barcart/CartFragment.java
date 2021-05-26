package ph.edu.dlsu.s12.barcart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private ArrayList<Cart> cartList;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cartRecycler);
        cartList= new ArrayList<>();
        setCartInfo();

        setAdadpter();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                cartList.remove(viewHolder.getAdapterPosition());

                setAdadpter();



            }
        }).attachToRecyclerView(recyclerView);
        /*
        final Activity activity = getActivity();

        RecyclerView recycler_view = (RecyclerView) root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recycler_view.setLayoutManager(layoutManager);
        */
        /*
        //https://stackoverflow.com/questions/26621060/display-a-recyclerview-in-fragment
         new Thread(new Runnable() {
        @Override
        public void run() {
            final RecyclerAdapter adapter = new RecyclerAdapter(c);
            c.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }).start();
         */
        /*
        Log.i("test", "Logcat->Verbose tagname(test)");

        this.data = load_data();
        Log.i("test", "Logcat->Verbose tagname(test)");

        MainAdapter mainAdapter = new MainAdapter(data);
        recycler_view.setAdapter(mainAdapter);
        recycler_view.addItemDecoration(new DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //When item swipe
                //Remove from arrayList
                data.remove(viewHolder.getAdapterPosition());
                //notify adapter
                mainAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recycler_view);

         */
        return view;
    }
    private void setAdadpter() {
        final Activity activity = getActivity();
        recyclerAdapter adapter = new recyclerAdapter(cartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setCartInfo() {

        ArrayList<Item> product1 = new ArrayList<>();



        product1.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));


        cartList.add(new Cart(
                "Robinsons Grocery", "Cart for groceries" , "user1",product1));

    }

}
