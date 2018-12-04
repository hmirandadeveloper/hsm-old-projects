using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class PauseMenuController : MonoBehaviour {

	public GameObject panelPause;
	public GameObject panelOtto;

	// Use this for initialization
	void Start () {
		this.panelPause.SetActive (false);
		this.panelOtto.SetActive (true);
	}
	
	void OnGUI()
	{
		if(!GameController.instance.gameStarted)
		{
			return;
		}

		if (Input.GetButtonDown("Cancel") || Input.touches.Length > 2) {
			GameController.instance.StartPause();
			this.panelPause.SetActive(true);
		}
	}

	public void BTReturnMenuGame()
	{
		SceneController.instance.SetNextScene (EScenes.MENU_INICIAL, "menuInicial");
		SceneController.instance.ClosingScene (0.1F);
		GameController.instance.StopPause ();
		GameController.instance.Clean ();
		this.panelPause.SetActive (false);
	}

	public void BTReturn()
	{
		this.panelPause.SetActive (false);
		GameController.instance.StopPause ();
	}

	public void BTUnderstand()
	{
		this.panelOtto.SetActive (false);
		GameController.instance.StartGame ();
		GameController.instance.wait = false;
	}
	
}
