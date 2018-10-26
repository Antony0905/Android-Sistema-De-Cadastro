package usuario.app.sistemadecadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import usuario.app.sistemadecadastro.Registro;

public class Update extends SQLiteOpenHelper {

    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/usuario.app.sistemacadastro/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;

    public Update(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean insertPessoa(Registro r) {
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put("NOME", r.getNome());
            cv.put("ENDERECO", r.getEndereco());
            cv.put("TELEFONE", r.getTelefone());
            db.insert(TABELA_PESSOA, null, cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean updatePessoa(Registro r) {
        openDB();
        try {
            String where = "ID = '" + r.getId() + "'";
            ContentValues cv = new ContentValues();
            cv.put("NOME", r.getNome());
            cv.put("ENDERECO", r.getEndereco());
            cv.put("TELEFONE", r.getTelefone());
            db.update(TABELA_PESSOA, cv, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    private void openDB() {
        if (!db.isOpen()) {
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }
}
