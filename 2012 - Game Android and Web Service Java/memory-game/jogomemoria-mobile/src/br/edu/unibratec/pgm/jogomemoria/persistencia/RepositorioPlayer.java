package br.edu.unibratec.pgm.jogomemoria.persistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.unibratec.pgm.jogomemoria.basicas.Partida;

public class RepositorioPlayer {

	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private final Context context;

	public static final String SCRIPT_DB_DELETE = "DROP TABLE IF EXISTS partida";
	public static final String SCRIPT_DB_CREATE = "create table partida(_id interger primary key, nome text not null, pontuacao text, data text not null)";

	public RepositorioPlayer (Context ctx)
	{
		context = ctx;
		dbHelper = new SQLiteHelper(ctx, "db.JogoVelha", 1, SCRIPT_DB_CREATE, SCRIPT_DB_DELETE);
	}

	public long inserir(Partida partida)
	{
		ContentValues cv = new ContentValues();

		
		
		/*if(buscarPlayerPorLogin(player.getLogin()))
		{
			Toast.makeText(context, R.string.msgLoginExistente, Toast.LENGTH_SHORT).show();
		}*/
		//else
		{
			cv.put("nome", partida.getUsuarioNome());			
			cv.put("pontuacao", partida.getPontuacao());
			cv.put("data", partida.getData());
		}	

		db = dbHelper.getWritableDatabase();	

		long id = db.insert("partida", null, cv);
	    partida.setId(id);
		db.close();
		//Toast.makeText(context, R.string.msgRegSuccess, Toast.LENGTH_SHORT).show();

		return id;
	}

	/*public long atualizar(Player player)
	{
		ContentValues cv = new ContentValues();

		cv.put("nome", player.getNome());
		cv.put("senha", player.getSenha());
		cv.put("login", player.getLogin());
		cv.put("pontuacao", player.getPontuacao());

		db = dbHelper.getWritableDatabase();		
		long rows = db.update("player", cv, "_id = ?", new String[]{ String.valueOf(player.getId())});
		db.close();		
		return rows;
	}

	public long excluir(int id)
	{
		db = dbHelper.getWritableDatabase();
		int rows = db.delete("player", "_id = ?", new String[]{String.valueOf(id)});
		return rows;
	}*/
	
	/*public List<Player> buscarPlayerPorNome(String nome)
	{
		List<Player> lista = new ArrayList<Player>();
		String[] columns = new String[]{"_id", "nome", "senha", "login", "pontuacao"};
		String[] args = new String[]{nome+"%"};
		
		db = dbHelper.getWritableDatabase();
		Cursor c = db.query("player", columns, "nome like ?", args, null, null, "nome");
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			Player player = fillPlayer(c);
			lista.add(player);
			c.moveToNext();
		}
		c.close();
		db.close();
		return lista;
	}*/
	
	/*public Boolean buscarPlayerPorLogin(String login)
	{
		//List<Player> lista = new ArrayList<Player>();
		String[] columns = new String[]{"_id", "nome", "senha", "login", "pontuacao"};
		String[] args = new String[]{login+"%"};
		
		db = dbHelper.getWritableDatabase();
		Cursor c = db.query("player", columns, "login like ?", args, null, null, "login");
		
		if(c.getCount() > 1) //usu�rio inexistente
		{
			c.close();
		}
		else

		db.close();
		return false;
	}*/

	
	public List<Partida> gerarRanking()
	{
		List<Partida> lista = new ArrayList<Partida>();
				
		db = dbHelper.getWritableDatabase();
		Cursor c =  db.rawQuery("select _id, nome, pontuacao, data from partida", null);
		
		c.moveToFirst();
		while(c.moveToNext())
		{
			Partida partida = fillPartida(c);
			lista.add(partida);
		}
		c.close();
		db.close();
		return lista;
	}
	
	
	/*public Player processarLogin(String login, String senha)
	{
		//List<Player> lista = new ArrayList<Player>();
		Player player = null;
				
		db = dbHelper.getWritableDatabase();
		Cursor c =  db.rawQuery("select _id, login, senha, nome, pontuacao from player where login =? and senha=?", new String[]{login, senha});
		
		if(c.getCount() < 1) //usu�rio inexistente
		{
			c.close();		
		}		
		
		while(c.moveToNext())
		{
			player = fillPlayerLogin(c);
			//lista.add(player);
		}
		c.close();
		db.close();
		return player;
	}
	
	public long atualizarPontuacao(Player player)
	{
		ContentValues cv = new ContentValues();

		/*cv.put("nome", player.getNome());
		cv.put("senha", player.getSenha());
		cv.put("login", player.getLogin());
		cv.put("pontuacao", player.getPontuacao());

		db = dbHelper.getWritableDatabase();		
		long rows = db.update("player", cv, "_id = ?", new String[]{ String.valueOf(player.getId())});
		db.close();		
		return rows;
	}*/
	
	
	private Partida fillPartida(Cursor c)
	{
		Partida partida = new Partida();
		
		partida.setId(c.getLong(0));
		partida.setUsuarioNome(c.getString(1));
		partida.setPontuacao(c.getString(2));
		partida.setData(c.getString(3));
		return partida;
	}

	public Context getContext() {
		return context;
	}
	
	/*private Player fillPlayerLogin(Cursor c)
	{
		Player player = new Player();
		
		player.setId((int)c.getLong(0));
		player.setLogin(c.getString(1));
		player.setSenha(c.getString(2));
		player.setNome(c.getString(3));
		player.setPontuacao(c.getInt(4));
		
		
		return player;
	}*/


}
