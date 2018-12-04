using UnityEngine;
using System.Collections;

public class ParticleController : MonoBehaviour, IStageChange {
	
	private ParticleSystem[] _particleSystems;
	private ESituationScene currentSituationScene;
	public bool onlyVisibleRenderer;
	public ESituationScene sceneOfChange;
	public EIdStage idStage;
	
	private bool _isLoading;

	// Use this for initialization
	void Start () {
		this._particleSystems = GetComponentsInChildren<ParticleSystem> ();
		this._isLoading = true;
	}
	
	void LateUpdate () {
		if(this._isLoading)
		{
			OnStageChangeFixed (GameController.instance.currentStage.situationScene);
			this._isLoading = false;
		}
		if (this.onlyVisibleRenderer) 
		{
			for (int i = 0; i < _particleSystems.Length; i++) {
				ParticleSystemRenderer renderer = this._particleSystems [i].GetComponent<ParticleSystemRenderer> ();

				if (!renderer.isVisible || (int)this.currentSituationScene >= (int)this.sceneOfChange) {
					this._particleSystems [i].enableEmission = false;
				} else {
					this._particleSystems [i].enableEmission = true;
				}
			}
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
			for(int i = 0; i < _particleSystems.Length; i++)
			{
				this._particleSystems[i].enableEmission = false;
			}
		}
	}

	public void OnStageChangeFixed(ESituationScene situationScene)
	{
		this.currentSituationScene = situationScene;
		if ((int)this.currentSituationScene >= (int)this.sceneOfChange) {
			for(int i = 0; i < _particleSystems.Length; i++)
			{
				this._particleSystems[i].enableEmission = false;
			}
		}
		else
		{
			for(int i = 0; i < _particleSystems.Length; i++)
			{
				this._particleSystems[i].enableEmission = true;
			}
		}
	}

	public void OnReset()
	{
		this.currentSituationScene = ESituationScene.DRY_SCENE;
		this._isLoading = true;
	}
}
