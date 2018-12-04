using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {

	public Transform player;
	public Vector2 margin, 
	smoothing;
	public BoxCollider2D bounds;
	public bool isFollowing;
	public Camera cameraReference;
	
	private Vector3 _min,
	_max;
	
	public void Start()
	{
		_min = bounds.bounds.min;
		_max = bounds.bounds.max;
	}
	
	public void Update()
	{
		var x = transform.position.x;
		var y = transform.position.y;
		
		if(isFollowing)
		{
			if(Mathf.Abs(x - player.position.x) > margin.x)
			{
				x = Mathf.Lerp(x, player.position.x, smoothing.x * Time.deltaTime);
			}
			
			if(Mathf.Abs(y - player.position.y) > margin.y)
			{
				y = Mathf.Lerp(y, player.position.y, smoothing.y * Time.deltaTime);
			}
		}
		
		var cameraHalfWidth = cameraReference.orthographicSize * ((float) Screen.width / Screen.height);
		
		x = Mathf.Clamp (x, _min.x + cameraHalfWidth, _max.x - cameraHalfWidth);
		y = Mathf.Clamp (y, _min.y + cameraReference.orthographicSize, _max.y - cameraReference.orthographicSize);
		
		transform.position = new Vector3 (x, y, transform.position.z);
	}
}
