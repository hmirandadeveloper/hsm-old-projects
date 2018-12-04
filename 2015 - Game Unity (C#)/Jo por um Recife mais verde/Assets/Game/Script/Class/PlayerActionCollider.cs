using UnityEngine;
using System.Collections;


public class PlayerActionCollider : MonoBehaviour {

	public enum EPlayerAction
	{
		NONE,
		PLANT,
		REBUKE
	}

	public EPlayerAction playerAction;
	private PlayerController _player;
	private ShadowController _shadow;
	public int pointsToRebuke;
	public int pointsToPlant;
	public Transform pointScore;
	public GameObject pointGUIPrefab;
	private BoxCollider _collider;
	public bool onlyFaceRight;

	// Use this for initialization
	void Start () {
		this._collider = GetComponent<BoxCollider> ();
		this._shadow = GetComponentInParent<ShadowController> ();
	}

	void GrowingPlant()
	{
		print ("Planta crescer!");
	}

	void OnTriggerEnter(Collider other)
	{
		if(other.tag != "Player")
		{
			return;
		}
		this._player = other.GetComponentInParent<PlayerController> ();

		switch(this.playerAction)
		{

		case EPlayerAction.NONE:
		{
			break;
		}

		case EPlayerAction.PLANT:
		{
			this._player.canPlant = true;
			break;
		}

		case EPlayerAction.REBUKE:
		{
			this._player.canRebuke = true;
			break;
		}

		}
	}

	void OnTriggerStay(Collider other)
	{
		if(this._player == null)
		{
			return;
		}





		switch(this.playerAction)
		{
			
		case EPlayerAction.NONE:
		{
			break;
		}
			
		case EPlayerAction.PLANT:
		{
			if(this._player.startPlant)
			{
				this._collider.enabled = false;
			}
			break;
		}
			
		case EPlayerAction.REBUKE:
		{
			if(this._player.startRebuke)
			{
				//this._collider.enabled = false;
				this._shadow.StartRebuke();
			}
			
			if(this._player.hasRebuked)
			{
				this._player.hasRebuked = false;
				this._shadow.StartRun();
				var obj = Instantiate(this.pointGUIPrefab, pointScore.position, new Quaternion(0,0,0,0))  as GameObject;
				GUIPointController score = obj.GetComponent<GUIPointController>();
				score.StartEffect(this.pointsToRebuke, true);
				
				Destroy(this.gameObject);
			}
			break;
		}
			
		}






		/*
		if (this._player.startPlant || this._player.startRebuke) {
			return;
		}

		if (other.tag != "Player") {
			return;
		}

		switch (this.playerAction) {
			
		case EPlayerAction.PLANT:
			{
				if (this._player.withObject) {
					this._player.canPlant = false;
				} else {
					this._player.canPlant = true;
				}

				break;
			}
			
		case EPlayerAction.REBUKE:
			{
				if (this._player.withObject) {
					this._player.canRebuke = false;
				} else {
					this._player.canRebuke = true;
				}
				break;
			}
		}
		
		if(this.playerAction == EPlayerAction.REBUKE)
		{
			if (!this.onlyFaceRight) {
				return;
			}
			
			if (onlyFaceRight && !this._player.facingRight) {
				this._player.canRebuke = false;
				return;
			} else if (onlyFaceRight && this._player.facingRight) {
				this._player.canRebuke = true;
			}
		}*/
	}

	void OnTriggerExit(Collider other)
	{
		if(other.tag != "Player")
		{
			return;
		}
		
		switch(this.playerAction)
		{
			
		case EPlayerAction.PLANT:
		{
			this._player.canPlant = false;
			break;
		}
			
		case EPlayerAction.REBUKE:
		{
			this._player.canRebuke = false;
			break;
		}
			
		}
	}

	public void CompletePlantation()
	{
		var obj = Instantiate(this.pointGUIPrefab, pointScore.position, new Quaternion(0,0,0,0))  as GameObject;
		GUIPointController score = obj.GetComponent<GUIPointController>();
		score.StartEffect(this.pointsToPlant, true);
	}

	public void Restart()
	{
		this._collider.enabled = true;
	}
}
