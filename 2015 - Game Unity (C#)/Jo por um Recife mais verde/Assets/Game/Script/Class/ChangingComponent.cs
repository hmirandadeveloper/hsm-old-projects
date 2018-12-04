using UnityEngine;
using System.Collections;

public class ChangingComponent : MonoBehaviour, IStageChange {
	
	private Renderer _renderer;
	private ESituationScene currentSituationScene;
	public ESituationScene sceneOfChange;
	public EIdStage idStage;
	
	private bool _isLoading;

	// Use this for initialization
	void Start () {
		this._renderer = GetComponent<Renderer> ();
		this._isLoading = true;
	}
	
	void LateUpdate () {
		if(this._isLoading)
		{
			OnStageChangeFixed (GameController.instance.currentStage.situationScene);
			this._isLoading = false;
		}


	}

	public void OnStageChange(EIdStage idStage, ESituationScene situationScene)
	{
		if(this.idStage != idStage)
		{
			return;
		}

		this.currentSituationScene = situationScene;
		if ((int)this.currentSituationScene >= (int)this.sceneOfChange) {
			this._renderer.enabled = true;
		}
	}

	public void OnStageChangeFixed(ESituationScene situationScene)
	{
		this.currentSituationScene = situationScene;
		if ((int)this.currentSituationScene >= (int)this.sceneOfChange) {
			this._renderer.enabled = true;
		}
		else
		{
			this._renderer.enabled = false;
		}
	}

	public void OnReset()
	{
		this.currentSituationScene = ESituationScene.DRY_SCENE;
		this._isLoading = true;
	}
}
