package com.example.store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

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
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    private EditText username, password;
    private Button signIn, signUp;
    private TextView forgotPassword, usernameWarn, passWarn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        username = root.findViewById(R.id.loginUsernameEdtTxt);
        password = root.findViewById(R.id.loginPassEdtTxt);
        signIn = root.findViewById(R.id.signInBtn);
        signUp = root.findViewById(R.id.signUpBtn);
        forgotPassword = root.findViewById(R.id.forgotPassTxt);
        usernameWarn = root.findViewById(R.id.loginUsernameWarningTxt);
        passWarn = root.findViewById(R.id.loginPassWarningTxt);

        signIn.setOnClickListener(view -> {

            FragmentManager fm = requireActivity().getSupportFragmentManager();

            boolean loggedIn = false, wrongPass = true;
            User user = new User(-1, "", "", "");

            for (User u : Management.getSystem().getAllUsers()) {

                if (u.getUsername().equals(username.getText().toString())) {
                    user = u;
                    if (u.getPassword().equals(password.getText().toString())) {
                        loggedIn = true;
                        wrongPass = false;
                        Management.getSystem().setCurrentUser(u);
                        break;
                    }
                }
            }




            if (loggedIn) {
                usernameWarn.setText("");
                passWarn.setText("");
                LayoutInflater toastInflater = getLayoutInflater();
                View toastLayout = toastInflater.inflate(R.layout.toast_layout,
                        (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                text.setText("Welcome!");

                Toast toast = new Toast(getActivity());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(toastLayout);
                toast.show();

                fm.beginTransaction().replace(R.id.fragmentContainerView, MainStore.class, null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            } else if (user.getUsername().isEmpty()) {
                LayoutInflater toastInflater = getLayoutInflater();
                View toastLayout = toastInflater.inflate(R.layout.toast_layout_negative,
                        (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                text.setText("Username not found.\nIf you don't have an account, please sign up.");

                Toast toast = new Toast(getActivity());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(toastLayout);
                toast.show();
            } else if (wrongPass) {
                passWarn.setText("Wrong Password");
            }
        });

        forgotPassword.setOnClickListener(view -> {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragmentContainerView, ForgotPass.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        });

        signUp.setOnClickListener(view -> {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragmentContainerView, SignUp.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        });

        return root;
    }
}