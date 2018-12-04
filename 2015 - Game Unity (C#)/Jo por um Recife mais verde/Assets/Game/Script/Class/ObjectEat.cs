using UnityEngine;
using System.Collections;

public class ObjectEat : MonoBehaviour {

	public enum EFood
	{
		MUSHROOM,
		PLANT
	}

	private ObjectCatch objectCatch;
	public EFood food;
	//private GreenCaterpillarController caterpillar;
	private ObjectCatch _objectFood;
	public GameObject pointGUIPrefab;

	// Use this for initialization
	void Start () {
		if (this.food == EFood.MUSHROOM) {
			_objectFood = GetComponentInParent<ObjectCatch> ();
		}
	}
	
	// Update is called once per frame
	void LateUpdate () {
		/*
		if(this.caterpillar == null)
		{
			return;
		}

		if(this.caterpillar.hasEat)
		{
			Destroy(this);
		}
		*/
	}
	
	void OnTriggerEnter(Collider other)
	{
		if(!other.tag.Contains("CaterpillarHead"))
		{
			return;
		}
		print ("Hummmmm...");
		CaterpillarEatController caterpillar = other.GetComponent<CaterpillarEatController> ();

		if(!caterpillar.IsWalk() && this.food == EFood.MUSHROOM)
		{
			print ("Nao posso comer!!!");
			return;
		}

		print ("Comer...Comer...");
		caterpillar.Eat (this.food);

		if (this.food == EFood.PLANT) {
			CompleteEatPlant();
			GameController.instance.currentStage.RemovePlantation();
		}

		Destroy (this.gameObject);

		if (this.food == EFood.MUSHROOM) {
			CompleteEatMushroom ();
			Destroy (_objectFood.gameObject);
		}
	}

	public void CompleteEatMushroom()
	{
		var obj = Instantiate(this.pointGUIPrefab, transform.position, new Quaternion(0,0,0,0))  as GameObject;
		GUIPointController score = obj.GetComponent<GUIPointController>();
		score.StartEffect(50, true);
	}

	public void CompleteEatPlant()
	{
		var obj = Instantiate(this.pointGUIPrefab, transform.position, new Quaternion(0,0,0,0))  as GameObject;
		GUIPointController score = obj.GetComponent<GUIPointController>();
		score.StartEffect(30, false);
	}
}
