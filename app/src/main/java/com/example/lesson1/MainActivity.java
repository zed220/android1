package com.example.lesson1;

import android.annotation.SuppressLint;
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

    TextView tv_Result;

    ArrayAdapter<GameUnit> unitsAdapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            viewModel = new ViewModel(getBaseContext().getResources());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ((Button)findViewById(R.id.clear_btn)).setOnClickListener(clearButtonListener);
        ((Button)findViewById(R.id.calculate_btn)).setOnClickListener(calculateButtonListener);
        FillUIElements();

        unitsAdapter = new ArrayAdapter<GameUnit>(this, android.R.layout.simple_spinner_item, viewModel.GetUnits());
        sp_AttackType.setAdapter(unitsAdapter);
        sp_DefendType.setAdapter(unitsAdapter);

        viewModel.AddOnChangeAttackingGameUnitListener(attackChangedListener);
        viewModel.AddOnChangeDefendingGameUnitListener(defendChangedListener);

        Clear();
    }
    private void FillUIElements(){
        sp_AttackType = (Spinner) findViewById(R.id.attack_type_sp);
        sp_AttackType.setOnItemSelectedListener(attackItemSelectedListener);

        sp_AttackHealth = (Spinner) findViewById(R.id.attack_health_sp);
        sp_AttackHealth.setOnItemSelectedListener(attackHealthItemSelectedListener);

        sw_AttackIsHard = (Switch)  findViewById(R.id.attack_hardattack_sw);
        sw_AttackIsHard.setOnCheckedChangeListener(attackIsHardCheckedListener);

        sp_AttackDistance = (Spinner) findViewById(R.id.attack_distance_sp);
        sp_AttackDistance.setOnItemSelectedListener(attackDistanceItemSelectedListener);

        sp_DefendType = (Spinner) findViewById(R.id.defend_type_sp);
        sp_DefendType.setOnItemSelectedListener(defendItemSelectedListener);

        sp_DefendHealth = (Spinner) findViewById(R.id.defend_health_sp);
        sp_DefendHealth.setOnItemSelectedListener(defendHealthItemSelectedListener);

        sp_DefendArmor = (Spinner) findViewById(R.id.defend_armor_sp);
        sp_DefendArmor.setOnItemSelectedListener(defendArmorItemSelectedListener);

        tv_Result = (TextView) findViewById(R.id.result_tv);
    }
    private void Clear(){
        viewModel.Clear();
    }
    private void RefreshAttackingInfo(){
        sp_AttackType.setSelection(unitsAdapter.getPosition(viewModel.GetAttackingUnit()));
        sw_AttackIsHard.setChecked(viewModel.GetAttackingUnitStatus().SuppressFire);

        Integer[] lives = IterateIntAsArray(viewModel.GetAttackingUnit().Health, false);
        ArrayAdapter<Integer> livesAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, lives);
        sp_AttackHealth.setAdapter(livesAdapter);
        sp_AttackHealth.setSelection(livesAdapter.getPosition(viewModel.GetAttackingUnitStatus().Health));

        Integer[] distances = IterateIntAsArray(viewModel.GetAttackingUnit().Distances.length, true);
        ArrayAdapter<Integer> distancesAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, distances);
        sp_AttackDistance.setAdapter(distancesAdapter);
        sp_AttackDistance.setSelection(distancesAdapter.getPosition(viewModel.GetAttackingUnitStatus().Distance));

        tv_Result.setText(null);
    }
    private static Integer[] IterateIntAsArray(int value, boolean canNull) {
        Integer[] result = new Integer[value + (canNull ? 1 : 0)];
        for(int i = 0; i < value; i++) {
            result[i] = i + (canNull ? 0 : 1);
        }
        return result;
    }
    private void RefreshDefendingInfo(){
        sp_DefendType.setSelection(unitsAdapter.getPosition(viewModel.GetDefendingUnit()));

        Integer[] lives = IterateIntAsArray(viewModel.GetDefendingUnit().Health, false);
        ArrayAdapter<Integer> livesAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, lives);
        sp_DefendHealth.setAdapter(livesAdapter);
        sp_DefendHealth.setSelection(livesAdapter.getPosition(viewModel.getDefendingUnitStatus().Health));

        Integer[] armory = IterateIntAsArray(viewModel.GetDefendingUnit().Armor + 4, true);
        ArrayAdapter<Integer> armoryAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, armory);
        sp_DefendArmor.setAdapter(armoryAdapter);
        sp_DefendArmor.setSelection(armoryAdapter.getPosition(viewModel.getDefendingUnitStatus().Armor));

        tv_Result.setText(null);
    }

    private final OnChangeGameUnitListener attackChangedListener = new OnChangeGameUnitListenerEx() {
        ArrayAdapter<int[]> lifesAdapter;

        @Override
        void Unsubscribe(GameUnitStatus old) {

        }

        @Override
        void Subscribe(GameUnitStatus unit) {
            RefreshAttackingInfo();
        }
    };
    private final OnChangeGameUnitListener defendChangedListener = new OnChangeGameUnitListenerEx() {
        @Override
        void Unsubscribe(GameUnitStatus old) {

        }

        @Override
        void Subscribe(GameUnitStatus unit) {
            RefreshDefendingInfo();
        }
    };

    private final AdapterView.OnItemSelectedListener attackItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.SetAttackingUnit((GameUnit)parent.getItemAtPosition(position));
            RefreshAttackingInfo();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener defendItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.SetDefendingUnit((GameUnit)parent.getItemAtPosition(position));
            RefreshDefendingInfo();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener attackHealthItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.GetAttackingUnitStatus().Health = (int)parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final CompoundButton.OnCheckedChangeListener attackIsHardCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            viewModel.GetAttackingUnitStatus().SuppressFire = isChecked;
        }
    };
    private final AdapterView.OnItemSelectedListener attackDistanceItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.GetAttackingUnitStatus().Distance = (int)parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final AdapterView.OnItemSelectedListener defendHealthItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.getDefendingUnitStatus().Health = (int)parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener defendArmorItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.getDefendingUnitStatus().Armor = (int)parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final View.OnClickListener clearButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Clear();
        }
    };
    private final View.OnClickListener calculateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CalculationResult result = viewModel.Calculate();
            tv_Result.setText(String.format("Dec Armor: %d, Dec Health: %d, Panic: %s", result.DecArmor, result.DecHealth, result.IsPanic));
        }
    };
}

abstract class OnChangeGameUnitListenerEx implements OnChangeGameUnitListener {
    private GameUnitStatus old;

    @Override
    final public void UnitChanged(GameUnitStatus unit) {
        if(old != null)
            Unsubscribe(old);
        old = unit;
        Subscribe(unit);
    }

    abstract void Unsubscribe(GameUnitStatus old);
    abstract void Subscribe(GameUnitStatus unit);
}