using UnityEngine;
using UnityEngine.UI;
using System.Collections;


public class PlantPoint : MonoBehaviour {

	public int index;
	public PlayerActionCollider action;
	private Transform _playerHand;
	private PlayerController _player;
	public bool plantPointCheck { get; private set;}
	private bool _isActive;
	private BoxCollider _collider;
	public GameObject flowerEat;
	public GameObject flowerEatPrefab;

	// Use this for initialization
	void Start () {
		this._collider = GetComponent<BoxCollider> ();
		this.action = GetComponentInParent<PlayerActionCollider> ();
	}

	void OnTriggerStay(Collider other)
	{
		if(other.tag != "PlayerHand")
		{
			return;
		}
		
		if(this.flowerEat == null)
		{
			if(!this._collider.enabled)
			{
				this._collider.enabled = true;
			}

			if(this._player == null)
			{
				return;
			}

			if(this._player.hasPlanted && this.plantPointCheck)
			{
				InstantiateObject();
				this._player.hasPlanted = false;
				this._collider.enabled = false;
				this.plantPointCheck = false;
				GameController.instance.currentStage.AddPlantation();
				this.action.CompletePlantation();
			}
			else if(this._player.canPlant)
			{
				this._collider.enabled = true;
			}
		}
	}

	public bool IsActive()
	{
		return this._isActive = this._collider.enabled;
	}

	void OnTriggerEnter(Collider other)
	{

		if(other.tag != "PlayerHand")
		{
			return;
		}

		this._player = other.GetComponentInParent<PlayerController> ();
		this.plantPointCheck = true;
	}	

	void Update()
	{
		if(this.flowerEat == null)
		{
			this.action.Restart();
			this._collider.enabled = true;
		}

		if(this._player == null)
		{
			return;
		}

		if (this._player.hasPlanted && this.plantPointCheck) {
			InstantiateObject ();
			this._player.hasPlanted = false;
			this._collider.enabled = false;
			this.plantPointCheck = false;
			GameController.instance.currentStage.AddPlantation ();
			this.action.CompletePlantation ();
		}
	}

	void InstantiateObject()
	{
		if(this.flowerEat != null)
		{
			return;
		}
		this.flowerEat = Instantiate(this.flowerEatPrefab, new Vector3(transform.position.x, transform.position.y + 0.25F , transform.position.z - 2.5F), transform.rotation) as GameObject;
		this.flowerEat.name = "flower" + this.index;
	}
}
