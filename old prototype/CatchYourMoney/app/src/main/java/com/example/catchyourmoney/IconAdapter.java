package com.example.catchyourmoney;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class IconAdapter extends ArrayAdapter<IconItem> {

    public IconAdapter(Context context, ArrayList<IconItem> iconList) {
        super(context, 0, iconList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.icon_spinner, parent, false
            );
        }

        ImageView imageViewIcon = convertView.findViewById(R.id.image_view_icon);

        IconItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewIcon.setImageResource(currentItem.geticonImage());

        }

        return convertView;
    }
}

