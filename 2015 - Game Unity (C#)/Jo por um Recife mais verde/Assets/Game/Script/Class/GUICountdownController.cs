using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GUICountdownController : MonoBehaviour {

	public Color[] colors;
	private Color _currentColor;
	private Text _textCountdownTime;
	private string _timeFormated;
	private int _reductionFactor;

	// Use this for initialization
	void Start () {
		this._textCountdownTime = GetComponent<Text> ();
	}
	
	// Update is called once per frame
	void LateUpdate () {
		if (GameController.instance.gameStarted) 
		{
			if(this._reductionFactor == 0)
			{
				this._reductionFactor = 
					((int)GameController.instance.currentStage.timeToFinish / this.colors.Length);
			}

			this._timeFormated = 
			GetTimeFormated (GameController.instance.GameTimeInSeconds ());

			int index = (((GameController.instance.GameTimeInSeconds () / 
			               this._reductionFactor) + 
			              RoundFactor(GameController.instance.GameTimeInSeconds (),this._reductionFactor)) - 1);

			index = Mathf.Clamp(index, 0, (this.colors.Length - 1));

			this._currentColor = 
				this.colors [index];
		}
	}

	void OnGUI()
	{
		this._textCountdownTime.text = this._timeFormated;
		this._textCountdownTime.color = this._currentColor;
	}

	public int RoundFactor(int valueA, int valueB)
	{
		int factor = valueA % valueB;

		return factor > 0 ? 1 : 0;
	}

	public string GetTimeFormated(int seconds)
	{
		string formated = "";

		if(seconds >= 0)
		{
			int minutesTime = 0;
			int secondsTime = 0;

			minutesTime = seconds / 60;
			secondsTime = seconds % 60;

			formated = (minutesTime > 9 ? minutesTime.ToString() : ("0" + minutesTime.ToString())) + ":" +
				(secondsTime > 9 ? secondsTime.ToString() : (0 + secondsTime.ToString()));
		}

		return formated;
	}
}
