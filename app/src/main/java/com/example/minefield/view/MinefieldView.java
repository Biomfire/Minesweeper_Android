package com.example.minefield.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.minefield.R;
import com.example.minefield.controller.FieldController;


public class MinefieldView extends View {
    FieldController field;
    private float fieldCenterPointX = 0;
    private float fieldCenterPointY = 0;
    Paint paint = new Paint();
    Bitmap mine;
    Bitmap flag;
    Bitmap covered;
    Bitmap[] numbers;
    int mineFieldSizeX = 5;
    int mineFieldSizeY = 8;
    int fieldSizeX;
    int fieldSizeY;
    float scale = 0.25f;
    int scaleX = 0;
    int scaleY = 0;
    Matrix drawMatrix = new Matrix();
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    public void setGameEndListener(GameEndListener gameEndListener) {
        this.gameEndListener = gameEndListener;
    }

    GameEndListener gameEndListener;
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float lastFocusX;
        float lastFocusY;
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            lastFocusX = detector.getFocusX();
            lastFocusY = detector.getFocusY();
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Matrix transformationMatrix = new Matrix();
            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();
            transformationMatrix.postTranslate(-focusX, -focusY);
            transformationMatrix.postScale(detector.getScaleFactor(), detector.getScaleFactor());
            transformationMatrix.postTranslate(focusX, focusY);
            drawMatrix.postConcat(transformationMatrix);
            invalidate();
            return true;
        }
    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            drawMatrix.postTranslate(-distanceX, -distanceY);
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            uncoverCoordinate(e.getX(), e.getY());
            invalidate();
            if(field.hasEnded() && gameEndListener != null)
                gameEndListener.endGame();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            flagCoordinate(e.getX(), e.getY());
            invalidate();
            if(field.hasEnded() && gameEndListener != null)
                gameEndListener.endGame();
            super.onLongPress(e);
        }
    }
    public MinefieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mine = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
        flag = BitmapFactory.decodeResource(getResources(), R.drawable.flagged);
        covered = BitmapFactory.decodeResource(getResources(), R.drawable.facingdown);
        numbers = new Bitmap[9];
        numbers[0] = BitmapFactory.decodeResource(getResources(), R.drawable.number_0);
        numbers[1] = BitmapFactory.decodeResource(getResources(), R.drawable.number_1);
        numbers[2] = BitmapFactory.decodeResource(getResources(), R.drawable.number_2);
        numbers[3] = BitmapFactory.decodeResource(getResources(), R.drawable.number_3);
        numbers[4] = BitmapFactory.decodeResource(getResources(), R.drawable.number_4);
        numbers[5] = BitmapFactory.decodeResource(getResources(), R.drawable.number_5);
        numbers[6] = BitmapFactory.decodeResource(getResources(), R.drawable.number_6);
        numbers[7] = BitmapFactory.decodeResource(getResources(), R.drawable.number_7);
        numbers[8] = BitmapFactory.decodeResource(getResources(), R.drawable.number_8);
        fieldSizeX = covered.getWidth();
        fieldSizeY = covered.getHeight();
        fieldCenterPointX = fieldSizeX * mineFieldSizeX * scale / 2;
        fieldCenterPointY = fieldSizeY * mineFieldSizeY * scale / 2;
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //canvas.translate(getWidth() / 2.0f - fieldCenterPointX, getHeight() / 2.0f - fieldCenterPointY);
       // canvas.scale(scale, scale, scaleX, scaleY);
        canvas.concat(drawMatrix);
        for (int i = 0; i < mineFieldSizeX; ++i) {
            for (int j = 0; j < mineFieldSizeY; ++j) {
                drawField(i, j, canvas);
            }
        }
        canvas.restore();
    }

    public void setField(FieldController field) {
        this.field = field;
    }

    void drawField(int x, int y, Canvas canvas) {
        FieldController.FieldDrawType type = field.getField(x, y);
        Rect destinationRectangle = new Rect(x * fieldSizeX, y * fieldSizeY, (x + 1) * fieldSizeX, (y + 1) * fieldSizeY);
        switch (type) {
            case MINE:
                canvas.drawBitmap(numbers[0], null, destinationRectangle, paint);
                canvas.drawBitmap(mine, null, destinationRectangle, paint);
                break;
            case FLAGGED:
                canvas.drawBitmap(flag, null, destinationRectangle, paint);
                break;
            case COVERED:
                canvas.drawBitmap(covered, null, destinationRectangle, paint);
                break;
            case ZERO:
                canvas.drawBitmap(numbers[0], null, destinationRectangle, paint);
                break;
            case ONE:
                canvas.drawBitmap(numbers[1], null, destinationRectangle, paint);
                break;
            case TWO:
                canvas.drawBitmap(numbers[2], null, destinationRectangle, paint);
                break;
            case THREE:
                canvas.drawBitmap(numbers[3], null, destinationRectangle, paint);
                break;
            case FOUR:
                canvas.drawBitmap(numbers[4], null, destinationRectangle, paint);
                break;
            case FIVE:
                canvas.drawBitmap(numbers[5], null, destinationRectangle, paint);
                break;
            case SIX:
                canvas.drawBitmap(numbers[6], null, destinationRectangle, paint);
                break;
            case SEVEN:
                canvas.drawBitmap(numbers[7], null, destinationRectangle, paint);
                break;
            case EIGHT:
                canvas.drawBitmap(numbers[8], null, destinationRectangle, paint);
                break;
            case OTHER:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = scaleGestureDetector.onTouchEvent(event);
        retVal = gestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }


    private void uncoverCoordinate(float tX, float tY) {
        Pair<Integer, Integer> worldcords = getFieldIndexFromViewCoordinates(tX,tY);
        if(worldcords != null) {
            field.uncoverField(worldcords.first, worldcords.second);
        }
    }

    private void flagCoordinate(float tX, float tY) {
        Pair<Integer, Integer> worldcords = getFieldIndexFromViewCoordinates(tX,tY);
        if(worldcords != null) {
            field.flagField(worldcords.first, worldcords.second);
        }
    }

    public Pair<Integer, Integer> getFieldIndexFromViewCoordinates(float tX, float tY) {
        float[] points = {tX, tY};
        float transformationX = getWidth() / 2.0f - fieldCenterPointX;
        float transformationY = getHeight() / 2.0f - fieldCenterPointY;
        float worldCoordinatesX = tX / scale - transformationX / scale;
        float worldCoordinatesY = tY / scale - transformationY / scale;
        if (worldCoordinatesX >= 0 && worldCoordinatesX <= fieldSizeX * mineFieldSizeX && worldCoordinatesY >= 0 && worldCoordinatesY <= fieldSizeY * mineFieldSizeY) {
            int x = (int) worldCoordinatesX / fieldSizeX;
            int y = (int) worldCoordinatesY / fieldSizeY;
            return new Pair<>(x, y);
        }
        else{
            return null;
        }
    }
}
