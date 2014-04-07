package com.nw.helloglass;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends Activity {

	@Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.helloglassmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
        
        	case R.id.action_call:
        		Intent intent = new Intent(Intent.ACTION_DIAL);
        	    intent.setData(Uri.parse("tel:" + "0000000000"));
        	    if (intent.resolveActivity(getPackageManager()) != null) {
        	        startActivity(intent);
        		return true;
            
        	case R.id.action_directions:
        		Intent intent = new Intent(Intent.ACTION_VIEW);
        		Double myLatitude = 44.433106;
        		Double myLongitude = 26.103687;
        		String labelLocation = "Jorgesys @ Bucharest";
        		Uri geoLocation = Uri.parse("geo:<" + myLatitude  + ">,<" + myLongitude + ">?q=<" + myLatitude  + ">,<" + myLongitude + ">(" + labelLocation + ")");
        		intent.setData(geoLocation);
        	    
        	    if (intent.resolveActivity(getPackageManager()) != null) {
        	        startActivity(intent);
        	    }
                return true;
        	
        	case R.id.action_close:
                stopService(new Intent(this, HelloService.class));
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the activity.
        finish();
    }

}
