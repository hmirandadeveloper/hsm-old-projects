using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class GUIPlantController : MonoBehaviour {

	public Image[] imagesLeaf;
	public float transparenceValue;
	private int TotalPlantMade;
	private int currentIndex;

	// Use this for initialization
	void Start () {
		this.TotalPlantMade = 0;
	}
	
	// Update is called once per frame
	void Update () {
		if(GameController.instance.pause)
		{
			return;
		}

		if(GameController.instance.currentStage.GetTotalPlantationsMade() == GetLeafNoTransparent())
		{
			return;
		}

		this.TotalPlantMade = GameController.instance
			.currentStage
				.GetTotalPlantationsMade ();

		ClearAll ();
		AddPlant(this.TotalPlantMade);
	}

	public int GetLeafTransparent()
	{
		int total = 0;

		for(int i=0; i < this.imagesLeaf.Length; i++)
		{
			if(this.imagesLeaf[i].color.a < 1)
			{
				total++;
			}
		}

		return total;
	}

	public int GetLeafNoTransparent()
	{
		int total = 0;
		
		for(int i=0; i < this.imagesLeaf.Length; i++)
		{
			if(this.imagesLeaf[i].color.a >= 1)
			{
				total++;
			}
		}
		
		return total;
	}

	public void ClearAll()
	{
		for(int i=0; i < this.imagesLeaf.Length; i++)
		{
			this.imagesLeaf[i].color = new Color(1,1,1,this.transparenceValue);
		}
	}

	public void AddPlant(int total)
	{
		for(int i=0; i < this.TotalPlantMade; i++)
		{
			this.imagesLeaf[i].color = new Color(1,1,1,1);
		}
	}

}
