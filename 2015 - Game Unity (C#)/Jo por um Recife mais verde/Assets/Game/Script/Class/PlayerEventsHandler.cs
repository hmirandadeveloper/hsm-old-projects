using UnityEngine;
using System.Collections;

public class PlayerEventsHandler : MonoBehaviour {

	public PlayerController playerController;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void DisableInput()
	{
		this.playerController.disabledInput = true;
	}

	public void EnableInput()
	{
		this.playerController.disabledInput = false;
	}

	public void StartIdleJump()
	{
		this.playerController.StartJumpIdle ();
	}

	public void DropObject()
	{
		this.playerController.withObject = false;
	}

	public void HasRebuke()
	{
		this.playerController.startRebuke = false;

		this.playerController.hasRebuked = true;
		this.playerController.ResetPlayer ();
	}

	public void HasPlant()
	{
		this.playerController.startPlant = false;
		this.playerController.hasPlanted = true;
		this.playerController.ResetPlayer ();
	}

	public void AddSpeedAfterJumpRun()
	{
		this.playerController.speedAfterJump = 10;
	}

	public void EndJump()
	{
		this.playerController.isJump = false;
	}

	public void HasCatchObject()
	{
		this.playerController.ResetPlayer ();
	}

	public void HasDropObject()
	{
		this.playerController.ResetPlayer ();
	}

	public void HasTakingDamage()
	{
		this.playerController.ResetPlayer ();
	}

	public void ParticlesRun()
	{
		this.playerController.ParticlesRunInstantiate ();
	}

	public void ParticlesJumpEnd()
	{
		this.playerController.PlaySoundOnGroundCollider ();
		this.playerController.ParticlesJumpImpactInstantiate ();
	}

	public void ParticlesJumpRunEnd()
	{
		this.playerController.PlaySoundOnGroundCollider ();
		this.playerController.ParticlesJumpRunImpactInstantiate ();
	}

}
