package com.yxw.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private ImageView iv1;
    private ImageView iv2;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private int animCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initUI();
        anim();
    }

    private void anim() {
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
    }

    private void initUI() {
        rl2 = findViewById(R.id.rl2);
        rl3 = findViewById(R.id.rl3);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        rl2.setVisibility(View.INVISIBLE);
        rl3.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv1:
                if (animCount == 0) {
                    if (!flag1) {
                        animIn(rl2);
                    } else if (flag1 && !flag2) {
                        animOut(rl2, 0);
                    } else if (flag1 && flag2) {
                        animOut(rl2, 100);
                        animOut(rl3, 0);
                        flag2 = false;
                    }
                    flag1 = !flag1;
                }
                break;
            case R.id.iv2:
                if (animCount == 0) {
                    if (!flag2) {
                        animIn(rl3);
                    } else {
                        animOut(rl3, 0);
                    }
                    flag2 = !flag2;
                }
                break;
        }
    }

    /**
     * 出去的动画
     *
     * @param rl          播放动画的布局
     * @param startOffset 延时播放
     */
    private void animOut(RelativeLayout rl, long startOffset) {
        RotateAnimation ra;
        ra = new RotateAnimation(0, -180,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        ra.setDuration(500);
        //动画延时播放
        ra.setStartOffset(startOffset);
        ra.setAnimationListener(new MyAnimationListener());
        rl.startAnimation(ra);
        rl.setVisibility(View.INVISIBLE);
    }

    /**
     * 进来的动画
     *
     * @param rl 播放动画的布局
     */
    private void animIn(RelativeLayout rl) {
        RotateAnimation ra;
        rl.setVisibility(View.VISIBLE);
        ra = new RotateAnimation(-180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        ra.setDuration(500);
        ra.setAnimationListener(new MyAnimationListener());
        rl.startAnimation(ra);
    }

    class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            animCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            animCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
}
