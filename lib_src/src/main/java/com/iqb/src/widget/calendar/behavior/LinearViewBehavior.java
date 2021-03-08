package com.iqb.src.widget.calendar.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import com.iqb.src.widget.calendar.Utils;
import com.iqb.src.widget.calendar.view.MonthPager;


public class LinearViewBehavior extends CoordinatorLayout.Behavior<LinearLayout>{
    private int initOffset = -1;
    private int minOffset = -1;
    private Context context;
    private boolean initiated = false;
    private boolean hidingTop = false;
    private boolean showingTop = false;

    public LinearViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull LinearLayout child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        MonthPager monthPager = getMonthPager(parent);
        initMinOffsetAndInitOffset(parent, child, monthPager);
        return true;

    }

//    @Override
//    public boolean onLayoutChild(CoordinatorLayout parent,  RecyclerView child, int layoutDirection) {
//
//    }

    private void initMinOffsetAndInitOffset(CoordinatorLayout parent,
                                            LinearLayout child,
                                            MonthPager monthPager) {
        if (monthPager.getBottom() > 0 && initOffset == -1) {
            initOffset = monthPager.getViewHeight();
            saveTop(initOffset);
        }
        if (!initiated) {
            initOffset = monthPager.getViewHeight();
            saveTop(initOffset);
            initiated = true;
        }
        child.offsetTopAndBottom(Utils.loadTop());
        minOffset = getMonthPager(parent).getCellHeight();
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child,
                                       @NonNull View directTargetChild, @NonNull View target, int axes) {
        MonthPager monthPager = (MonthPager) coordinatorLayout.getChildAt(0);
        monthPager.setScrollable(false);
        boolean isVertical = (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;

        return isVertical;
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        child.setVerticalScrollBarEnabled(true);

        MonthPager monthPager = (MonthPager) coordinatorLayout.getChildAt(0);
        if (monthPager.getPageScrollState() != ViewPager.SCROLL_STATE_IDLE) {
            consumed[1] = dy;
            Toast.makeText(context, "loading month data", Toast.LENGTH_SHORT).show();
            return;
        }

        // 上滑，正在隐藏顶部的日历
        hidingTop = dy > 0 && child.getTop() <= initOffset
                && child.getTop() > getMonthPager(coordinatorLayout).getCellHeight();
        // 下滑，正在展示顶部的日历
        showingTop = dy < 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hidingTop || showingTop) {
            consumed[1] = Utils.scroll(child, dy,
                    getMonthPager(coordinatorLayout).getCellHeight(),
                    getMonthPager(coordinatorLayout).getViewHeight());
            saveTop(child.getTop());
        }
    }


    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child, @NonNull View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        MonthPager monthPager = (MonthPager) coordinatorLayout.getChildAt(0);
        monthPager.setScrollable(true);
        if (!Utils.isScrollToBottom()) {
            if (initOffset - Utils.loadTop() > Utils.getTouchSlop(context) && hidingTop) {
                Utils.scrollTo(coordinatorLayout, child, getMonthPager(coordinatorLayout).getCellHeight(), 500);
            } else {
                Utils.scrollTo(coordinatorLayout, child, getMonthPager(coordinatorLayout).getViewHeight(), 150);
            }
        } else {
            if (Utils.loadTop() - minOffset > Utils.getTouchSlop(context) && showingTop) {
                Utils.scrollTo(coordinatorLayout, child, getMonthPager(coordinatorLayout).getViewHeight(), 500);
            } else {
                Utils.scrollTo(coordinatorLayout, child, getMonthPager(coordinatorLayout).getCellHeight(), 150);
            }
        }
    }


    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }


    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child, @NonNull View target, float velocityX, float velocityY) {
        return hidingTop || showingTop;
    }


    private MonthPager getMonthPager(CoordinatorLayout coordinatorLayout) {
        return (MonthPager) coordinatorLayout.getChildAt(0);
    }

    private void saveTop(int top) {
        Utils.saveTop(top);
        if (Utils.loadTop() == initOffset) {
            Utils.setScrollToBottom(false);
        } else if (Utils.loadTop() == minOffset) {
            Utils.setScrollToBottom(true);
        }
    }
}
