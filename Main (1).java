package com.exmaple.colorchanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GridColorActivity extends AppCompatActivity {

    /*
    creating field for GridView
     */
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_color);

        /*
        Set title of action bar as Palette Activity
         */
        setTitle("Palette Activity");

        /*
        Getting reference of grid view.
         */
        gridView = findViewById(R.id.grid);

        /*
        Initializing color name and codes as array
        will be used to pass as argument to CustomGridAdapter class
         */
        final String[] colorName = {"BLUE","RED","YELLOW","LIME","GRAY","AQUA","Fuchsia","Teal","Green","Cyan","Magenta","Maroon"};
        final int[] colorCode = {R.color.blue,R.color.red,R.color.yellow,R.color.lime,R.color.gray,R.color.aqua,R.color.fuchsia,R.color.teal,R.color.green,R.color.cyan,R.color.magenta,R.color.maroon};

        /*
        creating object og custom grid adapter
         */
        CustomGridAdapter adapter = new CustomGridAdapter(GridColorActivity.this,colorName,colorCode);

        /*
        setting custom gredd adapter as adpter for grid view
         */
        gridView.setAdapter(adapter);

        /*
        Setting onItem click listener on grid view

         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*

                when any item clicked then start canvas activity by passing color code and color name at that position.
                 */
                Intent intent = new Intent(GridColorActivity.this,CanvasColorActivity.class);
                intent.putExtra("colorCode",colorCode[i]);
                intent.putExtra("colorName",colorName[i]);
                startActivity(intent);
            }
        });
    }
}
activity_grid_color.xml (layout for above GridColorAcitivity.java) :

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".GridColorActivity" >

<!-- adding a grid view -->

    <GridView
        android:numColumns="3"
        android:gravity="center"
        android:stretchMode="columnWidth"
        android:adjustViewBounds="true"
        android:layout_margin="8dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/grid"/>

</LinearLayout>
CustomGridAdapter.java (custom adapter for grid view) :

package com.exmaple.colorchanger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/*
Adapter Class for showing custom grid view
*/

public class CustomGridAdapter extends BaseAdapter{
    private Context mContext;
    private final String[] colorName;
    private final int[] colorCode;
/*
Getting colorName and colorCode from Grid Color Activity as Array.
 */
    /*
    Constructor for this class to initialize fields
     */
    public CustomGridAdapter(Context c,String[] colorName,int[] colorCode ) {
        mContext = c;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    /*
    return total number of element in array as total grid
     */
    @Override
    public int getCount() {
        return colorName.length;
    }

    /*
    return item object at specific position (not required in this app.)
     */
    @Override
    public Object getItem(int position) {
        return null;
    }
/*
return item id at specific position (not required for this app)
 */
    @Override
    public long getItemId(int position) {
        return 0;
    }
/*
Return view (inflate a layout) that we created in layout folder named grid_item.xml

 */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            textView.setText(colorName[position]);
            textView.setBackgroundColor(mContext.getResources().getColor(colorCode[position]));
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
grid_item.xml (view for each grid item) :

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <!-- this view is used for each grid that we populate programmatically -->

    <TextView
        android:id="@+id/grid_text"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:text="TEXT"
        android:padding="40dp"
        android:textStyle="bold"/>

</LinearLayout>
Second Activity :

CanvasColorActivity.java

package com.exmaple.colorchanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CanvasColorActivity extends AppCompatActivity {

    /*
    Fields view object declaration
     */
    private RelativeLayout rootLayout;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_color);

        /*
        initializing view objects by id
         */
        rootLayout = findViewById(R.id.canvas_color_root);
        textView = findViewById(R.id.canvas_color_text);

        /*
        getting intent values from previous activity (activity that start this activity)
         */
        Intent intent = getIntent();
        int backgroundColor = intent.getIntExtra("colorCode",0);
        String colorName = intent.getStringExtra("colorName");

        /*
        setting root layout background color and color name
         */

        rootLayout.setBackgroundColor(getResources().getColor(backgroundColor));
        textView.setText(colorName);

    }
}
activity_canvas_color.xml (layout file for above CanvasColorActivity):

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".CanvasColorActivity"
    android:id="@+id/canvas_color_root"
    android:orientation="vertical">

    <!-- Shows text view in center of layout name of color-->
    <TextView
        android:id="@+id/canvas_color_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:textAlignment="center"
        android:textSize="15sp"
        android:textColor="@android:color/black"
        android:gravity="center_horizontal" />

</RelativeLayout>
colors.xml (this xml file contain color code for each color that will display on grid view)

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#6200EE</color>
    <color name="colorPrimaryDark">#3F51B5</color>
    <color name="colorAccent">#03DAC5</color>

    <!-- Setting color name and value for color grid activity-->
    <color name="red">#F44336</color>
    <color name="blue">#3F51B5</color>
    <color name="yellow">#FFEB3B</color>
    <color name="lime">#00FF00</color>
    <color name="gray">#808080</color>
    <color name="aqua">#00FFFF</color>
    <color name="teal">#009688</color>
    <color name="green">#4CAF50</color>
    <color name="magenta">#FF1DCE</color>
    <color name="cyan">#00FFFF</color>
    <color name="fuchsia">#FF00FF</color>
    <color name="maroon">#800000</color>

</resources>
AndroidManifest.xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.exmaple.colorchanger">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:label="@string/app_name"
        android:theme="@style/AppTheme">
        <activity android:name=".CanvasColorActivity"
            android:label="Canvas Activity"/>
        <activity
            android:name=".GridColorActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>