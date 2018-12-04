using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class IntroGameController : MonoBehaviour {

	public TextEffectController textIntroGame;
	public Text textLoad;
	public Text textSkip;
	private bool _isFinish;
	public TransitionMusic transitionMusic;

	// Use this for initialization
	void Start () {
		this.textLoad.enabled = true;
		this.textSkip.enabled = false;
		this._isFinish = false;
	}
	
	// Update is called once per frame
	void Update () {

		if (SceneController.instance.loadFinish) {

			if(this.textLoad.enabled && !this.textSkip.enabled)
			{
				this.textLoad.enabled = false;
				this.textSkip.enabled = true;
			}

			if (Input.GetKey (KeyCode.Return) || Input.touches.Length > 1) {
				this.transitionMusic.Transition (0.5F);
				SceneController.instance.pauseTransition = false;
			}

			if (!this.textIntroGame.IsFinish ()) {
				return;
			} else if(!this._isFinish){
				this.transitionMusic.Transition (1.0F);
				SceneController.instance.ClosingScenePause (4.0F);
			}
		}

	}
}
