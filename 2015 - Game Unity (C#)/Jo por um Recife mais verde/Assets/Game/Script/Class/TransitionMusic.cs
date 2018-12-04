using UnityEngine;
using System.Collections;

public class TransitionMusic : MonoBehaviour {

	private AudioSource _audioSource;
	private bool _isTrantition;
	private float _timeCount;

	public float timeToReduction;


	// Use this for initialization
	void Start () {
		this._audioSource = GetComponent<AudioSource> ();
		this._timeCount = 0;
	}
	
	// Update is called once per frame
	void Update () {
		if(this._isTrantition)
		{
			this._timeCount += Time.deltaTime;

			this._audioSource.volume -= (this._timeCount/this.timeToReduction);

			if(this._timeCount <= 0)
			{
				this._isTrantition = false;
			}
		}
	}

	public void Transition()
	{
		this._isTrantition = true;
	}

	public void Transition(float otherTimeToReduction)
	{
		this.timeToReduction = otherTimeToReduction;
		this._isTrantition = true;
	}
}
