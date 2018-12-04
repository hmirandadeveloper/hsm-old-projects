using UnityEngine;
using System.Collections;

public class ShadowEventHandler : MonoBehaviour {

	private ShadowController _shadow;

	// Use this for initialization
	void Start () {
		this._shadow = GetComponentInParent<ShadowController> ();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void StartRun()
	{
		this._shadow.canRun = true;
	}

	public void PlayCutSound()
	{
		this._shadow.PlayCutSound ();
	}
}
