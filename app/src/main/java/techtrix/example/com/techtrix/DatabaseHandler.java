package techtrix.example.com.techtrix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static android.content.ContentValues.TAG;

/**
 * Created by risha on 01-02-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String databasename="Message";
    private static final String tableMessage = "MessageTable";
    public static final int databaseversion=1;
    private static final String noteId = "id";
    private static final String noteMessageDesc = "messageDesc";
    private static final String noteEventName = "eventName";
    public DatabaseHandler(Context context){
        super(context,databasename,null,databaseversion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabase="CREATE TABLE " + tableMessage + "("+
                noteId+ " INT PRIMARY KEY," + noteEventName + " TEXT,"
                + noteMessageDesc + " TEXT" + ")";
        db.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + databasename);
        // Create tables again
        onCreate(db);
    }
    boolean addMessage(NotifyMessage note) {
        Boolean f=false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cur=db.query(tableMessage,new String[]{noteId},noteId+"=?",new String[]{note.getId()+""},null,null,null);
        cur.moveToFirst();
        if(cur.getCount()==0) {
            values.put(noteId,note.getId());
            values.put(noteEventName, note.getEventName());
            values.put(noteMessageDesc, note.getMessDesc());
            db.insert(tableMessage, null, values);
            db.close();
            f = true;
        }
        return f;
    }
    ArrayList<NotifyMessage> getAllMessage(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+tableMessage,null);
        cur.moveToFirst();
        ArrayList<NotifyMessage> noteMess=new ArrayList<NotifyMessage>();
        if(cur.moveToFirst()) {
            do {
                NotifyMessage note = new NotifyMessage();
                note.setId(cur.getInt(0));
                note.setEventName(cur.getString(1));
                note.setMessDesc(cur.getString(2));
                noteMess.add(note);
            } while (cur.moveToNext());
        }
        Collections.reverse(noteMess);
        return noteMess;
    }
    void delete()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+tableMessage);
    }
}
