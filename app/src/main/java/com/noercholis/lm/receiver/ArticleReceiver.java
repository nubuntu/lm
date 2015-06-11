package com.noercholis.lm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.noercholis.lm.service.ArticleService;

public class ArticleReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) {
		 if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){     
		context.startService(new Intent(context, ArticleService.class));//start my your service here
             Log.d("Receiver", "Article Receiver has started!");
			 Toast.makeText(context, "Article Receiver Running...", Toast.LENGTH_LONG).show();

		 }
	}
}
