using UnityEngine;
using System.Collections;

public class EnemyCheckPoint : MonoBehaviour {

	public GreenCaterpillarController _greenCaterpillar;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnTriggerEnter(Collider other)
	{
		if(other.tag != "Player")
		{
			return;
		}

		this._greenCaterpillar.AwakeCaterpillar ();
		Destroy(this.gameObject);
	}
}
