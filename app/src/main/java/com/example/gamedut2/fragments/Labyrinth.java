package com.example.gamedut2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.gamedut2.R;
import com.example.gamedut2.adapters.LabyrinthAdapter;
import com.example.gamedut2.custom_views.Quad;
import com.example.gamedut2.custom_views.Simple;
import com.example.gamedut2.databinding.FragmentLabyrinthBinding;
import com.example.gamedut2.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.Random;

public class Labyrinth extends Fragment {

    final String TAG = "LABYRINTH";
    private FragmentLabyrinthBinding binding;
    MainViewModel model;
    //ArrayList<Boolean> bool = new ArrayList<Boolean>();
    LabyrinthAdapter adapter;
    Quad quad;
    Boolean endGame = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //init view
        binding = FragmentLabyrinthBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //init navigation

        //init adapter
        adapter = new LabyrinthAdapter(getContext());
        binding.grid.setAdapter(adapter);

        //communique avec la customView
        binding.quad.setEventListener(new Quad.CommandEventListener() {
            @Override
            public void onBottom() {
                model.setSteps();
                //get player position
                int player = adapter.getPlayer();
                // new position = position + 8
                int newPos = player + 8;
                if (newPos < 64) {
                    if (!adapter.getIsWall(newPos)) {
                        model.setBool(newPos, true);// new pos
                        model.setBool(player, false);// old pos
                        model.setArray(model.getTable());// update
                        //move player
                        Log.d(TAG, "onTouch: Go to the Bottom");
                    }
                }
            }

            @Override
            public void onTop() {
                model.setSteps();
                //get player position
                int player = adapter.getPlayer();
                // new position = position - 8
                int newPos = player - 8;
                if (newPos >= 0) {
                    if (!adapter.getIsWall(newPos)) {
                        model.setBool(newPos, true);// new pos
                        model.setBool(player, false);// old pos
                        model.setArray(model.getTable());// update
                        //move player
                        Log.d(TAG, "onTouch: Go to the Bottom");
                    }
                }
            }

            @Override
            public void onRight() {
                model.setSteps();
                //get player position
                int player = adapter.getPlayer();
                // new position = position + 1
                int newPos = player + 1;
                if (newPos < 64) {
                    if (!adapter.getIsWall(newPos)) {
                        model.setBool(newPos, true);// new pos
                        model.setBool(player, false);// old pos
                        model.setArray(model.getTable());// update
                        //move player
                        Log.d(TAG, "onTouch: Go to the Bottom");
                    }
                }
            }

            @Override
            public void onLeft() {
                model.setSteps();
                //get player position
                int player = adapter.getPlayer();
                // new position = position - 1
                int newPos = player - 1;
                if (newPos >= 0) {
                    if (!adapter.getIsWall(newPos)) {
                        model.setBool(newPos, true);// new pos
                        model.setBool(player, false);// old pos
                        model.setArray(model.getTable());// update
                        //move player
                        Log.d(TAG, "onTouch: Go to the Bottom");
                    }
                }
            }
        });

        //Hit (casse une brique)
        binding.simple.setEventListener(new Simple.CommandEventListener() {

            @Override
            public void onHit() {
                model.setSteps();
                //get player position
                int player = adapter.getPlayer();
                // new position = position - 1
                int wall = player + 1;
                if (wall < 64) {
                        model.setIsWall(wall, false);// new pos
                        model.setArraySecond(model.getIsWall());// update
                        //casse une brique
                        Log.d(TAG, "Hit !");
                }
            }
        });

        // Buttons
        binding.restart.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                endGame = true;
                model.clearTable();
                model.clearWallTable();
                Navigation.findNavController(view).navigate(R.id.labyrinth);
            }
        });
        binding.back.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.menu);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init view model
        model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        if (model.getIsWall().isEmpty()) {
            //add values for the random wall
            for (int i = 0; i < 62; i++) {
                //remplissage random
                Random r1 = new Random();
                int random = r1.nextInt(100);
                // 60% de chance d'avoir de l'herbe et pas un mur
                Boolean b = random > 50;
                // sending to viewmodel
                model.addIsWall(b);
                model.setArraySecond(model.getIsWall());
            }
            // last cases
            model.addIsWall(false);
            model.addIsWall(false);
        }
        if (model.getTable().isEmpty()) {
            // find player initial position
            model.addBool(true);
            //the other case are evidents
            for (int i = 1; i < 64; i++) {
                model.addBool(false);
                model.setArray(model.getTable());
            }
        }

        // apply table to adapter (WALL)
        model.getArraySecond().observe(getViewLifecycleOwner(), item -> {
            adapter.setIsWall(item);
        });
        // apply table to adapter (PLAYER)
        model.getArray().observe(getViewLifecycleOwner(), item -> {
            adapter.setIsPlayer(item);

            // gamer win ?
            if (!endGame) {
                if (item.get(63)) {
                    win();
                }
            }

        });

        //observe steps and prints it
        model.getObserverSteps().observe(getViewLifecycleOwner(), item -> {
            String mov = item + " movement";
            binding.steps.setText(mov);
        });
    }

    public void win() {
        // gone
        binding.quad.setVisibility(View.GONE);
        binding.simple.setVisibility(View.GONE);
        // visible
        binding.win.setVisibility(View.VISIBLE);
        binding.restart.setVisibility(View.VISIBLE);
        binding.back.setVisibility(View.VISIBLE);
        // infos
        String win = "Win ! (" + model.getSteps() + " Steps)";
        binding.win.setText(win);
    }
}
