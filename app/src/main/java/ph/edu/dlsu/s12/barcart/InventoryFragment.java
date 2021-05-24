package ph.edu.dlsu.s12.barcart;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryFragment extends Fragment {

    private ArrayList<Item> itemList;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        recyclerView = view.findViewById(R.id.inventoryRecycler);
        itemList= new ArrayList<>();
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

        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));

        /*
        ItemAdapter adapter = new ItemAdapter(itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        */


        setItemAdapter();

        return view;


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
