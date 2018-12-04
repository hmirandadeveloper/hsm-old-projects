using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GUIScoreController : MonoBehaviour {

	private Text _text;
	public Color color;
	public Color colorAdd;
	public Color colorRem;
	private int _currentScore;
	public AudioClip coinAddSound;
	public AudioClip coinRemSound;

	// Use this for initialization
	void Start () {
		this._text = GetComponent<Text> ();
		this._currentScore = GameController.instance.gameScore;
	}
	
	// Update is called once per frame
	void Update () {
		if(this._currentScore == GameController.instance.gameScore)
		{
			this._text.color = color;
			return;
		}

		if(this._currentScore < GameController.instance.gameScore)
		{
			this._text.color = colorAdd;
			this._currentScore += 5;
			AudioSource.PlayClipAtPoint(this.coinAddSound, transform.position, 0.3F);

			this._currentScore = Mathf.Clamp(this._currentScore, 0, GameController.instance.gameScore);
		}
		else if(this._currentScore > GameController.instance.gameScore)
		{
			this._text.color = colorRem;
			this._currentScore -= 5;
			AudioSource.PlayClipAtPoint(this.coinRemSound, transform.position, 0.2F);

			this._currentScore = Mathf.Clamp(this._currentScore, GameController.instance.gameScore, this._currentScore);
		}
	}

	void OnGUI()
	{
		this._text.text = this._currentScore.ToString ();
	}
}
