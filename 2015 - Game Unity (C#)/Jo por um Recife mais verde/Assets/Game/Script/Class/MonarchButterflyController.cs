using UnityEngine;
using System.Collections;

public class MonarchButterflyController : MonoBehaviour {

	private FollowControler _followControler;
	private bool _isFly;
	public bool facingRight { get; private set;}
	public Animator animatorMesh;
	public Animator animatorButterfly;

	// Use this for initialization
	void Start () {
		this._isFly = true;
		this._followControler = GetComponentInParent<FollowControler> ();
		this.facingRight = false;
	}
	
	// Update is called once per frame
	void Update () {

		if(this._followControler.stateFollow == 
		   FollowControler.EFollowPointState.FOLLOW_COMPLETE)
		{
			this._followControler.NextIndex();
			print("Complete!");
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
