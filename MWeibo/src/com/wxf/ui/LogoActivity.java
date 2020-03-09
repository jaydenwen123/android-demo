package com.wxf.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class LogoActivity extends Activity {

	private TextView textView;
	private ImageView imageView;
	private static int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题，
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏.其实只是把底部的操作的三个键去掉而已。
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		// 加载布局。
		setContentView(R.layout.activity_logo);
		imageView=(ImageView) findViewById(R.id.logo);
		textView = (TextView) findViewById(R.id.count);
		// 设置动画。来实现延时三秒进入到下一个界面。
		AlphaAnimation animator = new AlphaAnimation(0f, 1f);
		// 设置延时的时间
		animator.setDuration(4000);
		// 设置重复的次数
		animator.setRepeatCount(0);
		imageView.setAnimation(animator);
		// 设置监听器
		animator.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				// 设置在该界面上显示倒计时效果
				ValueAnimator aValueAnimator = ValueAnimator.ofInt(0, 1);
				aValueAnimator.setDuration(1000);
				aValueAnimator.setRepeatCount(4);
				aValueAnimator.addListener(new AnimatorListener() {

					public void onAnimationStart(Animator animation) {
						// TODO Auto-generated method stub
						i = 4;
					}

					public void onAnimationRepeat(Animator animation) {
						// TODO Auto-generated method stub
						textView.setText((i--) + "s");
					}

					public void onAnimationEnd(Animator animation) {
						// TODO Auto-generated method stub

					}

					public void onAnimationCancel(Animator animation) {
						// TODO Auto-generated method stub

					}
				});
				aValueAnimator.start();
			}
			
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LogoActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		/*animator.addListener(new AnimatorListener() {

			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				// 设置在该界面上显示倒计时效果
				ValueAnimator aValueAnimator = ValueAnimator.ofInt(0, 1);
				aValueAnimator.setDuration(1000);
				aValueAnimator.setRepeatCount(4);
				aValueAnimator.addListener(new AnimatorListener() {

					public void onAnimationStart(Animator animation) {
						// TODO Auto-generated method stub
						i = 4;
					}

					public void onAnimationRepeat(Animator animation) {
						// TODO Auto-generated method stub
						textView.setText((i--) + "s");
					}

					public void onAnimationEnd(Animator animation) {
						// TODO Auto-generated method stub

					}

					public void onAnimationCancel(Animator animation) {
						// TODO Auto-generated method stub

					}
				});
				aValueAnimator.start();
			}

			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				ValueAnimator valueAnimator = (ValueAnimator) animation;
				System.out.println("--->" + valueAnimator.getValues());
			}

			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(LogoActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}

			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}
		});*/
		// 启动动画。
		animator.start();

	}
}
