package com.example.gamedut2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.gamedut2.R;
import com.example.gamedut2.adapters.ItemAdapter;
import com.example.gamedut2.databinding.FragmentGameListBinding;

public class GameListFragment extends Fragment {

    private FragmentGameListBinding binding;
    ItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //init view
        binding = FragmentGameListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //init adapter
        adapter = new ItemAdapter(getContext());
        binding.grid.setAdapter(adapter);

        binding.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.puzzle);
                } else {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.labyrinth);
                }
            }
        });

        return view;
    }
}
