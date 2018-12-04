using UnityEngine;
using System;
using System.Collections;

[Serializable]
public class StageState {
	
	public int index;
	public int maxPlantation;
/*private*/public int _plantationMade;
	public float timeToFinish;
	public ESituationScene situationScene;
	public bool isCaterpillarRescued { get; private set;}
	public bool isStageEnd { 
		get{ return this.isCaterpillarRescued && this.maxPlantation == this._plantationMade; } 
	}

	public int GetTotalPlantationsMade()
	{
		return this._plantationMade;
	}

	public void AddPlantation()
	{
		this._plantationMade++;

		if(this._plantationMade > this.maxPlantation)
		{
			this._plantationMade = this.maxPlantation;
		}
	}
	
	public void RemovePlantation()
	{
		this._plantationMade--;
		
		if(this._plantationMade < 0)
		{
			this._plantationMade = 0;
		}
	}
	
	public void RemoveAllPlantations()
	{
		this._plantationMade = 0;
	}

	public void IsCaterpillarRescued()
	{
		this.isCaterpillarRescued = true;
	}

	public void Reset()
	{
		RemoveAllPlantations ();
		this.isCaterpillarRescued = false;
		this.situationScene = ESituationScene.DRY_SCENE;
	}
}
