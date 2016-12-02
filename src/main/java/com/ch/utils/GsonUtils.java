package com.ch.utils;

import com.ch.utils.gson.ext.IgnoreStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonUtils {
	private static Gson instance = null;
	public synchronized static Gson getGson(){
        if(instance == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.addSerializationExclusionStrategy(new IgnoreStrategy());
            instance = builder.create();
        }
        return instance;
	}


}
