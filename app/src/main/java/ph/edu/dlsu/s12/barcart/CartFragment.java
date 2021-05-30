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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

        setCartAdapter();
        setCartInfo();



        return view;
    }
    private void setCartAdapter() {
        final Activity activity = getActivity();
        recyclerAdapter adapter = new recyclerAdapter(cartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setCartInfo() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference cartCollectionRef = db.collection("carts");

        Query cartQuery = cartCollectionRef.whereEqualTo("userIDC",  FirebaseAuth.getInstance().getCurrentUser().getUid());

        cartQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Cart cartGet = document.toObject(Cart.class);
                        cartList.add(cartGet);
                    }

                    setCartAdapter();

                } else {

                    ArrayList<Item> product2 = new ArrayList<>();



                    product2.add(new Item(
                            "Iphone 12",
                            "14444124124",
                            "This is a scam by steve jobs",
                            "123456"
                    ));


                    cartList.add(new Cart(
                            "Failed to get data from firebase", "Cart for groceries" , "user1",product2));




                }

            }
        });


    }


}
