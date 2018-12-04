using System;
using System.Linq;
using UnityEngine;
using System.Collections;

public class GameController : MonoBehaviour {

	public static GameController instance;

	public float timeToEnd;
	public float currentTimeToEnding;
	public bool isVictory;
	public int gameScore { get; private set;}
	public int lastGameScore { get; private set;}
	public bool gameStarted;
	public bool paused;
	public bool wait;
	public bool nextStage;
	private EDifficulty _difficulty;
	public bool pause { get; private set;}
	public int maxTemperature;
	public int currentTemperature { get; private set;}
	public float currentGameTime { get; private set;}
	public float currentTimeToFinish { get; private set;}
	public int maxKeadCaterpillar;
	public int currentKneadCaterpillar { get; private set;}

	public StageState stage01;
	public StageState stage02;
	public StageState stage03;
	public StageState currentStage { get; private set;}

	void Awake()
	{
		instance = this;
		if(Application.platform == RuntimePlatform.Android)
		{
			QualitySettings.vSyncCount = 2;
			if(Screen.currentResolution.width > 1280)
			{
				Screen.SetResolution(1280, 720, true);
			}
		}
	}

	// Use this for initialization
	void Start () {
		this._difficulty = EDifficulty.NORMAL;
		this.currentKneadCaterpillar = 0;
		Clean ();
	}
	
	// Update is called once per frame
	void Update () {
		PauseCheck ();
		CheckInput (); //Remove this!!!

		if(!gameStarted)
		{
			return;
		}

		if(this.paused)
		{
			return;
		}

		if(this.currentKneadCaterpillar >= this.maxKeadCaterpillar)
		{
			GameOver();
		} 

		if(this.currentStage == null)
		{
			this.currentStage = this.stage01;
		}

		this.currentTemperature = maxTemperature - CountTemperature();

		if(!this.currentStage.isStageEnd)
		{
			if((this.currentGameTime += Time.fixedDeltaTime) >= this.currentTimeToFinish)
			{
				this.currentGameTime = this.currentStage.timeToFinish;
				GameOver();
			}
			
			return;
		}

		else
		{
			this.currentGameTime = 0;
			if(this.currentStage.situationScene == ESituationScene.DRY_SCENE)
			{
				var listeners = FindObjectsOfType<MonoBehaviour> ().OfType <IStageChange> ();
				
				foreach(var listener in listeners)
				{
					listener.OnStageChangeFixed(ESituationScene.GREEN_SCENE);
				}
				this.currentStage.situationScene = ESituationScene.GREEN_SCENE;
			}
			else if(this.currentStage.situationScene == ESituationScene.GREEN_SCENE)
			{
				if((this.currentTimeToEnding += Time.deltaTime) >= this.timeToEnd)
				{
					this.isVictory = true;
					GameOver();
				}
			}


		}
	}

	public bool IsVictory()
	{
		return this.isVictory;
	}

	public void StartPause()
	{
		this.paused = true;
	}

	public void StopPause()
	{
		this.paused = false;
	}

	public void PauseCheck()
	{		
		if(this.paused)
		{
			Time.timeScale = 0;
		}
		else
		{
			Time.timeScale = 1;
		}
	}

	private void LoadStageDifficulty()
	{
		switch(this._difficulty)
		{
		case EDifficulty.EASY:
			this.currentTimeToFinish = this.stage01.timeToFinish;
			return;

		case EDifficulty.NORMAL:
			this.currentTimeToFinish = this.stage01.timeToFinish - 20;
			break;

		case EDifficulty.HARD:
			this.currentTimeToFinish = this.stage01.timeToFinish - 90;
			break;
		}

		if(this.currentTimeToFinish <= 0)
		{
			this.currentTimeToFinish = this.stage01.timeToFinish;
		}
	}

	private int CountTemperature()
	{
		return (int)((
			this.stage01.GetTotalPlantationsMade()) * 10.5F);
	}

	private void CheckInput()
	{
		/*if(!this.gameStarted)
		{
			return;
		}

		if(Input.GetButtonDown("Cancel"))
		{
			this.paused = !paused;

			if(paused)
			{
				Time.timeScale = 0;
			}
			else
			{
				Time.timeScale = 1;
			}
		}*/
//Test Function

	}

	public void SetCurrentStage(int indexStage)
	{
		switch(indexStage)
		{
		case 1:
			this.currentStage = this.stage01;
			break;

		case 2:
			this.currentStage = this.stage02;
			break;

		case 3:
			this.currentStage = this.stage03;
			break;
		}
	}

	public void AddKneadCaterpillar()
	{
		this.currentKneadCaterpillar++;
	}
	
	public void AddScore(int scoreToAdd)
	{
		this.gameScore += scoreToAdd;
	}

	public void LowerScore(int scoreToLower)
	{
		this.gameScore -= scoreToLower;
		if(this.gameScore < 0)
		{
			this.gameScore = 0;
		}
	}

	public void StartGame()
	{
		LoadStageDifficulty ();
		this.gameStarted = true;
	}

	public void SetDifficulty(EDifficulty difficulty)
	{
		this._difficulty = difficulty;
	}

	public EDifficulty GetDifficulty()
	{
		return this._difficulty;
	}

	public void ResetAllComponent ()
	{
		this.stage01.Reset ();
		this.stage02.Reset ();
		this.stage03.Reset ();
		Start ();
	}

	public void Clean ()
	{
		this.isVictory = false;
		this.wait = true;
		this.paused = false;
		this.nextStage = false;
		this.gameStarted = false;
		this.currentTemperature = maxTemperature;
		this.currentStage = this.stage01;
		ResetScore ();
	}

	public void ResetScore()
	{
		this.gameScore = 0;
	}

	public void CleanCurrentStage ()
	{
		this.currentStage.Reset();
	}
	

	public void CleanStage(int indexStage)
	{
		switch(indexStage)
		{
		case 1:
			this.stage01.Reset();
			break;
			
		case 2:
			this.stage02.Reset();
			break;
			
		case 3:
			this.stage03.Reset();
			break;
		}
	}

	public void GameOver()
	{
		this.currentGameTime = 0;
		this.lastGameScore = gameScore;
		SceneController.instance.SetNextScene (EScenes.GAME_OVER, "gameOver");
		SceneController.instance.ClosingScene (0.1F);
		this.gameStarted = false;
	}

	public int GameTimeInSeconds()
	{
		return (int)(this.currentStage.timeToFinish - this.currentGameTime);
	}	

	public float GetTemperatureReduction()
	{
		return this.maxTemperature - this.currentTemperature;
	}
}
