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
