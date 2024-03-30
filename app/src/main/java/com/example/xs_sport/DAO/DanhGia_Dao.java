package com.example.xs_sport.DAO;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.xs_sport.R;

public class DanhGia_Dao extends LinearLayout {

    private ImageView[] star;
    private int xephang = 0;

    public DanhGia_Dao(Context context) {
        super(context);
        init();
    }

    public DanhGia_Dao(Context context,@Nullable AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.danhgia, this);
        star = new ImageView[]{
                findViewById(R.id.star1),
                findViewById(R.id.star2),
                findViewById(R.id.star3),
                findViewById(R.id.star4),
                findViewById(R.id.star5)
        };
        for (int i=0; i<star.length; i++){
            final int position=i;
            star[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRating(position+1);
                }
            });
        }
    }

    public void setRating(int rating){
        if(rating<0||rating>star.length)
            return;
        this.xephang=rating;
        for(int i=0;i<star.length;i++){
            if(i<rating){
                star[i].setImageResource(R.drawable.sao1);
            }else{
                star[i].setImageResource(R.drawable.sao2);
            }
        }
    }

    public int getRating(){
        return xephang;
    }
}
