using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GUITemperatureController : MonoBehaviour {

	public float temperatureReductionColorFactor;
	public Color[] colorsTemperature;
	private int _currentIndexReduction;
	private Text _textTemperature;

	// Use this for initialization
	void Start () {
		this._textTemperature = GetComponent<Text> ();
		this._textTemperature.text = GameController.instance.currentTemperature + "ºC";
	}
	
	// Update is called once per frame
	void Update () {
		if(GameController.instance.paused)
		{
			return;
		}

		this._currentIndexReduction =  Mathf.Abs((int)(GameController.instance.GetTemperatureReduction () / 
		                                               this.temperatureReductionColorFactor));
	}

	void OnGUI()
	{
		this._textTemperature.text = GameController.instance.currentTemperature + "ºC";
		this._textTemperature.color = this.colorsTemperature [this._currentIndexReduction];
	}
}
