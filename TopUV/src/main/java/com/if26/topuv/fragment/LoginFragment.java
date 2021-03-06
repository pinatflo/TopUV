package com.if26.topuv.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if26.topuv.R;
import com.if26.topuv.services.LoginService;


import java.util.concurrent.ExecutionException;


/**
 * envoyer git à contact@eutech.ssii.com
 * Created by Flo on 10/12/2013.
 */
public class LoginFragment extends Fragment implements OnClickListener {

    private EditText login, password;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_page, container, false);
        login  = (EditText) v.findViewById(R.id.usernameField);
        password = (EditText) v.findViewById(R.id.pwdField);
        loginButton = (Button) v.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {

        // Local validation
        boolean error = false;
        if(password.length() == 0)
        {
            error = true;
        }
        if(error)
        {
            return;
        }

        LoginService loginService = new LoginService();
        try
        {
            String token = loginService.execute(login.getText().toString(), password.getText().toString()).get();

            if(token == null){
                Toast.makeText(getActivity().getBaseContext(), "Invalid login details", Toast.LENGTH_SHORT).show();
            } else if(token != null){
                Toast.makeText(getActivity().getBaseContext(), "Login success !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity().getBaseContext(), "Something else wrong", Toast.LENGTH_SHORT).show();
            }
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
        catch(NullPointerException nullPointerException)
        {

        }
    }
}
