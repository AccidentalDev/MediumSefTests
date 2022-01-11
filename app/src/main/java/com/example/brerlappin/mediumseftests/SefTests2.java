package com.example.brerlappin.mediumseftests;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class SefTests2 extends ListActivity {
    static final String[] CHOICES_LIST = new String[]{
            "Abrir Pagina",
            "Abrir Contactos",
            "Abrir Marcador",
            "Busqueda de google",
            "Comando de voz"
    };
    static final String[] SEARCH_LIST = new String[]{
            "Alexis Texas",
            "Amanda Cherundolo",
            "Anastasia Leonova",
            "Anel Rodriguez",
            "Angela Yeo",
            "Arely Hernandez Montenegro",
            "Aria Giovanni",
            "Ava Koor",
            "Ava Sofie",
            "Bethany Lily",
            "Bria Myles",
            "Briclynn",
            "Carriejune Anne Bowlby",
            "Cat Donnelly",
            "Chelsea Kline",
            "Cindy Phillips",
            "Colette Nelson",
            "Hana Bunny",
            "Lada Lyumos",
            "Makachan",
            "PattieCosplay",
            "serinide",
            "Dai Oliveira",
            "Dani Daniels",
            "Deadly-Neurotoxin",
            "Ekaterina Katyukha Kuznetsova",
            "Elena Seiple",
            "Emma Hartley",
            "Estefania Trejo",
            "Gianna Michaels",
            "Hayley McNeff",
            "Jayden Jaymes",
            "Jaye Rose",
            "Jennifer Rish",
            "Johanna Svan",
            "Kim Yeon-Ah",
            "Kortney Olson",
            "Kristina Moser",
            "Kristina Nicole Mendoza",
            "Linda Durbesson",
            "BACK"
    };

    ArrayList<String> mainDataList;
    ArrayAdapter<String> myOptionsList;
    AdapterView.OnItemClickListener mainClickListener;
    AdapterView.OnItemClickListener searchClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sef_tests2);

        if(myOptionsList == null) {
            mainDataList = new ArrayList(5);

            //populateStringList(mainDataList, CHOICES_LIST);
            Collections.addAll(mainDataList, CHOICES_LIST);

            myOptionsList = new ArrayAdapter<String>(this, R.layout.activity_sef_tests2, mainDataList);
        }
        setListAdapter(myOptionsList);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setTextFilterEnabled(true);

        if(searchClickListener == null) {
            searchClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int option, long l) {
                    if(option == SEARCH_LIST.length-1){
                        //We change the content of the adapter for the main menu
                        myOptionsList.clear();
                        myOptionsList.addAll(CHOICES_LIST);
                        getListView().setOnItemClickListener(mainClickListener);
                    }else {
                        Toast.makeText(getApplicationContext(), "Searching for " + SEARCH_LIST[option], Toast.LENGTH_SHORT).show();
                        Intent sexySearch = new Intent(Intent.ACTION_WEB_SEARCH);
                        sexySearch.putExtra(SearchManager.QUERY, SEARCH_LIST[option]);
                        startActivity(sexySearch);
                    }
                }
            };
        }

        if(mainClickListener == null) {
            mainClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int option, long l) {
                    switch (option) {
                        case 0:
                            Toast.makeText(getApplicationContext(), "Opcion 1 seleccionada!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.3dtotal.com")));
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Opcion 2 seleccionada!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/")));
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Opcion 3 seleccionada!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:3311608385")));
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Opcion 4 seleccionada!", Toast.LENGTH_SHORT).show();
                            //We switch the content of the adapter
                            myOptionsList.clear();
                            myOptionsList.addAll(SEARCH_LIST);
                            getListView().setOnItemClickListener(searchClickListener);
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "Opcion 5 seleccionada!", Toast.LENGTH_SHORT).show();
                            try {
                                startActivity(new Intent(Intent.ACTION_VOICE_COMMAND));
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "ERROR al solicitar el servicio de voz!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Opcion no reconocida!!", Toast.LENGTH_SHORT).show();
                    }
                }
            };
        }

        getListView().setOnItemClickListener(mainClickListener);
    }

    //CFCU- Not used anymore, but it is a good example for creating a function with generic parameters
    private <T> void populateStringList(ArrayList<T> myList, T staticArray[]){
        for(int i=0; i<staticArray.length; i++){
            myList.add(staticArray[i]);
        }
    }
}
