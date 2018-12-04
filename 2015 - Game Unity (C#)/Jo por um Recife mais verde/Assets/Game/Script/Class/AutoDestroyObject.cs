using UnityEngine;
using System.Collections;

public class AutoDestroyObject : MonoBehaviour {

	public float timeToDestroy;
	private float _currentTime;

	// Use this for initialization
	void Start () {
		this._currentTime = 0;
	}
	
	// Update is called once per frame
	void Update () {
		if ((this._currentTime += Time.deltaTime) >= this.timeToDestroy) {
			Destroy (gameObject);
		}
	}
}
