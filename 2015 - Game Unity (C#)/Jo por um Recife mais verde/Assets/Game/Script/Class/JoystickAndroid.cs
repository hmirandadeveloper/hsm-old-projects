using UnityEngine;
using System.Collections;

public class JoystickAndroid : MonoBehaviour {

	public static JoystickAndroid instance;

	public int axis;
	public bool jump;
	public bool action;
	public bool pause;
	public bool cancel;


	void Awake()
	{
		instance = this;
	}

	// Use this for initialization
	void Start () {
		this.axis = 0;
		this.jump = false;
		this.action = false;
		this.pause = false;
		this.cancel = false;
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
}
