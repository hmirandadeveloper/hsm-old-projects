using UnityEngine;
using System.Collections;

public class CaterpillarEatController : MonoBehaviour {
	
	private ICaterpillarEat _caterpillar;

	// Use this for initialization
	void Start () {
		this._caterpillar = GetComponentInParent<ICaterpillarEat>();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void Eat(ObjectEat.EFood food)
	{
		this._caterpillar.Eat (food);
	}

	public bool HasEat()
	{
		return this._caterpillar.HasEat();
	}

	public bool IsWalk()
	{
		return this._caterpillar.IsWalk ();
	}
}
