package ph.edu.dlsu.s12.barcart;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class InventoryFragment extends Fragment {

    private ArrayList<Item> itemList;

    private RecyclerView recyclerView;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        recyclerView = view.findViewById(R.id.inventoryRecycler);
        itemList= new ArrayList<>();
        /*
        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));
        */

        /*
        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));

        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));

        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));

        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));
        */
        /*
        ItemAdapter adapter = new ItemAdapter(itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        */


        setItemAdapter();

        getItems();

        return view;


    }

    private void getItems() {


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference itemCollectionRef = db.collection("items");
        
        Query itemsQuery = itemCollectionRef.whereEqualTo("userID",  FirebaseAuth.getInstance().getCurrentUser().getUid());

        itemsQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Item itemGet = document.toObject(Item.class);
                        itemList.add(itemGet);
                    }

                    setItemAdapter();

                } else {




                }

            }
        });

    }

    private void setItemAdapter() {
        final Activity activity = getActivity();
        ItemAdapter adapter = new ItemAdapter(itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }



}
