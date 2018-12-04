using UnityEngine;
using System.Collections;

public class BlueButterflyController : MonoBehaviour {

	private FollowControler _followControler;
	public PlayerController player;
	private bool _isFly;
	public bool facingRight { get; private set;}
	public Animator animatorMesh;
	public Animator animatorButterfly;

	// Use this for initialization
	void Start () {
		this._isFly = false;
		this._followControler = GetComponentInParent<FollowControler> ();
		this.facingRight = true;
	}
	
	// Update is called once per frame
	void Update () {
		if(Input.GetKeyDown(KeyCode.O))
		{
			FlyToHead();
		}

		if(Input.GetKeyDown(KeyCode.P))
		{
			ResetFly();
		}

		if(this._followControler.stateFollow == 
		   FollowControler.EFollowPointState.FOLLOW_COMPLETE)
		{
			if(this.facingRight != this.player.facingRight)
			{
				Flip();
			}

			return;
		}

		if(this._followControler.stateFollow == 
		   FollowControler.EFollowPointState.FOLLOW_LEFT)
		{
			if(this.facingRight)
			{
				Flip();
			}
			return;
		}

		if(this._followControler.stateFollow == 
		   FollowControler.EFollowPointState.FOLLOW_RIGHT)
		{
			if(!this.facingRight)
			{
				Flip();
			}
			return;
		}
	}

	public void FlyToHead()
	{
		this._followControler.ToIndex (1);
		StopFly();
	}

	public void ResetFly()
	{
		this._followControler.ToIndex (0);
		Reset();
	}

	public void Flip()
	{
		facingRight = !facingRight;
		transform.Rotate (Vector3.up, 180.0f, Space.World);
	}

	public void StopFly()
	{
		this.animatorButterfly.SetBool("isFly", false);
		this.animatorMesh.SetBool("isFlyMesh", false);
	}

	public void Reset()
	{
		this.animatorButterfly.SetBool("isFly", true);
		this.animatorMesh.SetBool("isFlyMesh", true);
	}
}
