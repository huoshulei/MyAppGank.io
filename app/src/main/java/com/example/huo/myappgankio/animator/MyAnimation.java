package com.example.huo.myappgankio.animator;


import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by huo on 24/06/16.
 */

public class MyAnimation extends Animation {
    float centerX;//X轴 旋转坐标
    float centerY;//Y轴旋转坐标
    int duration;//动画时长
    Camera mCamera = new Camera();


    public MyAnimation(int duration, float centerX, float centerY) {
        this.duration = duration;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(duration);
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        mCamera.save();
        //三个参数分别是top距离 底部距离 两端距离
        mCamera.translate(0, 0, 80.f - 80.f * interpolatedTime);
//        mCamera.rotateX(360 * interpolatedTime);//x轴旋转
        mCamera.rotateY(-45 * interpolatedTime);//y轴旋转
//        mCamera.rotateZ(360 * interpolatedTime);//z轴旋转
        Matrix matrix = t.getMatrix();
        mCamera.getMatrix(matrix);
        mCamera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}
