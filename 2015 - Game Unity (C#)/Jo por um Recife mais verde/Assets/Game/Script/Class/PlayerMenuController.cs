using UnityEngine;
using System.Collections;

public class PlayerMenuController : MonoBehaviour, IGameStart {

	private bool isGamePlay;
	private Animator animator;

	// Use this for initialization
	void Start () {
		this.isGamePlay = false;
		this.animator = GetComponent<Animator> ();
	}

	// Update is called once per frame
	void Update () {

		if(this.isGamePlay)
		{
			this.animator.SetTrigger("playGame");
		}
	}

	public void OnGameStart()
	{
		this.isGamePlay = true;
	}

	public void StartGame()
	{
		SceneController.instance.ClosingScene (1.0F);
	}
}
