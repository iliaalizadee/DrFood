package com.khawrizmi.iliaalizadeh.doctorfood;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdviceFragment extends Fragment {


    public AdviceFragment() {

    }
    SQLiteHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_advice,container,false);
        Button btn = (Button) view.findViewById(R.id.insendbtn);
        final EditText titleedt=view.findViewById(R.id.intitleedt);
        final EditText targetedt=view.findViewById(R.id.intargetedt);
        final EditText dataedt=view.findViewById(R.id.indataedt);
        final EditText dateedt=view.findViewById(R.id.indateedt);
        db=new SQLiteHandler(getActivity().getApplicationContext());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    APISendingData apiSendingData = new APISendingData(getActivity());
                    JSONObject requestJsonObject = new JSONObject();

                    try {
                        requestJsonObject.put("title", titleedt.getText());
                        requestJsonObject.put("target", targetedt.getText());
                        requestJsonObject.put("data", dataedt.getText());
                        requestJsonObject.put("date", dateedt.getText());
                        requestJsonObject.put("drname", db.getUserDetails().get("name"));
                        apiSendingData.signUp(requestJsonObject, new APISendingData.OnSignupComplate() {
                            @Override
                            public void onSignUp(boolean success) {
                                if (success == true) {
                                    Toast.makeText(getActivity(), "اطلاعات با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "اطلاعات ارسال نشد", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

        });
        return view;
    }

}
