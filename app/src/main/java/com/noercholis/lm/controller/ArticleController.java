package com.noercholis.lm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.noercholis.lib.*;
import com.noercholis.lib.Object;
import com.noercholis.lm.object.ArticleObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nubuntu on 11/06/2015.
 */
public class ArticleController extends App {
    public ArticleController(Context ctx, Config config) {
        super(ctx, config);
    }
    public Cursor getAllCursor(int cat){
        String q="select * from articles where category=1 order by noid desc limit 0,10";
        Cursor rows=this.db.setQuery(q).loadObject();
        return rows;
    }
    public List<ArticleObject> getAllList(int cat){
        Cursor rows=this.getAllCursor(cat);
        ArrayList<ArticleObject> list=new ArrayList<>();
        for(Cursor row:CursorUtils.iterate(rows)){
            list.add(new ArticleObject(
                    row.getInt(row.getColumnIndex("_id")),
                    this.decode(row.getString(row.getColumnIndex("title"))),
                    this.decode(row.getString(row.getColumnIndex("content")))
            ));
        }
        rows.close();
        return list;
    }
    public void getUpdate(){
        String q="select max(noid) from articles";
        int lastid=this.db.setQuery(q).loadResultInt();
        String param="cmd=getArticle&id=" + lastid;
        final App self=this;
        this.doJSON(param, new Callback.jsonobject() {
            @Override
            public void run(JSONObject o) throws JSONException {
                if(o.getInt("count")>0){
                    JSONArray rows = o.getJSONArray("data");
                    Object.each(rows, new Callback.jsonobject() {
                        @Override
                        public void run(JSONObject row) throws JSONException {
                            ContentValues cv=new ContentValues();
                            cv.put("noid",row.getInt("id"));
                            cv.put("tanggal",row.getString("tanggal"));
                            cv.put("title",self.encode(row.getString("title")));
                            cv.put("content",self.encode(row.getString("content")));
                            cv.put("image",self.encode(row.getString("image")));
                            cv.put("category", row.getInt("category"));
                            long result=db.insert("articles", cv);
                            Console.log(Long.toString(result));
                        }
                    });
                }
            }
        });
    }
}
