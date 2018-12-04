package br.edu.unibratec.pgm.projeto.jogomemoria;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.unibratec.pgm.jogomemoria.basicas.Partida;

public class RankingAdapter extends BaseAdapter{
	
	private List<Partida> partidas;
	private Context ctx;
	
	public RankingAdapter(Context ctx, List<Partida> partidas)
	{
		this.partidas = partidas;
		this.ctx = ctx;
	}
	
	@Override
	public int getCount() {
		return this.partidas.size();
	}

	@Override
	public Object getItem(int position) {
		return this.partidas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.partidas.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		Partida p = this.partidas.get(position);
		
		if(convertView == null)
		{
			convertView = LayoutInflater.from(ctx).inflate(R.layout.ranking_item, null);
			
			holder = new ViewHolder();
			
			holder.txt_player = (TextView)convertView.findViewById(R.id.player_lb);
			holder.txt_pontuacao = (TextView)convertView.findViewById(R.id.pontuacao_lb);
			holder.txt_data = (TextView)convertView.findViewById(R.id.data_lb);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.txt_player.setText(p.getUsuarioNome());
		holder.txt_pontuacao.setText(p.getPontuacao());
		holder.txt_data.setText(p.getData());
		
		
		return convertView;
	}
	
	static class ViewHolder
	{
		TextView txt_player;
		TextView txt_pontuacao;
		TextView txt_data;
	}

}
