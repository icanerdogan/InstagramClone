package com.icanerdogan.instragramclone.Classes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.icanerdogan.instragramclone.R;

import java.util.ArrayList;

public class Post extends ArrayAdapter<String>{

    private final ArrayList<String> username; // Kullanıcı Adı için
    private final ArrayList<String> userComment;
    private final ArrayList<Bitmap> userImage;
    private final Activity context;

    public Post(ArrayList<String> username, ArrayList<String> userComment, ArrayList<Bitmap> userImage, Activity context) {
        super(context, R.layout.custom_view, username);
        this.username = username;
        this.userComment = userComment;
        this.userImage = userImage;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view, null, true);

        TextView userNameText = customView.findViewById(R.id.txtCustomViewUsernameText);
        TextView commentText = customView.findViewById(R.id.txtCustomViewCommentText);
        ImageView imageView = customView.findViewById(R.id.imgCustomViewImage);

        userNameText.setText(username.get(position));
        commentText.setText(userComment.get(position));
        imageView.setImageBitmap(userImage.get(position));

        return customView;

    }
}
