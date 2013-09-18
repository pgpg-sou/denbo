package com.example.dneshiboshiken;

import java.io.File;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Album_Main extends Activity implements View.OnClickListener {
    //LayoutParams�̒萔
    public final static int FP = ViewGroup.LayoutParams.FILL_PARENT;
    public final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    public final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    public static String imageFolder;
    public static File[] fileList;

    private final int gridSize = 16;
    private static int pageOffset = 0;

    private LinearLayout baseLayout, btnWindow1, btnWindow2, btnWindow3;
    private GridView imageGrid;
    private Button prevBtn, nextBtn, goToList;
    private TableLayout btnTableLayout;
    private TableRow btnRow;
    private ImageAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //�A�v�������\���ɐݒ�
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //�x�[�X���C�A�E�g���쐬
        baseLayout = new LinearLayout(this);
        setContentView(R.layout.main);
        baseLayout.setOrientation(LinearLayout.VERTICAL);

        //�O���b�h�r���[���쐬���Ĕz�u
        //imageGrid = new GridView(this);
        imageGrid = (GridView)findViewById(R.id.gridView1);
        //baseLayout.addView(imageGrid
        //    , new LinearLayout.LayoutParams(FP, FP));
        imageGrid.setNumColumns(4);
        imageGrid.setGravity(Gravity.CENTER_HORIZONTAL);

        //�e�[�u�����C�A�E�g���쐬���ă{�^����z�u
        btnTableLayout = new TableLayout(this);
        //baseLayout.addView(btnTableLayout, new LinearLayout.LayoutParams(FP, WC));
        btnTableLayout.setGravity(Gravity.BOTTOM);
        btnRow = new TableRow(this);
        //btnTableLayout.addView(btnRow);
        btnWindow1 = new LinearLayout(this);
        btnWindow2 = new LinearLayout(this);
        btnWindow3 = new LinearLayout(this);
        btnRow.addView(btnWindow1, new TableRow.LayoutParams(80, FP));
        btnRow.addView(btnWindow2, new TableRow.LayoutParams(320, FP));
        btnRow.addView(btnWindow3, new TableRow.LayoutParams(80, FP));
        btnWindow2.setGravity(Gravity.CENTER);
        btnWindow3.setGravity(Gravity.RIGHT);

        //�T�u���C�A�E�g���Ƀ{�^����z�u
        //prevBtn = new Button(this);
        prevBtn = (Button)findViewById(R.id.button1);
        //btnWindow1.addView(prevBtn, new LinearLayout.LayoutParams(WC, WC));
        prevBtn.setTextSize(16.0f);
        prevBtn.setText("戻る");
        prevBtn = (Button)findViewById(R.id.button1);
        prevBtn.setOnClickListener(this);
        prevBtn.setEnabled(false);

        //�T�u���C�A�E�g���Ƀ{�^����z�u
        //nextBtn = new Button(this);
        //btnWindow3.addView(nextBtn, new LinearLayout.LayoutParams(WC, WC));
        nextBtn = (Button)findViewById(R.id.button3);
        nextBtn.setTextSize(16.0f);
        nextBtn.setText("進む");
        nextBtn.setOnClickListener(this);

        //SD�J�[�h�̃t�H���_�����擾
        File sdcard = Environment.getExternalStorageDirectory();

        //�摜�f�[�^���ۑ�����Ă���t�H���_���擾
        File dir1 = new File(Environment.getExternalStorageDirectory().getPath());
        File dir = new File(dir1.getAbsolutePath()+"/Yukari/Photo");

        //�t�H���_�����݂��邩�ǂ����𔻒�
        if(!(dir.exists())) {
            //SD�J�[�h���g�p�\�ȂƂ��t�H���_���쐬
            if (sdcard.canWrite()){
                dir.mkdirs();
                //showDialog(this, "����", imageFolder+"���쐬���܂����B");
            }

            //�{�^�����g�p�s�\�ɐݒ�
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(false);
        } else {
            //�t�H���_���̃t�@�C�����X�g���쐬
            fileList = dir.listFiles();
            Arrays.sort(fileList);
            /* Reserved
            //�t�H���_���X�g���e�L�X�g�ɕϊ����ă_�C�A���O�ɕ\��
            String text = "";
            for (int i=0; i<fileList.length; i++) {
                text = text
                    + fileList[i].toString().replace(imageFolder.toString(), "") + '\n';
            }
            showDialog(this, "�t�@�C�����X�g", text);
            */
            //�O���b�h�r���[�ɃC���[�W�A�_�v�^��ݒ�
            imageAdapter = new ImageAdapter(this);
            imageGrid.setAdapter(imageAdapter);

            //�O���b�h�r���[�ɃR�[���o�b�N���X�i�[��ݒ�
            imageGrid.setOnItemClickListener(new OnImageGridClick());

            //�摜�f�[�^��1�y�[�W�Ŏ�܂�Ƃ��̓{�^�����g�p�s�\�ɐݒ�
            if (fileList.length<=gridSize) nextBtn.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        //�O�̉�ʂ�\��
        if (v==prevBtn) {
            if (pageOffset>0) {
                pageOffset--;
                if (pageOffset==0) prevBtn.setEnabled(false);
                nextBtn.setEnabled(true);
            }
            imageAdapter.notifyDataSetChanged();
        }

        //���̉�ʂ�\��
        if (v==nextBtn) {
            if ((pageOffset+1)*gridSize<fileList.length) {
                pageOffset++;
                prevBtn.setEnabled(true);
                if (pageOffset==fileList.length/gridSize) nextBtn.setEnabled(false);
            }
            imageAdapter.notifyDataSetChanged();
        }

        //���X�g�`���̕\���ɐؑ�
        if (v==goToList) {
            //�����I�ȃC���e���g�𐶐��i�������ʂ̃��X�g�\����ʁj
            Intent intent = new Intent(this, Album_ShowList.class);

            //�A�N�e�B�r�e�B�̌Ăяo��
            startActivity(intent);
        }
    }

    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public ImageAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            int count = Math.min(fileList.length-pageOffset*gridSize, gridSize);
            return count;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //�\������r���[�����݂��Ȃ��Ƃ��͍쐬
            if(convertView == null){
                convertView = inflater.inflate(R.layout.griditem, null);
            }

            //�C���[�W�r���[�ɉ摜��\��
            ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            imageView.setPadding(5, 0, 5, 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            BitmapFactory.Options sizeOption = new BitmapFactory.Options();
            sizeOption.inSampleSize = 4; //�摜�T�C�Y��1/4�ɂ���
            String fileName = fileList[position+pageOffset*gridSize].toString();
            Bitmap bmp = BitmapFactory.decodeFile(fileName, sizeOption);
            imageView.setImageBitmap(bmp);

            //�e�L�X�g�r���[�ɕ������\��
            TextView textView = (TextView) convertView.findViewById(R.id.filename);
            textView.setPadding(5, 0, 5, 20);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextColor(Color.rgb(255, 255, 255));
            fileName = fileList[position+pageOffset*gridSize].getName();
            //fileName = fileName.substring(0, fileName.indexOf('.'));
            textView.setText(fileName);

            return convertView;
        }
    }

    public class OnImageGridClick implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent
                , View v, int position, long id) {

            //�����I�ȃC���e���g�̐���
            Intent intent = new Intent(Album_Main.this
                , Album_ShowDetails.class);

            //�C���e���g�ɓn���p�����[�^�̐ݒ�
            intent.putExtra("filepath"
                , fileList[position+pageOffset*gridSize].toString());

            //�A�N�e�B�r�e�B�̌Ăяo��
            startActivity(intent);
        }
    }

    //�_�C�A���O�̕\��
    public static void showDialog(Context context, String title, String message) {
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }});
        ad.create();
        ad.show();
    }
}