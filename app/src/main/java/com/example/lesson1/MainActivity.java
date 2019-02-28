package com.example.lesson1;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ViewModel viewModel;
    Spinner sp_AttackType;
    Spinner sp_AttackHealth;
    Switch sw_AttackIsHard;
    Spinner sp_AttackDistance;

    Spinner sp_DefendType;
    Spinner sp_DefendHealth;
    Spinner sp_DefendArmor;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            viewModel = new ViewModel(getBaseContext().getResources());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        ((Button)findViewById(R.id.clear_btn)).setOnClickListener(clearButtonListener);
        ((Button)findViewById(R.id.calculate_btn)).setOnClickListener(calculateButtonListener);
        FillUIElements();
        ArrayAdapter<GameUnit> adapter = new ArrayAdapter<GameUnit>(this, android.R.layout.simple_spinner_item, viewModel.GetUnits());
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_AttackType.setAdapter(adapter);

        viewModel.AddOnChangeAttackingGameUnitListener(attackChangedListener);
        viewModel.AddOnChangeDefendingGameUnitListener(defendChangedListener);
    }
    void FillUIElements(){
        sp_AttackType = (Spinner) findViewById(R.id.attack_type_sp);
        sp_AttackHealth = (Spinner) findViewById(R.id.attack_health_sp);
        sw_AttackIsHard = (Switch)  findViewById(R.id.attack_hardattack_sw);
        sp_AttackDistance = (Spinner) findViewById(R.id.attack_distance_sp);

        sp_DefendType = (Spinner) findViewById(R.id.defend_type_sp);
        sp_DefendHealth = (Spinner) findViewById(R.id.defend_health_sp);
        sp_DefendArmor = (Spinner) findViewById(R.id.defend_armor_sp);
    }

    private final OnChangeGameUnitListener attackChangedListener = new OnChangeGameUnitListener() {
        @Override
        public void UnitChanged(GameUnitStatus unit) {

        }
    };
    private final OnChangeGameUnitListener defendChangedListener = new OnChangeGameUnitListener() {
        @Override
        public void UnitChanged(GameUnitStatus unit) {

        }
    };

    private final View.OnClickListener clearButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           viewModel.Clear();
        }
    };
    private final View.OnClickListener calculateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

/*    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            helloTv.setText("Hi!");
        }
    };*/
}
