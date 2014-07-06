package android.graph;

import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

public class GestureListener implements OnTouchListener {

    /**
         * downX, downY: Instance variable of float.
         */
    private float downX, downY;

    /**
         * discard: Instance variable of boolean.
         */
    private boolean discard = false;

    /**
     * Number 40.
     */
    public static final int FORTY = 40;

    /**
     * Number 90.
     */
    public static final int NINTY = 90;

    /**
         * onTouch performs an action when touched.
         *
         * @param v : the view the touch event has been dispatched to.
         * @param event the MotionEvent object containing full info on.
         * @return boolean true if listener consumed event, false otherwise.
         */
    public final boolean onTouch(final View v, final MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            downX = event.getX();
            downY = event.getY();
            discard = false;
            return !(v instanceof ListView || v instanceof GridView);
        case MotionEvent.ACTION_MOVE:
            float upX = event.getX();
            float upY = event.getY();
            float deltaX = downX - upX;
            float deltaY = downY - upY;
            if (Math.abs(deltaY) > FORTY) {
                discard = true;
            }
            if (!discard && Math.abs(deltaX) > Math.abs(deltaY)
                    && Math.abs(deltaX) > NINTY) {
                if (deltaX < 0) {
                    onSwipeRight();
                    return true;
                }
                if (deltaX > 0) {
                    onSwipeLeft();
                    return true;
                }
            }
            break;
        case MotionEvent.ACTION_UP:
        onTouchUp(event);
            return false;
        default:
            break;
        }
        return false;
    }

    /**
         * onTouchUp performs an action when touched upon.
         *
         * @param event MotionEvent object containing full info.
         */
    public void onTouchUp(final MotionEvent event) {
        // TODO Auto-generated method stub

    }

    /**
         * onSwipeLeft performs an action when swiped left.
         */
    public void onSwipeLeft() {
        // TODO Auto-generated method stub

    }

    /**
         * onSwipeRight performs an action when swiped right.
         */
    public void onSwipeRight() {
        // TODO Auto-generated method stub

    }
}
