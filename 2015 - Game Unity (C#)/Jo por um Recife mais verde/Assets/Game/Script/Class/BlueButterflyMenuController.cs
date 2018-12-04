using UnityEngine;
using System.Collections;

public class BlueButterflyMenuController : MonoBehaviour, IGameStart {

	private FollowControler followControler;
	private bool isFly;
	public Animator animatorMesh;
	public Animator animatorButterfly;

	// Use this for initialization
	void Start () {
		this.isFly = false;
		this.followControler = GetComponentInParent<FollowControler> ();
	}
	
	// Update is called once per frame
	void Update () {
		if(this.isFly)
		{
			this.followControler.isFollow = false;
			this.animatorButterfly.SetBool("isFly", true);
			this.animatorMesh.SetBool("isFlyMesh", true);
		}
	}

	public void OnGameStart()
	{
		this.isFly = true;
	}
}
