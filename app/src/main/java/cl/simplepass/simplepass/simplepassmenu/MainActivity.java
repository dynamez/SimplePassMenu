package java.cl.simplepass.simplepass.simplepassmenu;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ListView list,list2;

    private String[] sistemas = {"Categoria 1", "Categoria 2", "Categoria 3", "Windows", "Mac OSX",
            "Google Chrome OS", "Debian", "Mandriva", "Solaris", "Unix"};
    private String[] categoria2 = {"Productos 1", "Productos 1", "iOS", "Windows", "Mac OSX",
            "Google Chrome OS", "Debian", "Mandriva", "Solaris", "Unix"};
    private String[] categoria3 = {"Productos 2", "Productos 2", "iOS", "Windows", "Mac OSX",
            "Google Chrome OS", "Debian", "Mandriva", "Solaris", "Unix"};

    CustomAdapter adapter;
    public  MainActivity CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomListView = this;
        setListData();
        Resources res =getResources();
        list = (ListView)findViewById(R.id.listview);
        list2 = (ListView)findViewById(R.id.ListView2);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, R.layout.list_black_text, sistemas);
        final ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this, R.layout.list_black_text, categoria2);
        final ArrayAdapter<String> adaptador3 = new ArrayAdapter<>(this, R.layout.list_black_text, categoria3);
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list2.setAdapter( adapter );

        list.setAdapter(adaptador);

        //list2.setAdapter(adaptador2);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                String test = arg0.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position + test, Toast.LENGTH_SHORT).show();

                if(test.equals("Categoria 1")) list2.setAdapter(adaptador2);
              if(test.equals("Categoria 2")) list2.setAdapter(adapter);
            }

        });
        registerForContextMenu(list2);
    }
    /****** Function to set data in ArrayList *************/
    public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            final ListModel sched = new ListModel();

            /******* Firstly take data in model object ******/
            sched.setCompanyName("Producto "+i);
            sched.setImage("image"+i);
            sched.setUrl("Valor: $5.000");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // We know that each row in the adapter is a Map


        menu.setHeaderTitle("Options for " + "test");
        menu.add(1, 1, 1, "Comprar");
        menu.add(1, 2, 2, "Delete");

    }
    /*****************  This function used by adapter ****************/
//    public void onItemClick(int mPosition)
//    {
//        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
//
//
//        // SHOW ALERT
//
//        Toast.makeText(CustomListView,
//                ""+tempValues.getCompanyName()
//                        +"Image:"+tempValues.getImage()+"Url:"+tempValues.getUrl(),Toast.LENGTH_LONG).show();
//    }
}
