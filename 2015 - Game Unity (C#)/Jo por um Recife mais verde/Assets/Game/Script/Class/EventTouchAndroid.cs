using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using System.Collections;
using System.Collections.Generic;

public class EventTouchAndroid : MonoBehaviour {

	public Color originalColor;
	public Color touchColor;
	public Image btLeft;
	public Image btRight;
	public Image btJump;
	public Image btAction;
	public Image btPause;

	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {

		/*if(Input.GetMouseButton(0))
		{
			PointerEventData pointer = new PointerEventData(EventSystem.current);
			pointer.position = Input.mousePosition;
			
			List<RaycastResult> raycastResults = new List<RaycastResult>();
			EventSystem.current.RaycastAll(pointer, raycastResults);

			if(raycastResults.Count > 0)
			{
				if(raycastResults[0].gameObject.tag == "BtRight")
				{
					JoystickAndroid.instance.axis = 1;
				}

				if(raycastResults[0].gameObject.tag == "BtLeft")
				{
					JoystickAndroid.instance.axis = -1;
				}
			}
		}

		if(Input.GetMouseButtonDown(0))
		{
			PointerEventData pointer = new PointerEventData(EventSystem.current);
			pointer.position = Input.mousePosition;
			
			List<RaycastResult> raycastResults = new List<RaycastResult>();
			EventSystem.current.RaycastAll(pointer, raycastResults);
			
			if(raycastResults.Count > 0)
			{
				if(raycastResults[0].gameObject.tag == "BtJump")
				{
					JoystickAndroid.instance.jump = true;
				}
				
				if(raycastResults[0].gameObject.tag == "BtAction")
				{
					JoystickAndroid.instance.action = true;
				}
				
				if(raycastResults[0].gameObject.tag == "BtPause")
				{
					JoystickAndroid.instance.pause = true;
				}
				
				if(raycastResults[0].gameObject.tag == "BtCancel")
				{
					JoystickAndroid.instance.cancel = true;
				}
			}
		}

		if(Input.GetMouseButtonUp(0))
		{
			PointerEventData pointer = new PointerEventData(EventSystem.current);
			pointer.position = Input.mousePosition;
			
			List<RaycastResult> raycastResults = new List<RaycastResult>();
			EventSystem.current.RaycastAll(pointer, raycastResults);
			
			if(raycastResults.Count > 0)
			{
				if(raycastResults[0].gameObject.tag == "BtRight")
				{
					JoystickAndroid.instance.axis = 0;
				}
				
				if(raycastResults[0].gameObject.tag == "BtLeft")
				{
					JoystickAndroid.instance.axis = 0;
				}
				
				if(raycastResults[0].gameObject.tag == "BtJump")
				{
					JoystickAndroid.instance.jump = false;
				}
				
				if(raycastResults[0].gameObject.tag == "BtAction")
				{
					JoystickAndroid.instance.action = false;
				}
				
				if(raycastResults[0].gameObject.tag == "BtPause")
				{
					JoystickAndroid.instance.pause = false;
				}
				
				if(raycastResults[0].gameObject.tag == "BtCancel")
				{
					JoystickAndroid.instance.cancel = false;
				}
			}
		}*/
		
		for(int i = 0; i < Input.touches.Length; i++)
		{
			Touch touch = Input.touches[i];
			var pointer = new PointerEventData(EventSystem.current);
			//pointer.position = Camera.main.WorldToScreenPoint(touch.position);
			pointer.position = touch.position;
			var raycastResults = new List<RaycastResult>();
			EventSystem.current.RaycastAll(pointer, raycastResults);
			
			if(raycastResults.Count > 0)
			{
					switch(touch.phase)
					{
					case TouchPhase.Began:
					{
						if(raycastResults[0].gameObject.tag == "BtJump")
						{
							this.btJump.color = this.touchColor;
							JoystickAndroid.instance.jump = true;
						}
						
						if(raycastResults[0].gameObject.tag == "BtAction")
						{
							this.btAction.color = this.touchColor;
							JoystickAndroid.instance.action = true;
						}
						
						if(raycastResults[0].gameObject.tag == "BtPause")
						{
							JoystickAndroid.instance.pause = true;
						}
						
						if(raycastResults[0].gameObject.tag == "BtCancel")
						{
							JoystickAndroid.instance.cancel = true;
						}
						
						break;
					}
						
					case TouchPhase.Stationary:
					{
						if(raycastResults[0].gameObject.tag == "BtRight")
						{
							this.btRight.color = this.touchColor;
							JoystickAndroid.instance.axis = 1;
						}

						if(raycastResults[0].gameObject.tag == "BtLeft")
						{
							this.btLeft.color = this.touchColor;
							JoystickAndroid.instance.axis = -1;
						}
						
						break;
					}
						
					case TouchPhase.Ended:
					{
						if(raycastResults[0].gameObject.tag == "BtRight")
						{
							this.btRight.color = this.originalColor;
							JoystickAndroid.instance.axis = 0;
						}
						
						if(raycastResults[0].gameObject.tag == "BtLeft")
						{
							this.btLeft.color = this.originalColor;
							JoystickAndroid.instance.axis = 0;
						}
						
						if(raycastResults[0].gameObject.tag == "BtJump")
						{
							this.btJump.color = this.originalColor;	
							JoystickAndroid.instance.jump = false;
						}
						
						if(raycastResults[0].gameObject.tag == "BtAction")
						{
							this.btAction.color = this.originalColor;	
							JoystickAndroid.instance.action = false;
						}
						
						if(raycastResults[0].gameObject.tag == "BtPause")
						{
							JoystickAndroid.instance.pause = false;
						}
						
						if(raycastResults[0].gameObject.tag == "BtCancel")
						{
							JoystickAndroid.instance.cancel = false;
						}
						
						break;
					}
						
					case TouchPhase.Moved:
					{
						
						
						break;
					}
						
					}

			}
		}
	}	
}
