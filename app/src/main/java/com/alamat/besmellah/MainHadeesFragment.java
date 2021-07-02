package com.alamat.besmellah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.besmellah.databinding.FragmentMainHadeesBinding;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainHadeesFragment extends Fragment {


    private FragmentMainHadeesBinding binding;

    FragmentPagerItemAdapter fragmentPagerItemAdapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_hadees, container, false);
        view = binding.getRoot();
        initTabLayout();
        return view;
    }

    public void initTabLayout(){
        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getActivity())
                .add("الاحاديث", HadeesFragment.class)
                .add("ادخل حديث جديد", InsertHasdeesFragment.class)
                .create());


        binding.viewpager.setAdapter(fragmentPagerItemAdapter);
        binding.viewpagertab.setViewPager(binding.viewpager);


        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.viewpager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}