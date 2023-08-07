package com.example.store;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgotPass#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgotPass extends Fragment {

    private EditText email;
    private Button sendPass;
    private TextView emailWarn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForgotPass() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgotPass.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgotPass newInstance(String param1, String param2) {
        ForgotPass fragment = new ForgotPass();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        email = root.findViewById(R.id.forgotPassEmailEdtTxt);
        sendPass = root.findViewById(R.id.sendCodeBtn);
        emailWarn = root.findViewById(R.id.forgotPassEmailWarnTxt);

        sendPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User(0,"","","");
                boolean exists=false;

                if ((email.getText().toString().matches("^(.+)@(.+)$"))) {
                    for (User u : Management.getSystem().getAllUsers()
                    ) {
                        if (u.getEmail().equals(email.getText().toString())) {
                            user = u;
                            exists=true;
                        }
                    }
                    if (exists){
                        emailWarn.setText("");
                        LayoutInflater toastInflater = getLayoutInflater();
                        View toastLayout = toastInflater.inflate(R.layout.toast_layout,
                                (ViewGroup) view.findViewById(R.id.custom_toast_container));

                        TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                        text.setText("Please check your inbox for your password.");

                        Toast toast = new Toast(getActivity());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(toastLayout);
                        toast.show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
                        builder.setMessage("Your Password is:\n" +user.getPassword());
                        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                        FragmentManager fm = requireActivity().getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.fragmentContainerView, Login.class, null)
                                .setReorderingAllowed(true).addToBackStack(null).commit();
                    }
                    else {
                        emailWarn.setText("");
                        LayoutInflater toastInflater = getLayoutInflater();
                        View toastLayout = toastInflater.inflate(R.layout.toast_layout_negative,
                                (ViewGroup) view.findViewById(R.id.custom_toast_container));

                        TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                        text.setText("This email is not registered.");

                        Toast toast = new Toast(getActivity());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(toastLayout);
                        toast.show();
                    }
                } else {
                    emailWarn.setText("Please enter a valid email address.");
                }
            }
        });

        return root;
    }
}