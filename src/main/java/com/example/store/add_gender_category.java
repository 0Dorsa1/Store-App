package com.example.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_gender_category#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_gender_category extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_gender_category() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_gender_category.
     */
    // TODO: Rename and change types and number of parameters
    public static add_gender_category newInstance(String param1, String param2) {
        add_gender_category fragment = new add_gender_category();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_gender_category, container, false);
        Button save = root.findViewById(R.id.saveNewCategoryBtn);
        EditText categoryName = root.findViewById(R.id.categoryNameEdtTxt);
        EditText imageURL = root.findViewById(R.id.ImageURLEdtTxt);
        TextView categoryNameWarning = root.findViewById(R.id.categoryNameWarningTxt);
        TextView imageURLWarning = root.findViewById(R.id.imageURLWarningTxt);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Management.getSystem().addCategory(new Category(imageURL.getText().toString(), categoryName.getText().toString()))) {
                    Log.e("GENDER CATEGORY",String.valueOf(Management.getSystem().getAllCategories().size()));
                    categoryNameWarning.setVisibility(View.GONE);
                    imageURLWarning.setVisibility(View.GONE);
                    if (categoryName.getText().toString().equalsIgnoreCase("")) {
                        categoryNameWarning.setVisibility(View.VISIBLE);
                    } else if (imageURL.getText().toString().equalsIgnoreCase("")) {
                        imageURLWarning.setVisibility(View.VISIBLE);
                    } else if (!imageURL.getText().toString().matches(".*?(gif|jpeg|png|jpg|bmp)")) {
                        imageURLWarning.setText("Please enter a valid image url.");
                        imageURLWarning.setVisibility(View.VISIBLE);
                    } else {
                        categoryNameWarning.setVisibility(View.GONE);
                        imageURLWarning.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), categoryName.getText().toString() + " added.", Toast.LENGTH_SHORT).show();
                        FragmentManager fm = requireActivity().getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.fragmentContainerView, MainStore.class, null)
                                .setReorderingAllowed(true).addToBackStack(null).commit();
                    }
                }
            }
        });
        return root;
    }
}