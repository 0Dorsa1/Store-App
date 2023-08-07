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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_item_to_shopClick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_item_to_shopClick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static int mParam1;

    public add_item_to_shopClick() {
        // Required empty public constructor
    }

    private static void setmParam1(int mParam) {
        mParam1 = mParam;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment add_item_to_shopClick.
     */
    // TODO: Rename and change types and number of parameters


    public static add_item_to_shopClick newInstance(int param1) {
        add_item_to_shopClick fragment = new add_item_to_shopClick();
        Bundle args = new Bundle();
        setmParam1(param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_item_to_shop_click, container, false);
        Button save = root.findViewById(R.id.saveNewCategoryBtn);
        EditText categoryName = root.findViewById(R.id.categoryNameEdtTxt);
        EditText imageURL = root.findViewById(R.id.ImageURLEdtTxt);
        TextView categoryNameWarning = root.findViewById(R.id.categoryNameWarningTxt);
        TextView imageURLWarning = root.findViewById(R.id.imageURLWarningTxt);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("save button clicked");
                MainStoreAdapter adapter=new MainStoreAdapter(getActivity());
                if (mParam1==1 || mParam1==3){
                    if (Management.getSystem().addFemaleCategory(new Category(imageURL.getText().toString(), categoryName.getText().toString()))) {
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
                            fm.beginTransaction().replace(R.id.fragmentContainerView, ShopClick.class, null)
                                    .setReorderingAllowed(true).addToBackStack(null).commit();
                        }
                    }
                }
                else if (mParam1==2 || mParam1==4){
                    if (Management.getSystem().addMaleCategory(new Category(imageURL.getText().toString(), categoryName.getText().toString()))) {
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
                            LayoutInflater toastInflater = getLayoutInflater();
                            View toastLayout = toastInflater.inflate(R.layout.toast_layout,
                                    (ViewGroup) view.findViewById(R.id.custom_toast_container));

                            TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                            text.setText(categoryName.getText().toString() + " added.");

                            Toast toast = new Toast(getActivity());
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.setView(toastLayout);
                            toast.show();
                            FragmentManager fm = requireActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.fragmentContainerView, MainStore.class, null)
                                    .setReorderingAllowed(true).addToBackStack(null).commit();
                        }
                    }
                }
            }
        });
        return root;
    }
}