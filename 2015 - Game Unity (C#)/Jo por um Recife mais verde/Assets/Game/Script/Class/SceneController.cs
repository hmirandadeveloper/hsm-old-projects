using UnityEngine;
using System.Collections;

public class SceneController : MonoBehaviour {

	public enum ESceneState
	{
		IN,
		PLAY,
		OUT
	}

	public EScenes currentScene {get; private set;}
	public ESceneState currentSceneState { get; private set;}
	public EScenes nextScene { get; private set;}
	public string nextSceneName { get; private set;}
	private FadeController _fadeController;
	private AsyncOperation _async;
	private bool _isLoadSceneAsync;
	private float progress;
	public bool pauseTransition;
	public bool loadFinish;
	public float timeToClosePaused;
	public static SceneController instance { get; private set;}
	public bool canLoadNextScene;

	void Awake()
	{
		instance = this;

		DontDestroyOnLoad (this);
		if (FindObjectsOfType(GetType()).Length > 1)
		{
			Destroy(gameObject);
		}

	}

	// Use this for initialization
	void Start () {
		this.nextScene = EScenes.NONE;
		this.nextSceneName = "";
		this.currentSceneState = ESceneState.IN;
		this.currentScene = EScenes.NONE;
		this._fadeController = GetComponentInParent<FadeController> ();
		this._fadeController.setFadeTime(1.5f);
		this._isLoadSceneAsync = false;
		this.canLoadNextScene = false;
	}
	
	// Update is called once per frame
	void LateUpdate () {

		SceneManager ();
	}

	public void Restart()
	{
		this.Start ();
	}

	bool HasNextScene()
	{
		if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
		{
			return true;
		}

		return false;
	}

	public void SetNextScene(EScenes nextScene, string nextSceneName)
	{
		this.nextScene = nextScene;
		this.nextSceneName = nextSceneName;
	}

	public void ClosingScene(float timeToWait)
	{


		if(this.currentSceneState != ESceneState.PLAY)
		{

			return;
		}

		StopCoroutine(WaitToClosing(timeToWait));
		StartCoroutine(WaitToClosing(timeToWait));
	}

	public void ClosingScenePause(float timeToWait)
	{

		if(this.currentSceneState != ESceneState.PLAY)
		{
			return;
		}

		StartCoroutine(WaitToClosingPaused(timeToWait));
	}

	private void SceneManager()
	{
		switch (currentScene)
		{
		case EScenes.NONE:
			AutomaticSceneTransitionNoFade(EScenes.LOGO_PCR, "logoPCR");
			break;

		case EScenes.LOGO_PCR:
			GameController.instance.ResetAllComponent();
			AutomaticSceneTransition(EScenes.LOGO_PROGRAMA_AMBIENTAL, "logoProgramaAmbiental", 0.5f);
			break;
			
		case EScenes.LOGO_PROGRAMA_AMBIENTAL:
			AutomaticSceneTransition(EScenes.LOGO_HSM, "logoHSM", 0.5f);
			break;
			
		case EScenes.LOGO_HSM:
			AutomaticSceneTransition(EScenes.IMG_CONCIENTIZACAO_AMBIENTAL, "imgConcientizacaoAmbiental", 3.0f);
			break;
			
		case EScenes.IMG_CONCIENTIZACAO_AMBIENTAL:
			AutomaticSceneTransitionLoad(EScenes.MENU_INICIAL, "menuInicial");
			break;

		case EScenes.MENU_INICIAL:
			SceneTransition(EScenes.INTRO_GAME, "introGame");
			break;

		case EScenes.INTRO_GAME:
			SceneTransitionLoadWithPause(EScenes.STAGE_01, "stage01");
			break;

		case EScenes.STAGE_01:
			SceneTransitionLoadWithCommand(EScenes.STAGE_01, "stage01");
			break;

		case EScenes.STAGE_02:
			SceneTransition(EScenes.STAGE_03, "stage03");
			break;

		case EScenes.STAGE_03:
			SceneTransition(EScenes.END_GAME, "endGame");
			break;

		case EScenes.END_GAME:
			SceneTransitionLoadWithPause(EScenes.GAME_OVER, "gameOver");
			break;

		case EScenes.GAME_OVER:
			SceneTransitionLoadWithPause(EScenes.LOGO_PCR, "logoPCR");
			break;
		}
	}

	private void AutomaticSceneTransitionNoFade(EScenes nextScene, string nextSceneName)
	{		

		this.currentSceneState = ESceneState.IN;
		Application.LoadLevel(HasNextScene() ? this.nextSceneName : nextSceneName);
		this.currentScene = HasNextScene() ? this.nextScene : nextScene;
		print("Cena: " + currentScene.ToString());
		this._fadeController.CleanFade();
		
		if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
		{
			this.nextScene = EScenes.NONE;
			this.nextSceneName = "";
		}
	}

	private void AutomaticSceneTransition(EScenes nextScene, string nextSceneName, float timeFade)
	{
		if(Input.GetKey (KeyCode.Escape))
		{
			this.currentSceneState = ESceneState.OUT;
		}

		SceneFadeIn();
		
		if(this.currentSceneState == ESceneState.IN)
		{
			StopAllCoroutines ();
			return;
		}
		else if (this.currentSceneState == ESceneState.PLAY)
		{
			StartCoroutine(WaitToClosing(timeFade));
		}
		else if(this.currentSceneState == ESceneState.OUT)
		{
			SceneFadeOut ();
		}
		
		if(this.currentSceneState == ESceneState.OUT && 
		   this._fadeController.fadeState == FadeController.EFadeState.OUT_COMPLETE)
		{
			this.currentSceneState = ESceneState.IN;
			Application.LoadLevel(HasNextScene() ? this.nextSceneName : nextSceneName);
			this.currentScene = HasNextScene() ? this.nextScene : nextScene;
			print("Cena: " + currentScene.ToString());
			this._fadeController.CleanFade();

			if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
			{
				this.nextScene = EScenes.NONE;
				this.nextSceneName = "";
			}
		}	
	}

	private void AutomaticSceneTransitionLoad(EScenes nextScene, string nextSceneName)
	{		
		SceneFadeIn();
		
		if(this.currentSceneState == ESceneState.IN)
		{
			StopAllCoroutines ();
			return;
		}
		else if (this.currentSceneState == ESceneState.PLAY)
		{
			if(!_isLoadSceneAsync)
			{
				StartCoroutine(LoadNewScene(HasNextScene() ? this.nextSceneName : nextSceneName));
			}
			else
			{
				progress = this._async.progress;
				if(this.progress >= 0.9F)
				{
					this.currentSceneState = ESceneState.OUT;
				}
				return;
			}


		}
		else if(this.currentSceneState == ESceneState.OUT)
		{
				SceneFadeOut ();
		}
		
		if(this.currentSceneState == ESceneState.OUT && 
		   this._fadeController.fadeState == FadeController.EFadeState.OUT_COMPLETE)
		{
			this.currentSceneState = ESceneState.IN;
			this.currentScene = HasNextScene() ? this.nextScene : nextScene;
			print("Cena: " + currentScene.ToString());
			this._fadeController.CleanFade();
			this._async.allowSceneActivation = true;
			this.loadFinish = false;

			if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
			{
				this.nextScene = EScenes.NONE;
				this.nextSceneName = "";
			}
		}	
	}

	private void SceneTransitionLoadWithPause(EScenes nextScene, string nextSceneName)
	{		
		SceneFadeIn();
		
		if(this.currentSceneState == ESceneState.IN)
		{
			StopAllCoroutines ();
			return;
		}
		else if (this.currentSceneState == ESceneState.PLAY)
		{
			if(!_isLoadSceneAsync)
			{
				this.loadFinish = false;
				this.pauseTransition = true;
				StartCoroutine(LoadNewScene(HasNextScene() ? this.nextSceneName : nextSceneName));
			}
			else
			{
				progress = this._async.progress;
				if(this.progress >= 0.9F)
				{
					this.loadFinish = true;
					if(!this.pauseTransition)
					{
						ClosingScene (this.timeToClosePaused > 0 ? this.timeToClosePaused : 1.0F);
					}
				}
				return;
			}
			
			
		}
		else if(this.currentSceneState == ESceneState.OUT)
		{
			SceneFadeOut ();
		}
		
		if(this.currentSceneState == ESceneState.OUT && 
		   this._fadeController.fadeState == FadeController.EFadeState.OUT_COMPLETE)
		{
			this.currentSceneState = ESceneState.IN;
			this.currentScene = HasNextScene() ? this.nextScene : nextScene;
			print("Cena: " + currentScene.ToString());
			this._fadeController.CleanFade();
			this._async.allowSceneActivation = true;
			this.loadFinish = false;

			if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
			{
				this.nextScene = EScenes.NONE;
				this.nextSceneName = "";
			}
		}	
	}

	private void SceneTransitionLoadWithCommand(EScenes nextScene, string nextSceneName)
	{		
		if (!HasNextScene () && !canLoadNextScene) {
			if(this.currentSceneState == ESceneState.PLAY)
			{
				return;
			}

			if (this.currentSceneState == ESceneState.OUT &&
				(this._fadeController.fadeState == FadeController.EFadeState.NONE ||
				this._fadeController.fadeState == FadeController.EFadeState.DISABLED)) {
				this.currentSceneState = ESceneState.IN;
			}

			SceneFadeIn ();
		
			if (this.currentSceneState == ESceneState.IN) {
				StopAllCoroutines ();
				return;
			}
		}

		if(this.canLoadNextScene)
		{
			if(!_isLoadSceneAsync)
			{
				this.loadFinish = false;
				this.pauseTransition = true;
				StartCoroutine(LoadNewScene(HasNextScene() ? this.nextSceneName : nextSceneName));
			}
			else
			{
				progress = this._async.progress;
				if(this.progress >= 0.9F)
				{
					this.loadFinish = true;
				}
					return;
			}
		}
			
		if(this.currentSceneState == ESceneState.OUT)
		{
			SceneFadeOut ();
		}
		
		if(this.currentSceneState == ESceneState.OUT && 
		   this._fadeController.fadeState == FadeController.EFadeState.OUT_COMPLETE)
		{
			this.currentSceneState = ESceneState.IN;
			this.currentScene = HasNextScene() ? this.nextScene : nextScene;
			if(HasNextScene())
			{
				Application.LoadLevel(HasNextScene() ? this.nextSceneName : nextSceneName);
			}
			print("Cena: " + currentScene.ToString());
			this._fadeController.CleanFade();
			this._async.allowSceneActivation = true;
			this.loadFinish = false;
			
			if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
			{
				this.nextScene = EScenes.NONE;
				this.nextSceneName = "";
			}

			this.canLoadNextScene = false;
		}	
	}

	private void OnlyFadeIn()
	{
		if(this.currentSceneState == ESceneState.OUT &&
		   (this._fadeController.fadeState == FadeController.EFadeState.NONE ||
		 this._fadeController.fadeState == FadeController.EFadeState.DISABLED))
		{
			this.currentSceneState = ESceneState.IN;
		}

		SceneFadeIn();
	}

	private void SceneTransition(EScenes nextScene, string nextSceneName)
	{
		if(this.currentSceneState == ESceneState.PLAY)
		{
			return;
		}

		if(this.currentSceneState == ESceneState.OUT &&
		   (this._fadeController.fadeState == FadeController.EFadeState.NONE ||
		 this._fadeController.fadeState == FadeController.EFadeState.DISABLED))
		{
			this.currentSceneState = ESceneState.IN;
		}

		SceneFadeIn();
		
		if(this.currentSceneState == ESceneState.IN)
		{
			return;
		}

		if(this.currentSceneState == ESceneState.OUT)
		{
			SceneFadeOut ();
		}
		
		if(this.currentSceneState == ESceneState.OUT && 
		   this._fadeController.fadeState == FadeController.EFadeState.OUT_COMPLETE)
		{
			this.currentSceneState = ESceneState.IN;
			Application.LoadLevel(HasNextScene() ? this.nextSceneName : nextSceneName);
			this.currentScene = HasNextScene() ? this.nextScene : nextScene;
			print("Cena: " + currentScene.ToString());
			this._fadeController.CleanFade();

			if(this.nextScene != EScenes.NONE && this.nextSceneName != "")
			{
				this.nextScene = EScenes.NONE;
				this.nextSceneName = "";
			}
		}	
	}
	
	private void SceneFadeIn()
	{
		if(this._fadeController.fadeState == FadeController.EFadeState.NONE)
		this.currentSceneState = ESceneState.IN;

		if(this._fadeController.fadeIn)
		{
			return;
		}
		
		if(this.currentSceneState == ESceneState.IN)
		{
			if(!this._fadeController.fadeIn && 
			   this._fadeController.fadeState == FadeController.EFadeState.NONE)
			{
				this._fadeController.FadeInEffect();
			}
			else if(this._fadeController.fadeState == FadeController.EFadeState.IN_COMPLETE)
			{
				this.currentSceneState = ESceneState.PLAY;
			}
		}
	}

	private void SceneFadeOut()
	{
		if(this._fadeController.fadeOut)
		{
			return;
		}
		
		if(this.currentSceneState == ESceneState.OUT)
		{
			if(!this._fadeController.fadeOut && 
			   this._fadeController.fadeState == FadeController.EFadeState.IN_COMPLETE)
			{
				this._fadeController.FadeOutEffect();
			}
		}
	}

	IEnumerator WaitToClosing(float secondsWait)
	{
		float seconds = secondsWait;
		yield return new WaitForSeconds (secondsWait);

		this.currentSceneState = ESceneState.OUT;
		StopCoroutine(WaitToClosing(seconds));
	}

	IEnumerator WaitToClosingPaused(float secondsWait)
	{
		float seconds = secondsWait;
		yield return new WaitForSeconds (secondsWait);

		this.pauseTransition = false;
		this.currentSceneState = ESceneState.OUT;
		StopCoroutine(WaitToClosingPaused(seconds));
	}

	IEnumerator LoadNewScene(string scene)
	{
		this._isLoadSceneAsync = true;
		this._async = Application.LoadLevelAsync (scene);
		this._async.allowSceneActivation = false;
		yield return this._async;
		this._isLoadSceneAsync = false;

	}
}
