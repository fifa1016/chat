package com.wang.chat.view.custom;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by wang on 16-8-8.
 */

public class TestLayout extends FrameLayout {
    private static final String TAG = "TestLayout";
    ViewDragHelper viewDragHelper;

    public TestLayout(Context context) {
        super(context);
        init();
    }

    public TestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new DragCallback());
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            viewDragHelper.cancel();
            return false;
        }
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    Toast toast = null;

    private void showToast(String str) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), str, Toast.LENGTH_SHORT);
        toast.show();
    }

    class DragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == childDrawer;
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            Log.d(TAG, "onEdgeTouched: ");
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            Log.d(TAG, "onEdgeDragStarted: ");
            super.onEdgeDragStarted(edgeFlags, pointerId);
            if (childDrawer.getVisibility() == GONE) {
                childDrawer.setVisibility(VISIBLE);
                int width = childDrawer.getWidth();
                int height = childDrawer.getHeight();
                halfWidth = width / 2;
                childDrawer.layout(-width, 0, 0, height);
            }
            viewDragHelper.captureChildView(childDrawer, pointerId);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d(TAG, "clampViewPositionHorizontal: left=" + left + ", dx=" + dx);
            final int leftBound = -childDrawer.getWidth();
            final int rightBound = 0;
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            if (childDrawer == releasedChild) {
                int left = childDrawer.getLeft();

                // by animation
                if (left < halfWidth) {
                    childDrawer.animate()
                            .x(-1.0f * childDrawer.getWidth())
                            .start();
                } else {
                    childDrawer.animate()
                            .x(0)
                            .start();
                }

                // by viewDragHelper
//                int newLeft;
//                if (left < halfWidth) {
//                    newLeft = -childDrawer.getWidth();
//                } else {
//                    newLeft = 0;
//                }
//                if (viewDragHelper.smoothSlideViewTo(childDrawer, newLeft, childDrawer.getTop())) {
//                    Log.d(TAG, "onViewReleased: smooth success");
//                    ViewCompat.postInvalidateOnAnimation(childDrawer);
//                } else {
//                    Log.d(TAG, "onViewReleased: smooth failed");
//
//                }
            }
        }

    }//callback

    View childContent, childDrawer;
    int halfWidth;

    @Override
    protected void onFinishInflate() {
        childContent = getChildAt(0);
        childDrawer = getChildAt(1);
        super.onFinishInflate();
    }
}
