package com.nesterov.geodesicapp;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OnePageFragment extends Fragment implements YourFragmentInterface{


    SendMessage SM;
    ViewGroup rootView2;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;

    public static OnePageFragment newInstance(int page, String title) {
        OnePageFragment fragmentFirst = new OnePageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_one_page, container, false);
        rootView2 = rootView;
        editText1 = (EditText)rootView.findViewById(R.id.editText2);
        editText1.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hint (s);
            }
        });
        editText2 = (EditText)rootView.findViewById(R.id.editText3);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hint (s);
            }
        });
        editText3 = (EditText)rootView.findViewById(R.id.editText4);
        editText3.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hint (s);
            }
        });
        editText4 = (EditText)rootView.findViewById(R.id.editText5);
        editText4.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hint (s);
            }
        });
        editText5 = (EditText)rootView.findViewById(R.id.editText6);
        editText5.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hint (s);
            }
        });
        editText6 = (EditText)rootView.findViewById(R.id.editText7);
        editText6.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hint (s);
            }
        });
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = (TextView)rootView.findViewById(R.id.textView5);
                TextView textView2 = (TextView)rootView.findViewById(R.id.textView6);
                TextView textView3 = (TextView)rootView.findViewById(R.id.textView7);
                TextView textView4 = (TextView)rootView.findViewById(R.id.textView8);
                TextView textView5 = (TextView)rootView.findViewById(R.id.textView9);
                TextView textView6 = (TextView)rootView.findViewById(R.id.textView10);
                if (editText1.getText().toString().matches("") || editText2.getText() .toString().matches("") || editText3.getText() .toString().matches("") ||
                        editText4.getText() .toString().matches("") || editText5.getText() .toString().matches("") || editText6.getText() .toString().matches(""))
                {
                    if (editText1.getText() .toString().matches(""))
                        textView1.setTextColor(Color.RED);
                    else
                        textView1.setTextColor(Color.WHITE);
                    if (editText2.getText() .toString().matches(""))
                        textView2.setTextColor(Color.RED);
                    else
                        textView2.setTextColor(Color.WHITE);
                    if (editText3.getText() .toString().matches(""))
                        textView3.setTextColor(Color.RED);
                    else
                        textView3.setTextColor(Color.WHITE);
                    if (editText4.getText() .toString().matches(""))
                        textView4.setTextColor(Color.RED);
                    else
                        textView4.setTextColor(Color.WHITE);
                    if (editText5.getText() .toString().matches(""))
                        textView5.setTextColor(Color.RED);
                    else
                        textView5.setTextColor(Color.WHITE);
                    if (editText6.getText() .toString().matches(""))
                        textView6.setTextColor(Color.RED);
                    else
                        textView6.setTextColor(Color.WHITE);
                    Toast.makeText(getContext(), getString(R.string.empty_error), Toast.LENGTH_LONG).show();
                }
                else
                {
                    textView1.setTextColor(Color.WHITE);
                    textView2.setTextColor(Color.WHITE);
                    textView3.setTextColor(Color.WHITE);
                    textView4.setTextColor(Color.WHITE);
                    textView5.setTextColor(Color.WHITE);
                    textView6.setTextColor(Color.WHITE);
                    ((MainActivity)getActivity()).getViewPager().setCurrentItem(1);
                }
            }
        });

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.radio1:
                        saveState(false);
                        SM.sendData("true".trim());
                        break;
                    case R.id.radio2:
                        saveState(true);
                        SM.sendData("false".trim());
                        break;
                }
            }
        });
        return rootView;
    }
    private boolean saveState(boolean b)
    {
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSecondRadioChecked", b);
        return editor.commit();
    }

    @Override
    public void fragmentBecameVisible() {

    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    public void hint(Editable s)
    {
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean hint_showed = prefs.getBoolean("HintIsShowed", false);
        if (!hint_showed)
            if (s.toString().equals("."))
                Toast.makeText(getContext(),getString(R.string.minus_hint),Toast.LENGTH_LONG).show();
            else if (s.toString().equals("-"))
            {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("HintIsShowed", true);
                editor.commit();
            }
    }
}
