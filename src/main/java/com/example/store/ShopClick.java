package com.example.store;

import android.content.ClipData;
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
 * Use the {@link ShopClick} factory method to
 * create an instance of this fragment.
 */
public class ShopClick extends Fragment {


    // TODO: Rename and change types of parameters
    private static int mParam1;


    private static void setMParam1(int mParam1) {
        if (mParam1 == 1 || mParam1 == 2 || mParam1 == 3 || mParam1 == 4) {
            ShopClick.mParam1 = mParam1;
        }
    }

    public ShopClick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ShopClick.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopClick newInstance(int param1) {
        ShopClick fragment = new ShopClick();
        setMParam1(param1);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop_click, container, false);

        ConstraintLayout forAdmin = root.findViewById(R.id.forAdminInShopClick);
        FloatingActionButton addBtn = root.findViewById(R.id.floatingActionButtonSC);

        MainStoreAdapter adapter = new MainStoreAdapter(getActivity(),ShopClick.newInstance(0)); // passing a new instance of ShopClick to the adapter so we can use it in delete button


        if (mParam1 == 1 || mParam1 == 3) {
            adapter.setCategories(Management.getSystem().getItemCategoriesWomen());
        } else if (mParam1 == 2 || mParam1 == 4) {
            adapter.setCategories(Management.getSystem().getItemCategoriesMen());
        }


        if (Management.getSystem().getCurrentUser().getUsername().equalsIgnoreCase("admin")) {
            System.out.println("Admin entered.");
        }

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.shopClickRecView);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        if (Management.getSystem().getCurrentUser().getUsername().equalsIgnoreCase("admin")) {
            forAdmin.setVisibility(View.VISIBLE);

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = requireActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragmentContainerView, add_item_to_shopClick.class,null)
                            .setReorderingAllowed(true).addToBackStack(null).commit();

                }
            });
        }
        adapter.onItemClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ItemClicked.newInstance(i + 1);
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentContainerView, ItemClicked.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();

            }
        };

        return root;
    }
}
