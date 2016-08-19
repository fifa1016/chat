package com.wang.chat.test;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by wang on 16-8-8.
 */

public class DrawerLayout extends RelativeLayout {
    private static final String TAG = "test/DrawerLayout";
    ViewDragHelper viewDragHelper;

    public DrawerLayout(Context context) {
        super(context);
        init();
    }

    public DrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout: ");
        super.onLayout(changed, l, t, r, b);

        if (childDrawer != null) {
            int width = childDrawer.getWidth();
            halfWidth = width / 2;
            int height = childDrawer.getHeight();
            childDrawer.layout(-width, 0, 0, height);
        }
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
            Log.d(TAG, "tryCaptureView: drawer=" + (child == childDrawer)
                    + ", content=" + (child == childContent));


            if (drawerShowing) {

            }
            return child == childDrawer;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            Log.d(TAG, "onEdgeDragStarted: ");
            super.onEdgeDragStarted(edgeFlags, pointerId);
            viewDragHelper.captureChildView(childDrawer, pointerId);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d(TAG, "clampViewPositionHorizontal: left=" + left + ", dx=" + dx + ", right=" + child.getRight());
            final int leftBound = -childDrawer.getWidth();
            final int rightBound = 0;
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            return newLeft;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            if (childDrawer == releasedChild) {

                int right = childDrawer.getRight();

                Log.d(TAG, "onViewReleased: right=" + right + ", halfWidth=" + halfWidth);

                int newLeft;
                if (right < halfWidth) {
                    newLeft = -childDrawer.getWidth();
                    drawerShowing = false;
                } else {
                    newLeft = 0;
                    drawerShowing = true;
                }

                slideDrawer(newLeft, childDrawer.getTop());

            }
        }

    }//callback

    private boolean drawerShowing = false;

    private void slideDrawer(int left, int top) {
//        viewDragHelper.settleCapturedViewAt(left, top);
//        invalidate();

        if (viewDragHelper.smoothSlideViewTo(childDrawer, left, top)) {
            Log.d(TAG, "onViewReleased: smooth success, newLeft=" + left);
            ViewCompat.postInvalidateOnAnimation(this);
            postInvalidate();
        } else {
            Log.d(TAG, "onViewReleased: smooth failed");
        }
    }

    View childContent, childDrawer;
    int halfWidth;

    @Override
    protected void onFinishInflate() {
        Log.d(TAG, "onFinishInflate: ");
        childContent = getChildAt(0);
        childDrawer = getChildAt(1);
        super.onFinishInflate();
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
