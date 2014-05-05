package com.nw.helloglass;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    	Intent intent;
        switch (item.getItemId()) {
        
        	case R.id.action_call:
        		intent = new Intent(Intent.ACTION_DIAL);
        	    intent.setData(Uri.parse("tel:" + "0000000000"));
        	    //startActivity(intent);
        	    if (intent.resolveActivity(getPackageManager()) != null) {
        	        startActivity(intent);
        	    }
        	    return true;
            
        	case R.id.action_directions:
        		intent = new Intent(Intent.ACTION_VIEW);
        		intent.setData(Uri.parse("google.navigation:q=San+Diego"));
        	    
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
