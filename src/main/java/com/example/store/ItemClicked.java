package com.example.store;

import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemClicked#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemClicked extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static int mParam1;

    public ItemClicked() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ItemClicked.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemClicked newInstance(int param1) {
        ItemClicked fragment = new ItemClicked();
        setMParam1(param1);
        return fragment;
    }

    private static void setMParam1(int mParam1) {
        if (mParam1 == 1 || mParam1 == 2 || mParam1 == 3 || mParam1 == 4 || mParam1==5 || mParam1==6|| mParam1==7) {
            ItemClicked.mParam1 = mParam1;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ImageView itemImage;
        TextView name,size,description;

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_item_clicked, container, false);

        itemImage=root.findViewById(R.id.itemImage);
        name=root.findViewById(R.id.itemNameTxt);
        description=root.findViewById(R.id.descriptionTxt);
        size=root.findViewById(R.id.sizeTxt);

            Picasso.get().load(Management.getSystem().getItemCategoriesWomen().get(mParam1-1).getImageURL())
                    .into(itemImage);
            name.setText(Management.getSystem().getItemCategoriesWomen().get(mParam1-1).getCategoryName());





        return root;
    }
}