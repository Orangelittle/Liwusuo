package lws.com.liwushuo.view.fragment.shouye;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import lws.com.liwushuo.R;

/**
 * Created by King on 2016/10/12.
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    private int offset = 0;
    private int startOffset = 0;
    private int endOffset = 0;
    private Context context;

    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        startOffset = 1;
        endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) - toolbar.getHeight();
        offset += dyConsumed;
        TextView title = (TextView) coordinatorLayout.findViewById(R.id.gonglue_title);
        if (offset <= startOffset) {  //alpha为0
            toolbar.getBackground().setAlpha(0);
            title.setTextColor(Color.argb(0,255, 255, 255));

        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
            float precent = (float) (offset - startOffset) / endOffset;
            int alpha = Math.round(precent * 255);
            toolbar.getBackground().setAlpha(alpha);
            title.setTextColor(Color.argb(alpha, 255, 255, 255));

        } else if (offset >= endOffset) {  //alpha为255
            toolbar.getBackground().setAlpha(255);
            title.setTextColor(Color.argb(255, 255, 255, 255));

        }
    }

}
