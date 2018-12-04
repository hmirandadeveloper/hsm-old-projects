using UnityEngine;
using System.Collections;

public class BackgroundParallax : MonoBehaviour {

	public Transform [] backgrounds;
	public float parallaxScale;
	public float parallaxReductionFactor;
	public float smoothing;
	
	private Vector3 _lastPosition;	
	
	void Start () {
		_lastPosition = transform.position;
	}
	
	void Update () {
		var parallax = (_lastPosition.x - transform.position.x) * parallaxScale;
		
		for(int i = 0; i < backgrounds.Length; i++)
		{
			var backgroundTargetPosition = 
				backgrounds[i].position.x + parallax * (i * parallaxReductionFactor + 1);
			
			backgrounds[i].position = Vector3.Lerp(
				backgrounds[i].position,
				new Vector3(backgroundTargetPosition, backgrounds[i].position.y,
			            backgrounds[i].position.z),
				smoothing * Time.deltaTime);
		}
		
		_lastPosition = transform.position;
		
	}
}
