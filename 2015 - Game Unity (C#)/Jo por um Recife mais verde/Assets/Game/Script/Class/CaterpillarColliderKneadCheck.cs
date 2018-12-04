using UnityEngine;
using System.Collections;

public class CaterpillarColliderKneadCheck : MonoBehaviour {
	
	private GreenCaterpillarController _greenCaterpillar;
	public GUIPointController guiPointPrefab;
	public Transform scorePosition;
	public int pointsLowerToKnead;

	// Use this for initialization
	void Start () {
		this._greenCaterpillar = GetComponentInParent<GreenCaterpillarController> ();
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

		PlayerController player = other.GetComponent<PlayerController> ();

		var score = Instantiate(this.guiPointPrefab, scorePosition.position, new Quaternion(0,0,0,0))  as GUIPointController;
		score.StartEffect(this.pointsLowerToKnead, false);
		GameController.instance.AddKneadCaterpillar ();
		this._greenCaterpillar.Knead ();
	}	
}
