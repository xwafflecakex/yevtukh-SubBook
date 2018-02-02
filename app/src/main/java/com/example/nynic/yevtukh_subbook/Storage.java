package com.example.nynic.yevtukh_subbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;

import android.util.Log;
import android.content.Context;



/**
 * Created by nynic on 2018-02-01.
 */

public class Storage {

    private static final String FILENAME = "subscriptions.sav";


    public class testFile {

        Context fileContext;

        public testFile(Context fileContext) {
            this.fileContext = fileContext;
        }

        public void writeFile() {
            try {
                FileOutputStream os = fileContext.getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                //os.write(inventoryHeap.getBytes()); // writes the bytes
                os.close();
                System.out.println("Created file\n");
            } catch (IOException e) {
                System.out.print("Write Exception\n");
            }
        }
    }
}

