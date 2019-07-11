package util;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class LogWriter {
	Writer fstream = null;
	BufferedWriter out = null;

    public LogWriter( String paramString ) {
    	
    	    try {
				fstream = new OutputStreamWriter(new FileOutputStream(paramString), StandardCharsets.UTF_8);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    public void logEntry( String paramString ) {
    	try {
			this.out.write(paramString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void close() {
        try {
			this.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
