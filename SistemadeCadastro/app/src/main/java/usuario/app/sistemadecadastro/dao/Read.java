package usuario.app.sistemadecadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

import usuario.app.sistemadecadastro.Registro;

public class Read extends SQLiteOpenHelper {

    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/usuario.app.sistemacadastro/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;

    public Read(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Registro> getPessoas() {
        openDB();
        ArrayList<Registro> rArray = new ArrayList<>();
        String getPessoas = "SELECT * FROM " + TABELA_PESSOA;

        try {
            Cursor c = db.rawQuery(getPessoas, null);
            if (c.moveToFirst()) {
                do {
                    Registro r = new Registro();
                    r.setId(Integer.parseInt(c.getString(0)));
                    r.setNome(c.getString(1));
                    r.setEndereco(c.getString(2));
                    r.setTelefone(c.getString(3));
                    rArray.add(r);
                } while (c.moveToNext());
                c.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rArray;
    }


    private void openDB() {
        if (!db.isOpen()) {
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }
}
