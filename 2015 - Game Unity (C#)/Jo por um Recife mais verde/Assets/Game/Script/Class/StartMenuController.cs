using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class StartMenuController : MonoBehaviour {

	public enum EStateMenu
	{
		NONE,
		MENU_START,
		MENU_GAME,
		NEW_GAME,
		DIFICULTY,
		ABOUT_GAME,
		CONTROLLER,
		QUIT_GAME
	}

	public GameObject panelStart;
	public GameObject panelMenuGame;
	public GameObject panelDificulty;
	public GameObject panelAboutGame;
	public GameObject panelController;
	public float maxIdleTime;
	public TransitionMusic transitionMusic;

	private EStateMenu stateMenu;
	private float idleTime;

	// Use this for initialization
	void Start () {
		this.idleTime = 0;
		this.stateMenu = EStateMenu.MENU_START;
		this.panelMenuGame.SetActive(false);
		this.panelDificulty.SetActive(false);
		this.panelAboutGame.SetActive(false);
		this.panelController.SetActive(false);
	}
	
	
	void Update () {
		switch(this.stateMenu)
		{
		case EStateMenu.NONE:

			break;

		case EStateMenu.MENU_START:
			this.panelStart.SetActive(true);

			if(IsTimeEnd())
			{
				SceneController.instance.Restart();
			}

			if(Input.GetButtonDown("Submit") || Input.touchCount > 0)
			{
				this.stateMenu = EStateMenu.MENU_GAME;
				this.panelStart.SetActive(false);
				this.idleTime = 0;
			}
			
			break;
		
		case EStateMenu.MENU_GAME:
			this.panelMenuGame.SetActive(true);
			if(Input.GetButtonDown("Cancel") || IsTimeEnd() || Input.touchCount > 2)
			{
				this.stateMenu = EStateMenu.MENU_START;
				this.panelMenuGame.SetActive(false);
				this.idleTime = 0;
			}
			
			break;

		case EStateMenu.DIFICULTY:
			this.panelDificulty.SetActive(true);
			if(Input.GetButtonDown("Cancel") || IsTimeEnd() || Input.touchCount > 2)
			{
				this.stateMenu = EStateMenu.MENU_GAME;
				this.panelDificulty.SetActive(false);
				this.idleTime = 0;
			}


			break;

		case EStateMenu.ABOUT_GAME:
			this.panelAboutGame.SetActive(true);
			if(Input.GetButtonDown("Cancel") || IsTimeEnd() || Input.touchCount > 2)
			{
				this.stateMenu = EStateMenu.MENU_GAME;
				this.panelAboutGame.SetActive(false);
				this.idleTime = 0;
			}
			
			
			break;

		case EStateMenu.CONTROLLER:
			this.panelController.SetActive(true);
			if(Input.GetButtonDown("Cancel") || IsTimeEnd() || Input.touchCount > 2)
			{
				this.stateMenu = EStateMenu.MENU_GAME;
				this.panelController.SetActive(false);
				this.idleTime = 0;
			}
			
			
			break;
		}
	}

	public void BTNewGame()
	{
		this.idleTime = 0;
		this.stateMenu = EStateMenu.DIFICULTY;
		this.panelMenuGame.SetActive(false);
	}

	public void BTEasy()
	{
		this.stateMenu = EStateMenu.NONE;
		this.panelDificulty.SetActive(false);

		var listeners = FindObjectsOfType<MonoBehaviour> ().OfType <IGameStart> ();
		
		foreach(var listener in listeners)
		{
			listener.OnGameStart();
		}

		this.transitionMusic.Transition ();
		GameController.instance.SetDifficulty (EDifficulty.EASY);
	}

	public void BTNormal()
	{
		this.stateMenu = EStateMenu.NONE;
		this.panelDificulty.SetActive(false);

		var listeners = FindObjectsOfType<MonoBehaviour> ().OfType <IGameStart> ();
		
		foreach(var listener in listeners)
		{
			listener.OnGameStart();
		}

		this.transitionMusic.Transition ();
		GameController.instance.SetDifficulty (EDifficulty.NORMAL);
	}

	public void BTHard()
	{
		this.stateMenu = EStateMenu.NONE;
		this.panelDificulty.SetActive(false);
		
		var listeners = FindObjectsOfType<MonoBehaviour> ().OfType <IGameStart> ();
		
		foreach(var listener in listeners)
		{
			listener.OnGameStart();
		}

		this.transitionMusic.Transition ();
		GameController.instance.SetDifficulty (EDifficulty.HARD);
	}

	public void BTAboutGame()
	{
		this.idleTime = 0;
		this.stateMenu = EStateMenu.ABOUT_GAME;
		this.panelMenuGame.SetActive(false);
	}

	public void BTController()
	{
		this.idleTime = 0;
		this.stateMenu = EStateMenu.CONTROLLER;
		this.panelMenuGame.SetActive(false);
	}

	public void BTQuit()
	{
		Application.Quit ();
	}

	private bool IsTimeEnd()
	{
		return (this.idleTime += Time.fixedDeltaTime) >= this.maxIdleTime;
	}
	
}
