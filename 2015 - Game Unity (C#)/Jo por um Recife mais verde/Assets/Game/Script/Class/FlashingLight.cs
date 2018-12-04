using UnityEngine;
using System.Collections;

public class FlashingLight : MonoBehaviour {
	
	public float minRange;
	public float maxRange;
	public float velocityRange;
	public bool isActive;
	private Light light;

	// Use this for initialization
	void Start () {
		this.light = GetComponent<Light> ();
		this.light.range = maxRange;
	}
	
	// Update is called once per frame
	void Update () {
		if(!this.isActive)
		{
			return;
		}

		if(this.light.range <= this.minRange)
		{
			this.velocityRange  = this.velocityRange * -1;
		}

		if (this.light.range >= this.maxRange)
		{
			this.velocityRange  = this.velocityRange * -1;
		}

		this.light.range = this.light.range + (this.velocityRange * Time.deltaTime);
	}
}
