package com.asuper.aidldemo.actitvity;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @version create time：2015-2-5 下午8:29:39
 * 
 */

public class RoomGesturer implements GestureDetector.OnGestureListener {

	/** 滑动的最小距离 */
	private static final int FLING_MIN_DISTANCE = 200;
	/** 滑动的差异方向偏差距离，如当前是上下滑动，则左右滑动的距离大于此值则作废操作 */
	private static final int FLING_DIFFER_DISTANCE = 300;
	/** 滑动的最小速度,像素/秒 */
	private static final int FLING_MIN_VELOCITY = 0;

	private GestureListener gestureListener;
	private GestureDetector gestureDetector;
	private Context context;

	public interface GestureListener {
		boolean onSingleTapUp();

		void onLeftFling();

		void onRightFling();

		void onTopFling();

		void onDownFling();
	}

	public RoomGesturer(Context c, GestureListener g) {
		this.context = c;
		this.gestureListener = g;
		gestureDetector = new GestureDetector(context, this);
	}
	
	public GestureListener getGestureListener()
	{
		return gestureListener;
	}
	
	/**
	 * @return the gestureDetector
	 */
	public GestureDetector getGestureDetector() {
		return this.gestureDetector;
	}

	/**
	 * @param gestureDetector
	 *            the gestureDetector to set
	 */
	public void setGestureDetector(GestureDetector gestureDetector) {
		this.gestureDetector = gestureDetector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.
	 * MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onShowPress(android.view
	 * .MotionEvent)
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.
	 * view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		if (gestureListener != null)
			return gestureListener.onSingleTapUp();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onScroll(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (e1 == null || e2 == null)
			return false;
		// float xdet = e2.getX() - e1.getX();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onLongPress(android.view
	 * .MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (e1 == null || e2 == null)
			return false;
		float e1e2X = e1.getX() - e2.getX();
		float e2e1X = e2.getX() - e1.getX();
		float e1e2Y = e1.getY() - e2.getY();
		float e2e1Y = e2.getY() - e1.getY();
		// Log.i("", "GestureDetector zxd onFling " + e1e2X + "," + e2e1X + ","
		// + e1e2Y + "," + e2e1Y + "," + velocityX + "," + velocityY);
		if (e1e2X > FLING_MIN_DISTANCE && Math.abs(e1e2Y) < FLING_DIFFER_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// Fling left
			if (gestureListener != null)
				gestureListener.onLeftFling();
			return true;
		} else if (e2e1X > FLING_MIN_DISTANCE && Math.abs(e2e1Y) < FLING_DIFFER_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// Fling right
			if (gestureListener != null)
				gestureListener.onRightFling();
			return true;
		} else if (e1e2Y > FLING_MIN_DISTANCE && Math.abs(e1e2X) < FLING_DIFFER_DISTANCE
				&& Math.abs(velocityY) > FLING_MIN_VELOCITY) {
			// Fling top
			if (gestureListener != null)
				gestureListener.onTopFling();
			return true;
		} else if (e2e1Y > FLING_MIN_DISTANCE && Math.abs(e2e1X) < FLING_DIFFER_DISTANCE
				&& Math.abs(velocityY) > FLING_MIN_VELOCITY) {
			// Fling bottom
			if (gestureListener != null)
				gestureListener.onDownFling();
			return true;
		}
		return false;
	}

}
