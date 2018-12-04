using UnityEngine;
using System.Collections;

public interface IStageChange {
	void OnStageChange(EIdStage idStage, ESituationScene situationScene);
	void OnStageChangeFixed(ESituationScene situationScene);
	void OnReset();
}
