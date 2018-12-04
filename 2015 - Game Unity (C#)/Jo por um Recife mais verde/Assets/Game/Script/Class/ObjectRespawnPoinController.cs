using UnityEngine;
using System.Collections;

public class ObjectRespawnPoinController : MonoBehaviour {

	public bool isPointRight;
	public bool isPulled;
	public float timeToRespawn;
	private float _currentTimeToRespawn;
	public ObjectCatch objectCatch;
	public ObjectCatch objectCatchPrefab;

	// Use this for initialization
	void Start () {
		InstantiateObjectWithTag ();
		this.isPulled = false;
	}
	
	// Update is called once per frame
	void Update () {

		/*if(this.objectCatch == null && !this.isPulled)
		{
			InstantiateObjectWithTag();
		}*/

		/*if(this.timeToRespawn <= this.objectCatch.timeToSpoil)
		{
			this.timeToRespawn = this.objectCatch.timeToSpoil;
		}*/

		if(!this.isPulled)
		{
			if(this.objectCatch.isPulled)
			{
				this.isPulled = true;
			}

			return;
		}
		else
		{
			if((this._currentTimeToRespawn += Time.deltaTime) >= this.timeToRespawn)
			{
				InstantiateObjectWithTag();
			}
		}

	}

	void InstantiateObjectWithTag()
	{
		this._currentTimeToRespawn = 0;
		this.isPulled = false;
		this.objectCatch = Instantiate(this.objectCatchPrefab, transform.position, transform.rotation) as ObjectCatch;

		if(this.isPointRight)
		{
			this.objectCatch.tag = "MushroomB";
		}
		else
		{
			this.objectCatch.tag = "MushroomA";
		}
	}
}
