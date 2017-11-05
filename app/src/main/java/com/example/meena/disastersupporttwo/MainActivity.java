package com.example.meena.disastersupporttwo;

import android.Manifest;
import android.annotation.TargetApi;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Hospitals.CommunicationChannel, AdviceTab.CommunicationChannelTwo{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    URL url;
    String line;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (shouldAskPermissions()) {
            askPermissions();
        }

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.d("thisishard",permission+"");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
                */

        AudioThread audioThreader = new AudioThread();
        audioThreader.execute();








    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: Hospitals hospitals= new Hospitals();
                    return hospitals;
                /*case 1: GraphData graphData= new GraphData(series);
                    Bundle args = new Bundle();
                    args.putStringArrayList("Dates", dates2);
                    graphData.setArguments(args);
                    Log.d("called","case1 called");
                    return graphData;
                case 2: Timert timert= new Timert();
                    return timert;*/
                case 2: AdviceTab adviceTab= new AdviceTab();
                    return adviceTab;
                default: return new Fragment();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hospitals";
                case 1:
                    return "Shelter";
                case 2:
                    return "Our Advice";
            }
            return null;
        }
        //hi sruthi
        //hi sanjana
        //hi whats up sanj

    }

    public class AudioThread extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            DataOutputStream dos = null;
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1024; // 1024*1024 = 1MB.  212144 is a quarter MB.
            FileInputStream fileInputStream = null;
            //String path = context.getFilesDir().getAbsolutePath();
           /* try {
                //fileInputStream = new FileInputStream(new File("root/res/raw/hello.wav"));
                //Environment.getRootDirectory()
            }
            catch(FileNotFoundException fnfe){
                Log.d("thisishard",""+fnfe.toString());
            }*/
            try{
                URL url = new URL("https://speech.platform.bing.com/speech/recognition/conversation/cognitiveservices/v1?language=en-US");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //method
                conn.setRequestMethod("POST");
                conn.setChunkedStreamingMode(0);
                conn.setRequestProperty("Transfer-Encoding", "chunked");
                conn.setRequestProperty("Accept","application/json;text/xml");
                conn.setRequestProperty("Host","speech.platform.bing.com");
                //header
                conn.setRequestProperty("content-length", String.valueOf(fileInputStream.available()));
                conn.setRequestProperty("Content-Type", "audio/wav; codec=\"audio/pcm\"; samplerate=16000;");
                conn.setRequestProperty("Ocp-Apim-Subscription-Key","4b742e0d726944f2a1fd877935a67f23");
                //conn.setRequestProperty("Authorization", "Bearer "+ "4b742e0d726944f2a1fd877935a67f23" );
                conn.setReadTimeout(22222230);
                conn.setDoOutput(true);

                conn.connect();

                dos = new DataOutputStream(conn.getOutputStream());

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0)
                {
                    try {
                        dos.write(buffer, 0, bufferSize);
                    } catch (OutOfMemoryError oome) {

                        oome.printStackTrace();
                        fileInputStream.close();
                        throw new Exception("Out Of Memory!");
                    }
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                fileInputStream.close();
                dos.flush();
                dos.close();


                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                String decodedString;
                while ((decodedString = in.readLine()) != null) {
                    Log.d("thisishard",""+(decodedString));
                }

            }

            catch (Exception e)
            {
                Log.d("thisishard",""+(e.toString()));
            }
            /*try {
                Log.d("line reader", "skyfga isfhwajdfhbadksl");
                  url = new URL("https://speech.platform.bing.com/speech/recognition/dictation/cognitiveservices/v1?language=en-US&format=detailed");
               /* curl -v -X POST "https://speech.platform.bing.com/speech/recognition/dictation/cognitiveservices/v1?language=en-us&format=detailed"
                        -H "Transfer-Encoding: chunked" -H "Ocp-Apim-Subscription-Key: 98e2dfad93974691b8a871fe8c3d64e1" -H
                "Content-type: audio/wav; codec=audio/pcm; samplerate=16000" --data-binary @Desktop/hello-1.wav
                */
               /*HttpURLConnection client = null;
                try {
                    client = (HttpURLConnection) url.openConnection();
                    Log.d("line reader", client.toString());

                    //ArrayList<String> parameters = new ArrayList<String>();
                   // parameters.add(R.layout);

                    client.setRequestMethod("POST");
                    client.setRequestProperty("Ocp-Apim-Subscription-Key","98e2dfad93974691b8a871fe8c3d64e1");
                    client.setChunkedStreamingMode(0);
                    //client.setRequestProperty("Authorization","Bearer"+"98e2dfad93974691b8a871fe8c3d64e1");
                    client.setRequestProperty("Transfer-Encoding","chunked");
                    client.setRequestProperty("Content-type","audio/wav");
                    client.setRequestProperty("codec","audio/pcm");
                    client.setRequestProperty("samplerate","16000");
                   // client.setRequestProperty("Accept", "application/json;text/xml");
                   // client.setRequestProperty("Host","speech.platform.bing.com");

                    File file = new File(getFilesDir(),"hello-1.wav");
                    byte[] data = new byte[(int)file.length()];
                    FileOutputStream os = new FileOutputStream(file);
                    os.write(data);
                    os.close();

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                    int read;
                    byte[] buff = new byte[1024];
                    while ((read=in.read(buff))>0)
                    {
                        outputStream.write(buff, 0, read);

                    }
                    //outputStream.flush();
                    byte[] audioBytes = outputStream.toByteArray();

                    outputStream.writeTo(os);
                    Log.d("linereaders:FOS",""+os);

                    client.connect();


                   /* client.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                    writer.write(getQuery());
                    writer.flush();
                    writer.close();
                    os.close();


                    //client.
                   // client.setDoInput(true);

                    Log.d("line readers", client.toString());
                    //HttpResponseCache abc = new HttpResponseCache();

                    //Log.d("line readers", client.getHeaderField());
                    Log.d("line readers", client.getResponseMessage());
                    Log.d("line readers", client.getResponseCode()+"");
                   /* Log.d("line readers", client.toString());
                    Log.d("line readers", client.toString());
                    Log.d("line readers", client.toString());
                    Log.d("line readers", client.toString());
                    Log.d("line readers", client.toString());
                    Log.d("line readers", client.toString());
                    Log.d("line readers", client.toString());

                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String linea;
                        while ((linea = bufferedReader.readLine()) != null) {
                            stringBuilder.append(linea).append("\n");
                        }
                        bufferedReader.close();
                        line =  stringBuilder.toString();
                        Log.d("linereadersajfh",line+"");
                    }
                    finally{
                        client.disconnect();
                    }



                   /* InputStream inputStream = client.getInputStream();
                    Log.d("line reader", "1");
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    line="cfg";
                    line = br.readLine();
                    Log.d("line reader", line);

                }catch(Exception e){
                    Log.d("line reader",""+e);
                }

               // url = new URL("https://speech.platform.bing.com/speech/recognition/dictation/cognitiveservices/v1?language=en-US&format=detailed");
                //URLConnection urlConnection = url.openConnection();
                //Log.d("line reader", "skyfga isfhwajdfhbadksl");

               /* InputStream inputStream = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                line="cfg";
                line = br.readLine();*/

               // client.getIn
                /*OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
                //writeStream(outputPost);
                //InputStream inputStream = client.getInputStream();
                line = br.readLine();
                outputPost.flush();
                outputPost.close();

                Log.d("line reader","sgkafjdh");
            } catch (Exception e) {
            }*/

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
