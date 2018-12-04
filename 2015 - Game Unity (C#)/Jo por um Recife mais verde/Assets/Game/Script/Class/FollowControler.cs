using UnityEngine;
using System.Collections;

public class FollowControler : MonoBehaviour {

	public enum ETypeFollow
	{
		FIX_FOLLOW,
		SMOOTH_FOLLOW
	}
	
	public enum EFollowPointState
	{
		NONE,
		FOLLOW_LEFT,
		FOLLOW_COMPLETE,
		FOLLOW_RIGHT
	}
	public Transform[] pointsFollow;
	private int currentIndex;
	public Transform pointFollow;
	public ETypeFollow typeFollow;
	public EFollowPointState stateFollow;
	public float smooth;
	public float arrivalMargin;
	public bool isFollow;
	
	// Use this for initialization
	void Start () {
		this.isFollow = true;
		this.currentIndex = 0;
		this.pointFollow = this.pointsFollow [this.currentIndex];
	}
	
	// Update is called once per frame
	void LateUpdate () {
		if(!isFollow)
		{
			return;
		}
		
		checkStateFollow ();
		
		if(this.typeFollow == ETypeFollow.FIX_FOLLOW)
		{
			transform.position = this.pointFollow.position;
			return;
		}
		
		if(this.typeFollow == ETypeFollow.SMOOTH_FOLLOW)
		{
			float targetX = transform.position.x;
			float targetY = transform.position.y;
			
			if(Mathf.Abs(targetX - pointFollow.position.x) > this.arrivalMargin)
			{
				targetX = Mathf.Lerp(transform.position.x, this.pointFollow.position.x, this.smooth * Time.deltaTime);
			}
			
			if(Mathf.Abs(targetY - pointFollow.position.y) > this.arrivalMargin)
			{
				targetY = Mathf.Lerp(transform.position.y, this.pointFollow.position.y, this.smooth * Time.deltaTime);
			}
			
			transform.position = new Vector3(targetX, targetY, transform.position.z);
		}
	}
	
	void checkStateFollow()
	{
		if (Mathf.Abs((this.pointFollow.position.x - this.arrivalMargin) - transform.position.x) < 0.15 && 
		    Mathf.Abs((this.pointFollow.position.y - this.arrivalMargin) - transform.position.y) < 0.15)
		{
			this.stateFollow = EFollowPointState.FOLLOW_COMPLETE;
			return;
		}
		
		if ((this.pointFollow.position.x - this.arrivalMargin) > transform.position.x)
		{
			this.stateFollow = EFollowPointState.FOLLOW_RIGHT;
			return;
		}
		
		if ((this.pointFollow.position.x - this.arrivalMargin) < transform.position.x)
		{
			this.stateFollow = EFollowPointState.FOLLOW_LEFT;
			return;
		}
	}

	public void PreviousIndex()
	{
		if(--this.currentIndex < 0)
		{
			this.currentIndex = this.pointsFollow.Length - 1;
		}

		this.pointFollow = this.pointsFollow [this.currentIndex];
	}

	public void NextIndex()
	{
		this.currentIndex++;

		if(this.currentIndex >= this.pointsFollow.Length)
		{
			this.currentIndex = 0;
		}
		print ("X: " + this.pointFollow.position.x);

		this.pointFollow = this.pointsFollow [this.currentIndex];
	}

	public void ToIndex(int index)
	{
		if(index < 0)
		{
			index = 0;
		}

		this.currentIndex = index;

		if(this.currentIndex > this.pointsFollow.Length)
		{
			this.currentIndex = this.pointsFollow.Length - 1;
		}
		
		this.pointFollow = this.pointsFollow [this.currentIndex];
	}
}
