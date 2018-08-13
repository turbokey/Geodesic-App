package com.nesterov.geodesicapp;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TwoPageFragment extends Fragment implements YourFragmentInterface{

    MyArrayList data = new MyArrayList();
    Boolean groupselected;
    int selectedgroup;
    String newgroupname;
    public final String[] EXTERNAL_PERMS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public final int EXTERNAL_REQUEST = 138;
    DecimalFormat df = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.US));
    EditText editText12;
    EditText editText22;
    EditText editText32;
    TextView textView12;
    TextView textView22;
    TextView textView32;
    TextView textView02;

    private String convertToFormat(double value) {

        return df.format(value);
    }

    @TargetApi(23)
    private boolean requestForPermission() {
        boolean isPermissionOn = true;
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            if (!canAccessExternalSd()) {
                isPermissionOn = false;
                requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST);
            }
        }
        return isPermissionOn;
    }

    private boolean canAccessExternalSd() {
        return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    private boolean hasPermission(String perm) {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getContext(), perm));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setMessage(getString(R.string.permission_needed));
                    builder.setCancelable(false);
                    builder.setNegativeButton(getString(R.string.cancel_perm_button), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("NotAskForGrantPerm", true);
                            editor.apply();
                        }
                    });
                    builder.setPositiveButton(getString(R.string.grant_perm_button), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            requestForPermission();
                        }
                    });
                    builder.create().show();
                    break;
                }
                return;
            }
        }
    }

    public static TwoPageFragment newInstance(int page, String title) {
        TwoPageFragment fragmentFirst = new TwoPageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean notAskForPerm = prefs.getBoolean("NotAskForGrantPerm", false);
        if (!notAskForPerm)
            requestForPermission();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_two_page, container, false);
        editText12 = (EditText) rootView.findViewById(R.id.editText22);
        editText12.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText12.addTextChangedListener(new TextWatcher() {
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
        editText22 = (EditText) rootView.findViewById(R.id.editText32);
        editText22.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText22.addTextChangedListener(new TextWatcher() {
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
        editText32 = (EditText) rootView.findViewById(R.id.editText42);
        editText32.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL |
                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText32.addTextChangedListener(new TextWatcher() {
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
        textView12 = (TextView) rootView.findViewById(R.id.textView52);
        textView22 = (TextView) rootView.findViewById(R.id.textView62);
        textView32 = (TextView) rootView.findViewById(R.id.textView72);
        textView02 = (TextView) rootView.findViewById(R.id.textView22);
        Button clr_button = (Button) rootView.findViewById(R.id.erase_btn);
        clr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText12.setText("");
                editText22.setText("");
                editText32.setText("");
            }
        });
        Button button = (Button) rootView.findViewById(R.id.calculate_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard();

                EditText editText1 = (EditText) getActivity().findViewById(R.id.editText2);
                EditText editText2 = (EditText) getActivity().findViewById(R.id.editText3);
                EditText editText3 = (EditText) getActivity().findViewById(R.id.editText4);
                EditText editText4 = (EditText) getActivity().findViewById(R.id.editText5);
                EditText editText5 = (EditText) getActivity().findViewById(R.id.editText6);
                EditText editText6 = (EditText) getActivity().findViewById(R.id.editText7);
                TextView textView1 = (TextView) getActivity().findViewById(R.id.textView5);
                TextView textView2 = (TextView) getActivity().findViewById(R.id.textView6);
                TextView textView3 = (TextView) getActivity().findViewById(R.id.textView7);
                TextView textView4 = (TextView) getActivity().findViewById(R.id.textView8);
                TextView textView5 = (TextView) getActivity().findViewById(R.id.textView9);
                TextView textView6 = (TextView) getActivity().findViewById(R.id.textView10);
                if (editText1.getText().toString().matches("") || editText2.getText().toString().matches("") || editText3.getText().toString().matches("") ||
                        editText4.getText().toString().matches("") || editText5.getText().toString().matches("") || editText6.getText().toString().matches("")) {
                    if (editText1.getText().toString().matches(""))
                        textView1.setTextColor(Color.RED);
                    else
                        textView1.setTextColor(Color.WHITE);
                    if (editText2.getText().toString().matches(""))
                        textView2.setTextColor(Color.RED);
                    else
                        textView2.setTextColor(Color.WHITE);
                    if (editText3.getText().toString().matches(""))
                        textView3.setTextColor(Color.RED);
                    else
                        textView3.setTextColor(Color.WHITE);
                    if (editText4.getText().toString().matches(""))
                        textView4.setTextColor(Color.RED);
                    else
                        textView4.setTextColor(Color.WHITE);
                    if (editText5.getText().toString().matches(""))
                        textView5.setTextColor(Color.RED);
                    else
                        textView5.setTextColor(Color.WHITE);
                    if (editText6.getText().toString().matches(""))
                        textView6.setTextColor(Color.RED);
                    else
                        textView6.setTextColor(Color.WHITE);
                    if (editText12.getText().toString().matches("") || editText22.getText().toString().matches("") || editText32.getText().toString().matches("")) {
                        if (editText12.getText().toString().matches(""))
                            textView12.setTextColor(Color.RED);
                        else
                            textView12.setTextColor(Color.WHITE);
                        if (editText22.getText().toString().matches(""))
                            textView22.setTextColor(Color.RED);
                        else
                            textView22.setTextColor(Color.WHITE);
                        if (editText32.getText().toString().matches(""))
                            textView32.setTextColor(Color.RED);
                        else
                            textView32.setTextColor(Color.WHITE);
                        Toast.makeText(getContext(), getString(R.string.empty_error), Toast.LENGTH_LONG).show();
                    } else {
                        textView12.setTextColor(Color.WHITE);
                        textView22.setTextColor(Color.WHITE);
                        textView32.setTextColor(Color.WHITE);
                    }
                    Toast.makeText(getContext(), getString(R.string.empty_error), Toast.LENGTH_LONG).show();
                    ((MainActivity) getActivity()).getViewPager().setCurrentItem(0);
                } else {
                    textView1.setTextColor(Color.WHITE);
                    textView2.setTextColor(Color.WHITE);
                    textView3.setTextColor(Color.WHITE);
                    textView4.setTextColor(Color.WHITE);
                    textView5.setTextColor(Color.WHITE);
                    textView6.setTextColor(Color.WHITE);
                    if (editText12.getText().toString().matches("") || editText22.getText().toString().matches("") || editText32.getText().toString().matches("")) {
                        if (editText12.getText().toString().matches(""))
                            textView12.setTextColor(Color.RED);
                        else
                            textView12.setTextColor(Color.WHITE);
                        if (editText22.getText().toString().matches(""))
                            textView22.setTextColor(Color.RED);
                        else
                            textView22.setTextColor(Color.WHITE);
                        if (editText32.getText().toString().matches(""))
                            textView32.setTextColor(Color.RED);
                        else
                            textView32.setTextColor(Color.WHITE);
                        Toast.makeText(getContext(), getString(R.string.empty_error), Toast.LENGTH_LONG).show();
                    } else {
                        textView12.setTextColor(Color.WHITE);
                        textView22.setTextColor(Color.WHITE);
                        textView32.setTextColor(Color.WHITE);

                        Calculate(Double.parseDouble(editText1.getText().toString()), Double.parseDouble(editText2.getText().toString()), Double.parseDouble(editText3.getText().toString()),
                                Double.parseDouble(editText4.getText().toString()), Double.parseDouble(editText5.getText().toString()), Double.parseDouble(editText6.getText().toString()),
                                Double.parseDouble(editText12.getText().toString()), Double.parseDouble(editText22.getText().toString()), Double.parseDouble(editText32.getText().toString()));
                    }
                }
            }
        });
        return rootView;
    }

    private void Calculate(final Double XA, final Double YA, final Double HA, final Double XB, final Double YB, final Double HB, final Double unkn1, final Double unkn2, final Double unkn3) {
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean notAskForPerm = prefs.getBoolean("NotAskForGrantPerm", false);

        Double dx1, dy1, A, SAB;
        dx1 = increment(XA, XB);
        dy1 = increment(YA, YB);
        A = directional_corner(dx1, dy1);
        SAB = distance_calculation(dx1, A);
        if (((RadioButton) getActivity().findViewById(R.id.radio1)).isChecked()) {
            final Double XC, YC, A1, HC, v, dH, SAC, dx, dy, hA_B, H_C;
            final String deviation;
            XC = unkn1;
            YC = unkn2;
            HC = unkn3;
            dx1 = increment(XA, XC);
            dy1 = increment(YA, YC);
            A1 = directional_corner(dx1, dy1);
            v = A1 - A;
            SAC = distance_calculation(dx1, A1);
            dx = calc_inc_dx(v, SAC);
            dy = calc_inc_dy(v, SAC);
            hA_B = slope_calculation_hA_B(HA, HB);
            H_C = slope_calculation_H_C(hA_B, SAB,HB, dx);
            dH = H_C - HC;
            if (dH > 0)
                deviation = getString(R.string.under, convertToFormat(Math.abs(dH)));
            else
                deviation = getString(R.string.higher, convertToFormat(Math.abs(dH)));

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setMessage(getString(R.string.result2, convertToFormat(dx) + getString(R.string.meter), convertToFormat(dy) + getString(R.string.meter), deviation + getString(R.string.meter)));
            alertDialogBuilder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            if (!notAskForPerm) {
                groupselected = loadState();
                selectedgroup = loadSelectedGroup();
                data = loadGroupsList();

                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.my_dialog_layout, null);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                final AlertDialog alertDialog = alertDialogBuilder.create();

                final Spinner spinner = (Spinner) promptsView.findViewById(R.id.spinner);
                if (data.size() == 0)
                    spinner.setEnabled(false);
                final Button button = (Button) promptsView.findViewById(R.id.add_btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_item, ArrayListToArray(data));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                button.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            button.setTextColor(Color.parseColor("#4b4b4b"));

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage(getString(R.string.type_group_name));
                            final EditText input = new EditText(getContext());

                            builder.setView(input);
                            builder.setPositiveButton(getString(R.string.positive_answer), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    newgroupname = input.getText().toString();
                                    if (getFilesList().contains(newgroupname))
                                    {
                                        Toast.makeText(getContext(),getString(R.string.file_name_error),Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    data.add(newgroupname);
                                    spinner.setEnabled(true);
                                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_item, ArrayListToArray(data));
                                    spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                                            adapter,
                                            R.layout.contact_spinner_row_nothing_selected,
                                            getContext()));
                                    spinner.setSelection(data.size());
                                }
                            });
                            builder.setNegativeButton(getString(R.string.cansel_answer), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            final AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                            input.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if (TextUtils.isEmpty(s)) {
                                        dialog.getButton(
                                                AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                    } else {
                                        dialog.getButton(
                                                AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                    }
                                }
                            });

                            return true;
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            button.setTextColor(Color.WHITE);
                            return true;
                        }
                        return false;
                    }
                });
                spinner.setAdapter(
                        new NothingSelectedSpinnerAdapter(
                                adapter,
                                R.layout.contact_spinner_row_nothing_selected,
                                getContext()));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            groupselected = true;
                            selectedgroup = position;
                            saveState(groupselected);
                            saveState(selectedgroup);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                alertDialog.show();

                Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                theButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!groupselected) {
                            Toast.makeText(getContext(), getString(R.string.save_error), Toast.LENGTH_LONG).show();
                        } else {
                            if (saveFile(spinner.getSelectedItem().toString(), XA.toString(), YA.toString(), HA.toString(), XB.toString(), YB.toString(),
                                    HB.toString(), unkn1.toString(), unkn2.toString(), unkn3.toString(), convertToFormat(dx),
                                    convertToFormat(dy), deviation, getString(R.string.offset))) {
                                Toast.makeText(getContext(), getString(R.string.saved), Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                                hideKeyboard();
                            } else
                                Toast.makeText(getContext(), getString(R.string.saving_error), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                if (groupselected)
                    try {
                        spinner.setSelection(selectedgroup);
                    } catch (Exception e) {

                    }
                alertDialog.setCancelable(false);
            } else {
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
            }
        } else {
            final Double d, b, h, XD, YD, A2, XC, YC, hA_B, H_C, HC;
            Double dx, dy;
            d = unkn1;
            b = unkn2;
            h = unkn3;
            dx = calc_inc_dx(A, d);
            dy = calc_inc_dy(A, d);
            XD = XA + dx;
            YD = YA + dy;
            if (b > 0)
                A2 = A + 90;
            else
                A2 = A - 90;
            dx = calc_inc_dx(A2, Math.abs(b));
            dy = calc_inc_dy(A2, Math.abs(b));
            XC = XD + dx;
            YC = YD + dy;
            hA_B = slope_calculation_hA_B(HA, HB);
            H_C = slope_calculation_H_C(hA_B, SAB, HB, d);
            HC = H_C + h;

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            alertDialogBuilder.setMessage(getString(R.string.result, convertToFormat(XC) + getString(R.string.meter), convertToFormat(YC) + getString(R.string.meter), convertToFormat(HC) + getString(R.string.meter)));

            if (!notAskForPerm) {
                groupselected = loadState();
                selectedgroup = loadSelectedGroup();
                data = loadGroupsList();

                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.my_dialog_layout, null);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                final AlertDialog alertDialog = alertDialogBuilder.create();

                final Spinner spinner = (Spinner) promptsView.findViewById(R.id.spinner);
                if (data.size() == 0)
                    spinner.setEnabled(false);
                final Button button = (Button) promptsView.findViewById(R.id.add_btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_item, ArrayListToArray(data));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                button.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            button.setTextColor(Color.parseColor("#4b4b4b"));

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage(getString(R.string.type_group_name));
                            final EditText input = new EditText(getContext());
                            builder.setView(input);
                            builder.setPositiveButton(getString(R.string.positive_answer), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    newgroupname = input.getText().toString();
                                    if (getFilesList().contains(newgroupname))
                                    {
                                        Toast.makeText(getContext(),getString(R.string.file_name_error),Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    data.add(newgroupname);
                                    spinner.setEnabled(true);
                                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_item, ArrayListToArray(data));
                                    spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                                            adapter,
                                            R.layout.contact_spinner_row_nothing_selected,
                                            getContext()));
                                    spinner.setSelection(data.size());
                                }
                            });
                            builder.setNegativeButton(getString(R.string.cansel_answer), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            final AlertDialog dialog = builder.create();
                            dialog.show();
                            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                            input.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if (TextUtils.isEmpty(s)) {
                                        dialog.getButton(
                                                AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                    } else {
                                        dialog.getButton(
                                                AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                    }
                                }
                            });

                            return true;
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            button.setTextColor(Color.WHITE);
                            return true;
                        }
                        return false;
                    }
                });
                spinner.setAdapter(
                        new NothingSelectedSpinnerAdapter(
                                adapter,
                                R.layout.contact_spinner_row_nothing_selected,
                                getContext()));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            groupselected = true;
                            selectedgroup = position;
                            saveState(groupselected);
                            saveState(selectedgroup);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                alertDialog.show();
                Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                theButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!groupselected) {
                            Toast.makeText(getContext(), getString(R.string.save_error), Toast.LENGTH_LONG).show();
                        } else {
                            if (saveFile(spinner.getSelectedItem().toString(), XA.toString(), YA.toString(), HA.toString(), XB.toString(), YB.toString(),
                                    HB.toString(), unkn1.toString(), unkn2.toString(), unkn3.toString(), convertToFormat(XC), convertToFormat(YC), convertToFormat(HC),
                                    getString(R.string.coords))) {
                                Toast.makeText(getContext(), getString(R.string.saved), Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                                hideKeyboard();
                            } else
                                Toast.makeText(getContext(), getString(R.string.saving_error), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                if (groupselected)
                    try {
                        spinner.setSelection(selectedgroup);
                    } catch (Exception e) {

                    }

                alertDialog.setCancelable(false);
            } else {
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
            }
        }
    }


    private Double increment(Double X1, Double X2)
    {
        return X2 - X1;
    }
    private Double distance_calculation(Double dx11, Double A11)
    {
        return dx11 / Math.cos(A11 * (Math.PI/ 180));
    }
    private Double directional_corner(Double dX1, Double dY1) {
        Double az1;
        Double AZ;
        az1 = dY1 / dX1;
        AZ = (180 / Math.PI) * Math.atan(az1);
        if (dX1 > 0 && dY1 > 0)
            AZ = AZ;
         else
            if (dX1 < 0 && dY1 > 0)
                AZ = 180 - Math.abs(AZ);
            else
                if (dX1 < 0 && dY1 < 0)
                    AZ = 180 + Math.abs(AZ);
                else
                    if (dX1 > 0 && dY1 < 0)
                        AZ = 360 - Math.abs(AZ);
        return AZ;
    }
    private Double calc_inc_dx(Double Z, Double N)
    {
        return  N * Math.cos(Z * (Math.PI / 180));
    }
    private Double calc_inc_dy(Double Z, Double N)
    {
        return  N * Math.sin(Z * (Math.PI / 180));
    }
    private Double slope_calculation_hA_B(Double HA, Double HB)
    {
        return HB - HA;
    }
    private Double slope_calculation_H_C(Double hA_B, Double SAB, Double HB,  Double SAC)
    {
        double k = Math.sqrt(SAB*SAB + hA_B * hA_B);
        double al = Math.acos(SAB / k);
        double Ras = (SAB - SAC) * Math.tan(al);
        return HB + Ras;
    }
    private boolean saveState(boolean b)
    {
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("GroupSelected", b);
        return editor.commit();
    }
    private boolean saveState(int b)
    {
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("SelectedGroup", b);
        return editor.commit();
    }
    private Boolean loadState()
    {
        try {
            SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
            return prefs.getBoolean("GroupSelected", false);
        }catch (Exception e)
        {
            return false;
        }

    }
    private Integer loadSelectedGroup()
    {
        MyArrayList FilesList = new MyArrayList();
        try {
            SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
                if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    return 0;
                }
                File sdPath = new File(Environment.getExternalStorageDirectory() + getString(R.string.dir_name));
                if (!sdPath.exists()) {
                    sdPath.mkdir();
                }
                String path = Environment.getExternalStorageDirectory().toString() + getString(R.string.dir_name);
                File f = new File(path);
                File file[] = f.listFiles();
                if (file != null)
                for (int i = 0; i < file.length; i++)
                {
                    if (getFileType(file[i]) != null)
                        if (getFileType(file[i]).equals(getString(R.string.offset)) && ((RadioButton) getActivity().findViewById(R.id.radio1)).isChecked())
                            FilesList.add(file[i].getName());
                        else if (getFileType(file[i]).equals(getString(R.string.coords)) && !((RadioButton) getActivity().findViewById(R.id.radio1)).isChecked())
                            FilesList.add(file[i].getName());
                }
            if (prefs.getInt("SelectedGroup", 0) > FilesList.size())
            {
                return FilesList.size();
            }
            return prefs.getInt("SelectedGroup", 0);
        }catch (Exception e)
        {
            return 0;
        }

    }
    String[] ArrayListToArray(MyArrayList arrList)
    {
        String[] tmp = new String[arrList.size()];
        for (int i = 0; i < tmp.length; i++)
            tmp[i]= arrList.get(i);
        return tmp;
    }

    public MyArrayList loadGroupsList()
    {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return new MyArrayList();
        }
        File sdPath = new File(Environment.getExternalStorageDirectory() + getString(R.string.dir_name));
        if (!sdPath.exists()) {
            sdPath.mkdir();
        }
        String path = Environment.getExternalStorageDirectory().toString() + getString(R.string.dir_name);
        File f = new File(path);
        File file[] = f.listFiles();
        MyArrayList FilesList = new MyArrayList();
        if (file != null)
            for (int i = 0; i < file.length; i++)
                if (!FilesList.contains(file[i].getName())){
                    if (getFileType(file[i]) != null)
                        if (getFileType(file[i]).equals(getString(R.string.offset)) && ((RadioButton) getActivity().findViewById(R.id.radio1)).isChecked())
                            FilesList.add(file[i].getName());
                        else if (getFileType(file[i]).equals(getString(R.string.coords)) && !((RadioButton) getActivity().findViewById(R.id.radio1)).isChecked())
                            FilesList.add(file[i].getName());
                }

        if (FilesList.size() == 0) {
            groupselected = false;
            saveState(groupselected);
            return new MyArrayList();
        }
        return FilesList;
    }
    public boolean saveFile(String FileName,String A_x,String A_y,String A_h,String B_x,String B_y,String B_h,
                            String C_1,String C_2,String C_3, String result1, String result2, String result3, String dataType) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return false;
        }
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + getString(R.string.dir_name));
        if (!sdPath.exists()) {
            sdPath.mkdir();
        }
        File sdFile = new File(sdPath, FileName);

        try {
            if (!sdFile.exists() && dataType.equals(getString(R.string.coords))) {
                BufferedWriter bw_c = new BufferedWriter(new FileWriter(sdFile, true));
                bw_c.write(getString(R.string.coords));
                bw_c.newLine();
                bw_c.close();
            }
            if (!sdFile.exists() && dataType.equals(getString(R.string.offset))) {
                BufferedWriter bw_o = new BufferedWriter(new FileWriter(sdFile, true));
                bw_o.write(getString(R.string.offset));
                bw_o.newLine();
                bw_o.close();
            }
        }catch (Exception e)
        {
            return  false;
        }

        try {
            if (dataType.equals(getString(R.string.offset))) {
                BufferedWriter bw_o = new BufferedWriter(new FileWriter(sdFile,true));
                bw_o.write(A_x + ";" + A_y + ";"+A_h + ";"+B_x + ";"+B_y + ";"+B_h + ";"+C_1 + ";"+C_2 + ";"+C_3+";"+result1+";"+result2+";"+result3);
                bw_o.newLine();
                bw_o.close();
            } else {
                if (dataType.equals(getString(R.string.coords))) {
                    BufferedWriter bw_c = new BufferedWriter(new FileWriter(sdFile,true));
                    bw_c.write(A_x + ";" + A_y + ";"+A_h + ";"+B_x + ";"+B_y + ";"+B_h + ";"+C_1 + ";"+C_2 + ";"+C_3+";"+result1+";"+result2+";"+result3);
                    bw_c.newLine();
                    bw_c.close();
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void hideKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public String getFileType(File file)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            if (str != null)
                return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    protected void displayReceivedData(String message)
    {
        if (message.equals("true"))
        {
            textView02.setText(R.string.page2_title);
            textView12.setText(R.string.x);
            textView22.setText(R.string.y);
            textView32.setText(R.string.h);
            editText12.setText("");
            editText22.setText("");
            editText32.setText("");
        }
        else
        {
            textView02.setText(R.string.page3_title);
            textView12.setText(R.string.length_a);
            textView22.setText(R.string.indentation_b);
            textView32.setText(R.string.height_h);
            editText12.setText("");
            editText22.setText("");
            editText32.setText("");
        }
    }

    @Override
    public void fragmentBecameVisible() {

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

    public MyArrayList getFilesList()
    {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return new MyArrayList();
        }
        File sdPath = new File(Environment.getExternalStorageDirectory() + getString(R.string.dir_name));
        if (!sdPath.exists()) {
            sdPath.mkdir();
        }
        String path = Environment.getExternalStorageDirectory().toString() + getString(R.string.dir_name);
        File f = new File(path);
        File file[] = f.listFiles();
        MyArrayList FilesList = new MyArrayList();
        if (file != null)
            for (int i = 0; i < file.length; i++)
                if (!FilesList.contains(file[i].getName())){
                    if (getFileType(file[i]) != null)
                        FilesList.add(file[i].getName());
                }

        if (FilesList.size() == 0) {
            return new MyArrayList();
        }
        return FilesList;
    }
}