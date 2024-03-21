package com.example.lab1_menu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, gradesEditText;
    Button gradesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        gradesEditText = findViewById(R.id.gradesEditText);
        gradesButton = findViewById(R.id.gradesButton);

        final LinearLayout formLayout = findViewById(R.id.formLayout);

        gradesButton.setVisibility(View.GONE);

        gradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.przycisk_oceny_zosta_naci_ni_ty), Toast.LENGTH_SHORT).show();
            }
        });

        firstNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateFields();
                }
            }
        });

        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateFields();
                }
            }
        });

        gradesEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateFields();
                }
            }
        });
    }

    private void validateFields() {
        boolean isFirstNameValid = !TextUtils.isEmpty(firstNameEditText.getText().toString());
        boolean isLastNameValid = !TextUtils.isEmpty(lastNameEditText.getText().toString());
        boolean isGradesValid = false;
        try {
            int grades = Integer.parseInt(gradesEditText.getText().toString());
            isGradesValid = (grades >= 5 && grades <= 15);
        } catch (NumberFormatException e) {
            isGradesValid = false;
        }

        // Walidacja pól
        if (!isFirstNameValid) {
            Toast.makeText(MainActivity.this, getString(R.string.pole_imie_nie_mo_e_by_puste), Toast.LENGTH_SHORT).show();
            firstNameEditText.setError(getString(R.string.pole_imie_nie_mo_e_by_puste));
        }
        if (!isLastNameValid) {
            Toast.makeText(MainActivity.this, R.string.pole_nazwisko_nie_mo_e_by_puste, Toast.LENGTH_SHORT).show();
            lastNameEditText.setError(getString(R.string.pole_nazwisko_nie_mo_e_by_puste));
        }
        if (!isGradesValid) {
            Toast.makeText(MainActivity.this, R.string.liczba_ocen_musi_zawiera_si_mi_dzy_5_a_15, Toast.LENGTH_SHORT).show();
            gradesEditText.setError(getString(R.string.liczba_ocen_musi_zawiera_si_mi_dzy_5_a_15));
        }

        // Wyświetlenie przycisku tylko jeśli wszystkie pola są poprawne
        if (isFirstNameValid && isLastNameValid && isGradesValid) {
            gradesButton.setVisibility(View.VISIBLE);
        } else {
            gradesButton.setVisibility(View.GONE);
        }
    }

}