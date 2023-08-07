package com.example.store;

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
 * Use the {@link SignUp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp extends Fragment {

    private Button submit;
    private EditText name, pass, pass2, email;
    private TextView nameWarn, passWarn, pass2Warn, emailWarn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();
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

        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        submit = root.findViewById(R.id.submitBtn);
        name = root.findViewById(R.id.signUpUsernameEdtTxt);
        pass = root.findViewById(R.id.signUpPassEdtTxt);
        pass2 = root.findViewById(R.id.signUpPass2EdtTxt);
        email = root.findViewById(R.id.signUpEmailEdtTxt);
        nameWarn = root.findViewById(R.id.signUpUsernameWarningTxt);
        passWarn = root.findViewById(R.id.signUpPassWarningTxt);
        pass2Warn = root.findViewById(R.id.signUpPass2WarningEdtTxt);
        emailWarn = root.findViewById(R.id.signUpEmailWarningTxt);

        submit.setOnClickListener(view -> {
            boolean emailCompleted = false, nameCompleted = false, passCompleted = false, pass2Completed = false;

            //email validation
            if (!email.getText().toString().isEmpty()) {
                if (!(email.getText().toString().matches("^(.+)@(.+)$"))) {
                    emailWarn.setText("Please enter a valid email address.");
                } else {
                    emailWarn.setText("");
                    emailCompleted = true;
                }
            } else {
                emailWarn.setText("This field can't be empty.");
            }

            //name validation
            if (name.getText().toString().isEmpty()) {
                nameWarn.setText("This field can't be empty.");
            } else {
                nameWarn.setText("");
                nameCompleted = true;
            }

            //password validation
            if (pass.getText().toString().isEmpty()) {
                passWarn.setText("This field can't be empty.");
            } else {
                passWarn.setText("");
                passCompleted = true;
            }

            //re-enter password validation
            if (pass2.getText().toString().isEmpty()) {
                pass2Warn.setText("This field can't be empty.");
            } else {
                if (!pass.getText().toString().equals(pass2.getText().toString())) {
                    pass2Warn.setText("Passwords don't match.");
                } else {
                    pass2Warn.setText("");
                    pass2Completed = true;
                }
            }

            //submit
            if (emailCompleted && nameCompleted && passCompleted && pass2Completed) {
                if (Management.getSystem().addUser(new User(1, name.getText().toString(), pass.getText().toString(), email.getText().toString()))) {
                    LayoutInflater toastInflater = getLayoutInflater();
                    View toastLayout = toastInflater.inflate(R.layout.toast_layout,
                            (ViewGroup) view.findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) toastLayout.findViewById(R.id.custom_toast_text);
                    text.setText("You are registered.");

                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(toastLayout);
                    toast.show();
                    FragmentManager fm = requireActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragmentContainerView, Login.class, null)
                            .setReorderingAllowed(true).addToBackStack(null).commit();


                } else {
                    Toast.makeText(getActivity(), "Something went wrong...Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}