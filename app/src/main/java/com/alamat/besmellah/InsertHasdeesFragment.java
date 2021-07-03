package com.alamat.besmellah;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamat.besmellah.databinding.FragmentInsertHasdeesBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;


public class InsertHasdeesFragment extends Fragment {

    private FragmentInsertHasdeesBinding binding;
    //    DbManager database;
    View view;
    List<String> newHadeesFile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_hasdees, container, false);
        view = binding.getRoot();


        binding.btnInsertHadeesInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert(binding.etInsertHadeesHadeesTitle.getText().toString(), binding.etInsertHadeesHadeesContent.getText().toString());

            }
        });

        binding.btnInsertHadeesDeleteAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = new DbManager(getContext()).emptydb();
                Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnInsertHadeesReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defualtinsertion();

            }
        });


        return view;


    }

    private void processinsert(String title, String content) {
        String res = new DbManager(getContext()).addrecord(title, content);

//        String res = database.addrecord(title, content);

        binding.etInsertHadeesHadeesTitle.setText("");
        binding.etInsertHadeesHadeesContent.setText("");

        Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();

    }

    public static String[] listOfAhadethNames = {"الحديث الأول", "الحديث الثاني", "الحديث الـثـالـث", "الحديث الـرابع", "الحديث الخامس", "الحديث السادس", "الحديث السابع", "الحديث الثامن", "الحديث التاسع", "الحديث العاشر",
            "الحديث الحادي عشر", "الحديث الثانى عشر", "الحديث الثالث عشر", "الحد يث الرابع عشر", "الحديث الخامس عشر", "الحديث السادس عشر", "الحديث السابع عشر", "الحد يث الثامن عشر", "الحد يث التاسع عشر", "الحديث العشرون",
            "الحديث الحادي والعشرون", "الحديث الثانى والعشرون", "الحديث الثالث والعشرون", "الحديث الرابع والعشرون", "الحديث الخامس والعشرون", "الحديث السادس والعشرون", "الحديث السابع والعشرون", "الحديث الثامن والعشرون", "الحديث التاسع والعشرون", "الحديث الثلاثون",
            "الحديث الحادي والثلاثون", "الحديث الثانى والثلاثون", "الحديث الثالث والثلاثون", "الحديث الرابع والثلاثون", "الحديث الخامس والثلاثون", "الحديث السادس والثلاثون", "الحديث السابع والثلاثون", "الحديث الثامن والثلاثون", "الحديث التاسع والثلاثون", "الحديث الأربعون",
            "الحديث الحادي والأربعون", "الحديث الثانى والأربعون", "الحديث الثالث والأربعون", "الحديث الرابع والأربعون", "الحديث الخامس والأربعون", "الحديث السادس والأربعون", "الحديث السابع والأربعون", "الحديث الثامن والأربعون", "الحديث التاسع والأربعون", "الحديث الخمسون",
    };

    private void defualtinsertion() {
        newHadeesFile = readLine("ahadeth.txt");
        String res = "";
        for (int i = 0; i < newHadeesFile.size(); i++) {
            System.out.println(newHadeesFile.get(i) + "hi");
            res = new DbManager(getContext()).addrecord(listOfAhadethNames[i], newHadeesFile.get(i));
        }
        Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
    }

    public List<String> readLine(String path) {
        List<String> mLines = new ArrayList<>();

        AssetManager am = getActivity().getAssets();

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

//            do {
//                if (line != "#"){
//                    line += reader.readLine();
//                }
//                else {
//                    line += reader.readLine();
//                    mLines.add(line);
//                }
//            } while ((line = reader.readLine()) != null);

            String text = "";
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.contains("#")) {
                    mLines.add(text);
                    text = "";
                } else {
                    text = text + line;
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }

        return mLines;
    }

}
    //
//
//    public ArrayList<String> reorderFile(ArrayList<String> h) {
//
//        try {
//            InputStream stream = getActivity().getAssets().open("Ahadeth.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
////            String text = "";
//            int i = 0;
//            while (reader.ready()) {
//                String line = reader.readLine();
//                h.add(i,line);
////                if (line != "#") {
////                    newHadeesFile.add(i, "");
////                    text += line;
////                    newHadeesFile.set(i, text);
////                } else {
////                    text = line;
////                    newHadeesFile.add(i, text);
////                }
//                i++;
//            }
//
//        } catch (IOException e) {
////            e.printStackTrace();
//        }
//
//        return h;


//
//    private String hadeescontent(int i) {
//        ArrayList<String> v = reorderFile(newHadeesFile);
//
//        String content = "";
//        for (int x = 0; x < listOfAhadethNames.length; x++) {
//            if (x == i)
//                content = v.get(x);
//            break;
//        }
//
//        return content;
//    }
