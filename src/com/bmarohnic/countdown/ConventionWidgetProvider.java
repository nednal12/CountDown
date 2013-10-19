package com.bmarohnic.countdown;

import java.util.Calendar;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class ConventionWidgetProvider extends AppWidgetProvider {

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
Calendar conventionDate = Calendar.getInstance();
		
		conventionDate.set(2014, 0, 1);
		
		Calendar now = Calendar.getInstance();
		
		long millisUntilConvention = conventionDate.getTimeInMillis() - now.getTimeInMillis();
		long daysUntilConvention = millisUntilConvention / 86400000;
		
		
		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		if (daysUntilConvention <=0)
		{
			rv.setTextViewText(R.id.daysLeft, "The fun has already started");
		}
		else
		{
			rv.setTextViewText(R.id.daysLeft, daysUntilConvention + " days until the fun begins.");
		}
		
		appWidgetManager.updateAppWidget(appWidgetIds, rv);
	}

	
	
}
