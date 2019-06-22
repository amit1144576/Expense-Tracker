package com.example.catchyourmoney;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class PopUpPW extends AppCompatDialogFragment {
    private EditText editPopUp;
    private DialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.pop_up_pw,null);

        builder.setView(view)
                .setTitle("Set your Passwort")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         listener.resetSwitch();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String password=editPopUp.getText().toString();
                        listener.getPW(password);
                    }
                });

        editPopUp= view.findViewById(R.id.editPopUp);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (DialogListener) context;
    }

    public interface DialogListener{
        void getPW(String password);

        void resetSwitch();
    }


}

