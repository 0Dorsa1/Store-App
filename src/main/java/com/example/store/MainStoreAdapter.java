package com.example.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class MainStoreAdapter extends RecyclerView.Adapter<MainStoreAdapter.ViewHolder> {

    private ArrayList<Category> categories = new ArrayList<>();
    private Context context;
    private Fragment fragment;
    DialogInterface.OnClickListener onItemClickListener;


    public MainStoreAdapter( Context context) {
        this.context = context;
    }

    public MainStoreAdapter( Context context,Fragment fragment) { //create second constructor which takes a fragment as second parameter to be used in the delete button
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gender_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryName.setText(categories.get(position).getCategoryName());
        Picasso.get().load(categories.get(position).getImageURL())
                .into(holder.categoryImage);

        if (Management.getSystem().getCurrentUser().getUsername().equalsIgnoreCase("admin")) {
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.AlertDialogTheme));
                    builder.setMessage("Delete " + Management.getSystem().getAllCategories().get(position).getCategoryName() + "?");
                    builder.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if(fragment instanceof ShopClick){ //checking if the delete button clicked by an item not a category
                                Management.getSystem().deleteFemaleCategory(Management.getSystem().getItemCategoriesWomen().get(position));
                                Management.getSystem().deleteMaleCategory(Management.getSystem().getItemCategoriesMen().get(position));
                                FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                                fm.beginTransaction().replace(R.id.fragmentContainerView, MainStore.class, null)
                                        .setReorderingAllowed(true).addToBackStack(null).commit();
                            } else if (fragment instanceof MainStore) {
                                Management.getSystem().deleteCategory(Management.getSystem().getAllCategories().get(position));
                                FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                                fm.beginTransaction().replace(R.id.fragmentContainerView, Login.class, null)
                                        .setReorderingAllowed(true).addToBackStack(null).commit();
                            }
                            LayoutInflater toastInflater = LayoutInflater.from(context);
                            View toastLayout = toastInflater.inflate(R.layout.toast_layout,
                                    (ViewGroup) view.findViewById(R.id.custom_toast_container));

                            TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                            text.setText("Category deleted.");

                            Toast toast = new Toast(context);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.setView(toastLayout);
                            toast.show();

                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });


                    builder.create().show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView parentCardViewLayout;
        ImageView categoryImage;
        TextView categoryName;
        TextView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentCardViewLayout = itemView.findViewById(R.id.parentCardViewLayout);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryNameTxt);
            delete = itemView.findViewById(R.id.deleteForAdmin);

            parentCardViewLayout.setOnClickListener(view -> {
                FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentContainerView, ShopClick.class, null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
                int position = getLayoutPosition();
                onItemClickListener.onClick(null, position);
            });


        }

    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
