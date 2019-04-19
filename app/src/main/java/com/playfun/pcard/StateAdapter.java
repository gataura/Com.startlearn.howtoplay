package com.playfun.pcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

    private LayoutInflater inflater;
    private int layout;
    private List<State> states;

    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView thumbView = (ImageView) view.findViewById(R.id.mini_thumb);
        TextView miniNameView = (TextView) view.findViewById(R.id.mini_name);
        TextView miniCapitalView = (TextView) view.findViewById(R.id.mini_capital);

        State state = states.get(position);

        thumbView.setImageResource(state.getThumbResource());
        miniNameView.setText(state.getMiniName());
        miniCapitalView.setText(state.getMiniCapital());

        return view;
    }
}