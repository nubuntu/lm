package com.noercholis.lm.service;
import android.os.Message;
import android.util.Log;

import com.noercholis.lib.*;


public class ArticleService extends com.noercholis.lib.Service {

	@Override
	public int timeInterval() {
		return 10000;
	}

	@Override
	public void doAsyncTask(Message message, App app) {
		/**
		app.doJSON("cmd=getArticle&id=", new Callback.jsonobject() {
			@Override
			public void run(JSONObject o) throws JSONException {
				Console.log(o.toString());

			}
		});
		 **/
		/**
		app.getSession();
		String[] res = app.req("getMsg","null=null","#");
		if(res[0].equals("true")){
			app.notif(res[1]);
			app.getMsgL("Arminadroid:" + res[1]);
		}
		 **/
		Log.d("Service", "Article Service Running...");
		//app.alert("","ddsdsds");
		//ShowToast.makeText(getApplicationContext(), "Article Service Run...", ShowToast.LENGTH_LONG).show();
	}
}
