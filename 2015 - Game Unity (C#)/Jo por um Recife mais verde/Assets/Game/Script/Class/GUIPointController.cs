using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GUIPointController : MonoBehaviour {

	public float velocityTranparence;
	public float velocityUp;
	private Text _textPoint;
	private Color _transparentColor;
	private bool _isStart;
	private string _textScore;
	public float waitTime;
	private float _timeWait;
	private int _pointToScore;
	private bool _isPositive;
	private bool _wait;
	public AudioClip up;
	public AudioClip down;

	// Use this for initialization
	void Start () {
		this._wait = waitTime > 0;
		this._textPoint = GetComponentInChildren<Text> ();
		this._textPoint.text = "500";
		this._transparentColor = new Color (this._textPoint.color.r, 
		                                   this._textPoint.color.g,
		                                   this._textPoint.color.b, 
		                                   0);
	}
	
	// Update is called once per frame
	void Update () {
		if(this._isStart)
		{

			if(this._wait)
			{
				if((this._timeWait += Time.deltaTime) <= this.waitTime)
				{
					return;
				}
			}
			
			this._wait = false;

			transform.Translate (0, velocityUp * Time.deltaTime, 0);
			if(this._textPoint.color.a <= .08)
			{
				if(_isPositive)
				{
					GameController.instance.AddScore(this._pointToScore);
				}
				else
				{
					GameController.instance.LowerScore(this._pointToScore);
				}
					
				Destroy(this.gameObject);
			}
		}
	}

	void OnGUI()
	{
		if(this._isStart)
		{
			this._textPoint.text = _textScore;
			if(this._wait)
			{
				if((this._timeWait += Time.deltaTime) <= this.waitTime)
				{
					return;
				}
			}

			this._wait = false;

			this._textPoint.color = Color.Lerp (this._textPoint.color, 
		                                    	this._transparentColor, 
		                                    	this.velocityTranparence * Time.deltaTime);
		}
	}

	public void StartEffect(int point, bool isPositive)
	{
		if(isPositive)
		{
			AudioSource.PlayClipAtPoint (this.up, transform.position, 0.6F);
		}
		else
		{
			AudioSource.PlayClipAtPoint (this.down, transform.position, 1.0F);
		}
		this._isPositive = isPositive;
		this._pointToScore = point;
		this._textScore = (isPositive ? "+" : "-") + this._pointToScore;
		this._isStart = true;
	}
}
