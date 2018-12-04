using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GameOverController : MonoBehaviour {

	public TextEffectController textMsg;
	public Text textScore;
	public string textVictory;
	public string textLose;
	public string textLoseWithKnead;
	public Outline textGameOverOutline;
	public Color victoryColor;
	public Color loseColor;
	public TransitionMusic transitionMusic;

	// Use this for initialization
	void Start () {
		this.textScore.text = "Score: " + GameController.instance.gameScore;

		if(GameController.instance.IsVictory())
		{
			this.textGameOverOutline.effectColor = this.victoryColor;
			this.textMsg.SetText(this.textVictory);
		}
		else
		{
			this.textGameOverOutline.effectColor = this.loseColor;

			if(GameController.instance.currentKneadCaterpillar >= 
			   GameController.instance.maxKeadCaterpillar)
			{
				this.textMsg.SetText(this.textLoseWithKnead);
			}
			else
			{
				this.textMsg.SetText(this.textLose);
			}
		}
	}
	
	// Update is called once per frame
	void Update () {

		if (!this.textMsg.IsFinish ()) {
			return;
		} else {
			this.transitionMusic.Transition (1.0F);
			SceneController.instance.ClosingScenePause (2.0F);
		}
	}
}
