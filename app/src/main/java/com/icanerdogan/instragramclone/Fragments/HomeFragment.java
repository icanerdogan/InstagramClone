package com.icanerdogan.instragramclone.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.icanerdogan.instragramclone.Classes.Post;
import com.icanerdogan.instragramclone.R;
import com.icanerdogan.instragramclone.UploadActivity;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class HomeFragment extends Fragment {

    ListView lView;
    ArrayList<String> usernameFromParse;
    ArrayList<String> commentFromParse;
    ArrayList<Bitmap> imageFromParse;
    Post post;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {}

    public static HomeFragment newInstance(String param1, String param2) {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.upload_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.action_upload){
            Intent intent = new Intent(getActivity(), UploadActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Title Bar Logo
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setIcon(R.drawable.instagramlogo);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Title Bar Text
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        // Title Bar Color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(colorDrawable);

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Menu Visible
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lView = view.findViewById(R.id.listViewFragment);

        usernameFromParse = new ArrayList<>();
        commentFromParse = new ArrayList<>();
        imageFromParse = new ArrayList<>();

        post = new Post(usernameFromParse, commentFromParse, imageFromParse, getActivity());

        lView.setAdapter(post);

        download();

        return view;

    }

    public void setTextWithSpan(ArrayList arrayList, String text, String spanTextBold, String secondPartOfText, StyleSpan style) {

        SpannableStringBuilder sb = new SpannableStringBuilder(text);

        int start = text.indexOf(spanTextBold);
        int end = start + spanTextBold.length();
        sb.setSpan(style, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

        int startTwo = text.indexOf(secondPartOfText);
        int endTwo = startTwo + secondPartOfText.length();

        sb.setSpan(new RelativeSizeSpan(0.95f), startTwo, endTwo, 0);

        arrayList.add(sb);
    }

    private void download() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null)
                {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (objects.size() > 0)
                    {
                        for (ParseObject object:objects)
                        {
                            ParseFile parseFile = (ParseFile) object.get("image");

                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null)
                                    {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        imageFromParse.add(bitmap);

                                        String username = object.getString("username");
                                        String comment = object.getString("comment");

                                        usernameFromParse.add(username);

                                        setTextWithSpan(commentFromParse,
                                                username+" "+comment,
                                                    username,
                                                    comment,
                                                    new android.text.style.StyleSpan(Typeface.BOLD));

                                        //commentFromParse.add(usernameAndComment);

                                        post.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}