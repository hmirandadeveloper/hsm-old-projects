using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GUILifeController : MonoBehaviour {

	private Text lifeText;
	public PlayerHealth playerHealth;

	// Use this for initialization
	void Start () {
		this.lifeText = GetComponent<Text> ();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnGUI()
	{
		this.lifeText.text = "0" + this.playerHealth.currentLife;
	}
}
