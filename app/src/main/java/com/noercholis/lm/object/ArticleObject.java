package com.noercholis.lm.object;

/**
 * Created by nubuntu on 11/06/2015.
 */
public class ArticleObject {
    public String title;
    public String content;
    public int id;
    public ArticleObject(int id,String title,String content){
        this.id=id;
        this.title=title;
        this.content=content;
    }
    public void setTitle(String s){
        this.title=s;
    }
    public void setContent(String s){
        this.content=s;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public int getId(){
        return id;
    }
}
