package br.edu.unibratec.pgm.jogomemoria.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import br.edu.unibratec.pgm.jogomemoria.basicas.Partida;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.BackgroundComponent;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Card;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Sprite;
import br.edu.unibratec.pgm.jogomemoria.managers.Jogada;
import br.edu.unibratec.pgm.jogomemoria.managers.RankingWSAccess;
import br.edu.unibratec.pgm.jogomemoria.managers.util.DataUtil;
import br.edu.unibratec.pgm.jogomemoria.persistencia.RepositorioPlayer;
import br.edu.unibratec.pgm.projeto.jogomemoria.R;

@SuppressLint("ViewConstructor")
public class MainGameView extends GameView {

	private int [] CartasReferencias;
	private int [] cartasOrdenadas; 
	private int [] ordemAleatoria;
	private BackgroundComponent painelJogo;
	private String player_label;
	private String player_name;
	private String score_label;
	private int score;
	private String mensagem;
	private boolean host;
	private long lastClick;
	private boolean permissaoJogo;
	private Jogada jogada;
	private int cartasViradas;
	private int contadorBonus;
	private boolean fimDeJogo;
	private Card cartaDimensao;
	
	private RankingWSAccess rankingWSAccess;
	
	private RepositorioPlayer playerDB;
	
	private Bitmap cartaDeCostas;
	
	public MainGameView(Context context, EScreens nextScreen) {
		super(context, nextScreen);
	}
	
	public MainGameView(Context context, EScreens nextScreen, 
			String player_name) {
		super(context, nextScreen);
		this.player_name = player_name;
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		this.playerDB = new RepositorioPlayer(getContext());
		this.rankingWSAccess = new RankingWSAccess();
		this.cartaDimensao = new Card(BitmapFactory.decodeResource(
				getResources(), R.drawable.cartavazia), 0, 0, "carta_referencia");
		this.contadorBonus = 0;
		this.permissaoJogo = false;
		this.player_label = getContext().getString(R.string.player_lb);
		this.score_label = getContext().getString(R.string.score_lb);
		this.score = 0;
		this.cartasViradas = 0;
		this.cartaDeCostas = BitmapFactory.decodeResource(getResources(), R.drawable.cartadecostas);
		this.mensagem = getContext().getString(R.string.wait);
		CartasReferencias = new int[10];
		
		this.CartasReferencias[0] = R.drawable.monstroa;
		this.CartasReferencias[1] = R.drawable.monstrob;
		this.CartasReferencias[2] = R.drawable.monstroc;
		this.CartasReferencias[3] = R.drawable.monstrod;
		this.CartasReferencias[4] = R.drawable.monstroe;
		this.CartasReferencias[5] = R.drawable.monstrof;
		this.CartasReferencias[6] = R.drawable.monstrog;
		this.CartasReferencias[7] = R.drawable.monstroh;
		this.CartasReferencias[8] = R.drawable.monstroi;
		this.CartasReferencias[9] = R.drawable.monstroj;
		
		this.ordemAleatoria = new int[20];
		this.cartasOrdenadas = new int[20];
		
		//Toast.makeText(getContext(), "" + getGraphicContent().size(), Toast.LENGTH_LONG).show();
		
		for(int i = 0; i < (this.CartasReferencias.length * 2) ; i++)
		{
			getGraphicContent().add(new Card(BitmapFactory.decodeResource(getResources(), R.drawable.cartadecostas), 0, 0, "carta" + i));
		}
		
		this.painelJogo = new BackgroundComponent(BitmapFactory.decodeResource(getResources(), R.drawable.paineljogo),0,0,"painel_jogo");
		
		carregarParesDeCartas();
		
		getGraphicContent().add(painelJogo);
		this.jogada = new Jogada();
		
		//enviarRankingParaWebService();
	}
	
	@Override
	public void update() {
		super.update();
		
		for(int i = 0; i < 20 ; i = i + 5)
		{
			for(int x = 0; x < 5 ; x++)
			{
				getGraphicContent().get(x + i).setX((x * this.cartaDimensao.getWidth()) + ((getWidth() - (this.cartaDimensao.getWidth() * 5))/ 2));
				getGraphicContent().get(x + i).setY((i / 5 * this.cartaDimensao.getHeight()) + ((getHeight() - ((this.cartaDimensao.getHeight() * 4) + this.painelJogo.getHeight())) / 2));				
			}
		}
		
		this.painelJogo.setX((getWidth()/2) - (this.painelJogo.getWidth()/2));
		this.painelJogo.setY(getHeight() - this.painelJogo.getHeight());
		
		if(getGameTime() == 10 && !this.fimDeJogo)
		{
			ocultarCartas();
			this.mensagem = getContext().getString(R.string.turn);
			permissaoJogo = true;
		}
		
		if(this.fimDeJogo && getGameTime() == 30)
		{
			setChangeScreen(true);
		}
	}
	
	private void ocultarCartas()
	{
		for(int i = 0; i < getGraphicContent().size() ; i++)
		{
			if(getGraphicContent().get(i) != this.painelJogo)
			{
				if(!((Card)getGraphicContent().get(i)).isInativa())
				{
					getGraphicContent().get(i).setBmp(this.cartaDeCostas);
				}
			}
		}
	}
	
	private void carregarParesDeCartas()
	{
		getArrayOrdemAleatoria();
		int contador = 0;
		for(int i = 0; i < this.cartasOrdenadas.length - 1; i = i + 2)
		{
			this.cartasOrdenadas[this.ordemAleatoria[i] != 20 ? this.ordemAleatoria[i] : 0] = CartasReferencias[contador];
			this.cartasOrdenadas[this.ordemAleatoria[i + 1] != 20 ? this.ordemAleatoria[i + 1] : 0] = CartasReferencias[contador];
			
			if(contador < 10)
			{
				contador++;
			}
		}
		
		for(int i = 0; i < cartasOrdenadas.length; i++)
		{
			getGraphicContent().get(i).setBmp(BitmapFactory.decodeResource(getResources(), cartasOrdenadas[i]));
			((Card)getGraphicContent().get(i)).setCardName(String.valueOf(cartasOrdenadas[i]));
		}
	}
	
	private int[] getArrayOrdemAleatoria()
	{				
		for(int contador = 0; contador < this.ordemAleatoria.length; contador ++)
		{
			while(true)
			{
				int num = (int)(Math.random() * (this.ordemAleatoria.length + 1));
				if(!numeroRepetido(num, this.ordemAleatoria))
				{
					this.ordemAleatoria[contador] = num;
					break;
				}
			}
		}
		
		return this.ordemAleatoria;
	}
	
	private boolean numeroRepetido(int num, int[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == num)
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		getPaint().setTextSize(13);

		getPaint().setColor(Color.BLACK);

		getPaint().setTextAlign(Paint.Align.CENTER);
		getPaint().setShadowLayer(1, 0, 0, Color.BLACK);
		getPaint().setAntiAlias(true);
		
		super.onDraw(canvas);
		
		canvas.drawText(player_label +" "+ player_name, (this.painelJogo.getX() + 70) , 
				(this.painelJogo.getY() + 20), getPaint());
		canvas.drawText(score_label + " " + score, (this.painelJogo.getWidth() - 70) , 
				(this.painelJogo.getY() + 20), getPaint());
		
		getPaint().setTextSize(20);
		
		canvas.drawText(mensagem, (this.painelJogo.getWidth()/2) , 
				(this.painelJogo.getY() + 60), getPaint());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(System.currentTimeMillis() - lastClick > 300 && permissaoJogo)
		{
			lastClick = System.currentTimeMillis();
			float x_toque = event.getX();
			float y_toque = event.getY();
			synchronized(getHolder())
			{
				for(Sprite s : getGraphicContent())
				{
					if(s.isCollision(x_toque, y_toque) && ((Card)s).getBmp() == this.cartaDeCostas)
					{
						if(this.jogada.getCardA() == null)
						{
							this.jogada.setCardA((Card)s);
						}
						else
						{
							this.jogada.setCardB((Card)s);
							this.permissaoJogo = false;
							if(this.jogada.cartasIguais())
							{
								if(contadorBonus < 0)
								{
									contadorBonus = 0;
								}
								this.jogada.getCardA().setInativa(true);
								this.jogada.getCardB().setInativa(true);
								this.score = this.score + 15 + (10 * (this.contadorBonus < 8 ? this.contadorBonus : this.contadorBonus + 5));
								this.jogada = new Jogada();
								this.permissaoJogo = true;
								this.cartasViradas = cartasViradas + 2;
								this.contadorBonus++;
								
								if(cartasViradas == 20)
								{
									if(this.score > 0)
									{
										this.mensagem = player_name + " " + getContext().getString(R.string.win);
									}
									else
									{
										this.mensagem = player_name + " " + getContext().getString(R.string.lose);
									}
									setGameTime(0);
									this.fimDeJogo = true;
									
									enviarRankingParaWebService();
									
									
									persistirRanking();
								}
							}
							else
							{
								if(this.contadorBonus > 0)
								{
									this.contadorBonus = 0;
								}
								else
								{
									this.contadorBonus--;
								}
								this.mensagem = getContext().getString(R.string.validation);
								this.jogada = new Jogada();
								setGameTime(0);
								this.score = this.score - 10 - (5 * (contadorBonus * -1));
								this.permissaoJogo = false;
							}
						}
						((Card)s).setBmp(BitmapFactory.decodeResource(getResources(), Integer.parseInt(((Card)s).getCardName())));
					}
				}
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	public void enviarRankingParaWebService()
	{
		
		String[] dados = new String[3];
		
		dados[0] = player_name;
		dados[1] = String.valueOf(score);
		dados[2] = DataUtil.getDataAtual();
		
		this.rankingWSAccess.execute(dados);
	}
	
	public void persistirRanking()
	{
		Partida partida = new Partida();
		partida.setUsuarioNome(player_name);
		partida.setPontuacao(String.valueOf(score));
		partida.setData(DataUtil.getDataAtual());
		
		this.playerDB.inserir(partida);
	}
	

	public int[] getCartasReferencias() {
		return CartasReferencias;
	}

	public void setCartasReferencias(int[] cartasReferencias) {
		CartasReferencias = cartasReferencias;
	}

	public int [] getCartasOrdenadas() {
		return cartasOrdenadas;
	}

	public void setCartasOrdenadas(int [] cartasOrdenadas) {
		this.cartasOrdenadas = cartasOrdenadas;
	}

	public int [] getOrdemAleatoria() {
		return ordemAleatoria;
	}

	public void setOrdemAleatoria(int [] ordemAleatoria) {
		this.ordemAleatoria = ordemAleatoria;
	}

	public BackgroundComponent getPainelJogo() {
		return painelJogo;
	}

	public void setPainelJogo(BackgroundComponent painelJogo) {
		this.painelJogo = painelJogo;
	}

	public String getPlayer_label() {
		return player_label;
	}

	public void setPlayer_label(String player_label) {
		this.player_label = player_label;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public String getScore_label() {
		return score_label;
	}

	public void setScore_label(String score_label) {
		this.score_label = score_label;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public boolean isPermissaoJogo() {
		return permissaoJogo;
	}

	public void setPermissaoJogo(boolean permissaoJogo) {
		this.permissaoJogo = permissaoJogo;
	}

	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}

	public int getCartasViradas() {
		return cartasViradas;
	}

	public void setCartasViradas(int cartasViradas) {
		this.cartasViradas = cartasViradas;
	}

	public int getContadorBonus() {
		return contadorBonus;
	}

	public void setContadorBonus(int contadorBonus) {
		this.contadorBonus = contadorBonus;
	}

	public boolean isFimDeJogo() {
		return fimDeJogo;
	}

	public void setFimDeJogo(boolean fimDeJogo) {
		this.fimDeJogo = fimDeJogo;
	}

	public Card getCartaDimensao() {
		return cartaDimensao;
	}

	public void setCartaDimensao(Card cartaDimensao) {
		this.cartaDimensao = cartaDimensao;
	}

	public RankingWSAccess getRankingWSAccess() {
		return rankingWSAccess;
	}

	public void setRankingWSAccess(RankingWSAccess rankingWSAccess) {
		this.rankingWSAccess = rankingWSAccess;
	}

	public Bitmap getCartaDeCostas() {
		return cartaDeCostas;
	}

	public void setCartaDeCostas(Bitmap cartaDeCostas) {
		this.cartaDeCostas = cartaDeCostas;
	}
	
}
