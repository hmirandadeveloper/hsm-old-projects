using UnityEngine;
using System.Collections;

public class ChangingMaterialComponent : MonoBehaviour, IStageChange {
	
	public ESituationScene situationScene;
	public EIdStage idStage;
	private Renderer renderer;
	public Material[] materials = new Material[3];
	
	private bool _isLoading;

	// Use this for initialization
	void Start () {
		this.renderer = GetComponent<Renderer> ();
		this.renderer.material = materials [(int)situationScene];
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
		int index = (int)situationScene;
		if(index >= this.materials.Length)
		{
			index = this.materials.Length - 1;
		}
		this.situationScene = situationScene;
		this.renderer.material = materials [index];
	}

	public void OnStageChangeFixed(ESituationScene situationScene)
	{
		int index = (int)situationScene;
		if(index >= this.materials.Length)
		{
			index = this.materials.Length - 1;
		}
		this.situationScene = situationScene;
		this.renderer.material = materials [index];
	}

	public void OnReset()
	{
		this.situationScene = ESituationScene.DRY_SCENE;
		this.renderer.material = materials [(int)situationScene];
	}
}
