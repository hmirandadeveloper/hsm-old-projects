using UnityEngine;
using System.Collections;

public class FrightSoundController : MonoBehaviour {

	private AudioSource _audioSource;
	private bool _awake;
	private BoxCollider _collider;

	// Use this for initialization
	void Start () {
		this._awake = false;
		this._audioSource = GetComponent<AudioSource> ();
		this._collider = GetComponent<BoxCollider> ();
	}
	
	// Update is called once per frame
	void Update () {
		if(!this._awake)
		{
			return;
		}


		if(!this._audioSource.isPlaying)
		{
			Destroy(this.gameObject);
		}
	}

	void OnTriggerEnter (Collider other)
	{
		if(!other.tag.Contains("Caterpillar"))
		{
			return;
		}

		this._awake = true;
		this._audioSource.Play ();
		this._collider.enabled = false;
	}
}
