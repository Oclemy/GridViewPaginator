# Android GridView Pagination


How to page through given dataset example.

## Overview
- We have an arraylist of data
- We'll split into pages
- Then use next/previous pagination to page through the data


## Demos

- Project Structure

![](/Camposha/demos/Project-Structure.PNG)

## Paginator class
Now we shall have a simple paginator class as below.It shall be responsible fr paging our data appropriately.
It has a simpe method called generatePage() that takes page number and generates the page data.Of course in this case we are simply generating data dynamically at runtime.

```java
package com.tutorials.hp.gridviewpaginator;

import java.util.ArrayList;

/**
 * Created by Oclemy on 9/7/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Paginator {

    public static final int TOTAL_NUM_ITEMS=52;
    public static final int ITEMS_PER_PAGE=10;
    public static final int ITEMS_REMAINING=TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
    public static final int LAST_PAGE=TOTAL_NUM_ITEMS/ITEMS_PER_PAGE;


    public ArrayList<String> generatePage(int currentPage)
    {
        int startItem=currentPage*ITEMS_PER_PAGE+1;
        int numOfData=ITEMS_PER_PAGE;

        ArrayList<String> pageData=new ArrayList<>();



        if (currentPage==LAST_PAGE && ITEMS_REMAINING>0)
        {
            for (int i=startItem;i<startItem+ITEMS_REMAINING;i++)
            {
                pageData.add("Number "+i);
            }
        }else
        {
            for (int i=startItem;i<startItem+numOfData;i++)
            {
                pageData.add("Number "+i);
            }
        }


        return pageData;
    }
}


```

## MainActivity
In the MainActivity our job is now simple.Simply to enable and disable the next and previous buttons.Of course we also keep track of the current page position.If we are in the first page we disable the previous button while in the last page we disable the next button.

```java
package com.tutorials.hp.gridviewpaginator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    Button nextBtn,prevBtn;
    Paginator p=new Paginator();
    private int totalPages=Paginator.TOTAL_NUM_ITEMS/Paginator.ITEMS_PER_PAGE;
    private int currentPage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv= (GridView) findViewById(R.id.gv);
        nextBtn= (Button) findViewById(R.id.nextBtn);
        prevBtn= (Button) findViewById(R.id.prevBtn);
        prevBtn.setEnabled(false);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, p.generatePage(currentPage).get(i), Toast.LENGTH_SHORT).show();
            }
        });


        gv.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,p.generatePage(currentPage)));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPage+=1;
               // enableDisableButtons();
                gv.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,p.generatePage(currentPage)));
                toggleButtons();

            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage-=1;

                gv.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,p.generatePage(currentPage)));

                 toggleButtons();
            }
        });


    }

    private void toggleButtons()
    {
        if(currentPage==totalPages)
        {
            nextBtn.setEnabled(false);
            prevBtn.setEnabled(true);
        }else
        if(currentPage==0)
        {
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(true);
        }else
        if(currentPage>=1 && currentPage<=5)
        {
            nextBtn.setEnabled(true);
            prevBtn.setEnabled(true);
        }

    }
}


```

## Layout
In this case this is going to be our layout.Its this simple.We have a GridView on top pf two buttons,the next and previous buttons.

```xml
 <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <GridView
            android:id="@+id/gv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:numColumns="2" />

        <LinearLayout
            android:orientation="horizontal"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content">
            <Button
                android:text="Previous"
                android:id="@+id/prevBtn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <Button
                android:text="Next"
                android:id="@+id/nextBtn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
        </LinearLayout>

    </LinearLayout>

```



- GridView Pagination

![](/Camposha/demos/GridView-Paginator.PNG)

-

## How to Run.
Clone the project into your android studio or download and extract the project and import to your android studio.

## More
Visit http://camposha.info for more examples like these.

Author :
Oclemy,cheers. http://camposha.info
