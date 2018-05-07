package com.example.betuldemirci.gout.Helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private int height;

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigationViewBehavior() {
        super();
        height = 10;
    }


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
        height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       BottomNavigationView child, @NonNull
                                               View directTargetChild, @NonNull View target,
                                       int axes, int type)
    {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child,
                               @NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed,
                               @ViewCompat.NestedScrollType int type)
    {
        child.setTranslationY(Math.max(0f, Math.min(Float.parseFloat(String.valueOf(child.getHeight())), child.getTranslationY()+dyConsumed)));

//        if (dyConsumed > 0) {
//            slideDown(child);
//        } else if (dyConsumed < 0) {
//            slideUp(child);
//        }
    }

    private void slideUp(BottomNavigationView child) {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(BottomNavigationView child) {
        child.clearAnimation();
        child.animate().translationY(height).setDuration(200);
    }
}