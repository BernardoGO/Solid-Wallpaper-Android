package com.rane.solidwallpaper;

import java.io.IOException;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
	LinearLayout mainLay;
	ctrlWallpapers walls = new ctrlWallpapers();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainLay = (LinearLayout)findViewById(R.id.mainLay);
        //setWallpaper(R.drawable.red);
        
        for(int x=0; x< walls.listWalls.size(); x++)
        {
        	addButton("#"+walls.listWalls.get(x).HEX, walls.listWalls.get(x).ID);
        	
        }
    }
	
	public void addButton(String name, final int id)
	{
		Button bt = new Button(this);
        bt.setText(name);
        bt.setBackgroundResource(id);
        bt.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 100));
        bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setWallpaper(id);
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    	builder.setTitle("Solid Wallpapers");
		    	builder.setMessage("Wallpaper changed.");
		    	builder.setPositiveButton("Ok", null);
		    	AlertDialog dialog = builder.show();
			}
		});
        mainLay.addView(bt);
		
	}
    
    public void setWallpaper(int resId)
    {
    	try {
    		WallpaperManager.getInstance(getApplicationContext()).setResource(resId);
    		
    		//Bitmap bmp = Bitmap.createBitmap( 1, 1, Bitmap.Config.RGB_565);
    		//bmp.setPixel(0, 0, Color.RED);
    		//WallpaperManager.getInstance(getApplicationContext()).setBitmap(bmp);
    		} catch (IOException e){
    		e.printStackTrace();
    		}
    	
    }


    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	private void onMarketLaunch(String url) {
		
		Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
				"market://details?id=%s", url)));
		startActivity(donate);
	}
	
	public void share()
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.rane.solidwallpaper");
		startActivity(Intent.createChooser(intent, "Share with"));
	}
	
	private void showHelp()
	{
/*
        final String message = this.getString(R.string.help);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    	builder.setTitle("SMS Combo  -  Help");
    	builder.setMessage(message);
    	builder.setPositiveButton("Back", null);
    	AlertDialog dialog = builder.show();
	*/
	}
	
	private void onDevLaunch(String url) {
		
		Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
				"market://search?q=pub:%s", url)));
		startActivity(donate);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_rate:    onMarketLaunch("com.rane.solidwallpaper");
	                            break;
	                            
	        //case R.id.menu_help:    showHelp();
            //break;
	        case R.id.menu_moreapps:	onDevLaunch("Ranebord");
	        break; 
	        case R.id.menu_share:	share();
	        break;

	    }
	    return true;
	}
}