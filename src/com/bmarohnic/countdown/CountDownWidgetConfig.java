package com.bmarohnic.countdown;

import java.util.Calendar;

import com.bmarohnic.countdown.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.Toast;

public class CountDownWidgetConfig extends Activity {

	final String DEMURI = "http://www.whitehouse.gov";
	final String REPURI = "http://www.gop.com";
	
	Spinner politicalPartySpinner;
	ArrayAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        
        politicalPartySpinner = (Spinner) this.findViewById(R.id.politicalPartySpinner);
		
		adapter = ArrayAdapter.createFromResource(this, R.array.politicalPartyArray, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		politicalPartySpinner.setAdapter(adapter);
		
    }
    
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.count_down_widget, menu);
        return true;
    }


	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null)
		{
			int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			
			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID)
			{
				String tempString = politicalPartySpinner.getSelectedItem().toString();
				
				Toast.makeText(this, "You selected " + tempString, Toast.LENGTH_LONG).show();
				
				Calendar conventionDate = Calendar.getInstance();
				
				conventionDate.set(2014, 0, 1);
				
				Calendar now = Calendar.getInstance();
				
				long millisUntilConvention = conventionDate.getTimeInMillis() - now.getTimeInMillis();
				long daysUntilConvention = millisUntilConvention / 86400000;
				
				
				RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
				
				if (daysUntilConvention <=0)
				{
					rv.setTextViewText(R.id.daysLeft, "The fun has already started");
				}
				else
				{
					rv.setTextViewText(R.id.daysLeft, daysUntilConvention + " days until the fun begins.");
				}
				
				Intent buttonIntent;
				 
				
				if (tempString.equals("Democrat"))
				{
					buttonIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.DEMURI));
				}
				else
				{
					buttonIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.REPURI));
				}
				
				PendingIntent pi = PendingIntent.getActivity(this, 0/*requestCode*/, buttonIntent, 0/*flags*/);
				rv.setOnClickPendingIntent(R.id.button1, pi);
				
				AppWidgetManager.getInstance(this).updateAppWidget(widgetId, rv);
				
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
				setResult(RESULT_OK, resultValue);
				finish();
			}
		}
		
	}

    
}
