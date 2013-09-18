package com.example.dneshiboshiken;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Album_ShowDetails extends Activity implements View.OnClickListener {
    private LinearLayout baseLayout, btnWindow;
    private ImageView imageView;
    private Button backBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        String filepath = null;

        //�A�v�������\���ɐݒ�
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //�C���e���g�̒l���擾
        Bundle extras = getIntent().getExtras();
        if(extras!=null) filepath = extras.getString("filepath");

        //�x�[�X���C�A�E�g���쐬���Ĕz�u
        baseLayout = new LinearLayout(this);
        setContentView(baseLayout);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        //baseLayout.setBackgroundColor(Color.rgb(255, 255, 255));

        //�C���[�W�r���[���쐬���Ĕz�u
        imageView = new ImageView(this);
        baseLayout.addView(imageView, new LinearLayout.LayoutParams(Album_Main.FP, Album_Main.WC));

        //�摜�f�[�^��\��
        if (filepath!=null) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageBitmap(BitmapFactory.decodeFile(filepath));
        }

        //�T�u���C�A�E�g���쐬���Ĕz�u
        btnWindow = new LinearLayout(this);
        baseLayout.addView(btnWindow, new LinearLayout.LayoutParams(Album_Main.FP, Album_Main.FP));
        btnWindow.setGravity(Gravity.BOTTOM);

        //�{�^�����쐬���Ĕz�u
        backBtn = new Button(this);
        btnWindow.addView(backBtn, new LinearLayout.LayoutParams(Album_Main.FP, Album_Main.WC));
        backBtn.setTextSize(20.0f);
        backBtn.setText("戻る");
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn) {
        	imageView.setImageBitmap(null);
            //�A�N�e�B�r�e�B�̏I��
            finish();
        }
    }
}
