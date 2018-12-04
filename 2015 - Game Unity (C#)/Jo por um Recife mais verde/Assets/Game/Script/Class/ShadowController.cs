using UnityEngine;
using System.Collections;

public class ShadowController : MonoBehaviour {

	private Animator shadowAnimator;
	private Transform poinOfDestruction;
	public bool canRun;
	public float hSpeed;
	public AudioSource shadowAudioSource;
	public AudioClip cutSound;

	//ParticleEmission
	public ParticleSystem treePowderParticlePrefab;
	public Transform particleEmissionPoint;

	// Use this for initialization
	void Start () {
		this.shadowAnimator = GetComponentInChildren<Animator> ();
		this.poinOfDestruction = GameObject.FindGameObjectWithTag ("PointOfDestruction").GetComponent<Transform> ();
	}
	
	// Update is called once per frame
	void Update () {
		if(this.canRun)
		{
			transform.Translate(this.hSpeed * Time.deltaTime, -0.005F, 0);

			if(!this.shadowAudioSource.isPlaying)
			{
				this.shadowAudioSource.Play ();
			}
		}

		if(transform.position.x <= this.poinOfDestruction.position.x)
		{
			Destroy(this.gameObject);
		}
	}

	public void PlayCutSound()
	{
		AudioSource.PlayClipAtPoint (this.cutSound, transform.position, 0.4F);
		var clone = Instantiate (this.treePowderParticlePrefab, this.particleEmissionPoint.position, this.particleEmissionPoint.rotation) as ParticleSystem;
	}

	public void StartRebuke()
	{
		this.shadowAnimator.SetTrigger ("listener");
	}

	public void StartRun()
	{
		this.shadowAnimator.SetTrigger ("startRun");
		this.canRun = true;
	}
}
