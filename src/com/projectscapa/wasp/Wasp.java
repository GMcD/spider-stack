/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.projectscapa.wasp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.apache.cordova.*;

public class Wasp extends DroidGap
{
	final Activity activity = this;
	private ProgressBar loadingProgress;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init(); 
        super.clearCache(); 
        
        // super.setIntegerProperty("splashscreen", R.drawable.splash);
        // this.setIntegerProperty("loadUrlTimeoutValue", 120000);
        
        // Load entry point local - not remote at http://devel.projectscapa.com/exercise.html - that is done in exercise.js
        super.loadUrl("file:///android_asset/www/exercise.html");

        // Add a native Layout for the Activity Loading - before jQuery Mobile becomes available
        View footer = View.inflate(getContext(), R.layout.progress, null);
        root.addView(footer);
        // Find the Progress Bar in the layout
        loadingProgress = (ProgressBar) findViewById(R.id.progressBar1);
        // Add a delegate to call back with the actual progress
        this.appView.setWebChromeClient(new WebChromeClient() {

                public void onProgressChanged(WebView view, int progress) { 
                    activity.setProgress(progress * 1000);
                    if(progress < 100 && loadingProgress.getVisibility() == ProgressBar.GONE) {
                    	loadingProgress.setVisibility(ProgressBar.VISIBLE);
                    }
                    loadingProgress.setProgress(progress);
                    if(progress == 100) {
                    	loadingProgress.setVisibility(ProgressBar.GONE);
                    }
                    Log.d("Progress", progress+"");
                 }
            });
    }
    
}

