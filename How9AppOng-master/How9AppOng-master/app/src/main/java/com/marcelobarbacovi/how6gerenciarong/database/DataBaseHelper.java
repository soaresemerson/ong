package com.marcelobarbacovi.how6gerenciarong.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.alunos.Aluno;
import com.marcelobarbacovi.how6gerenciarong.parceiros.Parceiros;
import com.marcelobarbacovi.how6gerenciarong.voluntarios.Voluntario;

public class DataBaseHelper extends SQLiteOpenHelper {

    // criar os atributos do banco de dados e das tabelas
    private static final String DATABASE_NAME = "ong";
    private static final String TABLE_VOLUNTARIO = "voluntario";
    private static final String TABLE_PARCEIRO = "parceiro";
    private static final String TABLE_ALUNO = "aluno";

// Criação das tabelas do Banco de dados

    private static final String CREATE_TABLE_VOULUNTARIO = "CREATE TABLE " + TABLE_VOLUNTARIO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "especializacao VARCHAR(100)," +
            "telefone VARCHAR(15)," +
            "funcao vARCHAR(100));";

    private static final String CREATE_TABLE_PARCEIRO = "CREATE TABLE " + TABLE_PARCEIRO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "telefone VARCHAR(100)," +
            "email VARCHAR(100)," +
            "observacao vARCHAR(100));";


    private static final String CREATE_TABLE_ALUNO = "CREATE TABLE " + TABLE_ALUNO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "responsavel VARCHAR(100)," +
            "telefone VARCHAR(100)," +
            "cep VARCHAR(12)," +
            "endereco VARCHAR(100)," +
            "numero VARCHAR(6)," +
            "bairro VARCHAR(100)," +
            "cidade VARCHAR(100)," +
            "estado VARCHAR(100)," +
            "observacao vARCHAR(100));";


    private static final String DROP_TABLE_VOLUNTARIO = "DROP TABLE IF EXISTS " + TABLE_VOLUNTARIO;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VOULUNTARIO);
        db.execSQL(CREATE_TABLE_PARCEIRO);
        db.execSQL(CREATE_TABLE_ALUNO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE_VOLUNTARIO);
        onCreate(db);

    }

    /* Início CRUD Voluntario
    *  metodo de inserção da tabela voluntario
    * */
    public long createVoluntario(Voluntario v) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", v.getName());
        cv.put("especializacao", v.getEspecializacao());
        cv.put("telefone", v.getTelefone());
        cv.put("funcao", v.getFuncao());
        long id = db.insert(TABLE_VOLUNTARIO, null, cv);
        db.close();
        return id;
    }
 // metodo de alteração dos dados da tabela voluntario do Bd
    public long updateVoluntario(Voluntario v) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", v.getName());
        cv.put("especializacao", v.getEspecializacao());
        cv.put("telefone", v.getTelefone());
        cv.put("funcao", v.getFuncao());
        long id = db.update(TABLE_VOLUNTARIO, cv,
                "_id = ?", new String[]{String.valueOf(v.getId())});
        db.close();
        return id;
    }
  //metodo deletar dados da tabela voluntarios do Bd
    public long deleteVoluntario(Voluntario v) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_VOLUNTARIO, "_id = ?",
                new String[]{String.valueOf(v.getId())});
        db.close();
        return id;
    }
// metodo para recuperar os dados da tabela voluntarios
    public void getAllVoluntario(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "telefone",};
        Cursor data = db.query(TABLE_VOLUNTARIO, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarVoluntario, R.id.textViewNomeListarVoluntario,
                R.id.textViewTelefoneListarVoluntario};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.voluntario_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
/* metodo para recuperar os dados  clicados eselecionados no ListWiew
    e gravar dentro das variaveis da classe voluntario
 */
    public Voluntario getByIdVoluntario(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "especializacao", "telefone", "funcao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_VOLUNTARIO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Voluntario v = new Voluntario();
        v.setId(data.getInt(0));
        v.setName(data.getString(1));
        v.setEspecializacao(data.getString(2));
        v.setTelefone(data.getString(3));
        v.setFuncao(data.getString(4));
        data.close();
        db.close();
        return v;
    }
    /* Fim CRUD voluntario

    /* Início CRUD Aluno
        metodo inserção na tabela aluno
     */

    public long createAluno(Aluno a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", a.getNome());
        cv.put("responsavel", a.getResponsavel());
        cv.put("telefone", a.getTelefone());
        cv.put("cep", a.getCep());
        cv.put("endereco", a.getRua());
        cv.put("numero", a.getNumero());
        cv.put("bairro", a.getBairro());
        cv.put("cidade", a.getCidade());
        cv.put("estado", a.getEstado());
        cv.put("observacao", a.getObservao());


        long id = db.insert(TABLE_ALUNO, null, cv);
        db.close();
        return id;
    }

    // metodo de alteração dos dados tabela alunos
    public long updateAluno(Aluno a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", a.getNome());
        cv.put("responsavel", a.getResponsavel());
        cv.put("telefone", a.getTelefone());
        cv.put("cep", a.getCep());
        cv.put("endereco", a.getRua());
        cv.put("numero", a.getNumero());
        cv.put("bairro", a.getBairro());
        cv.put("cidade", a.getCidade());
        cv.put("estado", a.getEstado());
        cv.put("observacao", a.getObservao());
        long id = db.update(TABLE_ALUNO, cv,
                "_id = ?", new String[]{String.valueOf(a.getId())});
        db.close();
        return id;
    }
  // metodo para deletar dados tabela Alunos
    public long deleteAluno(Aluno a) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_ALUNO, "_id = ?",
                new String[]{String.valueOf(a.getId())});
        db.close();
        return id;
    }
    // metodo para recuperar dados tabela Alunos e gravar dentro dos campos do Listview adaptados
    public void getAllAluno(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "telefone",};
        Cursor data = db.query(TABLE_ALUNO, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarAluno, R.id.textViewNomeListarAluno,
                R.id.textViewTelefoneListarAluno};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.aluno_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* metodo para recuperar os dados  clicados e selecionados no ListWiew
     e gravar dentro das variaveis da classe alunos
  */
    public Aluno getByIdAluno(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "responsavel", "telefone", "cep", "endereco", "numero","bairro","cidade","estado", "observacao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_ALUNO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Aluno a = new Aluno();
        a.setId(data.getInt(0));
        a.setNome(data.getString(1));
        a.setResponsavel(data.getString(2));
        a.setTelefone(data.getString(3));
        a.setCep(data.getString(4));
        a.setRua(data.getString(5));
        a.setNumero(data.getString(6));
        a.setBairro(data.getString(7));
        a.setCidade(data.getString(8));
        a.setEstado(data.getString(9));
        a.setObservao(data.getString(10));
        data.close();
        db.close();
        return a;
    }
    /* Fim CRUD Alunos */

    /* Início CRUD Parceiros
    * metodo para inserção na tabela parceiros
    *  */
    public long createParceiro(Parceiros p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("telefone", p.getTelefone());
        cv.put("email", p.getEmail());
        cv.put("observacao", p.getObservacao());
        long id = db.insert(TABLE_PARCEIRO, null, cv);
        db.close();
        return id;
    }
// metodo de alteração na tabela parceiros
    public long updateParceiro(Parceiros p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("telefone", p.getTelefone());
        cv.put("email", p.getEmail());
        cv.put("observacao", p.getObservacao());
        long id = db.update(TABLE_PARCEIRO, cv,
                "_id = ?", new String[]{String.valueOf(p.getId())});
        db.close();
        return id;
    }
// metodo delete dos dados tabela parceiros
    public long deleteParceiro(Parceiros p) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_PARCEIRO, "_id = ?",
                new String[]{String.valueOf(p.getId())});
        db.close();
        return id;
    }
 // recupera dados do banco de dados e insere no listview adaptado dos parceiros
    public void getAllParceiro(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "telefone",};
        Cursor data = db.query(TABLE_PARCEIRO, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarParceiro, R.id.textViewNomeListarParceiro,
                R.id.textViewTelefoneListarParceiro};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.parceiro_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* metodo para recuperar os dados  clicados e selecionados no ListWiew
        e gravar dentro das variaveis da classe Parceiros
     */
    public Parceiros getByIdParceiro(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "telefone", "email", "observacao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_PARCEIRO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Parceiros p = new Parceiros();
        p.setId(data.getInt(0));
        p.setNome(data.getString(1));
        p.setTelefone(data.getString(2));
        p.setEmail(data.getString(3));
        p.setObservacao(data.getString(4));
        data.close();
        db.close();
        return p;
    }
    /* Fim CRUD aluno */
}



