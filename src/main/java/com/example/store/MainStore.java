package com.example.store;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainStore extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainStore() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainStore.
     */
    // TODO: Rename and change types and number of parameters
    public static MainStore newInstance(String param1, String param2) {
        MainStore fragment = new MainStore();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_store, container, false);

        ConstraintLayout forAdmin = root.findViewById(R.id.forAdmin);
        FloatingActionButton addBtn = root.findViewById(R.id.floatingActionButton);

        /**
         * formal icons
         *  "https://toppng.com/uploads/preview/dress-girl-women-fashion-garment-comments-little-black-dress-11563268182cyt4xai8ei.png"
         *  "https://www.kindpng.com/picc/m/3-34850_transparent-background-suit-icon-hd-png-download.png"
         *  "https://cdn2.vectorstock.com/i/1000x1000/37/26/girl-dress-line-icon-concept-sign-outline-vector-29703726.jpg"
         *  "https://cdn0.iconfinder.com/data/icons/types-of-clothes-and-accessories-for-children-in-l/32/tshirt-girl-512.png"
         */
        MainStoreAdapter adapter = new MainStoreAdapter( getActivity(),MainStore.newInstance("",""));   //passing main store fragment instance to adapter
        adapter.setCategories(Management.getSystem().getAllCategories());

        adapter.onItemClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ShopClick.newInstance(i + 1);

            }
        };

        RecyclerView recyclerView = root.findViewById(R.id.mainStoreRecView);

        recyclerView.setAdapter(adapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter.setCategories(Management.getSystem().getAllCategories());

        if (Management.getSystem().getCurrentUser().getUsername().equalsIgnoreCase("admin")) {
            forAdmin.setVisibility(View.VISIBLE);

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = requireActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragmentContainerView, add_gender_category.class, null)
                            .setReorderingAllowed(true).addToBackStack(null).commit();
                }
            });
        }


        return root;
    }
}
