using UnityEngine;
using System.Collections;

public class PlayerCollisionFrontCheck : MonoBehaviour {

	public PlayerController player;
	public bool isJumpValidation;

	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnTriggerEnter(Collider other)
	{

		if(!other.tag.Contains("RigidSurface"))
		{
			return;
		}

		if(!this.isJumpValidation)
		{
			this.player.canMoveFront = false;
		}
	}

	void OnTriggerStay(Collider other)
	{
		if(!this.isJumpValidation)
		{
			return;
		}
		if(!other.tag.Contains("RigidSurface"))
		{
			return;
		}

		if(!this.player.grounded)
		{
			this.player.disabledInput = true;
			this.player.correctionMoviment();
		}
		else
		{
			this.player.disabledInput = false;
		}

	}

	void OnTriggerExit(Collider other)
	{

		if(!other.tag.Contains("RigidSurface"))
		{
			return;
		}
		
		if(!this.isJumpValidation)
		{
			this.player.canMoveFront = true;
		}

	}
}
