package com.androidweblottery.welcome;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.androidweblottery.HomeActivity;
import com.androidweblottery.R;

/**
 * 引导界面
 * author :  xt
 * date :  2016/4/20 10:34
 */
public class WelcomeFragment extends Fragment {

    private String product;
    private boolean isLast;
    private int icon;
    private ImageView ivBg;
    private Button btnStart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        product = BuildConfig.product;
        Bundle bundle = getArguments();
        isLast = bundle.getBoolean("isLast");
        icon = bundle.getInt("icon");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_item, container, false);
        ivBg = (ImageView) view.findViewById(R.id.imageView);
        btnStart = (Button) view.findViewById(R.id.btn_start);

        initDatas();

        return view;
    }

    public void initDatas() {
        ivBg.setBackgroundResource(icon);
        if (isLast) {
            btnStart.setVisibility(View.VISIBLE);
        } else {
            btnStart.setVisibility(View.GONE);
        }

        ivBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnStart.setBackgroundResource(R.drawable.button_selector_welcome);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }


}
