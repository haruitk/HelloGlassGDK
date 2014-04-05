package com.nw.helloglass;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;
import com.google.android.glass.timeline.TimelineManager;

public class HelloService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private static final String CARD_TAG = "helloGlass";
	private HelloDrawer mHelloDrawer;

    private TimelineManager mTimelineManager;
    private LiveCard mLiveCard;

    @Override
    public void onCreate() {
        super.onCreate();
        mTimelineManager = TimelineManager.from(this);
        mHelloDrawer = new HelloDrawer(this);
    }
       

    

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	
    	 if (mLiveCard == null) {
             mLiveCard = mTimelineManager.createLiveCard(CARD_TAG);
             Intent menuIntent = new Intent(this, MenuActivity.class);
             mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(mHelloDrawer);
             
             menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
             mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
             mLiveCard.publish(PublishMode.REVEAL);
         } else {
             // TODO(alainv): Jump to the LiveCard when API is available.
         }
       

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
    	
    	if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        
        super.onDestroy();
    }

}
